package com.zht.serviceImpl;

import com.zht.bean.*;
import com.zht.dao.UserSystemMapper;
import com.zht.service.UserSystemService;
import com.zht.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zht
 * @create 2019-12-11 22:31
 */
@Service
public class UserSystemServiceImpl implements UserSystemService {

    @Autowired
    UserSystemMapper userSystemMapper;
    private UserSystem userSystem;

    @Override
    public UserSystem getUserSystemByUserIdAndSysId(Integer userId, String sysUuid) {
        return userSystemMapper.getUserSystemByUserIdAndSysId(userId, sysUuid);
    }

    @Override
    public List<Permission> getUserRolePermissionByIdAndSysUuid(Integer userId, String sysUuid) {
        List<Permission> permissionList = userSystemMapper.getUserRolePermissionByIdAndSysUuid(userId, sysUuid);
        if (permissionList != null){
            List<Permission> rootList = new ArrayList<>();
            Map<Integer , Permission> pidMap = new HashMap<>();
            for (Permission permission : permissionList){
                Integer pId = permission.getpId();
                if (pId.equals(0)){
                    rootList.add(permission);
                    pidMap.put(permission.getId(), permission);
                }else {
                    Permission permission1 = pidMap.get(pId);
                    if (permission1 != null){//存在父目录
                        List<Permission> children = permission1.getChildren();
                        if (children == null){
                            children = new ArrayList<>();
                        }
                        children.add(permission);
                        permission1.setChildren(children);
                        pidMap.put(pId, permission1);
                    }
                }
            }
            return  rootList;
        }
        return null;
    }

    @Override
    public UserSystem flageUserHasSystem(UserInfo userInfo) {
        return userSystemMapper.flageUserHasSystem( userInfo.getId());
    }

    @Override
    public void pageAgUserSystem(LayuiTable layuiTable, UserInfo adminUserInfo) {
        Integer count = userSystemMapper.countAgUserSystem(adminUserInfo.getId());
        layuiTable.setId(adminUserInfo.getId());
        List<SystemReg> userSystem =  userSystemMapper.pageAgUserSystem(layuiTable);
        layuiTable.setPagePage(count, userSystem);
    }

    @Override
    public UserSystem flagAverageuserReg(String sysUuid, Integer userId) {
        return userSystemMapper.selectUserSysBySysUuidAndUserId(sysUuid, userId);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userSystemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public boolean addNewSystem(String sysRegUuid, UserInfo userInfo) {
        int a = 0;
        if (sysRegUuid != null && !"".equals(sysRegUuid)){
            UserSystem userSystem;
            String[] strings = CommonUtil.formatStringAtToArray(sysRegUuid);
            for (String sysUuid: strings){
                userSystem = new UserSystem();
                //不为空表示以前注册过，那么更新最新的信息
                UserSystem userSystem1 = flagAverageuserReg(sysUuid, userInfo.getId());
                if (userSystem1 == null){
                    userSystem.setUserId(userInfo.getId());
                    userSystem.setSysUuid(sysUuid);
                    userSystem.setAudit(0);
                    a = userSystemMapper.insertSelective(userSystem);
                //重新设置状态为申请中
                }else {
                    userSystem1.setAudit(0);
                    a = updateByPrimaryKeySelective(userSystem1);
                }
            }
        }
        if (a != 0){
            return true;
        }
        return false;
    }

    @Override
    public void listUserSystemAudit(UserInfo sessionUser, LayuiTable layuiTable) {
        String sysUuid = sessionUser.getSysUuid();
        Integer count = userSystemMapper.countListUserSystemAudit(sysUuid);
        List<SystemReg> systemRegList =  userSystemMapper.listUserSystemAudit(sysUuid, layuiTable);
        layuiTable.setPagePage(count, systemRegList);
    }

    @Override
    public int updateByPrimaryKeySelective(UserSystem record) {
        return userSystemMapper.updateByPrimaryKeySelective(record);
    }


}
