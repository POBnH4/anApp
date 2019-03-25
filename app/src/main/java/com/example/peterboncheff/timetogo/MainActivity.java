package com.example.peterboncheff.timetogo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button newPickUp = findViewById(R.id.btn_NewPickUp);
        newPickUp.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_NewPickUp){
            Intent launchStartSearchIntent = new Intent (getApplicationContext(), StartSearch.class);
            startActivity(launchStartSearchIntent);
        }


    }
}
