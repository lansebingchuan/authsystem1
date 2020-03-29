package com.zht.controller;

import com.zht.bean.LayuiTable;
import com.zht.bean.Role;
import com.zht.bean.UserInfo;
import com.zht.bean.UserSystem;
import com.zht.service.RoleService;
import com.zht.service.UserService;
import com.zht.service.UserSystemService;
import com.zht.util.Page;
import com.zht.util.controller.BaseController;
import com.zht.util.controller.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author zht
 * @create 2019-12-14 19:10
 */
@Controller
public class UserManageController extends BaseController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserSystemService userSystemService;
    /**
     * 添加系统页面
     * @return
     */
    @RequestMapping("/userManage")
    public String userManage(){
        return "/user/admin/userManage";
    }
    /**
     * 添加系统用户页面
     * @return
     */
    @RequestMapping("/addUserPage")
    public String addUser(){
        return "/user/admin/addUser";
    }

    /**
     * 添加系统用户页面
     * @return
     */
    @RequestMapping("/editUserPage")
    public String editUserPage(){
        return "/user/admin/editUser";
    }

    /**
     * 添加系统用户页面
     * @return
     */
    @RequestMapping("/editUserRolePage")
    public String editUserRolePage(){
        return "/user/admin/editUserRolePage";
    }

    /**
     * 异步获取所有的管理系统人员-分页
     * @return
     */
    @RequestMapping("/listUserSysManager")
    @ResponseBody
    public Object listUserSysManager(HttpSession session, Integer pagePoint , Integer pageSize, UserInfo userInfo){
        start();
        UserInfo sessionUser = SessionUtil.getAdminUserInfo(session);
        userInfo.setSysUuid(sessionUser.getSysUuid());
        Page page = userService.listUserSysManager(pagePoint , pageSize , userInfo);
        flage(true);
        put("page", page);
        return end();
    }

    /**
     * 异步获增加人员
     * @return
     */
    @RequestMapping("/addUser")
    @ResponseBody
    public Object toAddUser(HttpSession session, UserInfo userInfo){
        start();
        UserInfo sessionUser = SessionUtil.getAdminUserInfo(session);
        Integer i = userService.insertSelective(userInfo, sessionUser);
        if (i > 0){
            flage(true);
        }else {
            flage(false);
        }
        return end();
    }

    /**
     * 异步删除人员
     * @return
     */
    @RequestMapping("/deleteUserListByIds")
    @ResponseBody
    public Object deleteUserListByIds(HttpSession session, Integer[] ids){
        start();
        Integer i = userService.delUserByIds(ids);
        if (i > 0){
            flage(true);
        }else {
            flage(false);
        }
        return end();
    }

    /**
     * 异步检测是否被注册过 登录名
     * @return
     */
    @RequestMapping("/judgeLoginName")
    @ResponseBody
    public Object toAddUser(HttpSession session,String loginName){
        start();
        UserInfo u = userService.judgeLoginName(loginName);
        if (u ==  null){
            flage(true);
        }else {
            flage(false);
        }
        return end();
    }

    /**
     * 异步通过userID获取用户
     * @return
     */
    @RequestMapping("/getUserById")
    @ResponseBody
    public Object getUserById(HttpSession session,Integer userId){
        start();
        UserInfo userInfo = userService.getUserById(userId);
        if (userInfo ==  null){
            flage(false);
        }else {
            flage(true);
            put("user", userInfo);
        }
        return end();
    }

    /**
     * 异步更新user
     * @return
     */
    @RequestMapping("/updateUser")
    @ResponseBody
    public Object updateUser(HttpSession session,UserInfo userInfo){
        start();
        Integer i = userService.updateUser(userInfo);
        if (i >  0){
            flage(true);
        }else {
            flage(false);
        }
        return end();
    }

    /**
     * 异步获取user的拥有的角色和 所有的角色
     * map.put("roleAll", roleAll);
     * map.put("listUserRole", listUserRole);
     * @return
     */
    @RequestMapping("/listUserRole")
    @ResponseBody
    public Object listUserRole(HttpSession session,UserInfo userInfo){
        start();
        UserInfo sessionUser = SessionUtil.getAdminUserInfo(session);
        userInfo.setSysUuid(sessionUser.getSysUuid());
        Map<String, List<Role>> listUserRole = roleService.listUserRole(userInfo);
        put("listUserRole", listUserRole);
        flage(true);
        return end();
    }

    /**
     * 异步获取user的拥有的角色和 所有的角色
     * map.put("roleAll", roleAll);
     * map.put("listUserRole", listUserRole);
     * @return
     */
    @RequestMapping("/updateUserRole")
    @ResponseBody
    public Object updateUserRole(HttpSession session, UserInfo userInfo){
        start();
        UserInfo sessionUser = SessionUtil.getAdminUserInfo(session);
        userInfo.setSysUuid(sessionUser.getSysUuid());
        Integer i = roleService.updateUserRole(userInfo);
        if (i > 0){
            flage(true);
        }else {
            flage(false);
        }
        return end();
    }

    /**
     * 需要管理员审核加入系统的页面
     * @return
     */
    @RequestMapping("/userSystemAuditPage")
    public String userSystemAuditPage(){
        return "/user/admin/userSystemAudit";
    }

    /**
     * 需要管理员审核加入系统的普通人员
     * @return
     */
    @ResponseBody
    @RequestMapping("/listUserSystemAudit")
    public Object listUserSystemAudit(HttpSession session, LayuiTable layuiTable){
        UserInfo sessionUser = SessionUtil.getAdminUserInfo(session);
        userSystemService.listUserSystemAudit(sessionUser, layuiTable);
        return layuiTable.getPagePage();
    }

    /**
     * 需要管理员审核加入系统的普通人员
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateAuditToThrough")
    public Object updateAuditToThrough(UserSystem userSystem){
        int i = userSystemService.updateByPrimaryKeySelective(userSystem);
        if (i > 0){
            return true;
        }
        return false;
    }



}
