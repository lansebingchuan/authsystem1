package com.zht.service;

import com.zht.bean.RolePermission;
import com.zht.bean.UserInfo;

import java.util.List;

/**
 * @author zht
 * @create 2019-12-10 21:18
 */
public interface RolePermissionService {

    //获取所有的系统角色权限信息
    List<RolePermission> listAllRolePerBySysUuid(UserInfo userInfo);

    int insertSelective(RolePermission rolePermission);

    int delRolePermissionSingle(RolePermission rolePermission);
}
