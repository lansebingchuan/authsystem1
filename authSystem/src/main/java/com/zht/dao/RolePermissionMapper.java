package com.zht.dao;

import com.zht.bean.RolePermission;
import com.zht.bean.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Repository
public interface RolePermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RolePermission record);

    int insertSelective(RolePermission rolePermission);

    RolePermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RolePermission record);

    int updateByPrimaryKey(RolePermission record);

    List<RolePermission> listAllRolePerBySysUuid(String sysUuid);

    int delRolePermissionSingle(RolePermission rolePermission);

    List<RolePermission> getRolePerByRoleId(@Param("roleId") Integer roleId,@Param("sysUuid") String sysUuid);
}