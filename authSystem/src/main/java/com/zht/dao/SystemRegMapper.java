package com.zht.dao;

import com.zht.bean.SystemReg;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemRegMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SystemReg record);

    int insertSelective(SystemReg record);

    SystemReg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SystemReg record);

    int updateByPrimaryKey(SystemReg record);

    List<SystemReg> listSystemRegList();

    SystemReg flageUserHasSystem(Integer userId);
}