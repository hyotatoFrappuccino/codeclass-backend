package net.codeclass.codeclassbackend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.codeclass.codeclassbackend.entity.JudgeResult;
import net.codeclass.codeclassbackend.entity.Problem;
import net.codeclass.codeclassbackend.repository.JudgeResultRepository;
import net.codeclass.codeclassbackend.repository.ProblemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class JudgeService {

    private final ProblemRepository problemRepository;
    private final JudgeResultRepository judgeResultRepository;

    private static final int TIME_LIMIT_SECONDS = 2;
    private static final Random RANDOM = new Random();

    @Transactional
    public JudgeResult submit(Long problemId, String userId, String language, String code) {
        Problem problem = problemRepository.findById(problemId)
                .orElseThrow(() -> new NoSuchElementException("Problem not found: " + problemId));

        ExecutionResult execResult = executeCode(language, code, problem.getExampleInput());

        String result;
        int memory = 0;
        int time = execResult.elapsedMs();

        if (execResult.timedOut()) {
            result = "시간 초과";
            time = TIME_LIMIT_SECONDS * 1000;
        } else if (execResult.compileError()) {
            result = "컴파일 에러";
            time = 0;
        } else if (execResult.runtimeError()) {
            result = "런타임 에러";
            log.error("[런타임 에러] problemId={} userId={} language={}\n{}", problemId, userId, language, execResult.output());
        } else if (normalize(execResult.output()).equals(normalize(problem.getExampleOutput()))) {
            result = "맞았습니다!";
            memory = 4096 + RANDOM.nextInt(8192);
        } else {
            result = "틀렸습니다!";
        }

        problem.setSubmissions(problem.getSubmissions() + 1);
        if ("맞았습니다!".equals(result)) {
            problem.setSolved(problem.getSolved() + 1);
        }
        problem.setRate(String.format("%.0f%%",
                (double) problem.getSolved() / problem.getSubmissions() * 100));
        problemRepository.save(problem);

        return judgeResultRepository.save(JudgeResult.builder()
                .userId(userId != null && !userId.isBlank() ? userId : "anonymous")
                .problemId(problemId)
                .result(result)
                .memory(memory)
                .time(time)
                .language(language)
                .codeLength(code.length())
                .submittedAt(LocalDateTime.now())
                .build());
    }

    private String normalize(String s) {
        if (s == null) return "";
        return s.trim().replaceAll("\r\n", "\n").replaceAll("\r", "\n");
    }

    private ExecutionResult executeCode(String language, String code, String input) {
        try {
            Path tempDir = Files.createTempDirectory("judge_");
            try {
                return switch (language.toLowerCase()) {
                    case "python3" -> executePython(tempDir, code, input);
                    case "java" -> executeJava(tempDir, code, input);
                    default -> ExecutionResult.runtimeError("지원하지 않는 언어입니다.", 0);
                };
            } finally {
                deleteRecursively(tempDir.toFile());
            }
        } catch (IOException e) {
            return ExecutionResult.runtimeError(e.getMessage(), 0);
        }
    }

    private ExecutionResult executePython(Path tempDir, String code, String input) {
        try {
            Path codeFile = tempDir.resolve("solution.py");
            Path inputFile = tempDir.resolve("input.txt");
            Files.writeString(codeFile, code, StandardCharsets.UTF_8);
            Files.writeString(inputFile, input, StandardCharsets.UTF_8);

            ProcessBuilder pb = new ProcessBuilder("python3", codeFile.toAbsolutePath().toString());
            pb.directory(tempDir.toFile());
            pb.redirectInput(inputFile.toFile());
            pb.redirectErrorStream(true); // stderr → stdout 병합

            long start = System.currentTimeMillis();
            Process process = pb.start();

            // stdout을 별도 스레드로 읽어 파이프 버퍼 데드락 방지
            CompletableFuture<String> outputFuture = CompletableFuture.supplyAsync(() -> {
                try {
                    return new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
                } catch (IOException e) {
                    return "";
                }
            });

            boolean finished = process.waitFor(TIME_LIMIT_SECONDS, TimeUnit.SECONDS);
            int elapsed = (int) (System.currentTimeMillis() - start);

            if (!finished) {
                process.destroyForcibly();
                return ExecutionResult.timedOut(elapsed);
            }

            String output = outputFuture.get(1, TimeUnit.SECONDS);
            if (process.exitValue() != 0) {
                return ExecutionResult.runtimeError(output, elapsed);
            }
            return ExecutionResult.success(output, elapsed);
        } catch (Exception e) {
            return ExecutionResult.runtimeError(e.getMessage(), 0);
        }
    }

    // Java 제출 시 클래스명은 반드시 Main이어야 합니다.
    private ExecutionResult executeJava(Path tempDir, String code, String input) {
        try {
            Path codeFile = tempDir.resolve("Main.java");
            Path inputFile = tempDir.resolve("input.txt");
            Files.writeString(codeFile, code, StandardCharsets.UTF_8);
            Files.writeString(inputFile, input, StandardCharsets.UTF_8);

            ProcessBuilder compilePb = new ProcessBuilder("javac", codeFile.toAbsolutePath().toString());
            compilePb.directory(tempDir.toFile());
            compilePb.redirectErrorStream(true);
            Process compileProcess = compilePb.start();

            CompletableFuture<String> compileOutputFuture = CompletableFuture.supplyAsync(() -> {
                try {
                    return new String(compileProcess.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
                } catch (IOException e) {
                    return "";
                }
            });

            boolean compiled = compileProcess.waitFor(10, TimeUnit.SECONDS);
            String compileOutput = compileOutputFuture.get(1, TimeUnit.SECONDS);
            if (!compiled || compileProcess.exitValue() != 0) {
                return ExecutionResult.compileError(compileOutput);
            }

            ProcessBuilder runPb = new ProcessBuilder(
                    "java", "-cp", tempDir.toAbsolutePath().toString(), "Main");
            runPb.directory(tempDir.toFile());
            runPb.redirectInput(inputFile.toFile());
            runPb.redirectErrorStream(true);

            long start = System.currentTimeMillis();
            Process runProcess = runPb.start();

            CompletableFuture<String> outputFuture = CompletableFuture.supplyAsync(() -> {
                try {
                    return new String(runProcess.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
                } catch (IOException e) {
                    return "";
                }
            });

            boolean finished = runProcess.waitFor(TIME_LIMIT_SECONDS, TimeUnit.SECONDS);
            int elapsed = (int) (System.currentTimeMillis() - start);

            if (!finished) {
                runProcess.destroyForcibly();
                return ExecutionResult.timedOut(elapsed);
            }

            String output = outputFuture.get(1, TimeUnit.SECONDS);
            if (runProcess.exitValue() != 0) {
                return ExecutionResult.runtimeError(output, elapsed);
            }
            return ExecutionResult.success(output, elapsed);
        } catch (Exception e) {
            return ExecutionResult.runtimeError(e.getMessage(), 0);
        }
    }

    private void deleteRecursively(File file) {
        if (file.isDirectory()) {
            File[] children = file.listFiles();
            if (children != null) {
                for (File child : children) {
                    deleteRecursively(child);
                }
            }
        }
        file.delete();
    }

    private record ExecutionResult(
            String output,
            boolean timedOut,
            boolean compileError,
            boolean runtimeError,
            int elapsedMs
    ) {
        static ExecutionResult success(String output, int elapsed) {
            return new ExecutionResult(output, false, false, false, elapsed);
        }

        static ExecutionResult timedOut(int elapsed) {
            return new ExecutionResult("", true, false, false, elapsed);
        }

        static ExecutionResult compileError(String output) {
            return new ExecutionResult(output, false, true, false, 0);
        }

        static ExecutionResult runtimeError(String output, int elapsed) {
            return new ExecutionResult(output, false, false, true, elapsed);
        }
    }
}
