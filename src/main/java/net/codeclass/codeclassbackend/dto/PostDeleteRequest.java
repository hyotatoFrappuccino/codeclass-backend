package net.codeclass.codeclassbackend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class PostDeleteRequest {

    @NotBlank
    private String password;
}
