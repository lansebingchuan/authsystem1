package com.zht.dao;

import com.zht.bean.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionMapper {

    int insert(Permission permission);

    int insertSelective(Permission permission);

    int updateByPrimaryKeySelective(Permission permission);

    int updateByPrimaryKey(Permission permission);

    List<Permission> listAllPermissionBySys(String sysUuid);

    int removeSingleSysPermission(Integer permissionId);

    Permission selectPermissionSingleById(Integer permissionId);
}