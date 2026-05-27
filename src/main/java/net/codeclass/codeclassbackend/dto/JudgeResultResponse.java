package net.codeclass.codeclassbackend.dto;

import net.codeclass.codeclassbackend.entity.JudgeResult;

import java.time.LocalDateTime;

public record JudgeResultResponse(
        Long submitNo,
        String userId,
        Long problemId,
        String result,
        int memory,
        int time,
        String language,
        int codeLength,
        LocalDateTime submittedAt
) {
    public static JudgeResultResponse from(JudgeResult judgeResult) {
        return new JudgeResultResponse(
                judgeResult.getId(),
                judgeResult.getUserId(),
                judgeResult.getProblemId(),
                judgeResult.getResult(),
                judgeResult.getMemory(),
                judgeResult.getTime(),
                judgeResult.getLanguage(),
                judgeResult.getCodeLength(),
                judgeResult.getSubmittedAt()
        );
    }
}
