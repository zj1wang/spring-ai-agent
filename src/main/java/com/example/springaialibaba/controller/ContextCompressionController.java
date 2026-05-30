package com.example.springaialibaba.controller;

import com.example.springaialibaba.service.ContextCompressionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/context")
public class ContextCompressionController {

    private final ContextCompressionService contextCompressionService;

    public ContextCompressionController(ContextCompressionService contextCompressionService) {
        this.contextCompressionService = contextCompressionService;
    }

    @PostMapping("/compress")
    public String compress(@RequestBody String message) {
        return contextCompressionService.addMessageAndCompress(message);
    }

    @GetMapping("/history")
    public List<String> getHistory() {
        return contextCompressionService.getContextHistory();
    }

    @DeleteMapping("/clear")
    public String clear() {
        contextCompressionService.clearContext();
        return "上下文已清空";
    }
}
