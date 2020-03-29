package com.zht.service;

import com.zht.bean.UserInfo;
import com.zht.util.Page;

/**
 * @author zht
 * @create 2019-11-22 23:16
 */
public interface UserService {
    /**
     * 根据密码查用户登录
     * @param loginName
     * @param password
     * @param role
     * @return
     */
    UserInfo getUserByPassword(String loginName, String password, String role);

    /**
     * 根据uuid查用户登录
     * @param loginName
     * @param password
     * @param uuid
     * @return
     */
    UserInfo getUserByUuid(String loginName, String password, String uuid);

    /**
     * 管理员管理系统用户
     * @param pagePoint
     * @param pageSize
     * @param userInfo
     * @return
     */
    Page listUserSysManager(Integer pagePoint, Integer pageSize, UserInfo userInfo);

    /**
     * 增加新的系统用户
     * @param userInfo
     * @param sessionUser
     * @return
     */
    Integer insertSelective(UserInfo userInfo, UserInfo sessionUser);

    //检测是否注册过
    UserInfo judgeLoginName(String loginName);

    /**
     * 根据id  删除系统人员
     * @param ids
     * @return
     */
    Integer delUserByIds(Integer[] ids);

    /**
     * 通过id 获取 user
     * @param userId
     * @return
     */
    UserInfo getUserById(Integer userId);

    /**
     * 更新用户
     * @param userInfo
     * @return
     */
    Integer updateUser(UserInfo userInfo);

    /**
     * 更新用户的img
     * @param userInfo
     * @param adminUserInfo
     * @return
     */
    Integer updateUserInfo(UserInfo userInfo, UserInfo adminUserInfo);
}
