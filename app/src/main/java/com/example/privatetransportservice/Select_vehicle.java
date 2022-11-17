package com.example.privatetransportservice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class Select_vehicle extends AppCompatActivity {
    LinearLayout b1,b2,b3,b4;
    int vehicleType= 0;
    String count="0";
    AppCompatButton registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_vehicle);
        b1=findViewById(R.id.bike);
        b2=findViewById(R.id.car4);
        b3=findViewById(R.id.car6);
        b4=findViewById(R.id.other);
        registerButton=findViewById(R.id.TypeVehRegisterBtn);
        getSupportActionBar().hide();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b1.setBackground(getDrawable(R.drawable.highlight));
                b2.setBackground(getDrawable(R.drawable.round_back_dark_blue1));
                b3.setBackground(getDrawable(R.drawable.round_back_dark_blue1));
                b4.setBackground(getDrawable(R.drawable.round_back_dark_blue1));
                count="2";
                vehicleType=1;
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b1.setBackground(getDrawable(R.drawable.round_back_dark_blue1));
                b2.setBackground(getDrawable(R.drawable.highlight));
                b3.setBackground(getDrawable(R.drawable.round_back_dark_blue1));
                b4.setBackground(getDrawable(R.drawable.round_back_dark_blue1));
                vehicleType=2;
                count="4";
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b1.setBackground(getDrawable(R.drawable.round_back_dark_blue1));
                b2.setBackground(getDrawable(R.drawable.round_back_dark_blue1));
                b3.setBackground(getDrawable(R.drawable.highlight));
                b4.setBackground(getDrawable(R.drawable.round_back_dark_blue1));
                vehicleType=3;
                count="6";
                /*Vehicle_Info vehicle_info=new Vehicle_Info(6);
               Intent i=new Intent(Vehicle_type.this,Vehicle_data.class);
               startActivity(i);*/
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b1.setBackground(getDrawable(R.drawable.round_back_dark_blue1));
                b2.setBackground(getDrawable(R.drawable.round_back_dark_blue1));
                b3.setBackground(getDrawable(R.drawable.round_back_dark_blue1));
                b4.setBackground(getDrawable(R.drawable.highlight));
                vehicleType=4;
                count="4";
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(vehicleType<Integer.parseInt(count)){
                    Intent i=new Intent(Select_vehicle.this, normal_vehicle_data.class);
                    i.putExtra("count", count);
                    startActivity(i);
                }
                else if(vehicleType==Integer.parseInt(count)){
                    startActivity(new Intent(Select_vehicle.this, other_vehicle_data.class));
                }
                else {
                    Toast.makeText(Select_vehicle.this, "Please select type of vehicle", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}