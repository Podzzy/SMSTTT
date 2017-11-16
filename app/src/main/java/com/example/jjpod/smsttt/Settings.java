package com.example.jjpod.smsttt;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    Button xButton = null, oButton = null, waitButton = null;
    EditText player1 = null;
    String player1Name = "", player1Symbol = "", player2Symbol = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        xButton = findViewById(R.id.xButton);
        oButton = findViewById(R.id.oButton);
        waitButton = findViewById(R.id.waitButton);

        player1 = findViewById(R.id.player1);



        xButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player1Symbol = "X";
                player2Symbol = "O";
                player1Name = player1.getText().toString();

                if(player1Name.equals("")) {
                    Toast toast = Toast.makeText(view.getContext(), "NAME CANNOT BE EMPTY!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER,0,-600);
                    toast.show();
                }else {
                    Intent i = new Intent(view.getContext(), Invite.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    i.putExtra("player1Name", player1Name);
                    i.putExtra("player1Symbol", player1Symbol);
                    i.putExtra("player2Symbol", player2Symbol);
                    startActivity(i);
                }
            }
        });

        oButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player1Symbol = "O";
                player2Symbol = "X";
                player1Name = player1.getText().toString();

                if(player1Name.equals("")) {
                    Toast toast = Toast.makeText(view.getContext(), "NAME CANNOT BE EMPTY!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER,0,-600);
                    toast.show();
                }
                else {
                    Intent i = new Intent(view.getContext(), Invite.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    i.putExtra("player1Name", player1Name);
                    i.putExtra("player1Symbol", player1Symbol);
                    i.putExtra("player2Symbol", player2Symbol);
                    startActivity(i);
                }
            }
        });

        waitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), PendingInvite.class);
                i.putExtra("currentPlayer", "P2");
                startActivity(i);
            }
        });

    }
}
