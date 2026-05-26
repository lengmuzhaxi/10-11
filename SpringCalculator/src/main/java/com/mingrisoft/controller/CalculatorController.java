package com.mingrisoft.controller;
import com.mingrisoft.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {
    @Autowired
    private CalculatorService calculatorService;

    @RequestMapping({"/", "/index"})
    public String index() {
        return "index";
    }

    @RequestMapping("/doCalculate")
    public String doCalculate(
            @RequestParam(value = "num1", required = false) Double num1,
            @RequestParam(value = "num2", required = false) Double num2,
            @RequestParam("operator") String operator,
            Model model) {
        String result = calculatorService.calculate(num1, num2, operator);
        model.addAttribute("operator", operator);
        model.addAttribute("result", result);
        return "result";
    }
}
