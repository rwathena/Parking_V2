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

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.zeroapp.parking.R;
import com.zeroapp.parking.client.ClientService;
import com.zeroapp.parking.common.CarInfo;
import com.zeroapp.parking.dialog.BaseDialog;
import com.zeroapp.parking.dialog.CarInfoDialog;
import com.zeroapp.parking.dialog.UpdateInfoDialog;
import com.zeroapp.parking.message.AMessage;
import com.zeroapp.parking.message.ClientServerMessage;
import com.zeroapp.parking.message.MessageConst;
import com.zeroapp.utils.JsonTool;
import com.zeroapp.utils.Log;


/**
 * <p>
 * Title: UserInfoFragment.
 * </p>
 * <p>
 * Description: 显示用户详情和汽车信息.
 * </p>
 * 
 * @author Alex(zeroapp@126.com) 2015-6-10.
 * @version $Id$
 */

public class UserInfoFragment extends BaseFragment implements OnLongClickListener {

    private UserActivity mainActivity;
    private View mainView;
    private TextView name;
    private TextView phoneNum;
    private TextView IdNum;
    private ListView listViewCars;
    private ProgressBar loadingBar;
    private Button btSignout;

    @Override
    public void onAttach(Activity activity) {
        Log.i("onAttach");
        super.onAttach(activity);
        mainActivity = (UserActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("onCreateView");
        mainView = inflater.inflate(R.layout.fragment_user_info, null);
        name = (TextView) mainView.findViewById(R.id.user_name);
        name.setText(mainActivity.me.getName());
        name.setOnLongClickListener(this);

        phoneNum = (TextView) mainView.findViewById(R.id.user_phone);
        phoneNum.setText(mainActivity.me.getPhoneNum());
        phoneNum.setOnLongClickListener(this);

        IdNum = (TextView) mainView.findViewById(R.id.user_idnum);
        IdNum.setText(mainActivity.me.getIdentityNum());
        IdNum.setOnLongClickListener(this);

        listViewCars = (ListView) mainView.findViewById(R.id.lv_cars);
        btSignout = (Button) mainView.findViewById(R.id.btn_signout);
        btSignout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 删除用户名和密码记录
                SharedPreferences prefNoVersion = getActivity().getApplicationContext().getSharedPreferences(ClientService.PREF_NAME, 0);
                prefNoVersion.edit().putString("account", null).commit();
                prefNoVersion.edit().putString("password", null).commit();
                // 启动登录界面
                Intent i = new Intent(mainActivity, SigninActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                mainActivity.finish();
            }
        });
        loadingBar = (ProgressBar) mainView.findViewById(R.id.loading);
        if (mainActivity.myCars == null) {
            requestMyCars();
        } else {
            updateListViewCars();
        }
        return mainView;
    }

    private void updateListViewCars() {
        listViewCars.setAdapter(new MyCarsAdeptet(mainActivity, mainActivity.myCars));
        listViewCars.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View v, int i, long arg3) {
                final CarInfo car = (CarInfo) adapterView.getAdapter().getItem(i);
                // TODO new dialog
                if (car != null) {
                    Log.i("num: " + car.getCarNum());
                    final CarInfoDialog dialog = new CarInfoDialog(mainActivity, car, "确定修改车辆信息?", "确定", "取消");
                    dialog.show();
                    dialog.setClicklistener(new CarInfoDialog.ClickListenerInterface() {

                        @Override
                        public void doConfirm() {
                            dialog.dismiss();
                            // TODO
                        }

                        @Override
                        public void doCancel() {
                            dialog.dismiss();
                        }
                    });
                } else {
                    final CarInfoDialog dialog = new CarInfoDialog(mainActivity, car, "确定添加车辆信息?", "确定", "取消");
                    dialog.show();
                    dialog.setClicklistener(new CarInfoDialog.ClickListenerInterface() {

                        @Override
                        public void doConfirm() {
                            dialog.dismiss();
                            CarInfo newcar = new CarInfo();
                            newcar.setCarNum("testcar");
                            newcar.setCarType("SUV");
                            newcar.setCarValue(300000);
                            newcar.setParkingArea("shinan");
                            ClientServerMessage m = new ClientServerMessage();
                            m.setMessageType(MessageConst.MessageType.MSG_TYPE_USER_ADD_CARS);
                            m.setMessageContent(JsonTool.getString(newcar));
                            mainActivity.mService.sendMessageToServer(m);
                        }

                        @Override
                        public void doCancel() {
                            dialog.dismiss();
                        }
                    });

                }

            }
        });
        // 隐藏缓冲圈
        loadingBar.setVisibility(View.INVISIBLE);

    }

    /**
     * <p>
     * Title: requestMyCars.
     * </p>
     * <p>
     * Description: 请求用户汽车列表.
     * </p>
     * 
     */
    private void requestMyCars() {
        ClientServerMessage m = new ClientServerMessage();
        m.setMessageType(MessageConst.MessageType.MSG_TYPE_USER_LIST_MYCARS);
        m.setMessageContent(JsonTool.getString(mainActivity.me));
        mainActivity.mService.sendMessageToServer(m);

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void refreshUI(AMessage msg) {
        Log.i("");
        switch (msg.getMessageType()) {
            case MessageConst.MessageType.MSG_TYPE_USER_LIST_MYCARS:
                if (msg.getMessageResult() == MessageConst.MessageResult.MSG_RESULT_SUCCESS) {
                    mainActivity.myCars = JsonTool.getUserCars(msg.getMessageContent());
                    // test code
//                    for (int i = 0; i < mainActivity.myCars.size(); i++) {
//                        Log.d(mainActivity.myCars.get(i).getCarNum());
//                        Log.d(mainActivity.myCars.get(i).getBiddingID() + "");
//                    }
                } else {
                    // TODO TOSAT FAIL
                }
                updateListViewCars();
                break;

            default:
                break;
        }

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
                TextView t = new TextView(mainActivity);
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

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @param arg0
     * @return
     */
    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {
            case R.id.user_phone:
                new UpdateInfoDialog(mainActivity, R.layout.device_list, v).show();
                break;
            case R.id.user_name:
                if (mainActivity.me.getName().equals("")) {
                    new BaseDialog(mainActivity, R.layout.device_list).show();
                }
                break;
            case R.id.user_idnum:
                if (mainActivity.me.getIdentityNum().equals("")) {
                    new BaseDialog(mainActivity, R.layout.device_list).show();
                }
                break;
            default:
                break;
        }
        return false;
    }


}
