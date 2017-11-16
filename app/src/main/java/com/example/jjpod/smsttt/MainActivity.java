package com.example.jjpod.smsttt;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView text = null;
    Button startButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text =  findViewById(R.id.textView);
        text.setShadowLayer(10,0,0, Color.BLACK);

        startButton = findViewById(R.id.startButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), Settings.class);
                i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(i);
                finish();
            }
        });



    }
}
