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
import com.zeroapp.parking.common.BiddingContainer;
import com.zeroapp.parking.common.Voting;
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
public class BiddingFragment extends BaseFragment {

    private UserActivity mainActivity;
    private View mainView;
    private TextView cityName;
    private ListView listViewBiddings;
    private ProgressBar loadingBar;
    private LinearLayout llBidding;
    private List<BiddingContainer> bs;

    @Override
    public void onAttach(Activity activity) {
        Log.i("onAttach");
        super.onAttach(activity);
        mainActivity = (UserActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("onCreateView");
        mainView = inflater.inflate(R.layout.fragment_bidddings, null);
        llBidding = (LinearLayout) mainView.findViewById(R.id.ll_bidding);
        cityName = (TextView) mainView.findViewById(R.id.city_name);
        cityName.setText("青岛");// TODO
        listViewBiddings = (ListView) mainView.findViewById(R.id.lv_biddings);
        loadingBar = (ProgressBar) mainView.findViewById(R.id.loading);
        requestBiddings();
        return mainView;
    }

    /**
     * <p>
     * Title: requestBiddings.
     * </p>
     * <p>
     * Description: 请求bidding列表.
     * </p>
     * 
     */
    private void requestBiddings() {
        ClientServerMessage m = new ClientServerMessage();
        m.setMessageType(MessageConst.MessageType.MSG_TYPE_USER_LIST_BIDDING);
        m.setMessageContent("qingdao");// TODO
        mainActivity.mService.sendMessageToServer(m);
    }

    private void updateListViewBiddings() {
        listViewBiddings.setAdapter(new BiddingsAdeptet(mainActivity, bs));
        listViewBiddings.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View v, int i, long arg3) {
                if (i != 0) {
                    final BiddingContainer b = (BiddingContainer) adapterView.getAdapter().getItem(i - 1);
                    final ConfirmDialog confirmDialog = new ConfirmDialog(mainActivity, "确定要选该广告吗?", "确定", "取消");
                    confirmDialog.show();
                    confirmDialog.setClicklistener(new ConfirmDialog.ClickListenerInterface() {

                        @Override
                        public void doConfirm() {
                            confirmDialog.dismiss();
                            Voting v = new Voting();
                            v.setCarNum(mainActivity.myCars.get(0).getCarNum());
                            v.setBiddingID(b.getBiddingID());
                            ClientServerMessage m = new ClientServerMessage();
                            m.setMessageType(MessageConst.MessageType.MSG_TYPE_USER_CREATE_VOTING);
                            m.setMessageContent(JsonTool.getString(v));
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
        llBidding.setVisibility(View.VISIBLE);
        // 隐藏缓冲圈
        loadingBar.setVisibility(View.INVISIBLE);

    }

    @Override
    public void refreshUI(AMessage msg) {
        Log.i("");
        switch (msg.getMessageType()) {
            case MessageConst.MessageType.MSG_TYPE_USER_LIST_BIDDING:
                if (msg.getMessageResult() == MessageConst.MessageResult.MSG_RESULT_SUCCESS) {
                    Log.i("success");
                    Log.d("getMessageContent: " + msg.getMessageContent());
                    bs = JsonTool.getBiddingsContainerList(msg.getMessageContent());// TODO
                } else if (msg.getMessageResult() == MessageConst.MessageResult.MSG_RESULT_FAIL) {
                    Log.i("fail");
                }
                updateListViewBiddings();
                break;

            default:
                break;
        }
    }

    public class BiddingsAdeptet extends BaseAdapter {

        private List<BiddingContainer> biddings = null;
        private Context mContext = null;;

        public BiddingsAdeptet(Context ctx, List<BiddingContainer> bs) {
            this.mContext = ctx;
            this.biddings = bs;
        }

        @Override
        public int getCount() {
            if (biddings != null) {
                return biddings.size() + 1;
            }
            return 1;
        }

        @Override
        public Object getItem(int i) {
            if (biddings != null && biddings.size() != 0) {
                return biddings.get(i);
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
            v = inflater.inflate(R.layout.lvitem_biddings, null);
            TextView num = (TextView) v.findViewById(R.id.num);
            TextView earning = (TextView) v.findViewById(R.id.earning);
            TextView areaName = (TextView) v.findViewById(R.id.area_name);
            TextView company = (TextView) v.findViewById(R.id.company_name);
            TextView startTime = (TextView) v.findViewById(R.id.starttime);
            TextView endTime = (TextView) v.findViewById(R.id.endtime);
            if (i == 0) {
                num.setText("  ");
                earning.setText("收益");
                areaName.setText("区域");
                company.setText("公司");
                startTime.setText("起始时间");
                endTime.setText("结束时间");
            } else {
                num.setText(i + "");
                earning.setText(biddings.get(i - 1).getEarnings() + "");
                areaName.setText(biddings.get(i - 1).getAreaName());
                company.setText(biddings.get(i - 1).getComName());
                startTime.setText(biddings.get(i - 1).getTimeStart());
                endTime.setText(biddings.get(i - 1).getTimeEnd());
            }
            return v;
        }

    }
}
