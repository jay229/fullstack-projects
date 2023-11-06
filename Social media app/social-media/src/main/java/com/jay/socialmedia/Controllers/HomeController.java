package com.jay.socialmedia.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping
    public String homeControllerHandler(){
        return "This is home controller";
    }
}
