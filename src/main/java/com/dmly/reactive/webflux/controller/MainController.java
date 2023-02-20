package com.dmly.reactive.webflux.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping(path = "/")
    public String getIndex(@RequestParam(required = false, defaultValue = "<unknown>") String user, Model model) {
        model.addAttribute("user", user);
        return "index";
    }

}
