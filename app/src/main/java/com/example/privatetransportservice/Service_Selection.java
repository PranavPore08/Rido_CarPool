package com.example.privatetransportservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Service_Selection extends AppCompatActivity {

    Button btn_ride_pool,btn_parkio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_service_selection);
        btn_ride_pool=findViewById(R.id.Btn_Ride_Sharing);
        btn_parkio=findViewById(R.id.Btn_Parkio);

        btn_ride_pool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent i=new Intent(Service_Selection.this,BottomNavigation.class);
                    startActivity(i);
            }
        });

        btn_parkio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Service_Selection.this,Parkio_Home.class);
                startActivity(i);
            }
        });
    }
}