package com.example.springaialibaba.mcp;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/mcp/translation")
public class TranslationMcp {

    private final ChatModel chatModel;

    public TranslationMcp(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @PostMapping("/zh-en")
    public String translateToEnglish(@RequestBody String text) {
        String template = "请将以下中文翻译成英文，只返回翻译结果：\n{text}";
        PromptTemplate promptTemplate = new PromptTemplate(template);
        Prompt prompt = promptTemplate.create(Map.of("text", text));
        return chatModel.call(prompt).getResult().getOutput().getContent();
    }

    @PostMapping("/en-zh")
    public String translateToChinese(@RequestBody String text) {
        String template = "请将以下英文翻译成中文，只返回翻译结果：\n{text}";
        PromptTemplate promptTemplate = new PromptTemplate(template);
        Prompt prompt = promptTemplate.create(Map.of("text", text));
        return chatModel.call(prompt).getResult().getOutput().getContent();
    }
}
