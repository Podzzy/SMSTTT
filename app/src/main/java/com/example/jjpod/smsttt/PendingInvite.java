package com.example.jjpod.smsttt;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PendingInvite extends AppCompatActivity {

    Button cancelButton = null;
    String player2Number = "", player1Name = "", player1Symbol = "", message = "", player2Symbol = null;
    BroadcastReceiver br = null, br2 = null;
    IntentFilter filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
    TextView pendingMessage = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_invite);
        cancelButton = findViewById(R.id.cancelButton);
        pendingMessage = findViewById(R.id.pendingMessage);

        Intent j = getIntent();
        player1Name = j.getStringExtra("player1Name");
        player1Symbol = j.getStringExtra("player1Symbol");
        player2Symbol = j.getStringExtra("player2Symbol");

        if(j.getStringExtra("currentPlayer").equals("P1")){
            pendingMessage.setText(j.getStringExtra("message"));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                pendingMessage.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            }
            br2 = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
                        SmsMessage[] messages = Telephony.Sms.Intents.getMessagesFromIntent(intent);

                        for(SmsMessage m : messages){
                            String text = m.getDisplayMessageBody();
                            String player2Number = m.getDisplayOriginatingAddress();

                            String[] sections = text.split("/");
                            String special = sections[0];
                            String gameType = sections[1];
                            String action = sections[2];
                            String name = sections[3];


                            if(special.equals("@!@") && gameType.equals("TTT") && action.equals("DECLINED")){
                                        Intent i = new Intent(context, MainActivity.class);
                                        i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                                        startActivity(i);
                                        finish();
                            }
                            if(special.equals("@!@") && gameType.equals("TTT") && action.equals("ACCEPTED")){
                                Intent i = new Intent(context, Game.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                                i.putExtra("currentPlayer", "P1");
                                i.putExtra("player2Number", player2Number);
                                i.putExtra("player1Name", player1Name);
                                i.putExtra("player1Symbol", player1Symbol);
                                i.putExtra("player2Symbol", player2Symbol);
                                i.putExtra("player2Name", name);

                                startActivity(i);
                                finish();
                            }

                        }
                    }
                }
            };
            registerReceiver(br2, filter);
        }


        if(!j.getStringExtra("currentPlayer").equals("P1")) {
            br = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        SmsMessage[] messages = Telephony.Sms.Intents.getMessagesFromIntent(intent);

                        for (SmsMessage m : messages) {
                            String text = m.getDisplayMessageBody();
                            String player1Number = m.getDisplayOriginatingAddress();

                            String[] sections = text.split("/");
                            String special = sections[0];
                            String gameType = sections[1];
                            String action = sections[2];
                            String parameter = sections[3];
                            String parameter2 = sections[4];


                            if (special.equals("@!@") && gameType.equals("TTT") && action.equals("INVITE")) {
                                Intent i = new Intent(context, InviteReceived.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                                i.putExtra("currentPlayer", "P2");
                                i.putExtra("player1Name", parameter);
                                i.putExtra("player1Symbol", parameter2);
                                i.putExtra("player1Number", player1Number);
                                i.putExtra("player2Symbol", player2Symbol);
                                startActivity(i);
                                finish();
                            }
                        }
                    }
                }
            };
            registerReceiver(br, filter);
        }

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(i);
                finish();
            }
        });
    }
}
