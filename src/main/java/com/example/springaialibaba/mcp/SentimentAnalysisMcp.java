package com.example.springaialibaba.mcp;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mcp/sentiment")
public class SentimentAnalysisMcp {

    @PostMapping("/analyze")
    public String analyzeSentiment(@RequestBody String text) {
        String lowerText = text.toLowerCase();
        
        int positiveScore = countMatches(lowerText, "好", "棒", "赞", "喜欢", "爱", "开心", "高兴", "优秀", "精彩", "完美");
        int negativeScore = countMatches(lowerText, "坏", "差", "糟", "讨厌", "恨", "伤心", "难过", "失望", "垃圾");
        
        String sentiment;
        double score;
        
        if (positiveScore > negativeScore) {
            sentiment = "正面";
            score = Math.min(1.0, positiveScore * 0.2);
        } else if (negativeScore > positiveScore) {
            sentiment = "负面";
            score = Math.max(-1.0, -negativeScore * 0.2);
        } else {
            sentiment = "中性";
            score = 0.0;
        }
        
        return String.format("1. 情感倾向：%s\n2. 评分：%.2f\n3. 分析理由：文本包含%d个正面词汇，%d个负面词汇", 
                           sentiment, score, positiveScore, negativeScore);
    }

    @PostMapping("/score")
    public String getSentimentScore(@RequestBody String text) {
        String lowerText = text.toLowerCase();
        
        int positiveScore = countMatches(lowerText, "好", "棒", "赞", "喜欢", "爱", "开心", "高兴", "优秀", "精彩", "完美");
        int negativeScore = countMatches(lowerText, "坏", "差", "糟", "讨厌", "恨", "伤心", "难过", "失望", "垃圾");
        
        double score = (positiveScore - negativeScore) * 0.1;
        score = Math.max(-1.0, Math.min(1.0, score));
        
        return String.format("%.2f", score);
    }
    
    private int countMatches(String text, String... keywords) {
        int count = 0;
        for (String keyword : keywords) {
            int index = 0;
            while ((index = text.indexOf(keyword, index)) != -1) {
                count++;
                index += keyword.length();
            }
        }
        return count;
    }
}
