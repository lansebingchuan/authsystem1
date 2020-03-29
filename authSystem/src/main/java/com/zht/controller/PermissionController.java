package com.zht.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zht
 * @create 2019-12-28 18:46
 */
@Controller
public class PermissionController {

    @RequestMapping("/permissionManage")
    public String permissionManage(){
        return "permission/permissionManage";
    }
}
