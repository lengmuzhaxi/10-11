package com.mingrisoft.service;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
    public String calculate(Double num1, Double num2, String operator) {
        if (num1 == null || num2 == null) return "❌ 请输入完整的数字！";
        try {
            switch (operator) {
                case "+": return String.valueOf(num1 + num2);
                case "-": return String.valueOf(num1 - num2);
                case "*": return String.valueOf(num1 * num2);
                case "/":
                    if (num2 == 0) return "❌ 除数不能为 0！";
                    return String.valueOf(num1 / num2);
                default: return "❌ 未知的运算符！";
            }
        } catch (Exception e) {
            return "❌ 计算异常：" + e.getMessage();
        }
    }
}
