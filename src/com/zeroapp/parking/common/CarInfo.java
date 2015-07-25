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
 * <p>Title: TODO.</p>
 * <p>Description: TODO.</p>
 *
 * @author Alex(zeroapp@126.com) 2015-6-4.
 * @version $Id$
 */

public class CarInfo {
	public static String CAR_STATE_DEFAULT_VALUE = "sleep";
	public static String CAR_STATE_ACTIVE_VALUE = "active";
	public static String CAR_STATE_BLACK_LIST = "bitch";
    /**
     * 车牌号
     */
    private String carNum = "";
    /**
     * 用户ID
     */
    private int userID = -1;
    private int biddingID = -1;
    /**
     * 该车当前的状态,分为两部分
     * _之前标示该车是否可以抢广告;
     * _之后标示该车是否可以收入Money;
     */
    private String carState = CAR_STATE_DEFAULT_VALUE;

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @return the carNum.
     */
    private String parkingAreaString;
    private String carTypeString;
    private int carValue;
    
    public String getParkingArea(){
    	return parkingAreaString;
    }
    public void setParkingArea(String parkingA){
    	this.parkingAreaString = parkingA;
    }
    public String getCarType(){
    	return carTypeString;
    }
    public void setCarType(String carT){
    	this.carTypeString = carT;
    }
    public int getCarValue(){
    	return carValue;
    }
    public void setCarValue(int carValue){
    	this.carValue = carValue;
    }
    public String getCarNum() {
        return carNum;
    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @param carNum
     *            the carNum to set.
     */
    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

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
     * @return the biddingID.
     */
    public int getBiddingID() {
        return biddingID;
    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @param biddingID
     *            the biddingID to set.
     */
    public void setBiddingID(int biddingID) {
        this.biddingID = biddingID;
    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @return the carState.
     */
    public String getCarState() {
        return carState;
    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @param carState
     *            the carState to set.
     */
    public void setCarState(String carState) {
        this.carState = carState;
    }


}
