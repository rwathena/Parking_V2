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

import java.util.List;

import com.zeroapp.parking.R;
import com.zeroapp.parking.common.Bidding;
import com.zeroapp.parking.common.CommercialDetails;
import com.zeroapp.parking.dialog.ConfirmDialog;
import com.zeroapp.parking.message.AMessage;
import com.zeroapp.parking.message.ClientServerMessage;
import com.zeroapp.parking.message.MessageConst;
import com.zeroapp.utils.JsonTool;
import com.zeroapp.utils.Log;


/**
 * <p>
 * Title: BiddingFragment.
 * </p>
 * <p>
 * Description: 用来显示bidding列表.
 * </p>
 * 
 * @author Alex(zeroapp@126.com) 2015-5-28.
 * @version $Id$
 */
public class BusinessFragment extends BaseFragment {

    private AdmanActivity mainActivity;
    private View mainView;
    private TextView cityName;
    private ListView listViewBbusiness;
    private ProgressBar loadingBar;
    private LinearLayout llBbusiness;
    private List<CommercialDetails> bs;

    @Override
    public void onAttach(Activity activity) {
        Log.i("onAttach");
        super.onAttach(activity);
        mainActivity = (AdmanActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("onCreateView");
        mainView = inflater.inflate(R.layout.fragment_business, null);
        llBbusiness = (LinearLayout) mainView.findViewById(R.id.ll_bbusiness);
        cityName = (TextView) mainView.findViewById(R.id.city_name);
        cityName.setText("青岛");// TODO
        listViewBbusiness = (ListView) mainView.findViewById(R.id.lv_business);
        loadingBar = (ProgressBar) mainView.findViewById(R.id.loading);
        requestbusinessList();
        return mainView;
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
    protected void testBid() {
        Bidding b = new Bidding();
        b.setBusinessID(bs.get(0).getBusinessID());
        b.setUserID(mainActivity.me.getUserID());
        ClientServerMessage m = new ClientServerMessage();
        m.setMessageType(MessageConst.MessageType.MSG_TYPE_COMPANY_CREATE_BIDDING);
        m.setMessageContent(JsonTool.getString(b));
        mainActivity.mService.sendMessageToServer(m);

    }

    /**
     * <p>
     * Title: requestbusinessList.
     * </p>
     * <p>
     * Description: 请求business列表.
     * </p>
     * 
     */
    private void requestbusinessList() {
        ClientServerMessage m = new ClientServerMessage();
        m.setMessageType(MessageConst.MessageType.MSG_TYPE_COMPANY_LIST_BUSINESS);
        m.setMessageContent("qingdao");// TODO
        mainActivity.mService.sendMessageToServer(m);

    }

    private void updateListViewBusinesses() {
        listViewBbusiness.setAdapter(new BusinessAdeptet(mainActivity, bs));
        listViewBbusiness.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View v, int i, long arg3) {
                if (i != 0) {
                    final CommercialDetails c = (CommercialDetails) adapterView.getAdapter().getItem(i - 1);
                    final ConfirmDialog confirmDialog = new ConfirmDialog(mainActivity, "确定要选该广告吗?", "确定", "取消");
                    confirmDialog.show();
                    confirmDialog.setClicklistener(new ConfirmDialog.ClickListenerInterface() {

                        @Override
                        public void doConfirm() {
                            confirmDialog.dismiss();
                            Bidding b = new Bidding();
                            b.setBusinessID(c.getBusinessID());
                            b.setUserID(mainActivity.me.getUserID());
                            ClientServerMessage m = new ClientServerMessage();
                            m.setMessageType(MessageConst.MessageType.MSG_TYPE_COMPANY_CREATE_BIDDING);
                            m.setMessageContent(JsonTool.getString(b));
                            mainActivity.mService.sendMessageToServer(m);
                        }

                        @Override
                        public void doCancel() {
                            confirmDialog.dismiss();
                        }
                    });
                }
            }
        });
        // 显示主View
        llBbusiness.setVisibility(View.VISIBLE);
        // 隐藏缓冲圈
        loadingBar.setVisibility(View.INVISIBLE);

    }

    @Override
    public void refreshUI(AMessage msg) {
        Log.i("");
        switch (msg.getMessageType()) {
            case MessageConst.MessageType.MSG_TYPE_COMPANY_LIST_BUSINESS:
                if (msg.getMessageResult() == MessageConst.MessageResult.MSG_RESULT_SUCCESS) {
                    Log.i("success");
                    bs = JsonTool.getBusinessList(msg.getMessageContent());
                } else if (msg.getMessageResult() == MessageConst.MessageResult.MSG_RESULT_FAIL) {
                    Log.i("fail");
                }
                updateListViewBusinesses();
                break;

            default:
                break;
        }
    }

    public class BusinessAdeptet extends BaseAdapter {

        private List<CommercialDetails> businesses = null;
        private Context mContext = null;;

        public BusinessAdeptet(Context ctx, List<CommercialDetails> bs) {
            this.mContext = ctx;
            this.businesses = bs;
        }

        @Override
        public int getCount() {
            if (businesses != null) {
                return businesses.size() + 1;
            }
            return 1;
        }

        @Override
        public Object getItem(int i) {
            if (businesses != null && businesses.size() != 0) {
                return businesses.get(i);
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
            v = inflater.inflate(R.layout.lvitem_business, null);
            TextView num = (TextView) v.findViewById(R.id.num);
            TextView areaName = (TextView) v.findViewById(R.id.area_name);
            TextView userNum = (TextView) v.findViewById(R.id.max_user_num);
            TextView startTime = (TextView) v.findViewById(R.id.starttime);
            TextView endTime = (TextView) v.findViewById(R.id.endtime);
            TextView cost = (TextView) v.findViewById(R.id.cost);
            if (i == 0) {
                num.setText("  ");
                areaName.setText("区域");
                userNum.setText("容量");
                startTime.setText("起始时间");
                endTime.setText("结束时间");
                cost.setText("成本");
            } else {
                num.setText(i + "");
                areaName.setText(businesses.get(i - 1).getAreaName());
                userNum.setText(businesses.get(i - 1).getMaxUserCount() + "");//
                startTime.setText(businesses.get(i - 1).getTimeStart());
                endTime.setText(businesses.get(i - 1).getTimeEnd());
                cost.setText(businesses.get(i - 1).getCost() + "");
            }
            return v;
        }

    }
}
