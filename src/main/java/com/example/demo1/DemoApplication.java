package com.example.demo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}

/*
    @Bean
    public CommandLineRunner demo(ProductRepository repository) {
        return (args -> {
            repository.save(new Product("Prod 1", 11, "https://picsum.photos/500?random=1", "Ett svart te smaksatt med bergamott.", 160, "Dryck", true));
            repository.save(new Product("Prod 2", 11, "https://picsum.photos/500?random=2", "Ett svart te smaksatt med bergamott.", 160, "Dryck", false));
            repository.save(new Product("Prod 3", 11, "https://picsum.photos/500?random=3", "Ett svart te smaksatt med bergamott.", 160, "Dryck", true));
        });
    }
 */