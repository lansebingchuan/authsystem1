package com.zht.serviceImpl;

import com.zht.bean.Role;
import com.zht.bean.RolePermission;
import com.zht.bean.UserInfo;
import com.zht.dao.RoleMapper;
import com.zht.dao.RolePermissionMapper;
import com.zht.dao.UserinfoRoleMapper;
import com.zht.service.RoleService;
import com.zht.util.CommonUtil;
import com.zht.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zht
 * @create 2019-12-08 16:22
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    UserinfoRoleMapper userinfoRoleMapper;

    @Autowired
    RolePermissionMapper rolePermissionMapper;

    @Override
    public int addSelectSysRole(Role role, UserInfo userInfo) {
        String[] roleNames = role.getRoleNames();
        if (CommonUtil.flageArrayIsNotNull(roleNames)) {
            List<Role> roleList = new ArrayList<>();
            Role role1 = null;
            for (String roleName : roleNames) {
                role1 = new Role();
                role1.setName(roleName);
                role1.setSysUuid(userInfo.getSysUuid());
                roleList.add(role1);
            }
            return roleMapper.addSelectSysRole(roleList);
        }
        return 1;
    }

    @Override
    public List<Role> listAllRolesBySysUuid(UserInfo userInfo) {
        return roleMapper.listAllRolesBySysUuid(userInfo.getSysUuid());
    }

    @Override
    public Map<String, List<Role>> listUserRole(UserInfo userInfo) {
        List<Role> roleAll = roleMapper.listAllRolesBySysUuid(userInfo.getSysUuid());
        Map<Integer , Role> roleMap = new HashMap<>();
        for (Role role:roleAll){
            roleMap.put(role.getId(), role);
        }
        List<Role>  listUserRole = roleMapper.listUserRole(userInfo);
        Map<String , List<Role>> map = new HashMap<>();
        for (Role role:listUserRole){
            Integer id = role.getId();
            Role role1 = roleMap.get(id);
            roleAll.remove(role1);
        }
        map.put("roleAll", roleAll);
        map.put("listUserRole", listUserRole);
        return map;
    }

    @Override
    public Integer updateUserRole(UserInfo userInfo) {
        Integer[] roleIds = userInfo.getRoleIds();
        List<Integer> listRoleIds = new ArrayList<>();
        userinfoRoleMapper.deleteByUserId(userInfo.getId());
        int i = 0;
        if (roleIds != null){
            for (Integer roleId: roleIds){
                listRoleIds.add(roleId);
            }
            i = userinfoRoleMapper.addUserRoles(userInfo.getId() , listRoleIds);
        }
        return i;
    }

    @Override
    public Page listRoleManagerPage(Integer pagePoint, Integer pageSize, UserInfo userInfo) {
        Page page = new Page(pagePoint, pageSize);
        Integer count = roleMapper.countRoleSize(userInfo.getSysUuid());
        page.setTotalSize(count);
        List<Role> roleList = roleMapper.pageQueryRole(page.getStartIndex() , pageSize, userInfo.getSysUuid());
        page.setLists(roleList);
        return page;
    }

    @Override
    public Integer deleteByPrimaryKey(Integer roleId, String sysUuid) {
        List<RolePermission> rolePermissionList = rolePermissionMapper.getRolePerByRoleId(roleId, sysUuid);
        if (rolePermissionList != null && rolePermissionList.size() > 0){
            return 0;
        }else {
            return roleMapper.deleteByPrimaryKey(roleId);
        }
    }

    @Override
    public int updateByPrimaryKeySelective(Role record) {
        return roleMapper.updateByPrimaryKeySelective(record);
    }
}
