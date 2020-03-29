package com.zht.serviceImpl;

import com.zht.bean.Permission;
import com.zht.bean.UserInfo;
import com.zht.dao.PermissionMapper;
import com.zht.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zht
 * @create 2019-12-08 22:30
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public int insertSelective(Permission permission) {
        return permissionMapper.insertSelective(permission);
    }

    @Override
    public List<Permission> listAllPermissionBySys(UserInfo userInfo) {
        return permissionMapper.listAllPermissionBySys(userInfo.getSysUuid());
    }

    @Override
    public int removeSingleSysPermission(Integer permissionId) {
        return permissionMapper.removeSingleSysPermission(permissionId);
    }

    @Override
    public Permission selectPermissionSingleById(Integer permissionId) {
        return permissionMapper.selectPermissionSingleById(permissionId);
    }

    @Override
    public Integer updateByPrimaryKeySelective(Permission permission) {
        return permissionMapper.updateByPrimaryKeySelective(permission);
    }
}
