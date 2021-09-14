package com.example.demo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    /*
    @Bean
    public CommandLineRunner demo(ProductRepository repository) {
        return (args -> {
            repository.save(new Product("Gurka", 11, "https://assets.icanet.se/t_product_large_2x_v1,f_auto/2092459500000.jpg", "Gurka styck ca 320g.", 21, "Grönsäker", true));
            repository.save(new Product("Broccoli", 11, "https://assets.icanet.se/t_product_large_v1,f_auto/7330671127168.jpg", "Broccoli 250g Klass 1 ICA", 16, "Grönsäker", true));
            repository.save(new Product("Tomat", 11, "https://assets.icanet.se/t_product_large_v1,f_auto/2092480700004.jpg", "Tomat kvist röd Svensk ca 120g", 34, "Grönsaker", false));
            repository.save(new Product("Ärter", 11, "https://assets.icanet.se/t_product_large_v1,f_auto/7310500001890.jpg", "Ärter Fryst 600g Findus", 24, "Grönsaker", false));
            repository.save(new Product("Pepsi", 11, "https://assets.icanet.se/t_product_large_v1,f_auto/7310072765992.jpg", "Pepsi Max 33cl 6-p", 37, "Dryck", true));
            repository.save(new Product("Red Bull", 11, "https://assets.icanet.se/t_product_large_v1,f_auto/9002490215675.jpg", "Energidryck 25cl 4-p Red Bull", 53, "Dryck", false));
            repository.save(new Product("Mousserande Vin", 11, "https://assets.icanet.se/t_product_large_v1,f_auto/5998813159921.jpg", "Mousserande Vitt Vin Alkoholfri 75cl Chapel Hill", 48, "Dryck", true));
            repository.save(new Product("Rött vin", 11, "https://assets.icanet.se/t_product_large_v1,f_auto/4009427825148.jpg", "Merlot Rött vin Alkoholfri 75cl Carl Jung", 46, "Dryck", false));
            repository.save(new Product("Öl", 11, "https://assets.icanet.se/t_product_large_2x_v1,f_auto/7310402006795.jpg", "Öl 3,5% 50cl 6-p Norrlands Guld ", 66, "Dryck", true));
            repository.save(new Product("Ramlösa", 11, "https://assets.icanet.se/t_product_large_2x_v1,f_auto/7310070001719.jpg", "Vatten Kolsyrad Original 50cl Ramlösa ", 12, "Dryck", true));
            repository.save(new Product("Bravo", 11, "https://assets.icanet.se/t_product_large_2x_v1,f_auto/7310867561402.jpg", "Apelsinjuice 1l Miljömärkt Bravo ", 20, "Dryck", true));
            repository.save(new Product("Chips", 11, "https://assets.icanet.se/t_product_large_v1,f_auto/8710398016515.jpg", "Chips Salted 175g Lay´s", 22, "Godis", true));
            repository.save(new Product("Kexchoklad", 11, "https://assets.icanet.se/t_product_large_v1,f_auto/7310350118342.jpg", "Kexchoklad 60g Cloetta", 9, "Godis", false));
            repository.save(new Product("Pringles", 11, "https://assets.icanet.se/t_product_large_v1,f_auto/5053990138753.jpg", "Chips Sourcream & onion 200g Pringles", 26, "Godis", true));
            repository.save(new Product("Glass", 11, "https://assets.icanet.se/t_product_large_v1,f_auto/8712566328352.jpg", "Glass Classic 4-pack Magnum", 44, "Godis", true));
        });
    }

     */
/*
    @Bean
    public CommandLineRunner demo(CustomerRepository repository) {
        return (args -> {

            repository.save(new Customer("hej","hje","hek","he"));
        });
        }

 */


}
