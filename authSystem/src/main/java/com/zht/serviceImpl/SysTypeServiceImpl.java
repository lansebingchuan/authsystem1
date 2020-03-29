package com.zht.serviceImpl;

import com.zht.bean.SysType;
import com.zht.dao.SysTypeMapper;
import com.zht.service.SysTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author zht
 * @create 2019-12-07 18:27
 */
@Service
public class SysTypeServiceImpl implements SysTypeService {

    @Autowired
    SysTypeMapper sysTypeMapper;

    @Override
    public List<SysType> listAllSysType() {
        return sysTypeMapper.listAllSysType();
    }
}
