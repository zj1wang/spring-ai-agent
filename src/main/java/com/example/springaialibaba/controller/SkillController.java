package com.example.springaialibaba.controller;

import com.example.springaialibaba.skill.CalculatorSkill;
import com.example.springaialibaba.skill.WeatherSkill;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    private final WeatherSkill weatherSkill;
    private final CalculatorSkill calculatorSkill;

    public SkillController(WeatherSkill weatherSkill, CalculatorSkill calculatorSkill) {
        this.weatherSkill = weatherSkill;
        this.calculatorSkill = calculatorSkill;
    }

    @GetMapping("/weather")
    public String getWeather(@RequestParam String city) {
        return weatherSkill.execute(city);
    }

    @GetMapping("/calculator")
    public String calculate(@RequestParam String expression) {
        return calculatorSkill.execute(expression);
    }
}
