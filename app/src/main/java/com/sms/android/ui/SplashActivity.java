package com.sms.android.ui;

import android.Manifest;
import android.content.Intent;
import android.os.Handler;

import com.permissionx.guolindev.PermissionX;
import com.sms.android.MainActivity;
import com.sms.android.R;
import com.sms.android.base.BaseActivity;
import com.sms.android.view.Titanic;
import com.sms.android.view.TitanicTextView;
import com.sms.android.view.Typefaces;

public class SplashActivity extends BaseActivity {
    @Override
    protected int getContentView() throws Exception {
        return R.layout.splash_activity;
    }

    @Override
    protected void initView() throws Exception {

        TitanicTextView tv = (TitanicTextView) findViewById(R.id.my_text_view);

        // set fancy typeface
        tv.setTypeface(Typefaces.get(this, "Satisfy-Regular.ttf"));

        // start animation
        Titanic titanic = new Titanic();
        titanic.start(tv);
        new Handler().postDelayed(()->{
            PermissionX.init(SplashActivity.this).permissions(
                    Manifest.permission.SEND_SMS,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE).request((allGranted,grantedList,deniedList)->{
                if (allGranted) {
                    mContext.startActivity(new Intent(mContext, HomeActivity.class));
                    finish();
                }
            });
        }, 5000);
    }

    @Override
    protected void initData() throws Exception {

    }
}
