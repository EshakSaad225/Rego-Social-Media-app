package com.penta.penta_service_posts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class PentaServicePostsApplication {

    public static void main(final String[] args) {
        SpringApplication.run(PentaServicePostsApplication.class, args);
    }

}
