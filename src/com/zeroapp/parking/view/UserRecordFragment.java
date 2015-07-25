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
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;

import java.util.List;

import com.zeroapp.parking.R;
import com.zeroapp.parking.common.ParkingInfo;
import com.zeroapp.parking.message.AMessage;
import com.zeroapp.parking.message.ClientServerMessage;
import com.zeroapp.parking.message.MessageConst;
import com.zeroapp.utils.JsonTool;
import com.zeroapp.utils.Log;
import com.zeroapp.utils.MyTime;


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
public class UserRecordFragment extends BaseFragment {

    private View mainView;
    private UserActivity mainActivity;
    private TextView wodeName;
    private ListView listViewTotal;
    private ProgressBar loadingBar;
    private LinearLayout llTotal;
    private List<ParkingInfo> parkingList;

    @Override
    public void onAttach(Activity activity) {
        Log.i("onAttach");
        super.onAttach(activity);
        mainActivity = (UserActivity) getActivity();
        // reqData = new HashMap<String, Object>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("onCreateView");
        mainView = inflater.inflate(R.layout.fragment_user_record, null);
        llTotal = (LinearLayout) mainView.findViewById(R.id.ll_total);
        wodeName = (TextView) mainView.findViewById(R.id.wode_name);
        wodeName.setText(mainActivity.me.getName());
        listViewTotal = (ListView) mainView.findViewById(R.id.lv_total);
        loadingBar = (ProgressBar) mainView.findViewById(R.id.loading);
        requestTotal();
        return mainView;
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
        m.setMessageType(MessageConst.MessageType.MSG_TYPE_USER_LIST_MONEY);
        m.setMessageContent(JsonTool.getString(mainActivity.me));
        mainActivity.mService.sendMessageToServer(m);
    }

    private void updateListViewTotal() {
        listViewTotal.setAdapter(new ParkingAdeptet(mainActivity, parkingList));
        listViewTotal.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View v, int i, long arg3) {

            }
        });

        // 显示主View
        llTotal.setVisibility(View.VISIBLE);
        // 隐藏缓冲圈
        loadingBar.setVisibility(View.INVISIBLE);

    }

    @Override
    public void refreshUI(AMessage msg) {
        Log.i("");
        switch (msg.getMessageType()) {
            case MessageConst.MessageType.MSG_TYPE_USER_LIST_MONEY:
                if (msg.getMessageResult() == MessageConst.MessageResult.MSG_RESULT_SUCCESS) {
                    parkingList = JsonTool.getParkingInfoList(msg.getMessageContent());
                } else {
                    Log.i("fail!");
                }
                updateListViewTotal();
                break;

            default:
                break;
        }
    }

    public class ParkingAdeptet extends BaseAdapter {

        private List<ParkingInfo> ps = null;
        private Context mContext = null;;

        public ParkingAdeptet(Context ctx, List<ParkingInfo> parkingList) {
            this.mContext = ctx;
            this.ps = parkingList;
        }

        @Override
        public int getCount() {
            if (ps != null) {
                return ps.size() + 1;
            }
            return 1;
        }

        @Override
        public Object getItem(int i) {
            if (ps != null && ps.size() != 0) {
                return ps.get(i);
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
            v = inflater.inflate(R.layout.lvitem_parking_record, null);
            TextView num = (TextView) v.findViewById(R.id.car_num);
            TextView earning = (TextView) v.findViewById(R.id.earning);
            TextView startTime = (TextView) v.findViewById(R.id.starttime);
            TextView endTime = (TextView) v.findViewById(R.id.endtime);
            TextView address = (TextView) v.findViewById(R.id.parking_addr);
            if (i == 0) {
                num.setText("车牌");
                earning.setText("收益");
                startTime.setText("起始时间");
                endTime.setText("结束时间");
                address.setText("停车地");
            } else {
                num.setText(ps.get(i - 1).getCarNum());
                earning.setText(ps.get(i - 1).getMoneyEarning() + "");
                startTime.setText(MyTime.getTimeNoS(ps.get(i - 1).getTimeStart()));
                endTime.setText(MyTime.getTimeNoS(ps.get(i - 1).getTimeEnd()));
                getLocationName(ps.get(i - 1).getLocationLatitude(), ps.get(i - 1).getLocationLongitude(), address);
            }
            return v;
        }

    }

    private void getLocationName(double lat, double lng, final TextView v) {
        GeoCoder mGeoCoder = GeoCoder.newInstance();
        ReverseGeoCodeOption mReverseGeoCodeOption = new ReverseGeoCodeOption();
        mReverseGeoCodeOption.location(new LatLng(lat, lng));
        mGeoCoder.reverseGeoCode(mReverseGeoCodeOption);
        mGeoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {


            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult arg0) {
                Log.i("call back,: " + arg0.getAddress().toString() + "\n" + arg0.getAddressDetail().street);
                v.setText(arg0.getAddress());
            }

            @Override
            public void onGetGeoCodeResult(GeoCodeResult arg0) {
                // TODO Auto-generated method stub
            }
        });
    }
}
