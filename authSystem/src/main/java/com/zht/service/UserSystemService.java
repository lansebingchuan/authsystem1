package com.zht.service;

import com.zht.bean.LayuiTable;
import com.zht.bean.Permission;
import com.zht.bean.UserInfo;
import com.zht.bean.UserSystem;

import java.util.List;

/**
 * @author zht
 * @create 2019-12-11 22:30
 */
public interface UserSystemService {
    /**
     * //用户id  系统id
     * @param userId
     * @param sysUuid
     * @return
     */
    UserSystem getUserSystemByUserIdAndSysId(Integer userId, String sysUuid);

    List<Permission> getUserRolePermissionByIdAndSysUuid(Integer userId, String sysUuid);

    /**
     * 判断用户是否加入系统
     */
    UserSystem flageUserHasSystem(UserInfo userInfo);

    /**
     * 分页查询用户所加入的系统
     * @param layuiTable
     * @param adminUserInfo
     */
    void pageAgUserSystem(LayuiTable layuiTable, UserInfo adminUserInfo);

    UserSystem flagAverageuserReg(String sysUuid, Integer id);

    int deleteByPrimaryKey(Integer id);

    /**
     * 用户新加入系统
     * @param sysRegUuid
     * @param userInfo
     * @return
     */
    boolean addNewSystem(String sysRegUuid, UserInfo userInfo);

    void listUserSystemAudit(UserInfo sessionUser, LayuiTable layuiTable);

    int updateByPrimaryKeySelective(UserSystem record);
}
