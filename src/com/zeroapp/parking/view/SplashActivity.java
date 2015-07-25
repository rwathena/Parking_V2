package com.zeroapp.parking.view;


import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.zeroapp.parking.R;
import com.zeroapp.parking.message.AMessage;
import com.zeroapp.parking.message.MessageConst;
import com.zeroapp.utils.Log;

public class SplashActivity extends BaseActivity {



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        TextView versionNumber = (TextView) findViewById(R.id.versionNumber);
        try {
            String appName = getString(R.string.app_name);
            String appVersion = getPackageManager().getPackageInfo(getApplication().getPackageName(), 0).versionName;
            versionNumber.setText(appName + ":" + appVersion);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
	}
    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("--- ON DESTROY ---");
    }

    @Override
    public void dealMessage(AMessage m) {
        if (m.getMessageType() == MessageConst.MessageType.MSG_TYPE_UI_SEVICE_CONNECTED) {
            mService.signIn();
        }
    }

}
