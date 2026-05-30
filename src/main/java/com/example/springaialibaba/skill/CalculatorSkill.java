package com.example.springaialibaba.skill;

import org.springframework.stereotype.Component;

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
            expression = expression.replaceAll("\\s+", "");
            
            double result = evaluateExpression(expression);
            return "计算结果: " + result;
        } catch (Exception e) {
            return "计算错误: " + e.getMessage();
        }
    }

    private double evaluateExpression(String expr) {
        expr = expr.replace(" ", "");
        return parseExpression(expr);
    }

    private double parseExpression(String expr) {
        if (expr.contains("+")) {
            int idx = findOperatorIndex(expr, '+');
            return parseExpression(expr.substring(0, idx)) + parseExpression(expr.substring(idx + 1));
        }
        if (expr.contains("-")) {
            int idx = findOperatorIndex(expr, '-');
            if (idx == 0) {
                return -parseExpression(expr.substring(1));
            }
            return parseExpression(expr.substring(0, idx)) - parseExpression(expr.substring(idx + 1));
        }
        if (expr.contains("*")) {
            int idx = findOperatorIndex(expr, '*');
            return parseExpression(expr.substring(0, idx)) * parseExpression(expr.substring(idx + 1));
        }
        if (expr.contains("/")) {
            int idx = findOperatorIndex(expr, '/');
            double divisor = parseExpression(expr.substring(idx + 1));
            if (divisor == 0) {
                throw new ArithmeticException("除零错误");
            }
            return parseExpression(expr.substring(0, idx)) / divisor;
        }
        return Double.parseDouble(expr);
    }

    private int findOperatorIndex(String expr, char op) {
        int depth = 0;
        for (int i = expr.length() - 1; i >= 0; i--) {
            char c = expr.charAt(i);
            if (c == ')') depth++;
            if (c == '(') depth--;
            if (depth == 0 && c == op && i > 0 && expr.charAt(i - 1) != '-') {
                return i;
            }
        }
        return -1;
    }
}
