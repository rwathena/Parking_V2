package com.zeroapp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyTime {

    public static String getTimeNoS(long timelong) {
        Date date = new Date(timelong);
		SimpleDateFormat df=new SimpleDateFormat("MM-dd HH:mm");   
		String time=df.format(date);
		return time;
	}

    public static String getTime(long timelong) {
        Date date = new Date(timelong);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=df.format(date);
		return time;
	}
    public static String getStringTime(long time) {
        Date date = new Date(time);
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String stime = df.format(date);
        return stime;
    }

    public static long getLongTime(String time) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = df.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }
        return date.getTime();
    }
}
