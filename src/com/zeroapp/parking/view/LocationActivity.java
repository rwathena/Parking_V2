package com.zeroapp.parking.view;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import com.zeroapp.parking.R;
import com.zeroapp.parking.client.ClientService;
import com.zeroapp.parking.common.CarInfo;
import com.zeroapp.parking.common.ParkingInfo;
import com.zeroapp.parking.locator.Tracer;
import com.zeroapp.parking.message.ClientServerMessage;
import com.zeroapp.parking.message.MessageConst;
import com.zeroapp.utils.JsonTool;
import com.zeroapp.utils.Log;
import com.zeroapp.utils.MyTime;

/**
 * 此demo用来展示如何结合定位SDK实现定位，并使用MyLocationOverlay绘制定位位置 同时展示如何使用自定义图标绘制并点击时弹出泡泡
 * 
 */
public class LocationActivity extends Activity implements Tracer {

	MapView mMapView;
	BaiduMap mBaiduMap;
    private Button btnStartParking;
	// UI相关
	OnCheckedChangeListener radioButtonListener;
	Button requestLocButton;
    /**
     * 是否首次定位.第一次定位则地图会自动定位到当前位置
     */
    private boolean isFirstLoc = true;
    BDLocation l;
    private ClientService mService;
    private ListView lvCarsParingInfo;
    private List<ParkingInfo> mParkingList = new ArrayList<ParkingInfo>();
    private ServiceConnection connection;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        Log.e("+++ ON CREATE +++");
        setContentView(R.layout.activity_locate);
        initParkingInfos();

        lvCarsParingInfo = (ListView) findViewById(R.id.lv_car_parking);
        lvCarsParingInfo.setAdapter(new CarParkingAdeptet(this, mParkingList));

		// 地图初始化
		mMapView = (MapView) findViewById(R.id.bmapView);
		mBaiduMap = mMapView.getMap();
		// 开启定位图层
		mBaiduMap.setMyLocationEnabled(true);
        // 设置模式和图标
        mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(LocationMode.NORMAL, true, null));
        bindLocateService();
    }

    /**
     * <p>
     * Title: initParkingInfos.
     * </p>
     * <p>
     * Description: 结合MainActivity.mCars信息初始化Parkinginfo列表,已有数据从本地数据库取出.
     * </p>
     * 
     */
    private void initParkingInfos() {
        // TODO Test Code
        for (CarInfo c : UserActivity.myCars) {
            ParkingInfo p = new ParkingInfo();
            p.setCarNum(c.getCarNum());
            mParkingList.add(p);
        }

    }

    @Override
	protected void onPause() {
		mMapView.onPause();
        testUpdateParkingInfo();
		super.onPause();
        Log.e("- ON RESUME -");
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
    private void testUpdateParkingInfo() {
        long t = System.currentTimeMillis();
        ParkingInfo p = mParkingList.get(0);
        p.setLocationLatitude(l.getLatitude());
        p.setLocationLongitude(l.getLongitude());
        p.setTimeStart(t - 3200000);
        p.setTimeEnd(t);
        ClientServerMessage m = new ClientServerMessage();
        m.setMessageType(MessageConst.MessageType.MSG_TYPE_USER_SEND_PARK_INFO);
        m.setMessageContent(JsonTool.getString(p));
//        UserActivity.getBox().sendMessage(m);
        Log.i("send over");
        
    }

    @Override
	protected void onResume() {
		mMapView.onResume();
		super.onResume();
        Log.e("+ ON RESUME +");
	}

	@Override
	protected void onDestroy() {
        unbindService(connection);
		// 关闭定位图层
		mBaiduMap.setMyLocationEnabled(false);
		mMapView.onDestroy();
		mMapView = null;
		super.onDestroy();
        Log.e("--- ON DESTROY ---");
	}

    private void bindLocateService() {
        connection = new ServiceConnection() {

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d("Disconnected " + name);
                mService.removeLocationListener(LocationActivity.this);
                mService = null;// TODO
            }

            @Override
            public void onServiceConnected(ComponentName name, IBinder binder) {
                Log.d("Connected " + name);
                mService = ((ClientService.MyBinder) binder).getService();
                mService.addLocationListener(LocationActivity.this);
            }
        };
        Intent i = new Intent(this, ClientService.class);
        i.setAction("com.zeroapp.parking.locator.LocateService");
        bindService(i, connection, Context.BIND_AUTO_CREATE);

    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @param location
     */
    @Override
    public void onLocationChanged(BDLocation location) {
        // 位置信息初始化
        l = location;
        MyLocationData locData = new MyLocationData.Builder().accuracy(l.getRadius()).direction(l.getDirection()).latitude(l.getLatitude()).longitude(l.getLongitude()).build();
        // 设置位置信息
        mBaiduMap.setMyLocationData(locData);
        // 定位到当前位置
        if (isFirstLoc) {
            isFirstLoc = false;
            LatLng ll = new LatLng(l.getLatitude(), l.getLongitude());
            MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
            mBaiduMap.animateMapStatus(u);
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
     * @param location
     */
    @Override
    public void onComingBack(BDLocation location) {
        Log.d("");
    }

    public class CarParkingAdeptet extends BaseAdapter {

        private List<ParkingInfo> parkings = null;
        private Context mContext = null;;

        public CarParkingAdeptet(Context ctx, List<ParkingInfo> l) {
            this.mContext = ctx;
            parkings = l;
        }

        @Override
        public int getCount() {
            return parkings.size();
        }

        @Override
        public Object getItem(int i) {
            return parkings.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View v, ViewGroup group) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            final Holder h;
            if (v != null) {
                h = (Holder) v.getTag();
            } else {
                v = inflater.inflate(R.layout.lvitem_parkinginfo, null);
                h = new Holder();
                h.carNum = (TextView) v.findViewById(R.id.car_num);
                h.carNum.setText(parkings.get(i).getCarNum());
                h.startTime = (TextView) v.findViewById(R.id.park_start_time);
//                h.startTime.setText(parkings.get(i).getTimeStart() == 0 ? "" : MyTime.getStringTime(parkings.get(i).getTimeStart()));
                h.btnStartParking = (Button) v.findViewById(R.id.btn_start_parking);
                h.btnStopParking = (Button) v.findViewById(R.id.btn_stop_parking);
//                h.btnStartParking.setVisibility(parkings.get(i).getTimeStart() == 0 ? View.VISIBLE : View.INVISIBLE);
//                h.btnStopParking.setVisibility(parkings.get(i).getTimeStart() != 0 ? View.VISIBLE : View.INVISIBLE);
                h.btnStartParking.setTag(parkings.get(i));
                h.btnStopParking.setTag(parkings.get(i));
            }
            OnClickListener listener = new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (v == h.btnStartParking) {
                        Log.i("btnStartParking");
                        long t = MyTime.getLongTime(((LocationActivity) mContext).l.getTime());
                        ((ParkingInfo) v.getTag()).setTimeStart(t);
                    }
                    if (v == h.btnStopParking) {
                        Log.i("btnStopParking");
                        long t = MyTime.getLongTime(((LocationActivity) mContext).l.getTime());
                        ((ParkingInfo) v.getTag()).setTimeEnd(t);
                    }
                }
            };
            h.btnStartParking.setOnClickListener(listener);
            h.btnStopParking.setOnClickListener(listener);

            return v;
        }

        private class Holder {

            public TextView carNum = null;
            public TextView startTime = null;
            public Button btnStartParking = null;
            public Button btnStopParking = null;

        }

    }



}
