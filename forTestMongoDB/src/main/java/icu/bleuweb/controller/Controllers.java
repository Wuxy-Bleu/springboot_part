package icu.bleuweb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controllers {

    @GetMapping("/table")
    public String inline_table(){
        System.out.println("inline table");
        return "vhaeurbgviae";
    }
}
