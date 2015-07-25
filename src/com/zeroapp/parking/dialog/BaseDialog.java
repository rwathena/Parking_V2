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

package com.zeroapp.parking.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;


/**
 * <p>Title: TODO.</p>
 * <p>Description: TODO.</p>
 *
 * @author Alex(zeroapp@126.com) 2015-6-12.
 * @version $Id$
 */

public class BaseDialog extends AlertDialog {

    private Context context;
    private Builder dialogBuilder;

    /**
     * <p>
     * Title: TODO.
     * </p>
     * <p>
     * Description: TODO.
     * </p>
     * 
     * @param context
     */
    public BaseDialog(Context ctx, int res) {
        super(ctx);
        context = ctx;
        dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.create();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(res, null);
        dialogBuilder.setView(view);
    }
    public void show() {
        dialogBuilder.show();
        super.setCanceledOnTouchOutside(true);
    }

}