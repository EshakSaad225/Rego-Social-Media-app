package com.penta.penta_service_posts.repos;

import com.penta.penta_service_posts.domain.SaveForLater;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SaveForLaterRepository extends JpaRepository<SaveForLater, UUID> {
}
