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
package com.zeroapp.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * <p>Title: TODO.</p>
 * <p>Description: TODO.</p>
 *
 * @author Alex(zeroapp@126.com) 2015-5-26.
 * @version $Id$
 */
public class Log {

    public static String TAG = "Zeroapp";

    public static final int LEVEL_ERROR = 1;
    public static final int LEVEL_WARNING = 2;
    public static final int LEVEL_INFO = 3;
    public static final int LEVEL_DEBUG = 4;
    public static final int LEVEL_VERBOSE = 5;

    /**
     * Send a DEBUG log message.
     * 
     * @param msg
     */
    public static void d(String msg) {
        if (getLogLevel() >= LEVEL_DEBUG) {
            android.util.Log.d(TAG, msg + getTrace());
        }
    }

    /**
     * Send a DEBUG log message.
     * 
     * @param msg
     * @deprecated please use {@link #d(String)}
     */
    public static void d(String tag, String msg) {
        if (getLogLevel() >= LEVEL_DEBUG) {
            android.util.Log.d(tag, msg + getTrace());
        }
    }

    /**
     * Send a DEBUG log message and log the exception.
     * 
     * @param msg
     *            The message you would like logged.
     * @param tr
     *            An exception to log
     * @deprecated please use {@link #d(String, Throwable)}
     */
    public static void d(String tag, String msg, Throwable thr) {
        if (getLogLevel() >= LEVEL_DEBUG) {
            android.util.Log.d(tag, msg + getTrace(), thr);
        }
    }

    /**
     * Send a DEBUG log message and log the exception.
     * 
     * @param msg
     *            The message you would like logged.
     * @param tr
     *            An exception to log
     */
    public static void d(String msg, Throwable thr) {
        if (getLogLevel() >= LEVEL_DEBUG) {
            android.util.Log.d(TAG, msg + getTrace(), thr);
        }
    }

    /**
     * Send an ERROR log message.
     * 
     * @param msg
     *            The message you would like logged.
     */
    public static void e(String msg) {
        if (getLogLevel() >= LEVEL_ERROR) {
            android.util.Log.e(TAG, msg + getTrace());
        }
    }

    /**
     * Send an ERROR log message.
     * 
     * @param msg
     *            The message you would like logged.
     * @deprecated please use {@link #e(String)}
     */
    public static void e(String tag, String msg) {
        if (getLogLevel() >= LEVEL_ERROR) {
            android.util.Log.e(tag, msg + getTrace());
        }
    }

    /**
     * Send an ERROR log message and log the exception.
     * 
     * @param msg
     *            The message you would like logged.
     * @param thr
     *            An exception to log
     * @deprecated please use {@link #e(String, Throwable)}
     */
    public static void e(String tag, String msg, Throwable thr) {
        if (getLogLevel() >= LEVEL_ERROR) {
            android.util.Log.e(tag, msg + getTrace(), thr);
        }
    }

    /**
     * Send an ERROR log message and log the exception.
     * 
     * @param msg
     *            The message you would like logged.
     * @param thr
     *            An exception to log
     */
    public static void e(String msg, Throwable thr) {
        if (getLogLevel() >= LEVEL_ERROR) {
            android.util.Log.e(TAG, msg + getTrace(), thr);
        }
    }

    public static void i(String msg) {
        if (getLogLevel() >= LEVEL_INFO) {
            android.util.Log.i(TAG, msg + getTrace());
        }
    }

    /**
     * Send an INFO log message.
     * 
     * @param msg
     *            The message you would like logged.
     * @deprecated please use {@link #i(String)}
     */
    public static void i(String tag, String msg) {
        if (getLogLevel() >= LEVEL_INFO) {
            android.util.Log.i(tag, msg + getTrace());
        }
    }

    /**
     * Send a INFO log message and log the exception.
     * 
     * @param msg
     *            The message you would like logged.
     * @param thr
     *            An exception to log
     * @deprecated please use {@link #i(String, Throwable)}
     */
    public static void i(String tag, String msg, Throwable thr) {
        if (getLogLevel() >= LEVEL_INFO) {
            android.util.Log.i(tag, msg + getTrace(), thr);
        }
    }

    /**
     * Send a INFO log message and log the exception.
     * 
     * @param msg
     *            The message you would like logged.
     * @param thr
     *            An exception to log
     */
    public static void i(String msg, Throwable thr) {
        if (getLogLevel() >= LEVEL_INFO) {
            android.util.Log.i(TAG, msg + getTrace(), thr);
        }
    }

    /**
     * Send a VERBOSE log message.
     * 
     * @param msg
     *            The message you would like logged.
     */
    public static void v(String msg) {
        if (getLogLevel() >= LEVEL_VERBOSE) {
            android.util.Log.v(TAG, msg + getTrace());
        }
    }

