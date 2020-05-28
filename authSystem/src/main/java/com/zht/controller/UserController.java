package com.zht.controller;

import com.zht.bean.Permission;
import com.zht.bean.UserInfo;
import com.zht.bean.UserSystem;
import com.zht.service.UserService;
import com.zht.service.UserSystemService;
import com.zht.util.PasswordUtil;
import com.zht.util.RedisUtil;
import com.zht.util.UUIDGenerator;
import com.zht.util.constant.ConstantInterface;
import com.zht.util.controller.BaseController;
import com.zht.util.controller.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author zht
 * @create 2019-11-21 22:34
 */
@Controller
public class UserController extends BaseController {

    @Autowired
    UserService userService;

    @Autowired
    UserSystemService userSystemService;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    RedisUtil.RedisString redisString;

    @Autowired
    RedisUtil.RedisList redisList;
    /**
     *
     * @param loginName 登录名
     * @param password 密码
     * @param uuid 管理员 uuid
     * @return boolean
     */
    @RequestMapping("/dologin")
    @ResponseBody
    public Object login(@RequestParam(required = false)String loginName ,@RequestParam(required = false)String role, @RequestParam(required = false)String password , @RequestParam(required = false) String uuid, HttpSession session,
    HttpServletResponse response) {
        start();
        if (uuid != null && !"".equals(uuid)){
            //是超级管理员
            UserInfo userByUuid = userService.getUserByUuid(loginName, password, uuid);
            if (userByUuid != null){
                flage(true);
            }else {
                flage(false);
            }
            return end();
        }else {//普通用户
            UserInfo userInfo = userService.getUserByPassword(loginName, password,role);
            if (userInfo != null){
                //登录成功，
                if (ConstantInterface.ADMIN.equals(userInfo.getRole())){
                    session.setAttribute(ConstantInterface.ADMIN, userInfo);
                    put("role", ConstantInterface.ADMIN);
                    session.setAttribute(ConstantInterface.AVERAGEUSER, null);
                }else {
                    session.setAttribute(ConstantInterface.AVERAGEUSER, userInfo);
                    put("role", ConstantInterface.AVERAGEUSER);
                    session.setAttribute(ConstantInterface.ADMIN, null);
                }
                String UserUuid = UUIDGenerator.getUUID();
                Cookie cookie = new Cookie("loginTicket", UserUuid);
                //24小时
                cookie.setMaxAge(60*60*24);
                response.addCookie(cookie);
                //设置过期为30分钟
                redisString.set(UserUuid, String.valueOf(userInfo.getId()), 60 * 30);
                redisUtil.del(ConstantInterface.VALIDATION+"_"+session.getId());
                flage(true);
                return end();
            }
            flage(false);
            return end();
        }
    }

    @RequestMapping("/getRandomNumber")//返回验证码
    @ResponseBody
    public String getRandomNumber(HttpSession session) {
        //随机6位验证码
        String random_6 = PasswordUtil.getRandom_6();
        //存活60秒
        redisString.set(ConstantInterface.VALIDATION+"_"+session.getId(), random_6 , 60);
        return random_6;
    }

    @RequestMapping("/testRandomNumber")//验证验证码
    @ResponseBody
    public Object testRandomNumber(String  discode , HttpSession session) {
        start();
        Object object = redisString.get(ConstantInterface.VALIDATION + "_" + session.getId());
        if (object == null){
            flage(false);
            message("请刷新后重试！");
            return end();
        }
        if (!object.equals(discode)){
            flage(false);
            message("输入验证码错误！");
            return end();
        }
        flage(true);
        return end();
    }

    @RequestMapping("/getSessionUser")//异步获取user
    @ResponseBody
    public Object getSessionUser(HttpSession session) {
        start();
        UserInfo admin =(UserInfo) session.getAttribute(ConstantInterface.ADMIN);
        UserInfo averageuser =(UserInfo) session.getAttribute(ConstantInterface.AVERAGEUSER);
        if (admin != null){
//            admin.setCreateTime(CommonUtil.formatDateToString(admin.getCreatetime()));
            put("user", admin);
            flage(true);
        }else if (averageuser != null){
//            averageuser.setCreateTime(CommonUtil.formatDateToString(averageuser.getCreatetime()));
            put("user", averageuser);
            flage(true);
        }else {
            flage(false);
        }
        return end();
    }

