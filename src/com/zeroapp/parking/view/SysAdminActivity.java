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

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.zeroapp.parking.R;
import com.zeroapp.parking.client.ClientService;
import com.zeroapp.parking.common.CarInfo;
import com.zeroapp.parking.common.User;
import com.zeroapp.parking.dialog.ConfirmDialog;
import com.zeroapp.parking.message.AMessage;
import com.zeroapp.parking.message.ClientServerMessage;
import com.zeroapp.parking.message.MessageConst;
import com.zeroapp.utils.JsonTool;
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
public class SysAdminActivity extends BaseActivity implements OnClickListener {

    public static List<CarInfo> userCars = null;
    private TextView search;
    private long mExitTime = 0;
    private EditText etUserName;
    private EditText etUserPhone;
    private EditText etUserIdNum;
    private ProgressBar loadingBar;
    private ProgressBar carLoadingBar;
    private ListView listViewCars;
    private User user = new User();
    private TextView userName;
    private TextView userPhone;
    private TextView userIdNum;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.e("+++ ON CREATE +++");
		// Set up the window layout
        setContentView(R.layout.activity_admin);
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
        search = (TextView) findViewById(R.id.et_search);
        etUserName = (EditText) findViewById(R.id.user_name);
        etUserPhone = (EditText) findViewById(R.id.user_phone);
        etUserIdNum = (EditText) findViewById(R.id.user_idnum);
        userName = (TextView) findViewById(R.id.user_name_before);
        userPhone = (TextView) findViewById(R.id.user_phone_before);
        userIdNum = (TextView) findViewById(R.id.user_idnum_before);
        findViewById(R.id.btn_serach).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);
        findViewById(R.id.btn_signout).setOnClickListener(this);
        getActionBar().setTitle(me.getName());
        listViewCars = (ListView) findViewById(R.id.lv_cars);
        loadingBar = (ProgressBar) findViewById(R.id.loading);
        carLoadingBar = (ProgressBar) findViewById(R.id.loading_small);
	}
    public void initUser() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_serach:
                if (search.getText().toString().equals("")) {//TODO check phoneNum
                    Toast.makeText(SysAdminActivity.this, "号码不符合规则", Toast.LENGTH_SHORT).show();
                } else {
                    user.setPhoneNum(search.getText().toString());
                    ClientServerMessage m = new ClientServerMessage();
                    m.setMessageType(MessageConst.MessageType.MSG_TYPE_ADMIN_SEARCH_USER);
                    m.setMessageContent(JsonTool.getString(user));
                    m.setMessageParameters("" + me.getUserID());
                    mService.sendMessageToServer(m);
                }
                break;
            case R.id.btn_update:
                final ConfirmDialog confirmDialog = new ConfirmDialog(SysAdminActivity.this, "确定要跟新信息吗?", "确定", "取消");
                confirmDialog.show();
                confirmDialog.setClicklistener(new ConfirmDialog.ClickListenerInterface() {

                    @Override
                    public void doConfirm() {
                        confirmDialog.dismiss();
                        user.setPhoneNum(etUserPhone.getText().toString());
                        user.setName(etUserName.getText().toString());
                        user.setIdentityNum(etUserIdNum.getText().toString());

                        ClientServerMessage m = new ClientServerMessage();
                        m.setMessageType(MessageConst.MessageType.MSG_TYPE_ADMIN_UPDATE_USER_INFO);
                        m.setMessageContent(JsonTool.getString(user));
                        m.setMessageParameters("" + me.getUserID());
                        mService.sendMessageToServer(m);
                    }

                    @Override
                    public void doCancel() {
                        confirmDialog.dismiss();
                    }
                });

                break;
            case R.id.btn_signout:
                // 删除用户名和密码记录
                SharedPreferences prefNoVersion = getApplicationContext().getSharedPreferences(ClientService.PREF_NAME, 0);
                prefNoVersion.edit().putString("account", null).commit();
                prefNoVersion.edit().putString("password", null).commit();
                // 启动登录界面
                Intent i = new Intent(SysAdminActivity.this, SigninActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
                break;

            default:
                break;
        }
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
            case MessageConst.MessageType.MSG_TYPE_ADMIN_SEARCH_USER:
                search.setText("");
                if (m.getMessageResult() == MessageConst.MessageResult.MSG_RESULT_SUCCESS) {
                    user = JsonTool.getUser(m.getMessageContent());
                    userName.setText(user.getName());
                    userPhone.setText(user.getPhoneNum());
                    userIdNum.setText(user.getIdentityNum());

                    // update user cars
                    if (user.getUserType().startsWith("3")) {
                        ClientServerMessage mm = new ClientServerMessage();
                        mm.setMessageType(MessageConst.MessageType.MSG_TYPE_USER_LIST_MYCARS);
                        mm.setMessageContent(JsonTool.getString(user));
                        mService.sendMessageToServer(mm);
                    } else if (user.getUserType().startsWith("2")) {
                        ClientServerMessage mm = new ClientServerMessage();
                        mm.setMessageType(MessageConst.MessageType.MSG_TYPE_COMPANY_LIST_MY_BIDDING);// TODO
                                                                                              // 列出我的biddings
                        mm.setMessageContent(JsonTool.getString(user));
                        mService.sendMessageToServer(mm);
                    }

                } else {
                    Toast.makeText(this, "没找到用户", Toast.LENGTH_SHORT).show();
                }
                break;
            case MessageConst.MessageType.MSG_TYPE_USER_LIST_MYCARS:
                if (m.getMessageResult() == MessageConst.MessageResult.MSG_RESULT_SUCCESS) {
                    userCars = JsonTool.getUserCars(m.getMessageContent());
                } else {
                    Toast.makeText(this, "车辆失败", Toast.LENGTH_SHORT).show();
                }
                updateListViewCars();
                break;

            default:
                break;
        }

    }

    private void updateListViewCars() {
        listViewCars.setAdapter(new MyCarsAdeptet(SysAdminActivity.this, userCars));
        listViewCars.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View v, int i, long arg3) {
                final CarInfo car = (CarInfo) adapterView.getAdapter().getItem(i);
                // TODO new dialog
                if (car != null) {
                    Log.i("num: " + car.getCarNum());
                    final ConfirmDialog confirmDialog = new ConfirmDialog(SysAdminActivity.this, "确定该车要开始业务吗?", "确定", "取消");
                    confirmDialog.show();
                    confirmDialog.setClicklistener(new ConfirmDialog.ClickListenerInterface() {

                        @Override
                        public void doConfirm() {
                            confirmDialog.dismiss();

                            ClientServerMessage m = new ClientServerMessage();
                            m.setMessageType(MessageConst.MessageType.MSG_TYPE_ADMIN_UPDATE_CAR_STATE);
                            m.setMessageContent(JsonTool.getString(car));
                            m.setMessageParameters("" + me.getUserID());
                            mService.sendMessageToServer(m);
                        }

                        @Override
                        public void doCancel() {
                            confirmDialog.dismiss();
                        }
                    });
                } else {
//                    new BaseDialog(mainActivity, R.layout.device_list).show();

                }

            }
        });
        // 隐藏缓冲圈
        carLoadingBar.setVisibility(View.INVISIBLE);
    }

    public class MyCarsAdeptet extends BaseAdapter {

        private List<CarInfo> cars = null;
        private Context mContext = null;;

        public MyCarsAdeptet(Context ctx, List<CarInfo> l) {
            this.mContext = ctx;
            if (l == null) {
                cars = new ArrayList<CarInfo>();
            } else {
                cars = l;
            }
        }

        @Override
        public int getCount() {
            return cars.size() + 1;
        }

        @Override
        public Object getItem(int i) {
            if (i < cars.size()) {
                return cars.get(i);
            }
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View v, ViewGroup group) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            v = inflater.inflate(R.layout.lvitem_carinfo, null);
            if (i < cars.size()) {
                TextView carNum = (TextView) v.findViewById(R.id.car_num);
                TextView carState = (TextView) v.findViewById(R.id.car_state);
                carNum.setText(cars.get(i).getCarNum());
                carState.setText(cars.get(i).getCarState());
            } else {
                TextView t = new TextView(SysAdminActivity.this);
                t.setText("add!!");
                t.setTextSize(60);
                LayoutParams lp = group.getLayoutParams();
                lp.height = LayoutParams.WRAP_CONTENT;
                lp.width = LayoutParams.MATCH_PARENT;
                t.setLayoutParams(lp);
                v = t;
            }
            return v;
        }

    }
}
