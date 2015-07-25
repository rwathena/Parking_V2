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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.zeroapp.parking.R;
import com.zeroapp.parking.common.User;
import com.zeroapp.parking.message.AMessage;
import com.zeroapp.parking.message.ClientServerMessage;
import com.zeroapp.parking.message.MessageConst;
import com.zeroapp.utils.JsonTool;
import com.zeroapp.utils.Log;

/**
 * <p>Title: TODO.</p>
 * <p>Description: TODO.</p>
 *
 * @author Alex(zeroapp@126.com) 2015-6-30.
 * @version $Id$
 */

public class SigninActivity extends BaseActivity {

    private EditText editTextAccount;
    private EditText editTextPwd;
    private Button buttonSingin;
    private Button buttonSingup;
    private ProgressBar loadingBar;
    private LinearLayout llSignin;
    private User newUser = new User();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("+++ ON CREATE +++");
        setContentView(R.layout.activity_signin);
        initView();
    }
    private void initView() {
        llSignin = (LinearLayout) findViewById(R.id.ll_signin);
        loadingBar = (ProgressBar) findViewById(R.id.loading);
        editTextAccount = (EditText) findViewById(R.id.et_account);
        editTextPwd = (EditText) findViewById(R.id.et_password);
        buttonSingin = (Button) findViewById(R.id.btn_signin);
        buttonSingup = (Button) findViewById(R.id.btn_signup);
        buttonSingin.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (editTextAccount.getText().toString().equals("") || editTextPwd.getText().toString().equals("")) {
                    Toast.makeText(SigninActivity.this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    newUser.setAccount(editTextAccount.getText().toString());
                    newUser.setPassword(editTextPwd.getText().toString());
                    sendMeToServer(newUser);
                    llSignin.setVisibility(View.INVISIBLE);
                    loadingBar.setVisibility(View.VISIBLE);
                }

            }
        });
        buttonSingup.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // 启动注册界面
                Intent i = new Intent(SigninActivity.this, SignupActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    private void sendMeToServer(User me) {
        ClientServerMessage m = new ClientServerMessage();
        m.setMessageType(MessageConst.MessageType.MSG_TYPE_USER_SIGN_IN);
        m.setMessageContent(JsonTool.getString(me));
        mService.sendMessageToServer(m);
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
            case MessageConst.MessageType.MSG_TYPE_USER_SIGN_IN:
                if (m.getMessageResult() == MessageConst.MessageResult.MSG_RESULT_SUCCESS) {
                } else if (m.getMessageResult() == MessageConst.MessageResult.MSG_RESULT_FAIL) {
                    Log.i("fail");
                    Toast.makeText(SigninActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                    editTextAccount.setText("");
                    editTextPwd.setText("");
                    llSignin.setVisibility(View.VISIBLE);
                    loadingBar.setVisibility(View.INVISIBLE);
                }
                break;
            default:
                break;
        }
    }
}
