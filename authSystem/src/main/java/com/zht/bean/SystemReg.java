package com.zht.bean;

import java.util.Date;

public class SystemReg {

    private Integer id;

    private Date createTime;

    private String sysName;

    private String sysUrl;

    private String sysUuid;

    private Integer userId;

    private Integer systemInfoId;

    private Integer sysTypeId;

    private String sysDescribed;

    private String sysTypeName;

    private String sysIconPath;

    private String fileName;

    private String userName;

    private Integer userSystemId;

    //注册系统状态
    private Integer audit;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName == null ? null : sysName.trim();
    }

    public String getSysUrl() {
        return sysUrl;
    }

    public void setSysUrl(String sysUrl) {
        this.sysUrl = sysUrl;
    }

    public String getSysUuid() {
        return sysUuid;
    }

    public void setSysUuid(String sysUuid) {
        this.sysUuid = sysUuid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSystemInfoId() {
        return systemInfoId;
    }

    public void setSystemInfoId(Integer systemInfoId) {
        this.systemInfoId = systemInfoId;
    }

    public Integer getSysTypeId() {
        return sysTypeId;
    }

    public void setSysTypeId(Integer sysTypeId) {
        this.sysTypeId = sysTypeId;
        if (sysTypeId == 1){
            this.sysTypeName = "个人网站";
        }else if (sysTypeId.equals(2)){
            this.sysTypeName = "商业网站";
        }else if (sysTypeId.equals(3)){
            this.sysTypeName = "政府网站";
        }else if (sysTypeId.equals(4)){
            this.sysTypeName = "教育网站";
        }else {
            this.sysTypeName = "暂无类型";
        }
    }

    public String getSysDescribed() {
        return sysDescribed;
    }

    public void setSysDescribed(String sysDescribed) {
        this.sysDescribed = sysDescribed;
    }

    public String getSysTypeName() {
        return sysTypeName;
    }

    public void setSysTypeName(String sysTypeName) {
        this.sysTypeName = sysTypeName;
    }

    public String getSysIconPath() {
        return sysIconPath;
    }

    public void setSysIconPath(String sysIconPath) {
        this.sysIconPath = sysIconPath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserSystemId() {
        return userSystemId;
    }

    public void setUserSystemId(Integer userSystemId) {
        this.userSystemId = userSystemId;
    }

    public Integer getAudit() {
        return audit;
    }

    public void setAudit(Integer audit) {
        this.audit = audit;
    }
}