package com.testing.RBAC.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculate")
public class CalculateController {
    @GetMapping("/do")
    public String calculate() {
        return "Perform calculation here!";
    }
}