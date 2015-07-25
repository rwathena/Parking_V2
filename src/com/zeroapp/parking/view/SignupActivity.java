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

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.zeroapp.parking.R;
import com.zeroapp.parking.common.User;
import com.zeroapp.parking.message.AMessage;
import com.zeroapp.parking.message.ClientServerMessage;
import com.zeroapp.parking.message.MessageConst;
import com.zeroapp.utils.JsonTool;
import com.zeroapp.utils.Log;
import android.widget.CompoundButton; 



/**
 * <p>
 * Title: SignupActivity.
 * </p>
 * <p>
 * Description: SignupActivity.
 * </p>
 * 
 * @author Alex(zeroapp@126.com) 2015-6-30.
 * @version $Id$
 */

public class SignupActivity extends BaseActivity {

    private RelativeLayout rlSignin;
    private ProgressBar loadingBar;
    private EditText accountEt;
    private EditText passwordEt;
    private CheckBox agreementCb;
    private CheckBox adManCb;
    private EditText phoneNumEt;
    protected User newUser = new User();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("+++ ON CREATE +++");
        setContentView(R.layout.activity_signup);
        initView();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
    private void initView() {
        rlSignin = (RelativeLayout) findViewById(R.id.rl_signup);
        loadingBar = (ProgressBar) findViewById(R.id.loading);
        accountEt = (EditText) findViewById(R.id.register_account);
        passwordEt = (EditText) findViewById(R.id.register_password);
        phoneNumEt = (EditText) findViewById(R.id.register_phone);
        adManCb = (CheckBox) findViewById(R.id.cb_ifadvertisingman);
        agreementCb = (CheckBox) findViewById(R.id.cb_agreement);
//        EditText nickEt = (EditText) mainView.findViewById(R.id.register_nick);
        RadioGroup group = (RadioGroup) findViewById(R.id.register_radiogroup);
        group.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            public void onCheckedChanged(RadioGroup arg0, int id) {
                if (id == R.id.register_radio_nv) {
                    newUser.setSex(0);// 女
                }
            }
        });
        findViewById(R.id.rigister_btn_register).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
            	adManCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

					@Override
					public void onCheckedChanged(CompoundButton arg0,
							boolean arg1) {
						
						if (arg1) {
							me.setUserType("2_0");
						} else {
							me.setUserType("3_0");
						}
					}
					
				});
                if (accountEt.getText().toString().equals("") || passwordEt.getText().toString().equals("")) {
                    Toast.makeText(SignupActivity.this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
                } else if (!agreementCb.isChecked()) {
                    Toast.makeText(SignupActivity.this, "请阅读并同意<用户协议>", Toast.LENGTH_SHORT).show();
                } else if (phoneNumEt.getText().toString().equals("")) {
                    // TODO 检查手机号码是否违规
                    Toast.makeText(SignupActivity.this, "手机号码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    newUser.setAccount(accountEt.getText().toString());
                    newUser.setPassword(passwordEt.getText().toString());
                    newUser.setPhoneNum(phoneNumEt.getText().toString());
                    ClientServerMessage m = new ClientServerMessage();
                    m.setMessageType(MessageConst.MessageType.MSG_TYPE_USER_SIGN_UP);
                    m.setMessageContent(JsonTool.getString(newUser));
                    mService.sendMessageToServer(m);
                    rlSignin.setVisibility(View.INVISIBLE);
                    loadingBar.setVisibility(View.VISIBLE);
                }
            }
        });
        findViewById(R.id.rigister_btn_cancel).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // 启动登录界面
                Intent i = new Intent(SignupActivity.this, SigninActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

    }

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @param m
     */
    @Override
    public void dealMessage(AMessage m) {
        switch (m.getMessageType()) {
            case MessageConst.MessageType.MSG_TYPE_USER_SIGN_UP:
                if (m.getMessageResult() == MessageConst.MessageResult.MSG_RESULT_SUCCESS) {
                } else if (m.getMessageResult() == MessageConst.MessageResult.MSG_RESULT_FAIL) {
                    Log.i("fail");
                    Toast.makeText(SignupActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                    accountEt.setText("");
                    passwordEt.setText("");
                    phoneNumEt.setText("");
                    rlSignin.setVisibility(View.VISIBLE);
                    loadingBar.setVisibility(View.INVISIBLE);
                }
                break;
            default:
                break;
        }
    }

}