    //获取用户的这个 sysUuid 下的
    @RequestMapping("/getUserRolePermission")//异步获取user
    @ResponseBody
    public Object getUserRolePermission(HttpSession session,@RequestParam(required = false) String sysUuid) {
        start();
        UserInfo userInfo = SessionUtil.getAdminUserInfo(session);
        if (userInfo != null){
            put("user", userInfo);
            if (userInfo.getRole().equals(ConstantInterface.ADMIN)){
                UserSystem userSystem = userSystemService.getUserSystemByUserIdAndSysId(userInfo.getId() , userInfo.getSysUuid());
                if (userSystem != null){
                    List<Permission> rootPermissionList = userSystemService.getUserRolePermissionByIdAndSysUuid(userInfo.getId(), sysUuid);
                }else {
                    flage(false);
                    put("sysNo", "您还没注册这个系统");
                }
            }else {
                if (sysUuid == null){
                    flage(false);
                    put("error", "缺少关键系统唯一id");
                }else {

                }
            }
        }else {
            flage(false);
            put("userDown", true);
        }
        return end();
    }

    /**
     * 返回freemarker模板格式
     * @param model
     * @return
     */
    @RequestMapping("/getMenuTree")
    public String getMenuTree(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session , String sysUuid) throws IOException {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie: cookies){
            if (cookie != null && cookie.getName().equals("loginTicket")){
                String userUuid = cookie.getValue();
                String userIds = (String)redisString.get(userUuid);
                if (userIds != null){
                    Integer userId = Integer.parseInt(userIds);
                    List<Permission> permissionList = userSystemService.getUserRolePermissionByIdAndSysUuid(userId, sysUuid);

                    model.addAttribute("permissionList", permissionList);
                    redisList.set("permission_"+userId, permissionList);
                }else {
                    response.setContentType("text/html;charset=utf-8");
                    request.setCharacterEncoding("UTF-8");
                    response.getWriter().print("no");
                    response.getWriter().close();
                    return null;
                }
            }
        }
        return "menuTree";
    }

    /**
     * 根据 cookie  loginTicket获取设置当前当前登录人 session
     * @return
     */
    @RequestMapping("/setSessionUser")
    public void setSessionUser(HttpServletRequest request, HttpSession session) throws IOException {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie: cookies){
            if (cookie != null && "loginTicket".equals(cookie.getName())){
                String userUuid = cookie.getValue();
                String userIds = (String)redisString.get(userUuid);
                UserInfo userInfo = userService.getUserById(Integer.parseInt(userIds));
                if (ConstantInterface.ADMIN.equals(userInfo.getRole())){
                    session.setAttribute(ConstantInterface.ADMIN , userInfo );
                }else {
                    session.setAttribute(ConstantInterface.AVERAGEUSER , userInfo );
                }
            }
        }
    }

    @ResponseBody
    @RequestMapping("/regUserInfo")
    public Object regUserInfo(UserInfo userInfo){
        start();
        Integer i = userService.insertSelective(userInfo, null);
        if (i > 0){
            put("status", "y");
            return end();
        }else {
            put("status", "n");
            return end();
        }
    }


    /**
     * 判断用户是否拥有该系统
     * @param sysUuid
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/flagAverageuserReg")
    public Object flagAverageuserReg(String sysUuid, HttpSession session){
        UserInfo userInfo = SessionUtil.getAdminUserInfo(session);
        if (userInfo == null){
            return "noUser";
        }
        UserSystem userSystem = userSystemService.flagAverageuserReg(sysUuid, userInfo.getId());
        userInfo.setSysUuid(sysUuid);
        SessionUtil.setSessionUserInfo(session, userInfo);
        if (userSystem == null){
            return "noSystem";
        }
        return userSystem.getAudit();
    }
}
