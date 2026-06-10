package net.codeclass.codeclassbackend.dto;

import net.codeclass.codeclassbackend.entity.Comment;

import java.time.LocalDateTime;

public record CommentResponse(
        Long id,
        String author,
        String content,
        LocalDateTime createdAt
) {
    public static CommentResponse from(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getAuthor(),
                comment.getContent(),
                comment.getCreatedAt()
        );
    }
}
