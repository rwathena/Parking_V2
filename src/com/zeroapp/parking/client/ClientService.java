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

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;

import java.util.ArrayList;

import com.zeroapp.parking.common.ParkingInfo;
import com.zeroapp.parking.common.User;
import com.zeroapp.parking.locator.Tracer;
import com.zeroapp.parking.message.ClientServerMessage;
import com.zeroapp.parking.message.MessageConst;
import com.zeroapp.parking.view.AdmanActivity;
import com.zeroapp.parking.view.SigninActivity;
import com.zeroapp.parking.view.SysAdminActivity;
import com.zeroapp.parking.view.UserActivity;
import com.zeroapp.utils.GraphTool;
import com.zeroapp.utils.JsonTool;
import com.zeroapp.utils.Log;
import com.zeroapp.utils.MyTime;

/**
 * <p>
 * Title: ClientService.
 * </p>
 * <p>
 * Description: 客户端服务,职责是:1-向服务器发送消息请求;2-接收服务端发来的消息;3-绑定百度地图定位服务.
 * </p>
 * 
 * @author Alex(zeroapp@126.com) 2015-6-17.
 * @version $Id$
 */

public class ClientService extends Service implements OnConnectStateChangeListener {

    /**
     * broadcast intent action
     */
    public static final String ACTION_MESSAGE = "com.zeroapp.parking.ACTION_MESSAGE";
    /**
     * broadcast intent action
     */
    public static final String ACTION_SERVICE_CONNECTED = "com.zeroapp.parking.ACTION_SERVICE_CONNECTED";

    /**
     * 坐标类型:gcj02(国测局加密经纬度坐标)\bd09ll(百度加密经纬度坐标)\bd09(百度加密墨卡托坐标)
     */
    private static final String COOR_TYPE = "bd09ll";
    /**
     * 定位时间间隔,单位ms
     */
    private static final int SCAN_SPAN = 1000;
    /**
     * 定位时间和停车记录起始时间之间的间隔,单位ms
     */
    private static final long TRACE_INTERVAL = 0;
    /**
     * 当前位置和停车位置的距离,单位m
     */
    public static final double TRACE_DISTANCE = 100000000;
    /**
     * 是否打开GPS
     */
    private static final boolean IS_OPEN_GPS = true;
    /**
     * SharedPreferences name
     */
    public static final String PREF_NAME = "Parking";
    private MyLocationListenner myListener = new MyLocationListenner();
    private LocationClient mLocClient;
    private SharedPreferences prefNoVersion = null;
    private MyBinder mBinder = new MyBinder();
    private ArrayList<Tracer> mTracerManager = new ArrayList<Tracer>();
    private ArrayList<ParkingInfo> mParkingManager = new ArrayList<ParkingInfo>();
    private MessageBox mBox;
    private User me = new User();

    public class MyBinder extends Binder {

        public ClientService getService() {
            return ClientService.this;
        }

    }

