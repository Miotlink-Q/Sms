package com.sms.android.application;

import android.app.Application;

import com.tencent.mmkv.MMKV;

public class SmsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

       MMKV.initialize(this);

    }
}
