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

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Iterator;

import com.zeroapp.utils.Log;

/**
 * <p>
 * Title: DBHelper.
 * </p>
 * <p>
 * Description: DBHelper.
 * </p>
 * 
 * @author Alex(zeroapp@126.com) 2015-6-25.
 * @version $Id$
 */
public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {

        // CursorFactory设置为null,使用默认值
        super(context, DBUtils.DATABASE_NAME, null, DBUtils.DATABASE_VERSION);
        Log.i("");
    }

    /**
     * <p>
     * Title: onCreate.
     * </p>
     * <p>
     * Description: onCreate.
     * </p>
     * 
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("");
        Iterator<Integer> it = DBUtils.getCategoryManager().keySet().iterator();
        while (it.hasNext()) {
            int i = it.next();
            String CREATE_DB = "CREATE TABLE IF NOT EXISTS " + DBUtils.getCategoryManager().get(i)
                    + "(" + DBUtils._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + DBUtils.KEY_USERNAME + " TEXT," + DBUtils.KEY_PWD + " TEXT,"
                    + DBUtils.IS_LOGIN + " INTERGER)";
            db.execSQL(CREATE_DB);
        }
    }

    /**
     * <p>
     * Title: onUpgrade.
     * </p>
     * <p>
     * Description: onUpgrade.
     * </p>
     * 
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("");
    }

}
