package com.zht.controller;

import com.zht.bean.Role;
import com.zht.bean.UserInfo;
import com.zht.service.RoleService;
import com.zht.util.Page;
import com.zht.util.controller.BaseController;
import com.zht.util.controller.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author zht
 * @create 2019-12-22 18:55
 */
@Controller
public class RoleController extends BaseController {

    final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping("/roleManage")
    public String roleManager(){
        return "role/roleManage";
    }


    @RequestMapping("/editRolePage")
    public String editRolePage(){
        return "role/editRole";
    }

    /**
     * 分页获取 角色信息
     * @param session session
     * @param pagePoint 第几页
     * @param pageSize 每页数量
     * @param userInfo 当前用户
     * @return Object
     */
    @RequestMapping("/listRoleManager")
    @ResponseBody
    public Object listRoleManager(HttpSession session, Integer pagePoint , Integer pageSize, UserInfo userInfo){
        start();
        UserInfo sessionUser = SessionUtil.getAdminUserInfo(session);
        if (sessionUser != null){
            userInfo.setSysUuid(sessionUser.getSysUuid());
            Page page = roleService.listRoleManagerPage(pagePoint , pageSize , userInfo);
            flage(true);
            put("page", page);
        }
        return end();
    }

    /**
     * 删除角色
     * @param session session
     * @return Object
     */
    @RequestMapping("/deleteRoleById")
    @ResponseBody
    public Object deleteRoleById(HttpSession session, Integer roleId){
        start();
        UserInfo sessionUser = SessionUtil.getAdminUserInfo(session);
        if (sessionUser != null){
            Integer i = roleService.deleteByPrimaryKey(roleId, sessionUser.getSysUuid());
            if (i > 0){
                flage(true);
            }else {
                flage(false);
                message("此角色正在被使用！");
            }
        }
        return end();
    }

    /**
     * 更新角色
     * @param session session
     * @return Object
     */
    @RequestMapping("/updateRoleSingle")
    @ResponseBody
    public Object updateRoleSingle(HttpSession session, Role role){
        start();
        UserInfo sessionUser = SessionUtil.getAdminUserInfo(session);
        if (sessionUser != null){
            int i = roleService.updateByPrimaryKeySelective(role);
            if (i > 0){
                flage(true);
            }else {
                flage(false);
                message("更新角色信息失败！");
            }
        }
        return end();
    }
}
