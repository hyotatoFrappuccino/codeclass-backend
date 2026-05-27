package net.codeclass.codeclassbackend.controller;

import lombok.RequiredArgsConstructor;
import net.codeclass.codeclassbackend.entity.Problem;
import net.codeclass.codeclassbackend.repository.ProblemRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/problems")
@RequiredArgsConstructor
public class ProblemController {

    private final ProblemRepository problemRepository;

    @GetMapping
    public List<Problem> getProblems() {
        return problemRepository.findAll();
    }
}
