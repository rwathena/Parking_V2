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

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.zeroapp.parking.common.ParkingInfo;
import com.zeroapp.utils.Config;
import com.zeroapp.utils.Log;

/**
 * <p>
 * Title: ParkingInfoDataControler.
 * </p>
 * <p>
 * Description: ParkingInfoDataControler.
 * </p>
 * 
 * @author Alex(zeroapp@126.com) 2015-6-25.
 * @version $Id$
 */
public class ParkingInfoDataControler {

    private final int TABLE = 1;
    private Context mContext = null;
    private ContentResolver mContentResolver;

    /**
     * <p>
     * Title: CategoryDataControler.
     * </p>
     * <p>
     * Description: CategoryDataControler.
     * </p>
     * 
     */
    public ParkingInfoDataControler(Context context) {
        this.mContext = context;
        mContentResolver = mContext.getContentResolver();
    }

    public void insert(ParkingInfo parkingInfo) {
        Log.i(" -> insert()");
        if (Config.USE_DB) {
            if (parkingInfo == null) {
                Log.i("parkingInfo == null");
                return;
            }
            ContentValues mContentValues = new ContentValues();
//TODO
//            mContentValues.put("username", categoryInfo.getUserName());
//            mContentValues.put("pwd", categoryInfo.getPwd());
//            mContentValues.put("islogin", 1);
            mContentResolver.insert(Uri.parse("content://" + DBUtils.AUTHORITY + "/" + DBUtils.getCategoryManager().get(TABLE)), mContentValues);
        }
    }

    /**
     * <p>
     * Title: delete.
     * </p>
     * <p>
     * Description:
     * 删除操作并非是真的删除数据，而是将登陆标记改为0.因此，此时数据库中仍然保留了用户登陆的信息，只是在UI调用是要先检查登陆标记.
     * </p>
     * 
     * @param categoryInfo
     */
    public void delete(ParkingInfo parkingInfo) {
        Log.i(" -> delete()");
        if (Config.USE_DB) {
            if (parkingInfo == null) {
                Log.i("delete -> parkingInfo == null");
                return;
            }
            ContentValues mContentValues = new ContentValues();
            // TODO
//            mContentValues.put("islogin", 0);
            mContentResolver.update(Uri.parse("content://" + DBUtils.AUTHORITY + "/" + DBUtils.getCategoryManager().get(TABLE)), mContentValues, null, null);
        }
    }

    public void update() {
        Log.i(" -> update()");
        if (Config.USE_DB) {

        }
    }

    public ParkingInfo query(int type) {
        return growParkingInfo(type);
    }

    /**
     * <p>
     * Title: growCategoryInfo.
     * </p>
     * <p>
     * Description: growCategoryInfo.
     * </p>
     * 
     * @param c
     * @return
     */
    private ParkingInfo growParkingInfo(int type) {
        ParkingInfo parkingInfo = new ParkingInfo();
        if (!Config.USE_DB) {
            // TODO
        } else {
            Cursor c = mContentResolver.query(Uri.parse("content://" + DBUtils.AUTHORITY + "/" + DBUtils.getCategoryManager().get(TABLE)), null, null, null, null);
            while (c != null && c.moveToLast()) {
                // TODO
//                String username = c.getString(c.getColumnIndex("username"));
//                String pwd = c.getString(c.getColumnIndex("pwd"));
//                int islogin = c.getInt(c.getColumnIndex("islogin"));
//                Log.d(TAG, "username === " + username);
//                Log.d(TAG, "pwd === " + pwd);
//                Log.d(TAG, "islogin === " + islogin);
//                Log.d(TAG, "type === " + type);
//                categoryInfo.setUserName(username);
//                categoryInfo.setPwd(pwd);
//                categoryInfo.setType(type);
//                if (islogin == 1) {// 1-登陆、0-未登录
//                    categoryInfo.setLogin(true);
//                } else {
//                    categoryInfo.setLogin(false);
//                }
                return parkingInfo;
            }
        }

        return parkingInfo;
    }

}
