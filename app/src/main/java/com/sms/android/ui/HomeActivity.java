package com.sms.android.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.sms.android.R;
import com.sms.android.base.BaseActivity;
import com.sms.android.base.BaseObserver;
import com.sms.android.utils.GetContractInfo;
import com.sms.android.utils.RxSchedulers;
import com.sms.android.utils.SmsUtils;
import com.zlylib.fileselectorlib.FileSelector;
import com.zlylib.fileselectorlib.bean.EssFile;
import com.zlylib.fileselectorlib.utils.Const;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.annotation.Nullable;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class HomeActivity extends BaseActivity {
    private EditText sendCountEdit;
    private EditText sendTimeEdit;
    private EditText sendTelEdit;
    private EditText sendContentEdit;
    private Disposable disposable;
    private boolean isSend=false;
    @Override
    protected int getContentView() throws Exception {
        return R.layout.sms_activity;
    }



    @Override
    protected void initView() throws Exception {
        sendContentEdit=findViewById(R.id.send_c_et);
        sendTimeEdit=findViewById(R.id.send_time_et);
        sendTelEdit=findViewById(R.id.send_tel_et);
        sendCountEdit=findViewById(R.id.send_count_et);
        findViewById(R.id.send_phone_btn).setOnClickListener((view)-> {
            Intent intent = new Intent(this,ExcelActivity.class);
            intent.putExtra("type", "1");
            startActivity(intent);
        });
        findViewById(R.id.send_excle_btn).setOnClickListener((view)-> {
            FileSelector.from(this)
                    // .onlyShowFolder()  //只显示文件夹
                    //.onlySelectFolder()  //只能选择文件夹

                    .setMaxCount(1)
                    .setFileTypes("xls","xlsx") //设置文件类型
                    .setSortType(FileSelector.BY_NAME_ASC) //设置名字排序
                    .setSortType(FileSelector.BY_TIME_ASC) //设置时间排序
                    //.setSortType(FileSelector.BY_SIZE_DESC) //设置大小排序
                    //.setSortType(FileSelector.BY_EXTENSION_DESC) //设置类型排序
                    .requestCode(1) //设置返回码
                    .start();
        });
        findViewById(R.id.send_btn).setOnClickListener((view)-> {

//            if (isSend) {
//                Toast.makeText(mContext, "数据发送中", Toast.LENGTH_SHORT).show();
//                return;
//            }
//            String content=sendContentEdit.getText().toString();
//            String sendTime=sendTimeEdit.getText().toString();
//            String sendTel=sendTelEdit.getText().toString();
//            String sendCount=sendContentEdit.getText().toString();
//            if (TextUtils.isEmpty(content)){
//                Toast.makeText(mContext, "内容不能为空", Toast.LENGTH_SHORT).show();
//            }
//            if (TextUtils.isEmpty(sendTel)){
//                Toast.makeText(mContext, "输入手机号", Toast.LENGTH_SHORT).show();
//            }
//            if (TextUtils.isEmpty(sendCount)){
//                Toast.makeText(mContext, "输入发送次数", Toast.LENGTH_SHORT).show();
//            }
//            if (TextUtils.isEmpty(sendTime)){
//                Toast.makeText(mContext, "输入间隔时间", Toast.LENGTH_SHORT).show();
//            }
//
//
//
//            int index=0;
//
//            isSend=true;
//            Observable.interval(0,Integer.parseInt(sendTime), TimeUnit.MINUTES)
//                    .compose(RxSchedulers.<Long>compose())
//                    .subscribe(new BaseObserver<Long>() {
//                        @Override
//                        public void onNextObserver(Long o) throws Exception {
//
//                          if (o>index) {
//                              if (disposable != null) {
//                                  disposable.dispose();
//                              }
//                              isSend=false;
//                              return;
//                          }
//                            SmsUtils.sendSMS(mContext, sendTel,content);
//                        }
//                        @Override
//                        public void onDisposable(Disposable d) throws Exception {
//                            disposable=d;
//                        }
//                    });
//            GetContractInfo getContractInfo=new GetContractInfo(mContext);
//            getContractInfo.TestRead();

        });









    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(data!=null){
                ArrayList<EssFile> essFileList = data.getParcelableArrayListExtra(Const.EXTRA_RESULT_SELECTION);
                if (essFileList!=null&&essFileList.size()>0){

                    Intent intent = new Intent(this,ExcelActivity.class);
                    intent.putExtra("type", "2");
                    intent.putExtra("url", essFileList.get(0).getAbsolutePath());
                    startActivity(intent);
                }


            }
        }
    }

    @Override
    protected void initData() throws Exception {
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (disposable!=null){
            disposable.dispose();
            isSend=false;
        }
    }
}
