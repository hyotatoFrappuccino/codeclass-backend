package net.codeclass.codeclassbackend.dto;

import net.codeclass.codeclassbackend.entity.PostCategory;

import java.time.LocalDateTime;

public record PostListResponse(
        Long id,
        PostCategory category,
        String title,
        String author,
        LocalDateTime createdAt,
        int viewCount,
        long commentCount,
        boolean pinned
) {}
