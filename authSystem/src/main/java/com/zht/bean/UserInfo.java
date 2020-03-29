package com.zht.bean;

import java.util.Date;

public class UserInfo {
    private Integer id;

    private String uuid;

    private Date createtime;

    private String createTime;

    private String loginName;

    private String userName;

    private String role;

    private String password;

    private String img;

    private String sysUuid;
    /**
     * 组合的角色名称
     */
    private String roleNames;

    /**
     * 组合的角色id
     */
    private Integer[] roleIds;

    /**
     * 注册系统uuids--string-s
     */
    private String sysRegUuidString;

    private String menuSysUuid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
        if (this.role.equals("")){
            this.role = null;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getSysUuid() {
        return sysUuid;
    }

    public void setSysUuid(String sysUuid) {
        this.sysUuid = sysUuid;
    }

    public String getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(String roleNames) {
        this.roleNames = roleNames;
    }

    public Integer[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Integer[] roleIds) {
        this.roleIds = roleIds;
    }

    public String getSysRegUuidString() {
        return sysRegUuidString;
    }

    public void setSysRegUuidString(String sysRegUuidString) {
        this.sysRegUuidString = sysRegUuidString;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


}