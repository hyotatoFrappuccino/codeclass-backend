package net.codeclass.codeclassbackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import net.codeclass.codeclassbackend.entity.PostCategory;

@Getter
public class PostCreateRequest {

    @NotNull
    private PostCategory category;

    @NotBlank
    @Size(max = 200)
    private String title;

    @NotBlank
    @Size(max = 5000)
    private String content;

    @NotBlank
    @Size(max = 50)
    private String author;

    @NotBlank
    @Size(min = 4, max = 20)
    private String password;
}
