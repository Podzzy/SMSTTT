package com.example.jjpod.smsttt;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Invite extends AppCompatActivity {

    EditText player2 = null;
    Button invitebutton = null;
    String player2Number = "", player1Name = "", player1Symbol = "", player2Symbol = null;
    BroadcastReceiver br = null;
    IntentFilter filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite);

        player2 = findViewById(R.id.player2);


        Intent j = getIntent();
        player1Name = j.getStringExtra("player1Name");
        player1Symbol = j.getStringExtra("player1Symbol");
        player2Symbol = j.getStringExtra("player2Symbol");



        invitebutton = findViewById(R.id.inviteButton);

        invitebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player2Number = player2.getText().toString();


                if(player2Number.equals("")){
                    Toast toast = Toast.makeText(view.getContext(), "PHONE NUMBER CANNOT BE EMPTY!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER,0,-600);
                    toast.show();
                }else {
                    Intent i = new Intent(view.getContext(), PendingInvite.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    i.putExtra("currentPlayer", "P1");
                    i.putExtra("player1Name", player1Name);
                    i.putExtra("player1Symbol", player1Symbol);
                    i.putExtra("player2Symbol", player2Symbol);
                    i.putExtra("message", "WAITING FOR PLAYER 2 RESPONSE...");
                    startActivity(i);
                    SmsManager sms = SmsManager.getDefault();

                    try {
                        sms.sendTextMessage(player2Number, null, "@!@/TTT/INVITE/" + player1Name + "/" + player1Symbol, null, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    finish();
                }
            }
        });


    }
}
