package net.codeclass.codeclassbackend.repository;

import net.codeclass.codeclassbackend.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepository extends JpaRepository<Problem, Long> {
}
