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
 * Description: 单次停车的信息.
 * </p>
 * 
 * @author Alex(zeroapp@126.com) 2015-6-4.
 * @version $Id$
 */

public class ParkingInfo {

    /**
     * 该次停车记录的ID
     */
    private int parkingID = -1;
    /**
     * 车牌号
     */
    private String carNum = "";
    /**
     * 该次停车记录点的经度
     */
    private double locationLongitude = 0;
    /**
     * 该次停车记录点的纬度
     */
    private double locationLatitude = 0;
    /**
     * 该次停车记录的起始时间
     */
    private long timeStart = 0;
    /**
     * 该次停车记录的终止时间
     */
    private long timeEnd = 0;
    /**
     * 该次停车记录的收入
     */
    private double moneyEarning = 0;
    private double moneyCost = 0;
    private int userId;
    
    public void setMoneyEarning(double m){
    	this.moneyEarning = m;
    }
    public double getMoneyEarning(){
    	return moneyEarning;
    }
    public void setMoneyCost(double m){
    	this.moneyCost = m;
    }
    public double getMoneyCost(){
    	return moneyCost;
    }
    public void setUserId(int userId){
    	this.userId = userId;
    }
    public int getUserId(){
    	return userId;
    }
    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @return the parkingID.
     */
    public int getParkingID() {
        return parkingID;
    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @param parkingID
     *            the parkingID to set.
     */
    public void setParkingID(int parkingID) {
        this.parkingID = parkingID;
    }

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
     * @return the locationLongitude.
     */
    public double getLocationLongitude() {
        return locationLongitude;
    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @param locationLongitude
     *            the locationLongitude to set.
     */
    public void setLocationLongitude(double locationLongitude) {
        this.locationLongitude = locationLongitude;
    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @return the locationLatitude.
     */
    public double getLocationLatitude() {
        return locationLatitude;
    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @param locationLatitude
     *            the locationLatitude to set.
     */
    public void setLocationLatitude(double locationLatitude) {
        this.locationLatitude = locationLatitude;
    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @return the timeStart.
     */
    public long getTimeStart() {
        return timeStart;
    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @param timeStart
     *            the timeStart to set.
     */
    public void setTimeStart(long timeStart) {
        this.timeStart = timeStart;
    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @return the timeEnd.
     */
    public long getTimeEnd() {
        return timeEnd;
    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @param timeEnd
     *            the timeEnd to set.
     */
    public void setTimeEnd(long timeEnd) {
        this.timeEnd = timeEnd;
    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @return the money.
     */
//    public String getMoney() {
//        return money;
//    }
//
//    /**
//     * <p>
//     * Title: TODO.
//     * </p>
//     * <p>
//     * Description: TODO.
//     * </p>
//     * 
//     * @param money
//     *            the money to set.
//     */
//    public void setMoney(String money) {
//        this.money = money;
//    }

}
