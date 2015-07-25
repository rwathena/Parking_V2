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
 * Description: 向广告商提供的业务类型.
 * </p>
 * 
 * @author Alex(zeroapp@126.com) 2015-6-4.
 * @version $Id$
 */

public class Business {

	/**
	 * 业务编号
	 */
	private int businessID = -1;
	/**
	 * 业务涉及到的区域范围
	 */
	private int areaid ;
	/**
	 * 该业务能承载的最大用户数量,受area大小限定
	 */
	private int maxUserCount = -1;
	/**
	 * 该业务能承载的最大广告商数量,受area大小限定
	 */
	private int maxtendererCount = -1;
	/**
	 * 用户选择该业务的单位收益
	 */
	private double earnings = 0;
	/**
	 * 广告商选择该业务的单位成本
	 */
	private double cost = 0;
	/**
	 * 该业务起始时间
	 */
	private String timeStart = "0";
	/**
	 * 该业务的终止时间
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
	 * @return the area.
	 */
	public int getAreaId() {
		return areaid;
	}

	/**
	 * <p>
	 * Title: TODO.
	 * </p>
	 * <p>
	 * Description: TODO.
	 * </p>
	 * 
	 * @param area
	 *            the area to set.
	 */
	public void setAreaId(int aId) {
		this.areaid = aId;
	}

	/**
	 * <p>
	 * Title: TODO.
	 * </p>
	 * <p>
	 * Description: TODO.
	 * </p>
	 * 
	 * @return the maxUserCount.
	 */
	public int getMaxUserCount() {
		return maxUserCount;
	}

	/**
	 * <p>
	 * Title: TODO.
	 * </p>
	 * <p>
	 * Description: TODO.
	 * </p>
	 * 
	 * @param maxUserCount
	 *            the maxUserCount to set.
	 */
	public void setMaxUserCount(int maxUserCount) {
		this.maxUserCount = maxUserCount;
	}

	/**
	 * <p>
	 * Title: TODO.
	 * </p>
	 * <p>
	 * Description: TODO.
	 * </p>
	 * 
	 * @return the maxtendererCount.
	 */
	public int getMaxtendererCount() {
		return maxtendererCount;
	}

	/**
	 * <p>
	 * Title: TODO.
	 * </p>
	 * <p>
	 * Description: TODO.
	 * </p>
	 * 
	 * @param maxtendererCount
	 *            the maxtendererCount to set.
	 */
	public void setMaxtendererCount(int maxtendererCount) {
		this.maxtendererCount = maxtendererCount;
	}

	/**
	 * <p>
	 * Title: TODO.
	 * </p>
	 * <p>
	 * Description: TODO.
	 * </p>
	 * 
	 * @return the earnings.
	 */
	public double getEarnings() {
		return earnings;
	}

	/**
	 * <p>
	 * Title: TODO.
	 * </p>
	 * <p>
	 * Description: TODO.
	 * </p>
	 * 
	 * @param earnings
	 *            the earnings to set.
	 */
	public void setEarnings(double earnings) {
		this.earnings = earnings;
	}

	/**
	 * <p>
	 * Title: TODO.
	 * </p>
	 * <p>
	 * Description: TODO.
	 * </p>
	 * 
	 * @return the cost.
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * <p>
	 * Title: TODO.
	 * </p>
	 * <p>
	 * Description: TODO.
	 * </p>
	 * 
	 * @param cost
	 *            the cost to set.
	 */
	public void setCost(double cost) {
		this.cost = cost;
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
