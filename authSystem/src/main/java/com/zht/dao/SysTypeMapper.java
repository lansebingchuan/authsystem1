package com.zht.dao;

import com.zht.bean.SysType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysType record);

    int insertSelective(SysType record);

    SysType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysType record);

    int updateByPrimaryKey(SysType record);

    List<SysType> listAllSysType();
}