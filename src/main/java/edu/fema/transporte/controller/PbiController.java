package edu.fema.transporte.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PbiController {

    @GetMapping("/pbi")
    public String showAnalyticsPage(){
        return "pbi";

    }
}
