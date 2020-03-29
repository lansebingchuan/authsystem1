package com.zht.intercepors;

import com.zht.bean.UserInfo;
import com.zht.util.controller.SessionUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class LoginIntercepors implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        HttpSession session = request.getSession();
        UserInfo userInfo = SessionUtil.getAdminUserInfo(session);
        if (userInfo == null){
            String servletPath = request.getServletPath();
            if (servletPath.equals("/")){
                return true;
            }
            response.sendRedirect("/login");
            return false;
        }else{
            return true;
        }

    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
