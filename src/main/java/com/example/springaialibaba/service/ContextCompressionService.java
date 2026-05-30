package com.example.springaialibaba.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContextCompressionService {

    private final List<String> contextHistory = new ArrayList<>();
    private static final int MAX_MESSAGES_BEFORE_COMPRESSION = 5;

    public String addMessageAndCompress(String message) {
        contextHistory.add(message);
        
        if (contextHistory.size() >= MAX_MESSAGES_BEFORE_COMPRESSION) {
            String compressed = compressContext();
            contextHistory.clear();
            contextHistory.add(compressed);
            return "上下文已压缩: " + compressed;
        }
        
        return "当前上下文: " + String.join(" → ", contextHistory);
    }

    private String compressContext() {
        if (contextHistory.isEmpty()) {
            return "空上下文";
        }
        
        String summary = "对话摘要: ";
        for (int i = 0; i < Math.min(3, contextHistory.size()); i++) {
            String msg = contextHistory.get(i);
            summary += (i + 1) + "." + msg.substring(0, Math.min(20, msg.length())) + "...";
            if (i < Math.min(3, contextHistory.size()) - 1) {
                summary += "; ";
            }
        }
        if (contextHistory.size() > 3) {
            summary += " (+"+ (contextHistory.size() - 3) +"条更多)";
        }
        return summary;
    }

    public List<String> getContextHistory() {
        return new ArrayList<>(contextHistory);
    }

    public void clearContext() {
        contextHistory.clear();
    }
}
