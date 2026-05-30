package com.example.springaialibaba.mcp;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/mcp/translation")
public class TranslationMcp {

    @PostMapping("/zh-en")
    public String translateToEnglish(@RequestBody String text) {
        Map<String, String> translations = new HashMap<>();
        translations.put("你好", "Hello");
        translations.put("谢谢", "Thank you");
        translations.put("再见", "Goodbye");
        translations.put("中国", "China");
        translations.put("北京", "Beijing");
        
        if (translations.containsKey(text.trim())) {
            return translations.get(text.trim());
        }
        
        return "Translated: " + text + " -> (English)";
    }

    @PostMapping("/en-zh")
    public String translateToChinese(@RequestBody String text) {
        Map<String, String> translations = new HashMap<>();
        translations.put("Hello", "你好");
        translations.put("Thank you", "谢谢");
        translations.put("Goodbye", "再见");
        translations.put("China", "中国");
        translations.put("Beijing", "北京");
        
        if (translations.containsKey(text.trim())) {
            return translations.get(text.trim());
        }
        
        return "翻译结果: " + text + " -> (中文)";
    }
}
