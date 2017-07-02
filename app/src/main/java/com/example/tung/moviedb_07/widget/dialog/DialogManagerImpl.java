package com.example.tung.moviedb_07.widget.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.WindowManager;
import com.example.tung.moviedb_07.R;

/**
 * Created by tung on 6/22/17.
 */

public class DialogManagerImpl implements DialogManager {
    private final Context mContext;
    private ProgressDialog mProgressDialog;

    public DialogManagerImpl(Context context) {
        mContext = context;
    }

    @Override
    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(mContext);
            mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            WindowManager.LayoutParams a = mProgressDialog.getWindow().getAttributes();
            a.gravity = Gravity.TOP;
            a.y = 220;
            mProgressDialog.getWindow().setAttributes(a);
        }
//        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();
        mProgressDialog.setContentView(R.layout.dialog_loading);
    }

    @Override
    public void dismissProgressDialog() {
        if (mProgressDialog == null) {
            return;
        }
        mProgressDialog.dismiss();
    }
}
