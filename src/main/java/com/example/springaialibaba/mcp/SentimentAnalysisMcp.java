package com.example.springaialibaba.mcp;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/mcp/sentiment")
public class SentimentAnalysisMcp {

    private final ChatModel chatModel;

    public SentimentAnalysisMcp(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @PostMapping("/analyze")
    public String analyzeSentiment(@RequestBody String text) {
        String template = """
            请分析以下文本的情感倾向：
            {text}
            
            请按照以下格式输出：
            1. 情感倾向：（正面/负面/中性）
            2. 评分：（-1到1之间）
            3. 分析理由：
            """;
        PromptTemplate promptTemplate = new PromptTemplate(template);
        Prompt prompt = promptTemplate.create(Map.of("text", text));
        return chatModel.call(prompt).getResult().getOutput().getContent();
    }

    @PostMapping("/score")
    public String getSentimentScore(@RequestBody String text) {
        String template = "请为以下文本打分，情感分数范围从-1到1，-1表示非常负面，1表示非常正面。请只返回分数：\n{text}";
        PromptTemplate promptTemplate = new PromptTemplate(template);
        Prompt prompt = promptTemplate.create(Map.of("text", text));
        return chatModel.call(prompt).getResult().getOutput().getContent();
    }
}
