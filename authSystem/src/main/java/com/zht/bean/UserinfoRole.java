package com.zht.bean;

public class UserinfoRole {
    private Integer id;

    private Integer userid;

    private Integer roleid;

    private String sysUuid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getSysUuid() {
        return sysUuid;
    }

    public void setSysUuid(String sysUuid) {
        this.sysUuid = sysUuid;
    }

    public UserinfoRole() {
    }

    public UserinfoRole(Integer userid, Integer roleid, String sysUuid) {
        this.userid = userid;
        this.roleid = roleid;
        this.sysUuid = sysUuid;
    }
}