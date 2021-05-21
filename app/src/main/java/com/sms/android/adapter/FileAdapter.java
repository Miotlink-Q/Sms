package com.sms.android.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

import java.io.File;

public class FileAdapter extends BaseQuickAdapter<File, BaseViewHolder> {

    public FileAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, File file) {

    }
}
