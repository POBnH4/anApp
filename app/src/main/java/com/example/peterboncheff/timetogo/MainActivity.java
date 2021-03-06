package com.example.peterboncheff.timetogo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private TextView premium;
    private TextView departure, arrival, delay, onYourWay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button newPickUp = findViewById(R.id.btn_NewPickUp);
        newPickUp.setOnClickListener(this);

        init();
    }

    private void init(){
        this.premium = findViewById(R.id.tv_Upgrade);
        this.premium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent indent = new Intent(getBaseContext(), PremiumActivity.class);
                startActivity(indent);
            }
        });

        this.departure = findViewById(R.id.departure);
        this.arrival = findViewById(R.id.arrival);
        this.delay = findViewById(R.id.delay);
        this.onYourWay = findViewById(R.id.onYourWay);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_NewPickUp){
            Intent launchStartSearchIntent = new Intent (getApplicationContext(), StartSearch.class);
            startActivity(launchStartSearchIntent);
        }


    }
}

