package ru.olmart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.olmart.controller.PostController;
import ru.olmart.repository.PostRepository;
import ru.olmart.service.PostService;

@Configuration
public class JavaConfig {
    @Bean
    // аргумент метода и есть DI
    // название метода - название бина
    public PostController postController(PostService service) {
        return new PostController(service);
    }

    @Bean
    public PostService postService(PostRepository repository) {
        return new PostService(repository);
    }

    @Bean
    public PostRepository postRepository() {
        return new PostRepository();
    }
}
