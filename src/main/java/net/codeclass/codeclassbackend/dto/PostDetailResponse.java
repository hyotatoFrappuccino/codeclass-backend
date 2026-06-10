package net.codeclass.codeclassbackend.dto;

import net.codeclass.codeclassbackend.entity.Post;
import net.codeclass.codeclassbackend.entity.PostCategory;

import java.time.LocalDateTime;
import java.util.List;

public record PostDetailResponse(
        Long id,
        PostCategory category,
        String title,
        String content,
        String author,
        LocalDateTime createdAt,
        int viewCount,
        boolean pinned,
        List<CommentResponse> comments
) {
    public static PostDetailResponse from(Post post) {
        return new PostDetailResponse(
                post.getId(),
                post.getCategory(),
                post.getTitle(),
                post.getContent(),
                post.getAuthor(),
                post.getCreatedAt(),
                post.getViewCount(),
                post.isPinned(),
                post.getComments().stream().map(CommentResponse::from).toList()
        );
    }
}
