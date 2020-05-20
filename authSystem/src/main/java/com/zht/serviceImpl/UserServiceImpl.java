package com.zht.serviceImpl;

import com.zht.bean.UserInfo;
import com.zht.bean.UserSystem;
import com.zht.dao.UserInfoMapper;
import com.zht.dao.UserSystemMapper;
import com.zht.service.UserService;
import com.zht.util.CommonUtil;
import com.zht.util.MD5Util;
import com.zht.util.Page;
import com.zht.util.constant.ConstantInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zht
 * @create 2019-11-22 23:21
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    UserSystemMapper userSystemMapper;

    @Override
    public UserInfo getUserByPassword(String loginName, String password, String role) {
        //转换为md5
        password = MD5Util.digest(password);
        List<UserInfo> userInfoList = userInfoMapper.getUserByPassword(loginName, password, role);
        if (userInfoList != null && userInfoList.size() >0){
            if (userInfoList.size() == 2){
                String sysUuid = userInfoList.get(0).getSysUuid();
                if (ConstantInterface.SYSTEMDEFULTUUID.equals(sysUuid)){

                    return userInfoList.get(1);
                }else {
                    //userInfoList.get(1).setRealSysUuid(userInfoList.get(0).getSysUuid());
                    return userInfoList.get(0);
                }
            }else {
                //userInfoList.get(0).setRealSysUuid(userInfoList.get(0).getSysUuid());
                return userInfoList.get(0);
            }
        }
        return null;
    }

    @Override
    public UserInfo getUserByUuid(String loginName, String password, String uuid) {
        password = MD5Util.digest(password);//转换为md5
        return userInfoMapper.getUserByUuid(loginName, password, uuid);
    }

    @Override
    public Page listUserSysManager(Integer pagePoint, Integer pageSize, UserInfo userInfo) {
        Page page = new Page(pagePoint, pageSize);
        Integer count = userInfoMapper.countUserSysManager(userInfo.getSysUuid(), userInfo);
        page.setTotalSize(count);
        Integer startIndex = page.getStartIndex();
        List<UserInfo> listUser = userInfoMapper.pageUserSysManager(startIndex , page.getPageSize() , userInfo.getSysUuid(), userInfo);
        page.setLists(listUser);
        return page;
    }

    @Override
    public Integer insertSelective(UserInfo userInfo, UserInfo sessionUser) {
        userInfo.setPassword(MD5Util.digest(userInfo.getPassword()));
        int i = userInfoMapper.insertSelective(userInfo);
        UserSystem userSystem = null;
        if (sessionUser != null && sessionUser.getSysUuid() != null){
            userSystem = new UserSystem();
            userSystem.setUserId(userInfo.getId());
            userSystem.setSysUuid(sessionUser.getSysUuid());
            userSystem.setAudit(0);
            userSystemMapper.insertSelective(userSystem);
        }
        String sysRegUuidString = userInfo.getSysRegUuidString();
        if (sysRegUuidString != null && !"".equals(sysRegUuidString)){
            String[] strings = CommonUtil.formatStringAtToArray(sysRegUuidString);
            for (String sysUuid: strings){
                userSystem = new UserSystem();
                userSystem.setUserId(userInfo.getId());
                userSystem.setSysUuid(sysUuid);
                userSystem.setAudit(0);
                userSystemMapper.insertSelective(userSystem);
            }
        }
        return i;
    }

    @Override
    public UserInfo judgeLoginName(String loginName) {
        UserInfo u = userInfoMapper.judgeLoginName(loginName);
        return u;
    }

    @Override
    public Integer delUserByIds(Integer[] ids) {
        List<Integer> listIds = new ArrayList<>();
        for (Integer id: ids){
            listIds.add(id);
        }
        return userInfoMapper.delUserByIds(listIds) ;
    }

    @Override
    public UserInfo getUserById(Integer userId) {
        return userInfoMapper.selectByPrimaryKey(userId);
    }

    @Override
    public Integer updateUser(UserInfo userInfo) {
        return userInfoMapper.updateByPrimaryKeySelective(userInfo);
    }

    @Override
    public Integer updateUserInfo(UserInfo userInfo, UserInfo adminUserInfo) {
        userInfo.setId(adminUserInfo.getId());
        if (userInfo.getImg() != null){
            userInfo.setImg(ConstantInterface.FILEPATH+userInfo.getSysUuid()+ "/"+userInfo.getImg());
            adminUserInfo.setImg(userInfo.getImg());
        }
        if (userInfo.getUserName() != null){
            adminUserInfo.setUserName(userInfo.getUserName());
        }
        return userInfoMapper.updateByPrimaryKeySelective(userInfo);
    }
}
