package com.sms.android.adapter;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.sms.android.R;
import com.sms.android.bean.PhoneBook;


import org.jetbrains.annotations.NotNull;

public
class PhoneContractsAdapter extends BaseQuickAdapter<PhoneBook, BaseViewHolder> {
    public PhoneContractsAdapter() {
        super(R.layout.item_scan_device);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, PhoneBook phonebook) {

        if (!TextUtils.isEmpty(phonebook.getUsername())){
            if (!TextUtils.isEmpty(phonebook.getPhoneNumber())){
                if (phonebook.getUsername().equals(phonebook.getPhoneNumber())){
                    holder.setText(R.id.bluetooth_tv, phonebook.getPhoneNumber());
                }else {
                    holder.setText(R.id.bluetooth_tv, phonebook.getUsername()+"（"+phonebook.getPhoneNumber()+"）");
                }
            }else if (!TextUtils.isEmpty(phonebook.getPhoneNumber())){
                holder.setText(R.id.bluetooth_tv, phonebook.getPhoneNumber());
            }
        }
    }
}
