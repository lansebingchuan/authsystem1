package com.zht.service;

import com.zht.bean.Permission;
import com.zht.bean.UserInfo;

import java.util.List;

/**
 * @author zht
 * @create 2019-12-08 22:30
 */
public interface PermissionService {

    int insertSelective(Permission record);

    //获取系统下的所有的授权信息
    List<Permission> listAllPermissionBySys(UserInfo userInfo);
    //移除一个授权路径
    int removeSingleSysPermission(Integer permissionId);
    //选择一个授权
    Permission selectPermissionSingleById(Integer permissionId);

    //更新权限单个
    Integer updateByPrimaryKeySelective(Permission permission);
}
