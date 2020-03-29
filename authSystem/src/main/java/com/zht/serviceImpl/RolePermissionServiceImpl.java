package com.zht.serviceImpl;

import com.zht.bean.RolePermission;
import com.zht.bean.UserInfo;
import com.zht.dao.RolePermissionMapper;
import com.zht.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zht
 * @create 2019-12-10 21:18
 */
@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    RolePermissionMapper rolePermissionMapper;

    @Override
    public List<RolePermission> listAllRolePerBySysUuid(UserInfo userInfo) {
        return rolePermissionMapper.listAllRolePerBySysUuid(userInfo.getSysUuid());
    }

    @Override
    public int insertSelective(RolePermission rolePermission) {
        return rolePermissionMapper.insertSelective(rolePermission);
    }

    @Override
    public int delRolePermissionSingle(RolePermission rolePermission) {
        return rolePermissionMapper.delRolePermissionSingle(rolePermission);
    }
}
