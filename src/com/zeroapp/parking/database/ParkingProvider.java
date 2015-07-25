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

package com.zeroapp.parking.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.zeroapp.utils.Log;


/**
 * <p>
 * Title: ParkingProvider.
 * </p>
 * <p>
 * Description: DB provider used for PakringInfos.
 * </p>
 * 
 * @author Alex(zeroapp@126.com) 2015-6-1.
 * @version $Id$
 */

public class ParkingProvider extends ContentProvider {

    private DBHelper helper;
    private SQLiteDatabase db;

    private static final UriMatcher sMatcher = DBUtils.initUriMatcher();

    @Override
    public synchronized int delete(Uri uri, String selection, String[] selectionArgs) {
        int type = sMatcher.match(uri);
        Log.i("-> delete()，type is " + type);
        return db.delete(DBUtils.getCategoryManager().get(type), selection, selectionArgs);
    }
    @Override
    public String getType(Uri arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public synchronized Uri insert(Uri uri, ContentValues values) {
        int type = sMatcher.match(uri);
        Log.i("-> insert(),type is " + type);
        db.insert(DBUtils.getCategoryManager().get(type), null, values);
        return null;
    }

    @Override
    public boolean onCreate() {
        Log.i("");
        try {
            helper = new DBHelper(this.getContext());
            db = helper.getWritableDatabase();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return false;
    }

    @Override
    public synchronized Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        int type = sMatcher.match(uri);
        Log.i("-> query()，type is " + type);
        Cursor c = null;
        c = db.query(DBUtils.getCategoryManager().get(type), projection, selection, selectionArgs, null, null, sortOrder);
        return c;
    }

    @Override
    public synchronized int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int type = sMatcher.match(uri);
        Log.i(" -> update()，type is " + type);
        return db.update(DBUtils.getCategoryManager().get(type), values, selection, selectionArgs);
    }

}
