package net.codeclass.codeclassbackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SubmitRequest {

    private String userId;

    @NotBlank
    @Pattern(
        regexp = "python3|java|c\\+\\+14|c\\+\\+17|c99|kotlin|c#",
        flags = Pattern.Flag.CASE_INSENSITIVE,
        message = "지원하지 않는 언어입니다."
    )
    private String language;

    @NotBlank
    @Size(max = 65536, message = "코드는 64KB를 초과할 수 없습니다.")
    private String code;
}
