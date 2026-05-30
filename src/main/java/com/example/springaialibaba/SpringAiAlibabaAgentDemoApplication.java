package com.example.springaialibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class SpringAiAlibabaAgentDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAiAlibabaAgentDemoApplication.class, args);
    }

    @GetMapping("/")
    public String index() {
        return "Spring AI Alibaba Agent Demo is running!";
    }

    @GetMapping("/api/hello")
    public String hello(@RequestParam(defaultValue = "World") String name) {
        return "Hello, " + name + "!";
    }
}
