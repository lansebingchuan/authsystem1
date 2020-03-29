package com.zht.serviceImpl;

import com.zht.bean.UserInfo;
import com.zht.bean.UserinfoRole;
import com.zht.dao.UserinfoRoleMapper;
import com.zht.service.UserinfoRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserinfoRoleServiceImpl implements UserinfoRoleService {

    @Autowired
    UserinfoRoleMapper userinfoRoleMapper;


    @Override
    public List<UserinfoRole> ListUserRole(UserInfo userInfo) {
        return userinfoRoleMapper.ListUserRole(userInfo.getId());
    }
}
