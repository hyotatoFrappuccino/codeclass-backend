package net.codeclass.codeclassbackend.dto;

import lombok.Getter;

@Getter
public class SubmitRequest {
    private String userId;
    private String language;
    private String code;
}
