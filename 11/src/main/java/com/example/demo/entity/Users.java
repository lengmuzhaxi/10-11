package com.example.demo.entity;
import java.util.Date;

public class Users {
    private Integer id;
    private String userId;
    private String userPwd;
    private Integer userAge;
    private String userName;
    private String userTel;
    private String userSex;
    private Date createTime;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getUserPwd() { return userPwd; }
    public void setUserPwd(String userPwd) { this.userPwd = userPwd; }
    public Integer getUserAge() { return userAge; }
    public void setUserAge(Integer userAge) { this.userAge = userAge; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getUserTel() { return userTel; }
    public void setUserTel(String userTel) { this.userTel = userTel; }
    public String getUserSex() { return userSex; }
    public void setUserSex(String userSex) { this.userSex = userSex; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
}