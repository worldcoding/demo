package com.quick.start.provider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo/user")
public class UserController {

    @GetMapping("/getName")
    public String getName(){
        return "guo jia";
    }

    @GetMapping("/getPhone")
    public String getPhone(){
        return "18812345678";
    }

    @GetMapping("/sayHello")
    public String sayHello(){
        return "hello world!";
    }
}
