package com.sms.android.ui;

import android.text.TextUtils;

import com.sms.android.R;
import com.sms.android.adapter.PhoneContractsAdapter;
import com.sms.android.base.BaseActivity;
import com.sms.android.bean.PhoneBook;
import com.sms.android.utils.ExcleUtils;
import com.sms.android.utils.GetContractInfo;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public  class ExcelActivity extends BaseActivity {
    private RecyclerView recyclerView=null;
    private PhoneContractsAdapter phoneContractsAdapter=null;
    private String url;
    private String type="0";
    @Override
    protected int getContentView() throws Exception {
        return R.layout.phone_activity;
    }

    @Override
    protected void initView() throws Exception {
        url=getIntent().getStringExtra("url");
        type=getIntent().getStringExtra("type");
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        phoneContractsAdapter=new PhoneContractsAdapter();
        recyclerView.getItemAnimator().setChangeDuration(300);
        recyclerView.getItemAnimator().setMoveDuration(300);
        recyclerView.setAdapter(phoneContractsAdapter);

    }

    @Override
    protected void initData() throws Exception {


        if (!TextUtils.isEmpty(type)&&type.equals("1")){
            GetContractInfo getContractInfo=new GetContractInfo(mContext);
            phoneContractsAdapter.setNewInstance(getContractInfo.getPhonebookList());
        }else if (!TextUtils.isEmpty(type)&&type.equals("2")&&!TextUtils.isEmpty(url)){

            List<Map<String, String>> list = ExcleUtils.readExcel(url,new String[]{"内含笔数","业务号","客户姓名","产品种类","逾期总金额", "本人电话"});
            if (list!=null&&list.size()>0){
                List<PhoneBook> listBook=new ArrayList<>();
                for (Map<String, String> map:list){
                    PhoneBook phonebook=new PhoneBook();
                    phonebook.setUsername(map.get("客户姓名"));
                    phonebook.setPhoneNumber(map.get("本人电话"));
                    listBook.add(phonebook);
                }
                phoneContractsAdapter.setNewInstance(listBook);
            }
        }
    }
}
