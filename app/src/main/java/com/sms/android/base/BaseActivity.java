package com.sms.android.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            mContext=this;
            setContentView(getContentView());
            initView();
            initData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract int getContentView()throws Exception;
    protected abstract void initView()throws Exception;
    protected abstract void initData()throws Exception;
}
