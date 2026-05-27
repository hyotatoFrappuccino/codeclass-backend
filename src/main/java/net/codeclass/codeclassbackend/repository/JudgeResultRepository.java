package net.codeclass.codeclassbackend.repository;

import net.codeclass.codeclassbackend.entity.JudgeResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JudgeResultRepository extends JpaRepository<JudgeResult, Long> {
    List<JudgeResult> findAllByOrderBySubmittedAtDesc();
}
