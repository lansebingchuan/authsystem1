package com.zht.util.controller;

import com.zht.bean.UserInfo;
import com.zht.util.constant.ConstantInterface;

import javax.servlet.http.HttpSession;

/**
 * @author zht
 * @create 2019-12-05 22:53
 */
public class SessionUtil {

    public static UserInfo getAdminUserInfo(HttpSession session) {
        Object admin = session.getAttribute(ConstantInterface.ADMIN);
        if (admin != null){
            return (UserInfo)admin;
        }
        Object averageuser = session.getAttribute(ConstantInterface.AVERAGEUSER);
        if (averageuser != null){
            return (UserInfo)averageuser;
        }
        return null;
    }

    public static void setSessionUserInfo(HttpSession session, UserInfo userInfo) {
        if (ConstantInterface.ADMIN.equals(userInfo.getRole())){
            session.setAttribute(ConstantInterface.ADMIN, userInfo);
            session.setAttribute(ConstantInterface.AVERAGEUSER, null);
        }else {
            session.setAttribute(ConstantInterface.AVERAGEUSER, userInfo);
            session.setAttribute(ConstantInterface.ADMIN, null);
        }
    }
}
