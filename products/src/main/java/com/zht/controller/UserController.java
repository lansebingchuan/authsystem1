package com.zht.controller;

import com.zht.bean.User;
import com.zht.service.UserService;
import com.zht.util.controller.BaseController;
import com.zht.util.controller.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author ZHT
 */
@Controller
@RequestMapping("/main")
public class UserController extends BaseController {

    @Autowired
    UserService userService;

    @RequestMapping("/userDetailedPage")
    public String userDetailedPage(){
        return "main/user/userDetailed";
    }

    @ResponseBody
    @RequestMapping("/getSessionUser")
    public Object getSessionUser(HttpSession session){
        start();
        User user = SessionUtil.getUser(session);
        put("user", user);
        flage(true);
        return end();
    }

    @ResponseBody
    @RequestMapping("/updateUser")
    public Object updateUser(HttpSession session, User user){
        start();
        User userSession = SessionUtil.getUser(session);
        userService.updateUser(user, userSession);
        SessionUtil.setSessionUser(session, userSession);
        flage(true);
        return end();
    }



}
