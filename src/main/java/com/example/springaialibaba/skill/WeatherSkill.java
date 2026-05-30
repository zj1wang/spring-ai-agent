package com.example.springaialibaba.skill;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class WeatherSkill {

    private final ChatModel chatModel;

    public WeatherSkill(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String getName() {
        return "get_weather";
    }

    public String getDescription() {
        return "查询指定城市的天气情况";
    }

    public String execute(String city) {
        String template = "请告诉我{city}今天的天气情况，包括温度、天气状况，用中文简洁回答。";
        PromptTemplate promptTemplate = new PromptTemplate(template);
        Prompt prompt = promptTemplate.create(Map.of("city", city));
        return chatModel.call(prompt).getResult().getOutput().getContent();
    }
}
