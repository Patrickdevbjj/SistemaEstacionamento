package com.patrick.Estacionamento.layouts;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LayoutControllers {

    @GetMapping("/index")
    public String index() {
        return "index";
    }

}
