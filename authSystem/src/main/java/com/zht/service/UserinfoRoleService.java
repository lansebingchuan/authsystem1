package com.zht.service;

import com.zht.bean.UserInfo;
import com.zht.bean.UserinfoRole;

import java.util.List;

public interface UserinfoRoleService {

    List<UserinfoRole> ListUserRole(UserInfo userInfo);
}
