package com.zht.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zht.bean.User;

public interface UserService  extends IService<User> {


    User getUserByLoginNameAndPwd(String loginName, String password);

    Integer updateUser(User user, User userSession);
}
