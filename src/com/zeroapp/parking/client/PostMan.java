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

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import com.zeroapp.utils.Config;
import com.zeroapp.utils.Log;


/**
 * <p>
 * Title: PostMan.
 * </p>
 * <p>
 * Description: 与服务器建立连接的工具类.
 * </p>
 * 
 * @author Alex(zeroapp@126.com) 2015-6-4.
 * @version $Id$
 */

public class PostMan implements Runnable {

    private MessageBox mBox = null;
    private OnConnectStateChangeListener mConnectStateChangeListener = null;

    public PostMan(MessageBox box) {
        this.mBox = box;

    }

    @Override
    public void run() {
        Log.d("");
        Bootstrap bootstrap = new Bootstrap();
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {

                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new ObjectEncoder(), new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)), mBox);
                }
            });
            ChannelFuture future = bootstrap.connect(Config.HOST_ADRESS, Config.HOST_PORT).sync();
            mConnectStateChangeListener.onConnect();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
            mConnectStateChangeListener.onDisconnect();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }

    }

    /**
     * <p>
     * Title: getConnectStateChangeListener.
     * </p>
     * <p>
     * Description: getConnectStateChangeListener.
     * </p>
     * 
     * @return the l.
     */
    public OnConnectStateChangeListener getConnectStateChangeListener() {
        return mConnectStateChangeListener;
    }

    /**
     * <p>
     * Title: setConnectStateChangeListener.
     * </p>
     * <p>
     * Description: setConnectStateChangeListener.
     * </p>
     * 
     * @param l
     *            the l to set.
     */
    public void setConnectStateChangeListener(OnConnectStateChangeListener l) {
        this.mConnectStateChangeListener = l;
    }

}
