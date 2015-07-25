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
 * Description: Voting指的是普通用户看到软件发布的招广告信息,选择之,然后和公司签约进而进行收益.
 * </p>
 * 
 * @author Alex(zeroapp@126.com) 2015-6-4.
 * @version $Id$
 */

public class Voting {
	
	public static String VOTING_DEFALUT_STRING_VALUE = "default";
	public static String VOTING_DEFALUT_STRING_VALUE_ACTIVE = "active";
	public static String VOTING_DEFALUT_STRING_VALUE_SLEEP = "sleep";

    /**
     * 该次Voting的ID
     */
    private int votingID = -1;
    /**
     * 所选Bidding的ID
     */
    private int biddingID = -1;
    /**
     * 绑定该bidding的车牌号
     */
    private String carNum = "";
    /**
     * TODO
     */
    private String state = VOTING_DEFALUT_STRING_VALUE;

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @return the votingID.
     */
    public int getVotingID() {
        return votingID;
    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @param votingID
     *            the votingID to set.
     */
    public void setVotingID(int votingID) {
        this.votingID = votingID;
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
     * @return the state.
     */
    public String getState() {
        return state;
    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @param state
     *            the state to set.
     */
    public void setState(String state) {
        this.state = state;
    }

}
