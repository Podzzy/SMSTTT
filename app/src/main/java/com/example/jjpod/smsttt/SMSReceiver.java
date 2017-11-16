package com.example.jjpod.smsttt;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;

/**
 * Created by jjpod on 11/9/2017.
 */

public class SMSReceiver extends BroadcastReceiver {

    static IntentFilter filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
    MainActivity main = null;

    SMSReceiver(MainActivity _main){
        main = _main;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        SmsManager manager = SmsManager.getDefault();
        Bundle bundle = intent.getExtras();
    }
}
