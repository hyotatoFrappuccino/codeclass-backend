package net.codeclass.codeclassbackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CommentCreateRequest {

    @NotBlank
    @Size(max = 50)
    private String author;

    @NotBlank
    @Size(max = 1000)
    private String content;
}
