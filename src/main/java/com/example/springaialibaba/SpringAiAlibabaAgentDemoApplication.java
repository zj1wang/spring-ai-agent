package com.example.springaialibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.ai.chat.model.ChatModel;

@SpringBootApplication
@RestController
public class SpringAiAlibabaAgentDemoApplication {

    private final ChatModel chatModel;

    public SpringAiAlibabaAgentDemoApplication(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringAiAlibabaAgentDemoApplication.class, args);
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
        return chatModel.call(message);
    }

    @PostMapping("/api/chat")
    public String chatPost(@RequestBody String message) {
        return chatModel.call(message);
    }
}
