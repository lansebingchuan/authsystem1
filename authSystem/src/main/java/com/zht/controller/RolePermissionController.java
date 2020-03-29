package com.zht.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zht
 * @create 2019-12-28 20:57
 */
@Controller
public class RolePermissionController {

    @RequestMapping("/rolePermission")
    public String rolePermission(){
        return "rolePermission/rolePermissionManage";
    }
}
