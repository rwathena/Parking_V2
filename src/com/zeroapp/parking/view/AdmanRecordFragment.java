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
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;

import java.util.List;

import com.zeroapp.parking.R;
import com.zeroapp.parking.common.ParkingInfo;
import com.zeroapp.parking.message.AMessage;
import com.zeroapp.parking.message.ClientServerMessage;
import com.zeroapp.parking.message.MessageConst;
import com.zeroapp.utils.JsonTool;
import com.zeroapp.utils.Log;


/**
 * <p>
 * Title: TotalFragment.
 * </p>
 * <p>
 * Description: TODO.
 * </p>
 * 
 * @author Alex(zeroapp@126.com) 2015-5-28.
 * @version $Id$
 */
public class AdmanRecordFragment extends BaseFragment {

    private View mainView;
    private AdmanActivity mainActivity;
    private TextView wodeName;
    private ListView listViewTotal;
    private ProgressBar loadingBar;
    private LinearLayout llTotal;
    private List<ParkingInfo> parkingList;
    private MapView mMapView;
    private BaiduMap mBaiduMap;

    @Override
    public void onAttach(Activity activity) {
        Log.i("onAttach");
        super.onAttach(activity);
        mainActivity = (AdmanActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("onCreateView");
        mainView = inflater.inflate(R.layout.fragment_adman_record, null);
        llTotal = (LinearLayout) mainView.findViewById(R.id.ll_total);
        wodeName = (TextView) mainView.findViewById(R.id.wode_name);
        wodeName.setText(mainActivity.me.getName());
        listViewTotal = (ListView) mainView.findViewById(R.id.lv_total);
        loadingBar = (ProgressBar) mainView.findViewById(R.id.loading);
        // 地图初始化
        mMapView = (MapView) mainView.findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        requestTotal();
        return mainView;
    }

    @Override
    public void onStart() {
        // TODO testcode
        updateListViewTotal();
        addpointonMap();
        super.onStart();
    }
    
    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     */
    private void addpointonMap() {
        double lat = 36.184572;
        double lng = 120.411623;
        LatLng ll = new LatLng(lat, lng);
        MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
        mBaiduMap.animateMapStatus(u);
        int Max = 1000;
        int Min = -1000;
        for (int i = 0; i <= 100; i++) {

            long tem = Math.round(Math.random() * (Max - Min) + Min);
            long tem2 = Math.round(Math.random() * (Max - Min) + Min);
            // 定义Maker坐标点
            LatLng point = new LatLng(lat - tem * 0.00001, lng - tem2 * 0.00001);
            // 构建Marker图标
            BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_marki);
            // 构建MarkerOption，用于在地图上添加Marker
            OverlayOptions option = new MarkerOptions().position(point).icon(bitmap);
            // 在地图上添加Marker，并显示
            mBaiduMap.addOverlay(option);
        }

    }

    /**
     * <p>
     * Title: requestTotal.
     * </p>
     * <p>
     * Description: 请求历史Parking记录.
     * </p>
     * 
     */
    private void requestTotal() {
        ClientServerMessage m = new ClientServerMessage();
        m.setMessageType(MessageConst.MessageType.MSG_TYPE_COMPANY_LIST_COST);
        m.setMessageContent(JsonTool.getString(mainActivity.me));
        m.setMessageParameters("~~~~~");
        mainActivity.mService.sendMessageToServer(m);
    }

    private void updateListViewTotal() {
        // TODO show on UI
        // test code
        TextView t = new TextView(mainActivity);
        t.setText("get Total success!");
        LayoutParams lp = llTotal.getLayoutParams();
        lp.height = LayoutParams.WRAP_CONTENT;
        lp.width = LayoutParams.MATCH_PARENT;
        t.setLayoutParams(lp);
        llTotal.addView(t, 0);

        // 显示主View
        llTotal.setVisibility(View.VISIBLE);
        // 隐藏缓冲圈
        loadingBar.setVisibility(View.INVISIBLE);

    }

    @Override
    public void refreshUI(AMessage msg) {
        Log.i("");
        switch (msg.getMessageType()) {
            case MessageConst.MessageType.MSG_TYPE_COMPANY_LIST_COST:
                if (msg.getMessageResult() == MessageConst.MessageResult.MSG_RESULT_SUCCESS) {
                    Log.i("success");
                    parkingList = JsonTool.getParkingInfoList(msg.getMessageContent());
                } else {

                }
                updateListViewTotal();
                break;

            default:
                break;
        }
    }

}
