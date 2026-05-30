package com.example.springaialibaba.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ContextCompressionService {

    private final ChatModel chatModel;
    private final List<String> contextHistory = new ArrayList<>();
    private static final int MAX_MESSAGES_BEFORE_COMPRESSION = 5;

    public ContextCompressionService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

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
        String combinedContext = String.join("\n", contextHistory);
        String template = """
            请将以下对话历史压缩为简洁的摘要，保留关键信息：
            {context}
            
            要求：
            1. 不超过100字
            2. 保留主要问题和答案
            3. 用中文回答
            """;
        PromptTemplate promptTemplate = new PromptTemplate(template);
        Prompt prompt = promptTemplate.create(Map.of("context", combinedContext));
        return chatModel.call(prompt).getResult().getOutput().getContent();
    }

    public List<String> getContextHistory() {
        return new ArrayList<>(contextHistory);
    }

    public void clearContext() {
        contextHistory.clear();
    }
}
