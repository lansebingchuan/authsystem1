package com.zht.service;

import com.zht.bean.Role;
import com.zht.bean.UserInfo;
import com.zht.util.Page;

import java.util.List;
import java.util.Map;

/**
 * @author zht
 * @create 2019-12-08 16:22
 */
public interface RoleService {

    int addSelectSysRole(Role role, UserInfo userInfo);

    //获取所有的权限信息
    List<Role> listAllRolesBySysUuid(UserInfo userInfo);

    Map<String, List<Role>> listUserRole(UserInfo userInfo);

    //更新用户角色
    Integer updateUserRole(UserInfo userInfo);

    Page listRoleManagerPage(Integer pagePoint, Integer pageSize, UserInfo userInfo);

    Integer deleteByPrimaryKey(Integer roleId, String sysUuid);

    int updateByPrimaryKeySelective(Role record);
}
