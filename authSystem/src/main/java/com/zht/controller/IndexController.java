package com.zht.controller;

import com.zht.bean.SystemReg;
import com.zht.service.SystemRegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author zht
 * @create 2019-11-28 21:36
 */
@Controller
public class IndexController {

    private final SystemRegService systemRegService;

    @Autowired
    public IndexController(SystemRegService systemRegService) {
        this.systemRegService = systemRegService;
    }

    @RequestMapping("/")
    public String doIndex() {
        return "redirect:index";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/main")
    public String main() {
        return "main";
    }

    @RequestMapping("/reg")
    public String reg() {
        return "reg";
    }


    @RequestMapping("/index/initIndexSysTemDate")
    @ResponseBody
    public List<SystemReg> initIndexSysTemDate() {
        return systemRegService.listSystemRegList();
    }


}
