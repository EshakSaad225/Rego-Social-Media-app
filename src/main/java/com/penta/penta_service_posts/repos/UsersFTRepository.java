package com.penta.penta_service_posts.repos;

import com.penta.penta_service_posts.domain.UsersFT;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsersFTRepository extends JpaRepository<UsersFT, UUID> {
}
