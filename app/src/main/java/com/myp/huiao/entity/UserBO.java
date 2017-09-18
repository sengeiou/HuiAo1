package com.myp.huiao.entity;

import java.io.Serializable;

/**
 * Created by wuliang on 2017/9/13.
 * <p>
 * 用户bean
 */

public class UserBO implements Serializable {


    /**
     * account : 15629127249
     * birthday : null
     * education : null
     * gender : null
     * header : null
     * loginDate : 2017-09-13 17:04:19
     * mobile : 15629127249
     * nickName : 92635504
     * uuid : 5d4dff34-1a2a-40ab-a316-ea959a7411db
     */

    private String account;
    private String birthday;
    private String education;
    private String gender;
    private String header;
    private String loginDate;
    private String mobile;
    private String nickName;
    private String uuid;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(String loginDate) {
        this.loginDate = loginDate;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
