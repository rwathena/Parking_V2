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

package com.zeroapp.parking.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;

import com.zeroapp.parking.R;


/**
 * <p>Title: TODO.</p>
 * <p>Description: TODO.</p>
 *
 * @author Alex(zeroapp@126.com) 2015-6-15.
 * @version $Id$
 */

public class MapLocateDialog extends BaseDialog {


    private View mainView;
    private MapView mMapView;
    private TextView t1;
    private Button btnStartParking;
    private Context mContext;
    private BaiduMap mBaiduMap;

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @param ctx
     * @param res
     */
    public MapLocateDialog(Context ctx, int res) {
        super(ctx, res);
        mContext = ctx;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        mainView = inflater.inflate(R.layout.activity_locate, null);
        mMapView = (MapView) mainView.findViewById(R.id.bmapView);
//        t1 = (TextView) mainView.findViewById(R.id.text1);
//        btnStartParking = (Button) mainView.findViewById(R.id.start_parking);
        mBaiduMap = mMapView.getMap();
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 定位初始化

        mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(LocationMode.FOLLOWING, true, null));
        locate();
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
    private void locate() {
//        BDLocation l = Tracer.getInstance(mContext).getLocation();
//        MyLocationData locData = new MyLocationData.Builder().accuracy(l.getRadius())
//        // 此处设置开发者获取到的方向信息，顺时针0-360
//                .direction(100).latitude(l.getLatitude()).longitude(l.getLongitude()).build();
//        mBaiduMap.setMyLocationData(locData);
//
//        LatLng ll = new LatLng(l.getLatitude(), l.getLongitude());
//            MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
//            mBaiduMap.animateMapStatus(u);

    }

}
