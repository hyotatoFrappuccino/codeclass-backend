package net.codeclass.codeclassbackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.codeclass.codeclassbackend.dto.*;
import net.codeclass.codeclassbackend.entity.PostCategory;
import net.codeclass.codeclassbackend.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public Page<PostListResponse> getPosts(
            @RequestParam(required = false) PostCategory category,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return postService.getPosts(category, pageable);
    }

    @GetMapping("/{id}")
    public PostDetailResponse getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @PostMapping
    public ResponseEntity<PostDetailResponse> createPost(@Valid @RequestBody PostCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPost(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(
            @PathVariable Long id,
            @Valid @RequestBody PostDeleteRequest request) {
        postService.deletePost(id, request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentResponse> createComment(
            @PathVariable Long id,
            @Valid @RequestBody CommentCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createComment(id, request));
    }
}
