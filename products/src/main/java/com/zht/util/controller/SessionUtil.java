package com.zht.util.controller;

import com.zht.bean.User;

import javax.servlet.http.HttpSession;

/**
 * @author zht
 * @create 2019-12-05 22:53
 */
public class SessionUtil {

    public static User getUser(HttpSession session) {
        return (User) session.getAttribute("user");
    }

    public static void setSessionUser(HttpSession session, User user) {
        session.setAttribute("user", user);
    }
}
