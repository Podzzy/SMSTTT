package com.example.jjpod.smsttt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class InviteReceived extends AppCompatActivity {

    TextView playerName = null;
    Button acceptButton = null, declineButton = null;
    String player1Name = null, player1Symbol = null, player1Number = null, player2Name = null, player2Symbol = null;
    EditText player2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_received);

        playerName = findViewById(R.id.playerName);
        acceptButton = findViewById(R.id.acceptButton);
        declineButton = findViewById(R.id.declineButton);
        player2 = findViewById(R.id.player2);


        Intent i = getIntent();
        player1Name = i.getStringExtra("player1Name");
        player1Symbol = i.getStringExtra("player1Symbol");
        player1Number = i.getStringExtra("player1Number");


        playerName.setText(player1Name);

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player2Name = player2.getText().toString();
                if(player1Symbol.equals("X")){
                    player2Symbol = "O";
                }
                if(player1Symbol.equals("O")){
                    player2Symbol = "X";
                }

                if(player2Name.equals("")){
                    Toast toast = Toast.makeText(view.getContext(), "NAME CANNOT BE EMPTY!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER,0,250);
                    toast.show();
                }
                else {
                    Intent i = new Intent(view.getContext(), Game.class);
                    i.putExtra("currentPlayer", "P2");
                    i.putExtra("player1Name", player1Name);
                    i.putExtra("player1Number",player1Number);
                    i.putExtra("player2Name", player2Name);
                    i.putExtra("player1Symbol", player1Symbol);
                    i.putExtra("player2Symbol", player2Symbol);
                    i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(i);


                    SmsManager sms = SmsManager.getDefault();

                    try {
                        sms.sendTextMessage(player1Number, null, "@!@/TTT/ACCEPTED/" + player2Name + "/" + player2Symbol, null, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    finish();
                }
            }
        });

        declineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(),MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(i);
                SmsManager sms = SmsManager.getDefault();

                try {
                    sms.sendTextMessage(player1Number, null, "@!@/TTT/DECLINED/" + player2Name, null, null);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                finish();
            }
        });

    }
}
