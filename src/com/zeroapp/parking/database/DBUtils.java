/* 
 * Copyright (C)  . 
 * All Rights Reserved.
 *
 * ALL RIGHTS ARE RESERVED BY HISENSE ELECTRIC CO., LTD. ACCESS TO THIS
 * SOURCE CODE IS STRICTLY RESTRICTED UNDER CONTRACT. THIS CODE IS TO
 * BE KEPT STRICTLY CONFIDENTIAL.
 *
 * UNAUTHORIZED MODIFICATION OF THIS FILE WILL VOID YOUR SUPPORT CONTRACT
 * WITH HISENSE ELECTRIC CO., LTD. IF SUCH MODIFICATIONS ARE FOR THE PURPOSE
 * OF CIRCUMVENTING LICENSING LIMITATIONS, LEGAL ACTION MAY RESULT.
 */

package com.zeroapp.parking.database;

import android.content.UriMatcher;

import java.util.HashMap;

import com.zeroapp.utils.Log;



/**
 * <p>
 * Title: DBUtils.
 * </p>
 * <p>
 * Description: DBUtils.
 * </p>
 * 
 * @author Bobby Zou(zeroapp@126.com) 2014-4-22.
 * @version $Id$
 */

public class DBUtils {

    public static final String DATABASE_NAME = "parking_client.db";
    public static final int DATABASE_VERSION = 1;

    public static final String AUTHORITY = "com.zeroapp.parking.database";

    public static final int CODE_PARKING_INFO = 1;

    
    
    public static final String TABLE_PARKING_INFO = "parking_info";


    public static final String _ID = "_id";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PWD = "pwd";
    public static final String IS_LOGIN = "islogin";// 0-未登陆、1-登陆
    

    private static HashMap<Integer, String> categoryManager;
    
    public static HashMap<Integer, String> getCategoryManager() {
        if (categoryManager == null) {
            Log.e("Init CategoryManager!");
            categoryManager = new HashMap<Integer, String>();
            // 将分类编号和分类名称加入HashMap进行管理；
            categoryManager.put(CODE_PARKING_INFO, TABLE_PARKING_INFO);
        }
        return categoryManager;
    	
	}
    public static UriMatcher initUriMatcher() {
    	UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(DBUtils.AUTHORITY, DBUtils.TABLE_PARKING_INFO, DBUtils.CODE_PARKING_INFO);

        return matcher;
    }
}
