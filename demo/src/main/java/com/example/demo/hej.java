package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;

/**
 * Created by Daniel Bojic
 * Date: 2021-04-13
 * Time: 15:45
 * Project: Project2003-G07
 * Copyright: MIT
 */
@RestController
public class hej {

    @RequestMapping("/")
    public Path  index(){
        Path path = Path.of("/Users/dannekun/Documents/Nackademin/Klientutveckling/Grupp 7/Project2003-G07/index.html");
        return path;
    }

}
