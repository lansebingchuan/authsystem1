package com.zht.service;

import com.zht.bean.SysType;

import java.util.List;

/**
 * @author zht
 * @create 2019-12-07 18:27
 */
public interface SysTypeService {
    //获取所有的项目类型
    List<SysType> listAllSysType();
}
