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

package com.zeroapp.parking.message;

import java.io.Serializable;

/**
 * <p>Title: TODO.</p>
 * <p>Description: TODO.</p>
 *
 * @author Alex(zeroapp@126.com) 2015-5-28.
 * @version $Id$
 */

public abstract class AMessage implements Serializable {

    /**
     * TODO
     */
    private static final long serialVersionUID = -3333863840063595999L;
    private int mMessageResult = -1;
    private int mMessageType = -1;
    private String mMessageContent = "";
    private long mMessageSendTime = 0;
    private String parameters;

    public int getMessageResult() {
        return mMessageResult;
    }

    public void setMessageResult(int messageResult) {
        mMessageResult = messageResult;
    }

    public int getMessageType() {
        return mMessageType;
    }

    public void setMessageType(int messageType) {
        mMessageType = messageType;
    }

    public String getMessageContent() {
        return mMessageContent;
    }

    public void setMessageContent(String messageContent) {
        mMessageContent = messageContent;
    }

    public long getMessageSendTime() {
        return mMessageSendTime;
    }

    public void setMessageSendTime(long messageSendTime) {
        mMessageSendTime = messageSendTime;
    }
    public void setMessageParameters(String para){
    	this.parameters = para;
    }
    public String getMessageParameters(){
    	return parameters;
    }

}
