package com.penta.penta_service_posts.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data 
@AllArgsConstructor 
@NoArgsConstructor 
public class SaveForLater {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "postSaveForLater_id")
    private Post post;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Users user;

    @Column
    private LocalDateTime savedAt;

}
