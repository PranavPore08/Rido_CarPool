package com.example.privatetransportservice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class normal_vehicle_data extends AppCompatActivity {
    private EditText v_name,v_number,v_colour,v_year;
    //FirebaseDatabase database;
    //FirebaseAuth auth;
    Button btn;
    String seats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_normal_vehicle_data);
        v_name = findViewById(R.id.v_name);
        v_number= findViewById(R.id.v_number);
        v_colour=findViewById(R.id.v_colour);
        v_year=findViewById(R.id.v_year);
      //  database = FirebaseDatabase.getInstance();
        // auth = FirebaseAuth.getInstance();
        btn=findViewById(R.id.RegisterBtn);
        Intent i=getIntent();
        seats = getIntent().getStringExtra("count");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v_name.getText().toString().equals("") || v_number.getText().toString().equals("")){
                    Toast.makeText(normal_vehicle_data.this, "Complete Fields properly", Toast.LENGTH_SHORT).show();
                }
                else{
                    String vName = v_name.getText().toString();
                    String vNumber = v_number.getText().toString();
                    String vColour = v_colour.getText().toString();
                    String vYear = v_year.getText().toString();
                    int vSeats = Integer.parseInt(seats);
                    /*   DataBase
                    VehicleInfo vehicleInfo = new VehicleInfo(vName,vNumber,vSeats,vColour,vSeats);
                    database.getReference().child("Drivers").child(auth.getUid().toString()).setValue(vehicleInfo);*/
                    Toast.makeText(normal_vehicle_data.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                    /*Intent i=new Intent(normal_vehicle_data.this,MainActivity.class);
                    startActivity(i);*/
                }
            }
        });
    }
}