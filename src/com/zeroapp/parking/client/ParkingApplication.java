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

package com.zeroapp.parking.client;

import android.app.Application;
import android.content.Intent;

import com.zeroapp.utils.Log;

/**
 * <p>
 * Title: ParkingApplication.
 * </p>
 * <p>
 * Description: ParkingApplication.
 * </p>
 * 
 * @author Alex(zeroapp@126.com) 2015-6-17.
 * @version $Id$
 */

public class ParkingApplication extends Application {

    public static final String SERVICE_ACTION = "com.zeroapp.parking.client.ClientService";

    @Override
    public void onCreate() {
        Log.i("");
        super.onCreate();
        startClientService();
    }

    @Override
    public void onTerminate() {
        // TODO Auto-generated method stub
        super.onTerminate();
    }

    private void startClientService() {
        Intent i = new Intent(this, ClientService.class);
        i.setAction(SERVICE_ACTION);
        startService(i);
    }
}
