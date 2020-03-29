package com.zht.controller;

import com.zht.bean.*;
import com.zht.service.*;
import com.zht.util.constant.ConstantInterface;
import com.zht.util.controller.BaseController;
import com.zht.util.controller.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author zht
 * @create 2019-12-05 22:49
 */
@Controller
public class SystemController extends BaseController {

    private final SystemRegService systemRegService;

    private final SysTypeService sysTypeService;

    private final RoleService roleService;

    private final PermissionService permissionService;

    private final RolePermissionService rolePermissionService;

    private final UserSystemService userSystemService;

    private final UserinfoRoleService userinfoRoleService;

    @Autowired
    public SystemController(SystemRegService systemRegService, SysTypeService sysTypeService, RoleService roleService, PermissionService permissionService, RolePermissionService rolePermissionService, UserSystemService userSystemService, UserinfoRoleService userinfoRoleService) {
        this.systemRegService = systemRegService;
        this.sysTypeService = sysTypeService;
        this.roleService = roleService;
        this.permissionService = permissionService;
        this.rolePermissionService = rolePermissionService;
        this.userSystemService = userSystemService;
        this.userinfoRoleService = userinfoRoleService;
    }

    /**
     * 判断用户是否注册系统
     * @return
     */
    @RequestMapping("/flageUserHasSystem")
    @ResponseBody
    public Object flageUserHasSystem(HttpSession session){
        this.start();
        UserInfo userInfo = SessionUtil.getAdminUserInfo(session);
        if (userInfo != null){
            Object o = systemRegService.flageUserHasSystem(userInfo);
//            List<UserinfoRole> roleList = userinfoRoleService.ListUserRole(userInfo);
//            if (o != null && roleList!= null && roleList.size() > 0){
            if (o != null){
                flage(true);
                return end();
            }
        }
        if (userInfo == null){
            put("userInfo", "N");
        }
        flage(false);
        return end();
    }

    /**
     * 添加系统页面
     * @return
     */
    @RequestMapping("/addSystemPage")
    public String addSystemPage(){
        return "system/addSystem";
    }

    /**
     * 添加多个添加的系统角色
     * @return
     */
    @RequestMapping("/addSelectSysRole")
    @ResponseBody
    public Object addSysRole(Role role, HttpSession session){
        start();
        UserInfo userInfo = SessionUtil.getAdminUserInfo(session);
        int i  = roleService.addSelectSysRole(role , userInfo);
        if (i > 0){
            flage(true);
        }else {
            flage(false);
        }
        return end();
    }

    /**
     * 添加系统角色页面
     * @return
     */
    @RequestMapping("/addRolePage")
    public String addRolePage(){
        return "system/addRolePage";
    }
    /**
     * 添加系统授权路径页面
     * @return view
     */
    @RequestMapping("/addSysPermissionPage")
    public String addSysPermissionPage(){
        return "system/addSysPermission";
    }

    /**
     * 添加系统授权路径页面
     * @return view
     */
    @RequestMapping("/addRolePermissionPage")
    public String addRolePermissionPage(){
        return "system/addRolePermission";
    }

    /**
     * 获取这个系统的全部的权限信息，角色信息，角色权限信息
     * @return Object
     */
    @RequestMapping("/listAllRoleAndPerAndRp")
    @ResponseBody
    public Object listAllRoleAndPerAndRp(HttpSession session){
        start();
        UserInfo userInfo = SessionUtil.getAdminUserInfo(session);
        List<Permission> permissionList = permissionService.listAllPermissionBySys(userInfo);
        put("permissionList", permissionList);
        List<Role> roleList = roleService.listAllRolesBySysUuid(userInfo);
        put("roleList", roleList);
        List<RolePermission> rolePerList = rolePermissionService.listAllRolePerBySysUuid(userInfo);
        put("rolePerList", rolePerList);
        flage(true);
        return end();
    }

    /**
     * 异步添加一个角色权限信息
     *
     * @return Object
     */
    @RequestMapping("/addRolePermissionSingle")
    @ResponseBody
    public Object addRolePermissionSingle(RolePermission rolePermission, HttpSession session){
        this.start();
        UserInfo userInfo = SessionUtil.getAdminUserInfo(session);
        if (userInfo != null){
            rolePermission.setSysUuid(userInfo.getSysUuid());
            int i = rolePermissionService.insertSelective(rolePermission);
            if (i > 0){
                flage(true);
            }else {
                flage(false);
            }
        }
        return end();
    }

    /**
     * 异步添加一个角色权限信息
     * @return Object
     */
    @RequestMapping("/delRolePermissionSingle")
    @ResponseBody
    public Object delRolePermissionSingle(RolePermission rolePermission ,HttpSession session){
        start();
        UserInfo userInfo = SessionUtil.getAdminUserInfo(session);
        if (userInfo != null){
            rolePermission.setSysUuid(userInfo.getSysUuid());
            int i = rolePermissionService.delRolePermissionSingle(rolePermission);
            flage(true);
        }
        return end();
    }

