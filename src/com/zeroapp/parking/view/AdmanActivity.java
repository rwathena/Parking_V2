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
package com.zeroapp.parking.view;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.zeroapp.parking.R;
import com.zeroapp.parking.message.AMessage;
import com.zeroapp.parking.message.MessageConst;
import com.zeroapp.utils.Log;

/**
 * <p>
 * Title: MainActivity.
 * </p>
 * <p>
 * Description: MainActivity.
 * </p>
 * 
 * @author Alex(zeroapp@126.com) 2015-5-27.
 * @version $Id$
 */
public class AdmanActivity extends BaseActivity implements OnClickListener {

	private BaseFragment f = null;
    private TextView balance = null;
    private long mExitTime = 0;
    private int lastClick = 0;// 记录上次点击的viewid,用于防止重复点击的逻辑


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.e("+++ ON CREATE +++");
		// Set up the window layout
        setContentView(R.layout.activity_adman);
		initView();
        initUser();
	}
	@Override
	public void onStart() {
		super.onStart();
		Log.e("++ ON START ++");
	}

    @Override
    public synchronized void onResume() {
        super.onResume();
        Log.e("+ ON RESUME +");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("-- ON STOP --");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("--- ON DESTROY ---");
    }

	private void initView() {
        balance = (TextView) findViewById(R.id.balance);
        findViewById(R.id.btn_adman_info).setOnClickListener(this);
        findViewById(R.id.btn_show_adman_record).setOnClickListener(this);
        findViewById(R.id.btn_show_business).setOnClickListener(this);
        // update balance
        balance.setText(me.getAccountBanlance() + "");
        getActionBar().setTitle(me.getName());
	}
    public void initUser() {
        lastClick = 0;
    }


	@Override
	public void onClick(View v) {
        if (lastClick != v.getId()) {
            // 恢复之前click的view 的点击状态
            if (lastClick != 0) {
                findViewById(lastClick).setAlpha(1);
                findViewById(lastClick).setClickable(true);
            }
            // 设置新click的view 的状态
            v.setClickable(false);
            v.setAlpha(0.5f);
            lastClick = v.getId();
        }
		showFragment(v.getId());
	}
	public void showFragment(int id) {
		FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        switch (id) {
            case MessageConst.MessageType.MSG_TYPE_UI_SHOW_ADMAN_INFO:
            case R.id.btn_adman_info:
                if (lastClick == 0) {
                    lastClick = R.id.btn_adman_info;
                    findViewById(lastClick).setAlpha(0.5f);
                    findViewById(lastClick).setClickable(false);
                }
                f = new AdmanInfoFragment();
                break;
            case R.id.btn_show_business:
                f = new BusinessFragment();
                break;
            case R.id.btn_show_adman_record:
                f = new AdmanRecordFragment();
                break;

            default:
                break;
        }
        t.replace(R.id.topfl_container, f).commit();
	}

	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                // 连按两次back键退出
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (System.currentTimeMillis() - mExitTime > 1500) {
                        Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                        mExitTime = System.currentTimeMillis();
                    } else {
                        moveTaskToBack(true);
                    }
                }
                return true;

            default:
                break;
        }
        return super.onKeyDown(keyCode, event);
	}

    @Override
    public void dealMessage(AMessage m) {
        switch (m.getMessageType()) {
            case MessageConst.MessageType.MSG_TYPE_UI_SEVICE_CONNECTED:
                showFragment(MessageConst.MessageType.MSG_TYPE_UI_SHOW_ADMAN_INFO);
                break;
            default:
                f.refreshUI(m);
                break;
        }
    }
}
