package com.zht.dao;

import com.zht.bean.UserinfoRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserinfoRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserinfoRole record);

    int insertSelective(UserinfoRole record);

    UserinfoRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserinfoRole record);

    int updateByPrimaryKey(UserinfoRole record);

    //根据user id 删除
    void deleteByUserId(@Param("userId") Integer userId, @Param("sysUuid") String sysUuid);

    //增加用户角色
    Integer addUserRoles(@Param("sysUuid") String sysUuid, @Param("userId") Integer userId,@Param("listRoleIds") List<Integer> listRoleIds);

    List<UserinfoRole> ListUserRole(Integer userId);
}