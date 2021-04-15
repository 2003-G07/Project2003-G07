package com.example.demo1;

import com.example.demo1.models.Produkt;
import com.example.demo1.repositories.ProduktRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    /*
    @Bean
    public CommandLineRunner demo(ProduktRepository repository){
        return (args -> {
           repository.save(new Produkt("Falun Gong Earl Grey",11,"https://picsum.photos/500?random=1","Ett svart te smaksatt med bergamott.",160,"Dryck"));
        });
    }



     */

}
