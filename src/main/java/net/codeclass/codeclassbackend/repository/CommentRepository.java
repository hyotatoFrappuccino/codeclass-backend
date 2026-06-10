package net.codeclass.codeclassbackend.repository;

import net.codeclass.codeclassbackend.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
