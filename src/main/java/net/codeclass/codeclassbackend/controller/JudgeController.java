package net.codeclass.codeclassbackend.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.codeclass.codeclassbackend.dto.JudgeResultResponse;
import net.codeclass.codeclassbackend.dto.SubmitRequest;
import net.codeclass.codeclassbackend.entity.JudgeResult;
import net.codeclass.codeclassbackend.repository.JudgeResultRepository;
import net.codeclass.codeclassbackend.service.JudgeService;
import net.codeclass.codeclassbackend.service.SubmitRateLimiter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class JudgeController {

    private final JudgeService judgeService;
    private final JudgeResultRepository judgeResultRepository;
    private final SubmitRateLimiter rateLimiter;

    @PostMapping("/submit/{problemId}")
    public ResponseEntity<JudgeResultResponse> submit(
            @PathVariable Long problemId,
            @Valid @RequestBody SubmitRequest request,
            HttpServletRequest httpRequest) {

        if (!rateLimiter.tryAcquire(httpRequest.getRemoteAddr())) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }

        try {
            JudgeResult result = judgeService.submit(
                    problemId, request.getUserId(), request.getLanguage(), request.getCode());
            return ResponseEntity.ok(JudgeResultResponse.from(result));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/judge-results")
    public List<JudgeResultResponse> getJudgeResults() {
        return judgeResultRepository.findAllByOrderBySubmittedAtDesc().stream()
                .map(JudgeResultResponse::from)
                .toList();
    }
}
