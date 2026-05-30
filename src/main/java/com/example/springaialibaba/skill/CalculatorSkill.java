package com.example.springaialibaba.skill;

import org.springframework.stereotype.Component;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

@Component
public class CalculatorSkill {

    public String getName() {
        return "calculate";
    }

    public String getDescription() {
        return "执行数学计算，支持加减乘除等基本运算";
    }

    public String execute(String expression) {
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("js");
            Object result = engine.eval(expression);
            return "计算结果: " + result;
        } catch (Exception e) {
            return "计算错误: " + e.getMessage();
        }
    }
}