    /**
     * 获取这个系统的全部的授权路径
     * @return Object
     */
    @RequestMapping("/listAllPermission")
    @ResponseBody
    public Object listAllPermission(HttpSession session){
        start();
        UserInfo userInfo = SessionUtil.getAdminUserInfo(session);
        //添加一个授权路径
        List<Permission> listAllPermission = permissionService.listAllPermissionBySys(userInfo);
        if (listAllPermission == null || listAllPermission.size() == 0){
            flage(false);
        }else {
            put("listAllPermission", listAllPermission);
            flage(true);
        }
        return end();
    }

    /**
     * 添加系统授权路径,单个
     * @return Object
     */
    @RequestMapping("/addSingleSysPermission")
    @ResponseBody
    public Object addSingleSysPermission(Permission permission, HttpSession session){
        start();
        UserInfo userInfo = SessionUtil.getAdminUserInfo(session);
        if (userInfo != null){
            permission.setSysUuid(userInfo.getSysUuid());
            //添加一个授权路径
            int i = permissionService.insertSelective(permission);
            if (i > 0){
                flage(true);
            }else {
                flage(false);
            }
        }
        return end();
    }
    /**
     * 移除系统授权路径,单个
     * @return Object
     */
    @RequestMapping("/removeSingleSysPermission")
    @ResponseBody
    public Object removeSingleSysPermission(Integer permissionId, HttpSession session){
        start();
        UserInfo userInfo = SessionUtil.getAdminUserInfo(session);
        //移除一个授权路径
        int i = permissionService.removeSingleSysPermission(permissionId);
        if (i > 0){
            flage(true);
        }else {
            flage(false);
        }
        return end();
    }

    /**
     * 获取系统授权路径,单个
     * @return Object
     */
    @RequestMapping("/selectPermissionSingle")
    @ResponseBody
    public Object selectPermissionSingle(Integer permissionId, HttpSession session){
        start();
        UserInfo userInfo = SessionUtil.getAdminUserInfo(session);
        //移除一个授权路径
        Permission permission = permissionService.selectPermissionSingleById(permissionId);
        if (permission != null){
            flage(true);
            put("permission", permission);
        }else {
            flage(false);
        }
        return end();
    }
    /**
     * 移除系统授权路径,单个
     * @return Object
     */
    @RequestMapping("/updatePermissionSingle")
    @ResponseBody
    public Object updatePermissionSingle(Permission permission, HttpSession session){
        start();
        UserInfo userInfo = SessionUtil.getAdminUserInfo(session);
        if(userInfo != null){
            permission.setSysUuid(userInfo.getSysUuid());
            Integer integer = permissionService.updateByPrimaryKeySelective(permission);
            if (integer > 0){
                flage(true);
            }else {
                flage(false);
            }
        }
        return end();
    }

    /**
     * 添加系统页面
     * @return Object
     */
    @RequestMapping("/listAllSysType")
    @ResponseBody
    public Object listAllSysType(){
        start();
        List<SysType> sysTypeList = sysTypeService.listAllSysType();
        put("sysTypeList", sysTypeList);
        flage(true);
        return end();
    }

    /**
     * 添加系统页面
     * @return Object
     */
    @RequestMapping(value = "/addSystem")
    @ResponseBody
    public Object addSystem(SystemReg systemReg , HttpSession session) throws IOException {
        start();
        UserInfo userInfo = SessionUtil.getAdminUserInfo(session);
        if (userInfo == null){
            flage(false);
            put("user", "n");
            return end();
        }
        systemReg.setUserId(userInfo.getId());
        systemReg.setSysIconPath(ConstantInterface.FILEPATH+systemReg.getSysUuid()+File.separator+systemReg.getFileName());
        userInfo.setSysUuid(systemReg.getSysUuid());
        //把系统uuid保存在session
        session.setAttribute(ConstantInterface.ADMIN, userInfo);
        //增加系统信息
        int i = systemRegService.insertSelective(systemReg, userInfo);
        if (i > 0){
            flage(true);
            put("sysUuid", systemReg.getSysUuid());
        }else {
            flage(false);
        }
        return end();
    }

    /**
     * 系统设置完成，对管理员增加默认角色，以及注册为默认拥有系统
     */
    @RequestMapping("/addUserDefaultRole")
    @ResponseBody
    public void addUserDefaultRole(HttpSession session){
        UserInfo userInfo = SessionUtil.getAdminUserInfo(session);
        systemRegService.addUserDefaultRole(userInfo);
    }
}
