package com.penta.penta_service_posts.repos;

import com.penta.penta_service_posts.domain.Comment;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepository extends JpaRepository<Comment, UUID> {
}
