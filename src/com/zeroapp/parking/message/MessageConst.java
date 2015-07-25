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

/**
 * <p>
 * Title: TODO.
 * </p>
 * <p>
 * Description: TODO.
 * </p>
 * 
 * @author Alex(zeroapp@126.com) 2015-5-28.
 * @version $Id$
 */
public class MessageConst {

    public class MessageResult {

        public static final int MSG_RESULT_FAIL = 0;
        public static final int MSG_RESULT_PARKING_NOT_AVAILABLE = -1001;
        public static final int MSG_RESULT_SUCCESS = 1;
        public static final int SQL_OPREATION_FAILURE_INT = -1;
        public static final String SQL_QUERY_FAILURE="sqlexp";
        public static final String MSG_RESULT_SUCCESS_STRING = "suc";
        public static final String MSG_RESULT_FAIL_STRIGN = "fail";
        public static final int SQL_OPREATION_EXCEPTION_INT = -2;
        public static final String SQL_OPREATION_EXCEPTION_STRING = "sqlexp";
    }

    public class MessageType {

        // Local message
        public static final int MESSAGE_UI = 1;
        // Message types sent to server
        public static final int MESSAGE_TO_SERVER = 2;
        // Message types sent from server
        public static final int MESSAGE_FROM_SERVER = 3;

        // Message types sent from Client by admin
        public static final int MSG_TYPE_ADMIN_SIGN_IN = 1000;
        public static final int MSG_TYPE_ADMIN_SEARCH_USER = 1001;
        public static final int MSG_TYPE_ADMIN_UPDATE_USER_INFO = 1002;
        public static final int MSG_TYPE_ADMIN_UPDATE_CAR_STATE = 1003;

        // Message types sent from Client by company
        public static final int MSG_TYPE_COMPANY_SIGN_IN = 2000;
        public static final int MSG_TYPE_COMPANY_UPDATE_ITEM_COST = 2001;
        public static final int MSG_TYPE_COMPANY_UPDATE_ITEM_EARINGS = 2002;
        public static final int MSG_TYPE_COMPANY_UPDATE_ITEM_TIME_END = 2003;
        public static final int MSG_TYPE_COMPANY_UPDATE_ITEM_TIME_START =2004;
        public static final int MSG_TYPE_COMPANY_LIST_BUSINESS = 2005;
        public static final int MSG_TYPE_COMPANY_CREATE_BIDDING = 2006;
        public static final int MSG_TYPE_COMPANY_LIST_COST = 2007;
        public static final int MSG_TYPE_COMPANY_LIST_MY_BIDDING = 2008;

        // Message types sent from Client by normal user
        public static final int MSG_TYPE_USER_SIGN_IN = 3000;
        public static final int MSG_TYPE_USER_SIGN_UP = 3001;
        public static final int MSG_TYPE_USER_LIST_MONEY = 3002;
        public static final int MSG_TYPE_USER_LIST_BIDDING = 3003;
        public static final int MSG_TYPE_USER_SELECT_BIDDING = 3004;
        public static final int MSG_TYPE_USER_DROP_BIDDING = 3005;
        public static final int MSG_TYPE_USER_SEND_PARK_INFO = 3006;
        public static final int MSG_TYPE_USER_LIST_MYCARS = 3007;
        public static final int MSG_TYPE_USER_ADD_CARS = 3008;
        public static final int MSG_TYPE_USER_UPDATE_ADING = 3009;
//        public static final int MSG_TYPE_USER_SET_BIDDING_CAR = 3010;
        public static final int MSG_TYPE_USER_CREATE_VOTING = 3010;

        // Message types sent from the BluetoothChatService Handler
        public static final int MSG_TYPE_BLUETOOTH_STATE_CHANGE = 11;
        public static final int MSG_TYPE_BLUETOOTH_READ = 12;
        public static final int MSG_TYPE_BLUETOOTH_WRITE = 13;
        public static final int MSG_TYPE_BLUETOOTH_DEVICE_NAME = 14;
        public static final int MSG_TYPE_BLUETOOTH_TOAST = 15;
        public static final int MSG_TYPE_UI_SHOW_USER_INFO = 16;
        public static final int MSG_TYPE_UI_SHOW_ADMAN_INFO = 19;
        public static final int MSG_TYPE_UI_SHOW_SIGN_IN = 17;
        public static final int MSG_TYPE_UI_DISMISS_LOADING = 18;
        public static final int MSG_TYPE_UI_SEVICE_CONNECTED = 19;


    }
    public class CAR_CONSTANSTS{
    	public static final int CAR_STATE_CAN_BID = 1;
    	public static final int CAR_STATE_GET_MONEY = 2;
    	public static final int CAR_STATE_BLACK_LIST= -1;
    	
    }
    public class BIDDING_CONSTANSTS{
    	public static final String BIDDING_SUCCESSFUL = "suc";
    	public static final String BIDDING_FAIL = "fail";
    	
    }
    public class USER_CONSTANST{
    	public static final int ALREADY_EXIST = -1;
    	public static final int NOT_EXIST = 1;
    	
    }
    public class BUSINESS_CONSTANST{
    	public static final String MONEY_EXPENDED = "nomeny";
    	public static final String OUT_OF_MONEY = "outofmoney";
    }
    public class AREA_CONSTANST{
    	public static final String NO_THIS_AREA = "noarea";
    }
    public class PARKING_CONSTANST{
    	public static final String NO_ENOUGH_MONEY_PARKING = "nomoneyparking";
    	public static final String UNAVAILABLE_PARKING = "unavailable";
    }
}
