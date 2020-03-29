package com.zht.service;

import com.zht.bean.SystemReg;
import com.zht.bean.UserInfo;

import java.util.List;

/**
 * @author zht
 * @create 2019-11-28 21:40
 */
public interface SystemRegService {

    /**
     * 获取所有的系统详细信息
     * @return list
     */
    List<SystemReg> listSystemRegList();

    /**
     * 注册一个系统
     * @param systemReg 系统信息
     * @param userInfo 用户信息
     * @return i
     */
    int insertSelective(SystemReg systemReg, UserInfo userInfo);

    /**
     * 为管理员增加默认角色 与拥有系统
     * @param userInfo
     */
    void addUserDefaultRole(UserInfo userInfo);

    /**
     * 判断管理员是否注册系统
     * @param userInfo
     * @return UserSystem
     */
    Object flageUserHasSystem(UserInfo userInfo);
}
