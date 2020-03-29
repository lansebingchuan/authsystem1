package com.zht.dao;

import com.zht.bean.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
	
	 /**
     * 根据用户名密码登录
     * @param loginName
     * @param password
     * @param role
      * @return
     */
    List<UserInfo> getUserByPassword(@Param("loginName") String loginName, @Param("password") String password, @Param("role")String role);
    /**
     * 根据uuid查用户登录
     * @param loginName
     * @param password
     * @param uuid
     * @return
     */
    UserInfo getUserByUuid(String loginName, String password, String uuid);

    /**
     * 管理的系统人数
     * @param sysUuid
     * @param userInfo
     * @return
     */
    Integer countUserSysManager(@Param("sysUuid") String sysUuid, @Param("userInfo") UserInfo userInfo);

    /**
     * 分页获取系统人员
     * @param startIndex
     * @param pageSize
     * @param sysUuid
     * @param userInfo
     * @return
     */
    List<UserInfo> pageUserSysManager(@Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize,
                                      @Param("sysUuid") String sysUuid,@Param("userInfo")  UserInfo userInfo);

    UserInfo judgeLoginName(String loginName);

    Integer delUserByIds(@Param("listIds") List<Integer> listIds);
}