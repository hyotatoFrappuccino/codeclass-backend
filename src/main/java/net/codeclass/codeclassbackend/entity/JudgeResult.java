package net.codeclass.codeclassbackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "judge_results")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JudgeResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private Long problemId;
    private String result;
    private int memory;
    private int time;
    private String language;
    private int codeLength;
    private LocalDateTime submittedAt;
}
