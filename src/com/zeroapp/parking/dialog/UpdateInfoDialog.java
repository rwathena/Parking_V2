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

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.zeroapp.utils.Log;


/**
 * <p>Title: TODO.</p>
 * <p>Description: TODO.</p>
 *
 * @author Alex(zeroapp@126.com) 2015-6-15.
 * @version $Id$
 */

public class UpdateInfoDialog extends BaseDialog {


    private TextView mTextView;

    public UpdateInfoDialog(Context ctx, int res, View v) {
        super(ctx, res);
        this.mTextView = (TextView) v;
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
    @Override
    public void dismiss() {
        Log.i("");
        super.dismiss();
        mTextView.setText("changed!");
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
    @Override
    public void cancel() {
        Log.i("");
        super.cancel();
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
    @Override
    public void hide() {
        Log.i("");
        super.hide();
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
    @Override
    protected void onStop() {
        Log.i("");
        super.onStop();
    }

}
