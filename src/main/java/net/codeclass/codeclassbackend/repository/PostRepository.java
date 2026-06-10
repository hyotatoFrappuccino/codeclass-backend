package net.codeclass.codeclassbackend.repository;

import net.codeclass.codeclassbackend.dto.PostListResponse;
import net.codeclass.codeclassbackend.entity.Post;
import net.codeclass.codeclassbackend.entity.PostCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT new net.codeclass.codeclassbackend.dto.PostListResponse(" +
           "p.id, p.category, p.title, p.author, p.createdAt, p.viewCount, SIZE(p.comments), p.pinned) " +
           "FROM Post p ORDER BY p.pinned DESC, p.createdAt DESC")
    Page<PostListResponse> findAllAsListResponse(Pageable pageable);

    @Query("SELECT new net.codeclass.codeclassbackend.dto.PostListResponse(" +
           "p.id, p.category, p.title, p.author, p.createdAt, p.viewCount, SIZE(p.comments), p.pinned) " +
           "FROM Post p WHERE p.category = :category ORDER BY p.pinned DESC, p.createdAt DESC")
    Page<PostListResponse> findByCategoryAsListResponse(@Param("category") PostCategory category, Pageable pageable);
}
