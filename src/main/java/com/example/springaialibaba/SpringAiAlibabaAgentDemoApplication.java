package com.example.springaialibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class SpringAiAlibabaAgentDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAiAlibabaAgentDemoApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/")
    public String index() {
        return "Spring AI Alibaba Agent Demo is running!\n\n" +
               "API Endpoints:\n" +
               "- GET /api/skills/weather?city=北京\n" +
               "- GET /api/skills/calculator?expression=1+2*3\n" +
               "- POST /mcp/translation/zh-en\n" +
               "- POST /mcp/sentiment/analyze\n" +
               "- POST /api/context/compress\n" +
               "- POST /api/queue/submit\n" +
               "- POST /api/chat";
    }

    @GetMapping("/api/chat")
    public String chatGet(@RequestParam String message) {
        return "收到消息: '" + message + "'\n\n这是一个模拟的AI回复。\n\n如需完整的AI功能，请配置阿里云DashScope API Key。";
    }

    @PostMapping("/api/chat")
    public String chatPost(@RequestBody String message) {
        return "收到消息: '" + message + "'\n\n这是一个模拟的AI回复。\n\n如需完整的AI功能，请配置阿里云DashScope API Key。";
    }
}
