package com.zht.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ZHT
 */
@Controller
@RequestMapping("main")
public class MainController {


    @RequestMapping("/main")
    public String main(){
        return "main/main";
    }
}
