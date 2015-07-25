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

package com.zeroapp.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import com.zeroapp.parking.common.Bidding;
import com.zeroapp.parking.common.BiddingContainer;
import com.zeroapp.parking.common.CarInfo;
import com.zeroapp.parking.common.CommercialDetails;
import com.zeroapp.parking.common.ParkingInfo;
import com.zeroapp.parking.common.User;


/**
 * <p>Title: TODO.</p>
 * <p>Description: TODO.</p>
 *
 * @author Alex(zeroapp@126.com) 2015-6-8.
 * @version $Id$
 */

public class JsonTool {

    public static String getString(Object o) {
        return new Gson().toJson(o);

    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @param messageContent
     * @return
     */
    public static User getUser(String messageContent) {
        User u = null;
        try {
            u = new Gson().fromJson(messageContent, User.class);
        } catch (Exception e) {
        }
        return u;
    }

//    public static Business getBusiness(String messageContent) {
//        Log.i("messageContent: " + messageContent);
//        Business b = new Gson().fromJson(messageContent, Business.class);
//        return b;
//    }

    public static CarInfo getCarInfo(String messageContent) {
        CarInfo o = new Gson().fromJson(messageContent, CarInfo.class);
        return o;
    }

    public static Bidding getBidding(String messageContent) {
        Bidding o = new Gson().fromJson(messageContent, Bidding.class);
        return o;
    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @param messageContent
     * @return
     */
    public static List<CarInfo> getUserCars(String messageContent) {
        Gson g = new Gson();
        List<CarInfo> cars = g.fromJson(messageContent, new TypeToken<List<CarInfo>>() {
        }.getType());
        return cars;
    }

    public static List<CommercialDetails> getBusinessList(String messageContent) {
        Gson g = new Gson();
        List<CommercialDetails> bs = g.fromJson(messageContent, new TypeToken<List<CommercialDetails>>() {
        }.getType());
        return bs;
    }
    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @param bmString
     * @return
     */
    public static BmapPoint[] getCoordinatesOfArea(String jString) {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonArray jArray = parser.parse(jString).getAsJsonArray();
        if (jArray.size() == 0) {
            return null;
        }
        BmapPoint[] bmapPoints = new BmapPoint[jArray.size()];
        Log.d("jArray: " + jArray);
        for (JsonElement obj : jArray) {
            int i = 0;

            BmapPoint bp = gson.fromJson(obj, BmapPoint.class);
            bmapPoints[i] = bp;
            Log.d("bmapPoints: " + bmapPoints[i].getLng());
            i++;
        }
        return bmapPoints;
    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @param messageContent
     * @return
     */
    public static List<Bidding> getBiddingsList(String messageContent) {
        Gson g = new Gson();
        List<Bidding> bs = g.fromJson(messageContent, new TypeToken<List<Bidding>>() {
        }.getType());
        return bs;
    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @param messageContent
     * @return
     */
    public static List<BiddingContainer> getBiddingsContainerList(String messageContent) {
        Gson g = new Gson();
        List<BiddingContainer> bs = g.fromJson(messageContent, new TypeToken<List<BiddingContainer>>() {
        }.getType());
        return bs;
    }

    public static List<ParkingInfo> getParkingInfoList(String messageContent) {
        Gson g = new Gson();
        List<ParkingInfo> ps = g.fromJson(messageContent, new TypeToken<List<ParkingInfo>>() {
        }.getType());
        return ps;
    }

}
