/* 
 * Copyright (C) 2015 Alex. 
 * All Rights Reserved.
 *
 * ALL RIGHTS ARE RESERVED BY Alex. ACCESS TO THIS
 * SOURCE CODE IS STRICTLY RESTRICTED UNDER CONTRACT. THIS CODE IS TO
 * BE KEPT STRICTLY CONFIDENTIAL.
 *
 * UNAUTHORIZED MODIFICATION OF THIS FILE WILL VOID YOUR SUPPORT CONTRACT
 * WITH Alex(zeroapp@126.com). IF SUCH MODIFICATIONS ARE FOR THE PURPOSE
 * OF CIRCUMVENTING LICENSING LIMITATIONS, LEGAL ACTION MAY RESULT.
 */

package com.zeroapp.parking.common;


/**
 * <p>
 * Title: TODO.
 * </p>
 * <p>
 * Description: 用户信息.
 * </p>
 * 
 * @author Alex(zeroapp@126.com) 2015-6-4.
 * @version $Id$
 */

public class User {

    /**
     * ID,账户的内部标示,唯一,不重复,由系统分配
     */
    private int userID = -1;
    /**
     * 用户账号,和ID一一对应,唯一,用户注册时提供
     */
    private String account = "";
    /**
     * 用户登陆密码
     */
    private String password = "";
    /**
     * 用户真实姓名
     */
    private String name = "";
    /**
     * 用户身份证号码
     */
    private String identityNum = "";
    /**
     * 用户性别:0-女;1-男;其他-未知;
     */
    private int sex = 1;
    /**
     * 用户电话号码,注册时提供
     */
    private String phoneNum = "";
    /**
     * 用户类别,分为两部分:
     * _之前表示用户类型0-普通用户;1-广告投放商;2-Admin;
     * _之后表示用户在该类型的等级,预留
     */
    private String userType = "3_0";

    /**
     * 用户账户余额
     */
    private double accountBanlance = 0;

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @return the userID.
     */
    public int getUserID() {
        return userID;
    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @param userID
     *            the userID to set.
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @return the account.
     */
    public String getAccount() {
        return account;
    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @param account
     *            the account to set.
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @return the password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @param password
     *            the password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @param name
     *            the name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @return the identityNum.
     */
    public String getIdentityNum() {
        return identityNum;
    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @param identityNum
     *            the identityNum to set.
     */
    public void setIdentityNum(String identityNum) {
        this.identityNum = identityNum;
    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @return the sex.
     */
    public int getSex() {
        return sex;
    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @param sex
     *            the sex to set.
     */
    public void setSex(int sex) {
        this.sex = sex;
    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @return the phoneNum.
     */
    public String getPhoneNum() {
        return phoneNum;
    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @param phoneNum
     *            the phoneNum to set.
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @return the userType.
     */
    public String getUserType() {
        return userType;
    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @param userType
     *            the userType to set.
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @return the accountBanlance.
     */
    public double getAccountBanlance() {
        return accountBanlance;
    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @param accountBanlance
     *            the accountBanlance to set.
     */
    public void setAccountBanlance(double accountBanlance) {
        this.accountBanlance = accountBanlance;
    }

}
