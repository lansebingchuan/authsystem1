package com.zht.controller;

import com.zht.bean.User;
import com.zht.service.UserService;
import com.zht.util.controller.BaseController;
import com.zht.util.controller.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author ZHT
 */
@Controller
public class IndexController extends BaseController {

    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String root(){
        return "redirect:index";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    /**
     *
     * @param loginName 登录名
     * @param password 密码
     * @return boolean
     */
    @RequestMapping("/doLogin")
    @ResponseBody
    public Object login(HttpSession session, @RequestParam(required = false)String loginName , @RequestParam(required = false)String password) {
        User user = userService.getUserByLoginNameAndPwd(loginName, password);
        if (user != null){
            SessionUtil.setSessionUser(session , user);
            return true;
        }else {
            return false;
        }
    }
}
