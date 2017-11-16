package com.example.jjpod.smsttt;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Game extends AppCompatActivity implements View.OnClickListener{

    IntentFilter filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
    TextView cPlayer = null, winner = null;
    Button b1=null, b2 = null, b3 = null, b4 = null, b5 = null, b6 = null, b7 = null, b8= null, b9 = null, menuButton = null;
    String[] moves = null;
    Button[] buttons = null;
    String player1Number = null, player2Number = null, player1Name = null, player2Name = null, player1Symbol = null, player2Symbol =  null, currentPlayer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        moves = new String[9];
        cPlayer = findViewById(R.id.currentPlayer);
        winner = findViewById(R.id.winner);
        winner.setShadowLayer(20,0,0, Color.GREEN);

        buttons = new Button[9];
        b1 = findViewById(R.id.b1);
        b1.setOnClickListener(this);
        buttons[0] = b1;
        b2 = findViewById(R.id.b2);
        b2.setOnClickListener(this);
        buttons[1] = b2;
        b3 = findViewById(R.id.b3);
        b3.setOnClickListener(this);
        buttons[2] = b3;
        b4 = findViewById(R.id.b4);
        b4.setOnClickListener(this);
        buttons[3] = b4;
        b5 = findViewById(R.id.b5);
        b5.setOnClickListener(this);
        buttons[4] = b5;
        b6 = findViewById(R.id.b6);
        b6.setOnClickListener(this);
        buttons[5] = b6;
        b7 = findViewById(R.id.b7);
        b7.setOnClickListener(this);
        buttons[6] = b7;
        b8 = findViewById(R.id.b8);
        b8.setOnClickListener(this);
        buttons[7] = b8;
        b9 = findViewById(R.id.b9);
        b9.setOnClickListener(this);
        buttons[8] = b9;

        menuButton = findViewById(R.id.menuButton);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent m = new Intent(view.getContext(), MainActivity.class);
                startActivity(m);
                finish();
            }
        });



        Intent j = getIntent();
        player1Name = j.getStringExtra("player1Name");
        player2Name = j.getStringExtra("player2Name");
        player2Number = j.getStringExtra("player2Number");
        player1Number = j.getStringExtra("player1Number");
        player1Symbol = j.getStringExtra("player1Symbol");
        player2Symbol = j.getStringExtra("player2Symbol");
        currentPlayer = j.getStringExtra("currentPlayer");



        cPlayer.setText(player1Name);

        if(currentPlayer.equals("P1")){
            Toast toast = Toast.makeText(this, player1Name + " will go first.", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER,0,250);
            toast.show();
        }else{
            Toast toast = Toast.makeText(this, player1Name + " will go first.", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER,0,250);
            toast.show();
            disableButtons();
        }

        if(!currentPlayer.equals("P1")){
            BroadcastReceiver br2 = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
                        SmsMessage[] messages = Telephony.Sms.Intents.getMessagesFromIntent(intent);

                        for(SmsMessage m : messages){
                            String text = m.getDisplayMessageBody();
                            String player1Number = m.getDisplayOriginatingAddress();

                            String[] sections = text.split("/");
                            String special = sections[0];
                            String gameType = sections[1];
                            String action = sections[2];
                            String move = sections[3];

                            if(special.equals("@!@") && gameType.equals("TTT") && action.equals("SELECTED")){
                                enableButtons();
                                cPlayer.setText(player2Name);
                                if(move.equals("b1")) {
                                    moves[0] = player1Symbol;
                                    b1.setText(player1Symbol);
                                    b1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                                    if(p1Winner())
                                        disableButtons();
                                }
                                if(move.equals("b2")) {
                                    moves[1] = player1Symbol;
                                    b2.setText(player1Symbol);
                                    b2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                                    if(p1Winner())
                                        disableButtons();
                                }
                                if(move.equals("b3")) {
                                    moves[2] = player1Symbol;
                                    b3.setText(player1Symbol);
                                    b3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                                    if(p1Winner())
                                        disableButtons();
                                }
                                if(move.equals("b4")) {
                                    moves[3] = player1Symbol;
                                    b4.setText(player1Symbol);
                                    b4.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                                    if(p1Winner())
                                        disableButtons();
                                }
                                if(move.equals("b5")) {
                                    moves[4] = player1Symbol;
                                    b5.setText(player1Symbol);
                                    b5.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                                    if(p1Winner())
                                        disableButtons();
                                }
                                if(move.equals("b6")) {
                                    moves[5] = player1Symbol;
                                    b6.setText(player1Symbol);
                                    b6.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                                    if(p1Winner())
                                        disableButtons();
                                }
                                if(move.equals("b7")) {
                                    moves[6] = player1Symbol;
                                    b7.setText(player1Symbol);
                                    b7.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                                    if(p1Winner())
                                        disableButtons();
                                }
                                if(move.equals("b8")) {
                                    moves[7] = player1Symbol;
                                    b8.setText(player1Symbol);
                                    b8.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                                    if(p1Winner())
                                        disableButtons();
                                }
                                if(move.equals("b9")) {
                                    moves[8] = player1Symbol;
                                    b9.setText(player1Symbol);
                                    b9.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                                    if(p1Winner())
                                        disableButtons();
                                }
                            }
                        }
                    }
                }
            };
            registerReceiver(br2, filter);
        }

        if(currentPlayer.equals("P1")){
            BroadcastReceiver br1 = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
                        SmsMessage[] messages = Telephony.Sms.Intents.getMessagesFromIntent(intent);

                        for(SmsMessage m : messages){
                            String text = m.getDisplayMessageBody();
                            String player1Number = m.getDisplayOriginatingAddress();

                            String[] sections = text.split("/");
                            String special = sections[0];
                            String gameType = sections[1];
                            String action = sections[2];
                            String move = sections[3];

                            if(special.equals("@!@") && gameType.equals("TTT") && action.equals("SELECTED")){
                                enableButtons();
                                cPlayer.setText(player1Name);
                                if(move.equals("b1")) {
                                    moves[0] = player2Symbol;
                                    b1.setText(player2Symbol);
                                    b1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                                    if(p2Winner())
                                        disableButtons();
                                }
                                if(move.equals("b2")) {
                                    moves[1] = player2Symbol;
                                    b2.setText(player2Symbol);
                                    b2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                                    if(p2Winner())
                                        disableButtons();
                                }
                                if(move.equals("b3")) {
                                    moves[2] = player2Symbol;
                                    b3.setText(player2Symbol);
                                    b3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                                    if(p2Winner())
                                        disableButtons();
                                }
                                if(move.equals("b4")) {
                                    moves[3] = player2Symbol;
                                    b4.setText(player2Symbol);
                                    b4.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                                    if(p2Winner())
                                        disableButtons();
                                }
                                if(move.equals("b5")) {
                                    moves[4] = player2Symbol;
                                    b5.setText(player2Symbol);
                                    b5.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                                    if(p2Winner())
                                        disableButtons();
                                }
                                if(move.equals("b6")) {
                                    moves[5] = player2Symbol;
                                    b6.setText(player2Symbol);
                                    b6.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                                    if(p2Winner())
                                        disableButtons();
                                }
                                if(move.equals("b7")) {
                                    moves[6] = player2Symbol;
                                    b7.setText(player2Symbol);
                                    b7.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                                    if(p2Winner())
                                        disableButtons();
                                }
                                if(move.equals("b8")) {
                                    moves[7] = player2Symbol;
                                    b8.setText(player2Symbol);
                                    b8.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                                    if(p2Winner())
                                        disableButtons();
                                }
                                if(move.equals("b9")) {
                                    moves[8] = player2Symbol;
                                    b9.setText(player2Symbol);
                                    b9.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                                    if(p2Winner())
                                        disableButtons();
                                }
                            }
                        }
                    }
                }
            };
            registerReceiver(br1, filter);
        }
    }

    void disableButtons(){
        for(int i=0; i<=8; i++){
            buttons[i].setEnabled(false);
        }
    }

    void enableButtons(){
        for(int i=0; i<=8; i++){
            buttons[i].setEnabled(true);
        }
    }

    boolean spaceTaken(int i){
        if(moves[i] == null)
            return false;

        Toast toast = Toast.makeText(this, "This space is taken", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER,0,250);
        toast.show();
        return true;
    }

    boolean p1Winner(){
        if(moves[0] == player1Symbol && moves[1] == player1Symbol && moves[2] == player1Symbol) {
            Toast toast = Toast.makeText(this, "PLAYER 1 WINS!", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER,0,250);
            toast.show();
            winner.setText(player1Name + " WINS!!!");
            menuButton.setVisibility(View.VISIBLE);
            return true;
        }
        if(moves[3]==  player1Symbol && moves[4]== player1Symbol && moves[5] == player1Symbol) {
            Toast toast = Toast.makeText(this, "PLAYER 1 WINS!", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER,0,250);
            toast.show();
            winner.setText(player1Name + " WINS!!!");
            menuButton.setVisibility(View.VISIBLE);
            return true;
        }
        if(moves[6] == player1Symbol && moves[7] == player1Symbol && moves[8] == player1Symbol) {
            Toast toast = Toast.makeText(this, "PLAYER 1 WINS!", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER,0,250);
            toast.show();
            winner.setText(player1Name + " WINS!!!");
            menuButton.setVisibility(View.VISIBLE);
            return true;
        }
        if(moves[0] == player1Symbol && moves[3] == player1Symbol && moves[6] == player1Symbol) {
            Toast toast = Toast.makeText(this, "PLAYER 1 WINS!", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER,0,250);
            toast.show();
            winner.setText(player1Name + " WINS!!!");
            menuButton.setVisibility(View.VISIBLE);
            return true;
        }
        if(moves[1] == player1Symbol && moves[4] == player1Symbol && moves[7] == player1Symbol) {
            Toast toast = Toast.makeText(this, "PLAYER 1 WINS!", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER,0,250);
            toast.show();
            winner.setText(player1Name + " WINS!!!");
            menuButton.setVisibility(View.VISIBLE);
            return true;
        }
        if(moves[2] == player1Symbol && moves[5] == player1Symbol && moves[8] == player1Symbol) {
            Toast toast = Toast.makeText(this, "PLAYER 1 WINS!", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER,0,250);
            toast.show();
            winner.setText(player1Name + " WINS!!!");
            menuButton.setVisibility(View.VISIBLE);
            return true;
        }
        if(moves[0]== player1Symbol && moves[4] == player1Symbol && moves[8]== player1Symbol) {
            Toast toast = Toast.makeText(this, "PLAYER 1 WINS!", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER,0,250);
            toast.show();
            winner.setText(player1Name + " WINS!!!");
            menuButton.setVisibility(View.VISIBLE);
            return true;
        }
        if(moves[6] == player1Symbol && moves[4] == player1Symbol && moves[2] == player1Symbol) {
            Toast toast = Toast.makeText(this, "PLAYER 1 WINS!", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER,0,250);
            toast.show();
            winner.setText(player1Name + " WINS!!!");
            menuButton.setVisibility(View.VISIBLE);
            return true;
        }
        return false;
    }

    boolean p2Winner(){
        if(moves[0] == player2Symbol && moves[1] == player2Symbol && moves[2] == player2Symbol) {
            Toast toast = Toast.makeText(this, "PLAYER 2 WINS!", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER,0,250);
            toast.show();
            winner.setText(player2Name + " WINS!!!");
            menuButton.setVisibility(View.VISIBLE);
            return true;
        }
        if(moves[3] == player2Symbol && moves[4] == player2Symbol && moves[5] == player2Symbol) {
            Toast toast = Toast.makeText(this, "PLAYER 2 WINS!", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER,0,250);
            toast.show();
            winner.setText(player2Name + " WINS!!!");
            menuButton.setVisibility(View.VISIBLE);
            return true;
        }
        if(moves[6] == player2Symbol && moves[7] == player2Symbol && moves[8] == player2Symbol) {
            Toast toast = Toast.makeText(this, "PLAYER 2 WINS!", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER,0,250);
            toast.show();
            winner.setText(player2Name + " WINS!!!");
            menuButton.setVisibility(View.VISIBLE);
            return true;
        }
        if(moves[0]== player2Symbol && moves[3] == player2Symbol && moves[6] == player2Symbol) {
            Toast toast = Toast.makeText(this, "PLAYER 2 WINS!", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER,0,250);
            toast.show();
            winner.setText(player2Name + " WINS!!!");
            menuButton.setVisibility(View.VISIBLE);
            return true;
        }
        if(moves[1] == player2Symbol && moves[4] == player2Symbol && moves[7] == player2Symbol) {
            Toast toast = Toast.makeText(this, "PLAYER 2 WINS!", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER,0,250);
            toast.show();
            winner.setText(player2Name + " WINS!!!");
            menuButton.setVisibility(View.VISIBLE);
            return true;
        }
        if(moves[2]== player2Symbol && moves[5]== player2Symbol && moves[8] == player2Symbol) {
            Toast toast = Toast.makeText(this, "PLAYER 2 WINS!", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER,0,250);
            toast.show();
            winner.setText(player2Name + " WINS!!!");
            menuButton.setVisibility(View.VISIBLE);
            return true;
        }
        if(moves[0]== player2Symbol && moves[4]== player2Symbol && moves[8]== player2Symbol) {
            Toast toast = Toast.makeText(this, "PLAYER 2 WINS!", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER,0,250);
            toast.show();
            winner.setText(player2Name + " WINS!!!");
            menuButton.setVisibility(View.VISIBLE);
            return true;
        }
        if(moves[6]== player2Symbol && moves[4] == player2Symbol && moves[2] == player2Symbol) {
            Toast toast = Toast.makeText(this, "PLAYER 2 WINS!", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER,0,250);
            toast.show();
            winner.setText(player2Name + " WINS!!!");
            menuButton.setVisibility(View.VISIBLE);
            return true;
        }
        return false;
    }



    @SuppressLint("NewApi")
    @Override
    public void onClick(View view) {
        //Intent j = getIntent();
        //j.getStringExtra()
        if(currentPlayer.equals("P1")) {
            switch (view.getId()) {
                case R.id.b1:
                    if(spaceTaken(0))
                        break;
                    b1.setText(player1Symbol);
                    b1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    cPlayer.setText(player2Name);
                    SmsManager sms = SmsManager.getDefault();
                    try {
                        sms.sendTextMessage(player2Number, null, "@!@/TTT/SELECTED/b1", null, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    moves[0] = player1Symbol;
                    if(p1Winner())
                        disableButtons();
                    disableButtons();
                    break;
                case R.id.b2:
                    if(spaceTaken(1))
                        break;
                    b2.setText(player1Symbol);
                    b2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    cPlayer.setText(player2Name);
                    SmsManager sms2 = SmsManager.getDefault();
                    try {
                        sms2.sendTextMessage(player2Number, null, "@!@/TTT/SELECTED/b2", null, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    moves[1] = player1Symbol;
                    if(p1Winner())
                        disableButtons();
                    disableButtons();
                    break;
                case R.id.b3:
                    if(spaceTaken(2))
                        break;
                    b3.setText(player1Symbol);
                    b3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    cPlayer.setText(player2Name);
                    SmsManager sms3 = SmsManager.getDefault();
                    try {
                        sms3.sendTextMessage(player2Number, null, "@!@/TTT/SELECTED/b3", null, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    moves[2] = player1Symbol;
                    if(p1Winner())
                        disableButtons();
                    disableButtons();
                    break;
                case R.id.b4:
                    if(spaceTaken(3))
                        break;
                    b4.setText(player1Symbol);
                    b4.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    cPlayer.setText(player2Name);
                    SmsManager sms4 = SmsManager.getDefault();
                    try {
                        sms4.sendTextMessage(player2Number, null, "@!@/TTT/SELECTED/b4", null, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    moves[3] = player1Symbol;
                    if(p1Winner())
                        disableButtons();
                    disableButtons();
                    break;
                case R.id.b5:
                    if(spaceTaken(4))
                        break;
                    b5.setText(player1Symbol);
                    b5.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    cPlayer.setText(player2Name);
                    SmsManager sms5 = SmsManager.getDefault();
                    try {
                        sms5.sendTextMessage(player2Number, null, "@!@/TTT/SELECTED/b5", null, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    moves[4] = player1Symbol;
                    if(p1Winner())
                        disableButtons();
                    disableButtons();
                    break;
                case R.id.b6:
                    if(spaceTaken(5))
                        break;
                    b6.setText(player1Symbol);
                    b6.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    cPlayer.setText(player2Name);
                    SmsManager sms6 = SmsManager.getDefault();
                    try {
                        sms6.sendTextMessage(player2Number, null, "@!@/TTT/SELECTED/b6", null, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    moves[5] = player1Symbol;
                    if(p1Winner())
                        disableButtons();
                    disableButtons();
                    break;
                case R.id.b7:
                    if(spaceTaken(6))
                        break;
                    b7.setText(player1Symbol);
                    b7.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    cPlayer.setText(player2Name);
                    SmsManager sms7 = SmsManager.getDefault();
                    try {
                        sms7.sendTextMessage(player2Number, null, "@!@/TTT/SELECTED/b7", null, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    moves[6] = player1Symbol;
                    if(p1Winner())
                        disableButtons();
                    disableButtons();
                    break;
                case R.id.b8:
                    if(spaceTaken(7))
                        break;
                    b8.setText(player1Symbol);
                    b8.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    cPlayer.setText(player2Name);
                    SmsManager sms8 = SmsManager.getDefault();
                    try {
                        sms8.sendTextMessage(player2Number, null, "@!@/TTT/SELECTED/b8", null, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    moves[7] = player1Symbol;
                    if(p1Winner())
                        disableButtons();
                    disableButtons();
                    break;
                case R.id.b9:
                    if(spaceTaken(8))
                        break;
                    b9.setText(player1Symbol);
                    b9.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    cPlayer.setText(player2Name);
                    SmsManager sms9 = SmsManager.getDefault();
                    try {
                        sms9.sendTextMessage(player2Number, null, "@!@/TTT/SELECTED/b9", null, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    moves[8] = player1Symbol;
                    if(p1Winner())
                        disableButtons();
                    disableButtons();
                    break;
                default:
                    break;
            }
        }
        else{
            switch (view.getId()){
                case R.id.b1:
                    if(spaceTaken(0))
                        break;
                    b1.setText(player2Symbol);
                    b1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    cPlayer.setText(player1Name);
                    SmsManager sms = SmsManager.getDefault();
                    try {
                        sms.sendTextMessage(player1Number, null, "@!@/TTT/SELECTED/b1", null, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    moves[0] =player2Symbol;
                    if(p2Winner())
                        disableButtons();

                    disableButtons();
                    break;
                case R.id.b2:
                    if(spaceTaken(1))
                        break;
                    b2.setText(player2Symbol);
                    b2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    cPlayer.setText(player1Name);
                    SmsManager sms2 = SmsManager.getDefault();
                    try {
                        sms2.sendTextMessage(player1Number, null, "@!@/TTT/SELECTED/b2", null, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    moves[1] =player2Symbol;
                    if(p2Winner())
                        disableButtons();

                    disableButtons();
                    break;
                case R.id.b3:
                    if(spaceTaken(2))
                        break;
                    b3.setText(player2Symbol);
                    b3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    cPlayer.setText(player1Name);
                    SmsManager sms3 = SmsManager.getDefault();
                    try {
                        sms3.sendTextMessage(player1Number, null, "@!@/TTT/SELECTED/b3", null, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    moves[2] = player2Symbol;
                    if(p2Winner())
                        disableButtons();

                    disableButtons();
                    break;
                case R.id.b4:
                    if(spaceTaken(3))
                        break;
                    b4.setText(player2Symbol);
                    b4.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    cPlayer.setText(player1Name);
                    SmsManager sms4 = SmsManager.getDefault();
                    try {
                        sms4.sendTextMessage(player1Number, null, "@!@/TTT/SELECTED/b4", null, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    moves[3] = player2Symbol;
                    if(p2Winner())
                        disableButtons();

                    disableButtons();
                    break;
                case R.id.b5:
                    if(spaceTaken(4))
                        break;
                    b5.setText(player2Symbol);
                    b5.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    cPlayer.setText(player1Name);
                    SmsManager sms5 = SmsManager.getDefault();
                    try {
                        sms5.sendTextMessage(player1Number, null, "@!@/TTT/SELECTED/b5", null, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    moves[4] = player2Symbol;
                    if(p2Winner())
                        disableButtons();

                    disableButtons();
                    break;
                case R.id.b6:
                    if(spaceTaken(5))
                        break;
                    b6.setText(player2Symbol);
                    b6.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    cPlayer.setText(player1Name);
                    SmsManager sms6 = SmsManager.getDefault();
                    try {
                        sms6.sendTextMessage(player1Number, null, "@!@/TTT/SELECTED/b6", null, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    moves[5] = player2Symbol;
                    if(p2Winner())
                        disableButtons();

                    disableButtons();
                    break;
                case R.id.b7:
                    if(spaceTaken(6))
                        break;
                    b7.setText(player2Symbol);
                    b7.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    cPlayer.setText(player1Name);
                    SmsManager sms7 = SmsManager.getDefault();
                    try {
                        sms7.sendTextMessage(player1Number, null, "@!@/TTT/SELECTED/b7", null, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    moves[6] = player2Symbol;
                    if(p2Winner())
                        disableButtons();

                    disableButtons();
                    break;
                case R.id.b8:
                    if(spaceTaken(7))
                        break;
                    b8.setText(player2Symbol);
                    b8.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    cPlayer.setText(player1Name);
                    SmsManager sms8 = SmsManager.getDefault();
                    try {
                        sms8.sendTextMessage(player1Number, null, "@!@/TTT/SELECTED/b8", null, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    moves[7] = player2Symbol;
                    if(p2Winner())
                        disableButtons();

                    disableButtons();
                    break;
                case R.id.b9:
                    if(spaceTaken(8))
                        break;
                    b9.setText(player2Symbol);
                    b9.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    cPlayer.setText(player1Name);
                    SmsManager sms9 = SmsManager.getDefault();
                    try {
                        sms9.sendTextMessage(player1Number, null, "@!@/TTT/SELECTED/b9", null, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    moves[8] = player2Symbol;
                    if(p2Winner())
                        disableButtons();

                    disableButtons();
                    break;
                default:
                    break;
            }
        }
    }
}
