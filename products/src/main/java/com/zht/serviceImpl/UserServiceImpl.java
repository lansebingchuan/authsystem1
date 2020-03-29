package com.zht.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zht.base.AbstractCrudService;
import com.zht.bean.User;
import com.zht.constant.ConstantInterface;
import com.zht.dao.UserMapper;
import com.zht.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class UserServiceImpl extends AbstractCrudService<UserMapper, User> implements UserService{

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserByLoginNameAndPwd(String loginName, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("loginName", loginName);
        queryWrapper.eq("password", password);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public Integer updateUser(User user, User userSession) {
        if (user.getUserName() != null){
            userSession.setUserName(user.getUserName());
        }
        if (user.getIcon() != null){
            userSession.setIcon(ConstantInterface.FILEPATH+ConstantInterface.HEAD+ File.separator +user.getIcon());
        }
        return userMapper.updateById(userSession);
    }
}
