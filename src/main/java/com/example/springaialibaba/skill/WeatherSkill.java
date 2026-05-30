package com.example.springaialibaba.skill;

import org.springframework.stereotype.Component;

@Component
public class WeatherSkill {

    public String getName() {
        return "get_weather";
    }

    public String getDescription() {
        return "查询指定城市的天气情况";
    }

    public String execute(String city) {
        return String.format("%s今天天气晴朗，温度25-32度，空气质量良好。", city);
    }
}
