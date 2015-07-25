
package com.zeroapp.parking.common;

public class BiddingContainer extends Bidding {

    private String areaName;
    private double earnings;
    private String companyName;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String aName) {
        this.areaName = aName;
    }

    public void setEarnings(double e) {
        this.earnings = e;
    }

    public double getEarnings() {
        return earnings;
    }

    public String getComName() {
        return companyName;
    }

    public void setComName(String cn) {
        this.companyName = cn;
    }
}
