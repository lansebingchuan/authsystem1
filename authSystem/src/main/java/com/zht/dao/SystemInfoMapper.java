package com.zht.dao;

import com.zht.bean.SystemInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SystemInfo record);

    int insertSelective(SystemInfo record);

    SystemInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SystemInfo record);

    int updateByPrimaryKey(SystemInfo record);
}