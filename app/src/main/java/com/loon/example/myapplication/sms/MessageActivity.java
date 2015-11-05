package com.loon.example.myapplication.sms;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;

import com.loon.example.myapplication.R;

/**
 * Created by Administrator on 2015/11/5.
 */
public class MessageActivity extends Activity {

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms);

        IntentFilter intentFilter = new IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION);
        intentFilter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        registerReceiver(new MessageReceiver(), intentFilter);
    }
}
