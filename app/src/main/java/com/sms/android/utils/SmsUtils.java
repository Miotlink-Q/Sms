package com.sms.android.utils;

import android.content.Context;
import android.telephony.SmsManager;
import android.widget.Toast;

import java.util.ArrayList;

public class SmsUtils {

    public static void sendSMS(Context context, String sendToNumber, String msg) {
        if (msg != null) {
            SmsManager sms = SmsManager.getDefault();
            ArrayList<String> texts = sms.divideMessage(msg);
            if (texts.size() <= 1) {
                sms.sendTextMessage(sendToNumber, null, msg, null, null);
            } else if (texts.size() > 1) {
                sms.sendMultipartTextMessage(sendToNumber, null, texts, null, null);
            }
            Toast.makeText(context, "转发至: " + sendToNumber, Toast.LENGTH_SHORT).show();
        }
    }
}
