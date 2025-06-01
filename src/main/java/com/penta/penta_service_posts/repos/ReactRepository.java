package com.penta.penta_service_posts.repos;

import com.penta.penta_service_posts.domain.React;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReactRepository extends JpaRepository<React, UUID> {
}
