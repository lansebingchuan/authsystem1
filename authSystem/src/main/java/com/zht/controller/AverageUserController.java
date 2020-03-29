package com.zht.controller;

import com.zht.bean.LayuiTable;
import com.zht.bean.UserInfo;
import com.zht.service.SystemRegService;
import com.zht.service.UserService;
import com.zht.service.UserSystemService;
import com.zht.util.constant.ConstantInterface;
import com.zht.util.controller.BaseController;
import com.zht.util.controller.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author ZHT
 * @date now()
 */
@RequestMapping("/averageUser")
@Controller
public class AverageUserController extends BaseController {

    @Autowired
    UserService userService;

    @Autowired
    UserSystemService userSystemService;

    @Autowired
    SystemRegService systemRegService;

    @RequestMapping("/main")
    public String main(){
        return "/user/averageUser/admin";
    }

    @RequestMapping("/regSysSelect")
    public String regSysSelect(){
        return "system/regSysSelect";
    }

    @RequestMapping("/avgUserBasicInformation")
    public String avgUserBasicInformation(){
        return "/user/averageUser/avgUserBasicInformation";
    }

    @ResponseBody
    @RequestMapping("/updateUserInfo")
    public Object updateUserInfo(UserInfo userInfo, HttpSession session){
        start();
        UserInfo adminUserInfo = SessionUtil.getAdminUserInfo(session);
        Integer i = userService.updateUserInfo(userInfo, adminUserInfo);
        if (i > 0){
            flage(true);
            //更新session  user
            if (adminUserInfo.getRole().equals(ConstantInterface.ADMIN)){
                session.setAttribute(ConstantInterface.ADMIN, adminUserInfo);
            }else {
                session.setAttribute(ConstantInterface.AVERAGEUSER, adminUserInfo);
            }
        }else {
            flage(false);
        }
        return end();
    }

    @RequestMapping("/agUserSystemPage")
    public String agUserSystemPage(){
        return "/user/averageUser/agUserSystemPage";
    }

    @ResponseBody
    @RequestMapping("/listAgUserSystem")
    public Object listAgUserSystem(LayuiTable layuiTable, HttpSession session){
        UserInfo adminUserInfo = SessionUtil.getAdminUserInfo(session);
        userSystemService.pageAgUserSystem(layuiTable, adminUserInfo);
        return layuiTable.getPagePage();
    }

    /**
     * 解除普通用户与一个系统的关系
     * @param userSystemId
     * @return
     */
    @ResponseBody
    @RequestMapping("/cancelUserSys")
    public Object cancelUserSys(Integer userSystemId){
        int i = userSystemService.deleteByPrimaryKey(userSystemId);
        if (i > 0){
            return true;
        }
        return false;
    }


    /**
     * 普通用户自己新加入一个系统
     * @param sysRegUuid
     * @return
     */
    @ResponseBody
    @RequestMapping("/addNewSystem")
    public Object addNewSystem(String sysRegUuid, HttpSession session){
        UserInfo userInfo = SessionUtil.getAdminUserInfo(session);
        return userSystemService.addNewSystem(sysRegUuid, userInfo);
    }
}