    // The Handler that gets information back
    public final Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MessageConst.MessageType.MESSAGE_FROM_SERVER:
                    ClientServerMessage m = (ClientServerMessage) msg.obj;
                    Intent i = null;
                    if (m.getMessageResult() == MessageConst.MessageResult.MSG_RESULT_SUCCESS) {
                        switch (m.getMessageType()) {
                            case MessageConst.MessageType.MSG_TYPE_USER_SIGN_IN:
                            case MessageConst.MessageType.MSG_TYPE_USER_SIGN_UP:
                                me = JsonTool.getUser(m.getMessageContent());
                                // remember me
                                prefNoVersion.edit().putString("account", me.getAccount()).commit();
                                prefNoVersion.edit().putString("password", me.getPassword()).commit();
                                if (me.getUserType().startsWith("3")) {
                                    // 启动普通用户详情界面
                                    i = new Intent(ClientService.this, UserActivity.class);
                                } else if (me.getUserType().startsWith("2")) {
                                    // 启动广告商详情界面
                                    i = new Intent(ClientService.this, AdmanActivity.class);
                                } else if (me.getUserType().startsWith("1")) {
                                    // 启动系统用户详情界面
                                    i = new Intent(ClientService.this, SysAdminActivity.class);
                                }
                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                i.putExtra("me", m.getMessageContent());
                                startActivity(i);
                                break;

                            default:
                                i = new Intent(ACTION_MESSAGE);
                                i.putExtra("message", m);
                                sendBroadcast(i);
                                break;
                        }
                    } else {
                        Log.w(m.getMessageType() + "  Fail!");
                        i = new Intent(ACTION_MESSAGE);
                        i.putExtra("message", m);
                        sendBroadcast(i);
                    }
                    break;
                case MessageConst.MessageType.MESSAGE_UI:
//                    dealUIMessage((AMessage) msg.obj);
                    break;
            }
        }
    };
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("");
        // 连接服务器
        bindServer();
        // 初始化百度定位服务
        registLocation();
    }

    @Override
    public void onDestroy() {
        exitApp();
        super.onDestroy();
    }

    private void exitApp() {
        System.exit(0);
    }
    @Override
    public IBinder onBind(Intent i) {
        return mBinder;
    }

    public void sendMessageToServer(ClientServerMessage m) {
        Log.i("");
        mBox.sendMessage(m);
    }

    public void addLocationListener(Tracer t) {
        mTracerManager.add(t);
    }

    public void removeLocationListener(Tracer t) {
        mTracerManager.remove(t);
    }

    public void addParkingInfo(ParkingInfo p) {
        mParkingManager.add(p);
    }

    public void removeParkingInfo(ParkingInfo p) {
        mParkingManager.remove(p);
    }

    public User getUserInfo() {
        return me;
    }

    public void signIn() {
        prefNoVersion = getApplicationContext().getSharedPreferences(PREF_NAME, 0);
        me.setAccount(prefNoVersion.getString("account", null));
        me.setPassword(prefNoVersion.getString("password", null));
        if (me.getAccount() != null && me.getPassword() != null) {
            // 从SharedPreferences中取出了账号&密码;自动登录
            ClientServerMessage m = new ClientServerMessage();
            m.setMessageType(MessageConst.MessageType.MSG_TYPE_USER_SIGN_IN);
            m.setMessageContent(JsonTool.getString(me));
            mBox.sendMessage(m);
        } else {
            // 账号或者密码为空;启动登录界面
            Intent i = new Intent(ClientService.this, SigninActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        }
    }
    private void bindServer() {
        Log.i("");
        mBox = new MessageBox(mHandler);
        PostMan man = new PostMan(mBox);
        man.setConnectStateChangeListener(this);
        new Thread(man).start();
    }
    private void registLocation() {
        Log.i("");
        // 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
        SDKInitializer.initialize(getApplicationContext());
        // 定位初始化
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(IS_OPEN_GPS);// 打开gps
        option.setCoorType(COOR_TYPE); // 设置坐标类型
        option.setScanSpan(SCAN_SPAN);
        mLocClient.setLocOption(option);
        mLocClient.start();
    }

    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null) {
                return;
            }
//            Log.d("Time: " + location.getTime() + "........Lat: " + location.getLatitude() + "........Lon: " + location.getLongitude());
//            Log.d("BD Time:" + MyTime.getLongTime(location.getTime()));
//            Log.d("CR Time:" + System.currentTimeMillis());
            for (Tracer t : mTracerManager) {
                t.onLocationChanged(location);
                for (ParkingInfo p : mParkingManager) {
                    Log.d(location.getTime() + "   " + MyTime.getLongTime(location.getTime()) + "    " + p.getTimeStart());
                    // 定位时间点和停车起始几时点超过规定的时间间隔时,计算距离
                    if (MyTime.getLongTime(location.getTime()) - p.getTimeStart() > TRACE_INTERVAL) {
                        Log.d(location.getLatitude() + "    " + location.getLongitude() + "\n" + p.getLocationLatitude() + "    " + p.getLocationLongitude());
                        // 当定位点和停车起始点距离小于一定值时,通知回来
                        if (GraphTool.getDistance(location.getLatitude(), location.getLongitude(), p.getLocationLatitude(), p.getLocationLongitude()) < TRACE_DISTANCE)
                            t.onComingBack(location);
                    }
                }
            }
        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }

    /**
     * <p>
     * Title: onConnect.
     * </p>
     * <p>
     * Description: 连接上服务器.
     * </p>
     * 
     */
    @Override
    public void onConnect() {

    }

    /**
     * <p>
     * Title: onDisconnect.
     * </p>
     * <p>
     * Description: 与服务器断开.
     * </p>
     * 
     */
    @Override
    public void onDisconnect() {
        bindServer();
    }
}
