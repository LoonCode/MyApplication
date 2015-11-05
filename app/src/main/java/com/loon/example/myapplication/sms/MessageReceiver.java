package com.loon.example.myapplication.sms;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;

import com.google.common.collect.Lists;

/**
 * Created by Administrator on 2015/11/4.
 */
public class MessageReceiver extends BroadcastReceiver {

    private static final String TAG = MessageReceiver.class.getSimpleName();


    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(action)) {
            return;
        }

        SmsMessage[] smsMessages = Telephony.Sms.Intents.getMessagesFromIntent(intent);
        SmsMessage smsMessage = smsMessages[0];
        smsMessage.getDisplayMessageBody();

        String originatingAddress = smsMessage.getOriginatingAddress();
        String messageBody = smsMessage.getDisplayMessageBody();
        Log.i(TAG, originatingAddress + "  " + smsMessage.getDisplayMessageBody());

        // if originatingAddress in  BlackList or KeywordList, abort Broadcast;
        if (checkBlackList(originatingAddress) || checkKeywordList(messageBody)) {
            abortBroadcast();
        }

        //    context.startService();
        //    IntentFilter intentFilter = new IntentFilter(SMS_ACTION);
        //    intentFilter.setPriority(Integer.MAX_VALUE);
        //    registerReceiver(dynamicReceiver, intentFilter);

    }

    /**
     * check KeywordList
     *
     * @param messageBody messageContext
     * @return 是否存在
     */
    private boolean checkKeywordList(String messageBody) {
        return Lists.newArrayList("110").contains(messageBody);
    }

    /**
     * checkBlackList
     *
     * @return blackList
     */
    private boolean checkBlackList(String originatingAddress) {
        return Lists.newArrayList("110").contains(originatingAddress);
    }
}
