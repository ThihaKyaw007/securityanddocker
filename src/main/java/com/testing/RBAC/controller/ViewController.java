package com.testing.RBAC.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/view")
public class ViewController {
    @GetMapping("/data")
    public String viewData() {
        return "Viewing data for all roles!";
    }
}