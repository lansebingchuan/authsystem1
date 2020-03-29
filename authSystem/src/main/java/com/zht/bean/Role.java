package com.zht.bean;

public class Role {
    private Integer id;

    private String name;

    private String sysUuid;


    private Integer userRoleId;

    private String[] roleNames;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSysUuid() {
        return sysUuid;
    }

    public void setSysUuid(String sysUuid) {
        this.sysUuid = sysUuid == null ? null : sysUuid.trim();
    }

    public String[] getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(String[] roleNames) {
        this.roleNames = roleNames;
    }

    public Integer getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }
}