package com.penta.penta_service_posts.model;

import java.util.UUID;

import com.penta.penta_service_posts.domain.Post;
import com.penta.penta_service_posts.domain.UsersFT;
import com.penta.penta_service_posts.enums.ReactType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data 
@AllArgsConstructor 
@NoArgsConstructor 
public class ReactDTO {

    private UUID id;

    private ReactType type;

    private Post post;

    private UsersFT createdBy;

}
