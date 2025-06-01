package com.penta.penta_service_posts.repos;

import com.penta.penta_service_posts.domain.Post;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post, UUID> {
}
