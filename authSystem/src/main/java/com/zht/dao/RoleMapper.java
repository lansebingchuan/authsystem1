package com.zht.dao;

import com.zht.bean.Role;
import com.zht.bean.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    int addSelectSysRole(@Param("roleList") List<Role> roleList);

    List<Role> listAllRolesBySysUuid(String sysUuid);

    List<Role> listUserRole(UserInfo userInfo);

    Integer countRoleSize(String sysUuid);

    List<Role> pageQueryRole(@Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize,
                             @Param("sysUuid") String sysUuid);

    List<Role> getRoleListBySysUuid(String sysUuid);
}