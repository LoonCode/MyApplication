package com.loon.example.myapplication;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * Created by Administrator on 2015/11/4.
 */
public class MessageReceiver extends BroadcastReceiver {

    private static final String TAG = MessageReceiver.class.getSimpleName();


//    IntentFilter intentFilter = new IntentFilter(SMS_ACTION);
//    intentFilter.setPriority(Integer.MAX_VALUE);
//    registerReceiver(dynamicReceiver, intentFilter);


    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        if(Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(action)){

            SmsMessage[] smsMessages =Telephony.Sms.Intents.getMessagesFromIntent(intent);
            SmsMessage smsMessage =smsMessages[0];
            smsMessage.getOriginatingAddress();
            smsMessage.getDisplayMessageBody();

            if (smsMessage.getOriginatingAddress().equals("10010")){
                Log.i(TAG,smsMessage.getOriginatingAddress() +"  "+ smsMessage.getDisplayMessageBody());
            }

//            context.startService();
        }

        abortBroadcast() ;
    }
}
