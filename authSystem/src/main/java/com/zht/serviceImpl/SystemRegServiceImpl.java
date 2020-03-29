package com.zht.serviceImpl;

import com.zht.bean.*;
import com.zht.dao.RoleMapper;
import com.zht.dao.SystemRegMapper;
import com.zht.dao.UserSystemMapper;
import com.zht.dao.UserinfoRoleMapper;
import com.zht.service.SystemRegService;
import com.zht.util.constant.ConstantInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zht
 * @create 2019-11-28 21:40
 */
@Service
public class SystemRegServiceImpl implements SystemRegService {

    final SystemRegMapper systemRegMapper;

    final UserSystemMapper userSystemMapper;

    final RoleMapper roleMapper;

    final UserinfoRoleMapper userinfoRoleMapper;

    @Autowired
    public SystemRegServiceImpl(SystemRegMapper systemRegMapper, UserSystemMapper userSystemMapper, RoleMapper roleMapper, UserinfoRoleMapper userinfoRoleMapper) {
        this.systemRegMapper = systemRegMapper;
        this.userSystemMapper = userSystemMapper;
        this.roleMapper = roleMapper;
        this.userinfoRoleMapper = userinfoRoleMapper;
    }


    @Override
    public List<SystemReg> listSystemRegList() {
        return systemRegMapper.listSystemRegList();
    }

    @Override
    public int insertSelective(SystemReg systemReg, UserInfo userInfo) {
        int i = systemRegMapper.insertSelective(systemReg);
        return i;
    }

    @Override
    public void addUserDefaultRole(UserInfo userInfo) {
        //增加为能登录系统的人员
        UserSystem userSystem = new UserSystem();
        userSystem.setUserId(userInfo.getId());
        userSystem.setSysUuid(userInfo.getSysUuid());
        userSystem.setAudit(1);
        userSystemMapper.insertSelective(userSystem);
        //目标系统设置为全管理员
        List<Role> roleList = roleMapper.getRoleListBySysUuid(userInfo.getSysUuid());
        if (roleList != null){
            UserinfoRole userinfoRole = null;
            //设置默认角色
            for (Role role: roleList){
                userinfoRole = new UserinfoRole(userInfo.getId(), role.getId());
                userinfoRoleMapper.insertSelective(userinfoRole);
            }
        }
        //如果是注册其他系统，添加本系统的默认角色信息
        if (!ConstantInterface.SYSTEMDEFULTUUID.equals(userInfo.getSysUuid())){
            userSystem = new UserSystem();
            userSystem.setUserId(userInfo.getId());
            userSystem.setSysUuid(ConstantInterface.SYSTEMDEFULTUUID);
            userSystem.setAudit(1);
            userSystemMapper.insertSelective(userSystem);
            List<Role> roleListDefault = roleMapper.getRoleListBySysUuid(ConstantInterface.SYSTEMDEFULTUUID);
            if (roleListDefault != null){
                UserinfoRole userinfoRole = null;
                //设置默认角色
                for (Role role: roleListDefault){

                    userinfoRole = new UserinfoRole(userInfo.getId(), role.getId());
                    userinfoRoleMapper.insertSelective(userinfoRole);
                }
            }
        }
    }

    /**
     * 判断用户是否注册
     * @param userInfo
     * @return
     */
    @Override
    public Object flageUserHasSystem(UserInfo userInfo) {
        return userSystemMapper.flageUserHasSystem(userInfo.getId());
    }
}
