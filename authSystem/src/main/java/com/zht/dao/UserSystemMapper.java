package com.zht.dao;

import com.zht.bean.LayuiTable;
import com.zht.bean.Permission;
import com.zht.bean.SystemReg;
import com.zht.bean.UserSystem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSystemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserSystem record);

    int insertSelective(UserSystem record);

    UserSystem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserSystem record);

    int updateByPrimaryKey(UserSystem record);

    UserSystem getUserSystemByUserIdAndSysId(@Param("userId") Integer userId, @Param("sysUuid")String sysUuid);

    List<Permission> getUserRolePermissionByIdAndSysUuid(@Param("userId") Integer userId, @Param("sysUuid")String sysUuid);

    UserSystem flageUserHasSystem(Integer userId);


    Integer countAgUserSystem(Integer userId);

    List<SystemReg> pageAgUserSystem(@Param("layuiTable") LayuiTable layuiTable);

    UserSystem selectUserSysBySysUuidAndUserId(@Param("sysUuid") String sysUuid,@Param("userId") Integer userId);

    /**
     * 需要审核的数量
     * @param sysUuid
     * @return
     */
    Integer countListUserSystemAudit(String sysUuid);

    /**
     * 审核的list
     * @param sysUuid
     * @param layuiTable
     * @return
     */
    List<SystemReg> listUserSystemAudit(@Param("sysUuid") String sysUuid,@Param("layuiTable") LayuiTable layuiTable);
}