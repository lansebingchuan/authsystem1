package com.zht.dao;

import com.zht.bean.LeaveMessage;

public interface LeaveMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LeaveMessage record);

    int insertSelective(LeaveMessage record);

    LeaveMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LeaveMessage record);

    int updateByPrimaryKey(LeaveMessage record);
}