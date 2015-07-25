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
 * Description: bidding指的是广告商选择了公司向其提供的业务(Business).
 * </p>
 * 
 * @author Alex(zeroapp@126.com) 2015-6-4.
 * @version $Id$
 */

public class Bidding {

    /**
     * 投标的ID
     */
    private int biddingID = -1;
    /**
     * 业务的ID
     */
    private int businessID = -1;
    /**
     * 进行投标的广告商
     */
    private int userID = -1;
    /**
     * 广告商需要服务的起始时间
     */
    private String timeStart = "0";
    /**
     * 广告商需要服务的终止时间
     */
    private String timeEnd = "0";

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
     * @return the businessID.
     */
    public int getBusinessID() {
        return businessID;
    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @param businessID
     *            the businessID to set.
     */
    public void setBusinessID(int businessID) {
        this.businessID = businessID;
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
     * @return the timeStart.
     */
    public String getTimeStart() {
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
    public void setTimeStart(String timeStart) {
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
    public String getTimeEnd() {
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
    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

}