    /**
     * Send a VERBOSE log message.
     * 
     * @param msg
     *            The message you would like logged.
     * @deprecated please use {@link #v(String)}
     */
    public static void v(String tag, String msg) {
        if (getLogLevel() >= LEVEL_VERBOSE) {
            android.util.Log.v(tag, msg + getTrace());
        }
    }

    /**
     * Send a VERBOSE log message and log the exception.
     * 
     * @param msg
     *            The message you would like logged.
     * @param thr
     *            An exception to log
     * @deprecated please use {@link #v(String, Throwable)}
     */
    public static void v(String tag, String msg, Throwable thr) {
        if (getLogLevel() >= LEVEL_VERBOSE) {
            android.util.Log.v(tag, msg + getTrace(), thr);
        }
    }

    /**
     * Send a VERBOSE log message and log the exception.
     * 
     * @param msg
     *            The message you would like logged.
     * @param thr
     *            An exception to log
     */
    public static void v(String msg, Throwable thr) {
        if (getLogLevel() >= LEVEL_VERBOSE) {
            android.util.Log.v(TAG, msg + getTrace(), thr);
        }
    }

    /**
     * Send a WARN log message
     * 
     * @param msg
     *            The message you would like logged.
     */
    public static void w(String msg) {
        if (getLogLevel() >= LEVEL_WARNING) {
            android.util.Log.w(TAG, msg + getTrace());
        }
    }

    /**
     * Send a WARN log message
     * 
     * @param msg
     *            The message you would like logged.
     * @deprecated please use {@link #w(String)}
     */
    public static void w(String tag, String msg) {
        if (getLogLevel() >= LEVEL_WARNING) {
            android.util.Log.w(tag, msg + getTrace());
        }
    }

    /**
     * Send a WARN log message and log the exception.
     * 
     * @param msg
     *            The message you would like logged.
     * @param thr
     *            An exception to log
     * @deprecated please use {@link #w(String, Throwable)}
     */
    public static void w(String tag, String msg, Throwable thr) {
        if (getLogLevel() >= LEVEL_WARNING) {
            android.util.Log.w(tag, msg + getTrace(), thr);
        }
    }

    /**
     * Send a WARN log message and log the exception.
     * 
     * @param msg
     *            The message you would like logged.
     * @param thr
     *            An exception to log
     */
    public static void w(String msg, Throwable thr) {
        if (getLogLevel() >= LEVEL_WARNING) {
            android.util.Log.w(TAG, msg + getTrace(), thr);
        }
    }

    /**
     * <p>
     * Title: Send a DEBUG trace log message .
     * </p>
     * <p>
     * Description: Send a DEBUG trace log message.
     * </p>
     * 
     */
    public static void trace() {
        if (getLogLevel() >= LEVEL_DEBUG) {
            android.util.Log.d(TAG, getTrace());
        }
    }

    /**
     * 
     * <p>
     * Title: Send a DEBUG trace log message.
     * </p>
     * <p>
     * Description: Send a DEBUG trace log message.
     * </p>
     * 
     * @param tag
     */
    public static void trace(String tag) {
        if (getLogLevel() >= LEVEL_DEBUG) {
            android.util.Log.d(tag, getTrace());
        }
    }

    /**
     * 
     * <p>
     * Title: Send a INFO version log message.
     * </p>
     * <p>
     * Description: Send a INFO version log message.
     * </p>
     * 
     * @param context
     */
    public static void appVersion(Context context) {
        if (null == context) {
            return;
        }
        try {
            // 获取PackageManager的实例
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            appVersion(packInfo);
        } catch (Exception e) {
            Log.w("Failed to get packageinfo", e);
        }
    }

    /**
     * 
     * <p>
     * Title: Send a INFO version log message.
     * </p>
     * <p>
     * Description: Send a INFO version log message.
     * </p>
     * 
     * @param packInfo
     */
    public static void appVersion(PackageInfo packInfo) {
        int versionCode = packInfo.versionCode;
        String versionName = packInfo.versionName;
        StringBuilder info = new StringBuilder(packInfo.packageName);
        info.append("(VersionCode:").append(versionCode).append(", ").append("VersionName:")
                .append(versionName).append(")");
        if (getLogLevel() >= LEVEL_INFO) {
            android.util.Log.d(TAG, info.toString());
        }
    }

    private static String getTrace() {
        StackTraceElement te = Thread.currentThread().getStackTrace()[4];
        StringBuilder builder = new StringBuilder(" - ");
        return builder.append(te.getMethodName()).append("()#").append(te.getFileName())
                .append(":").append(te.getLineNumber()).append("(")
                .append(Thread.currentThread().getName()).append(")").toString();
    }

    private static int getLogLevel() {
        // return SystemProperties.getInt("hisense.appdebug", 0);
        return 5;
    }

}
