package net.codeclass.codeclassbackend.service;

import lombok.RequiredArgsConstructor;
import net.codeclass.codeclassbackend.dto.*;
import net.codeclass.codeclassbackend.entity.Comment;
import net.codeclass.codeclassbackend.entity.Post;
import net.codeclass.codeclassbackend.entity.PostCategory;
import net.codeclass.codeclassbackend.repository.CommentRepository;
import net.codeclass.codeclassbackend.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public Page<PostListResponse> getPosts(PostCategory category, Pageable pageable) {
        if (category != null) {
            return postRepository.findByCategoryAsListResponse(category, pageable);
        }
        return postRepository.findAllAsListResponse(pageable);
    }

    @Transactional
    public PostDetailResponse getPost(Long id) {
        Post post = findPostOrThrow(id);
        post.incrementViewCount();
        return PostDetailResponse.from(post);
    }

    @Transactional
    public PostDetailResponse createPost(PostCreateRequest request) {
        Post post = Post.builder()
                .category(request.getCategory())
                .title(request.getTitle())
                .content(request.getContent())
                .author(request.getAuthor())
                .password(request.getPassword())
                .createdAt(LocalDateTime.now())
                .viewCount(0)
                .build();
        return PostDetailResponse.from(postRepository.save(post));
    }

    @Transactional
    public void deletePost(Long id, PostDeleteRequest request) {
        Post post = findPostOrThrow(id);
        if (!post.getPassword().equals(request.getPassword())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "비밀번호가 일치하지 않습니다.");
        }
        postRepository.delete(post);
    }

    @Transactional
    public CommentResponse createComment(Long postId, CommentCreateRequest request) {
        Post post = findPostOrThrow(postId);
        Comment comment = Comment.builder()
                .post(post)
                .author(request.getAuthor())
                .content(request.getContent())
                .createdAt(LocalDateTime.now())
                .build();
        return CommentResponse.from(commentRepository.save(comment));
    }

    private Post findPostOrThrow(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 게시글입니다."));
    }
}
