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

import android.os.Handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import com.zeroapp.parking.message.ClientServerMessage;
import com.zeroapp.parking.message.MessageConst;
import com.zeroapp.utils.Log;

/**
 * <p>
 * Title: MessageBox.
 * </p>
 * <p>
 * Description: MessageBox used to receiver ClientServerMessage form Server and
 * send ClientServerMessage to Server.
 * </p>
 * 
 * @author Alex(zeroapp@126.com) 2015-6-4.
 * @version $Id$
 */

public class MessageBox extends ChannelInboundHandlerAdapter {

    private Handler mHandler = null;
    private ChannelHandlerContext mctx;

    public MessageBox(Handler handler) {
        mHandler = handler;
    }

    public void sendMessage(ClientServerMessage m) {
        if (mctx != null) {
            mctx.writeAndFlush(m);
        }

    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        Log.d("");
        mctx = ctx;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg != null) {
            ClientServerMessage m = (ClientServerMessage) msg;
            Log.d("" + m.getMessageContent());
            mHandler.obtainMessage(MessageConst.MessageType.MESSAGE_FROM_SERVER, m).sendToTarget();
        } else {
            Log.w("null");
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
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
    }

}
