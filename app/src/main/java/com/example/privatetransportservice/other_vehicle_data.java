package com.example.privatetransportservice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class other_vehicle_data extends AppCompatActivity {
    private EditText v_seats,v_name,v_number,v_year,v_colour;
    Button btn;
    /*FirebaseAuth auth;
    FirebaseDatabase database;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_vehicle_data);
        getSupportActionBar().hide();

       /* auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();*/
        v_name = findViewById(R.id.o_v_name);
        v_number = findViewById(R.id.o_v_number);
        v_seats = findViewById(R.id.o_v_seats);
        v_colour=findViewById(R.id.v_colour);
        v_colour=findViewById(R.id.v_colour);
        btn=findViewById(R.id.RegisterrBtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v_name.getText().toString().equals("") || v_seats.getText().toString().equals("") || v_number.getText().toString().equals("")){
                    Toast.makeText(other_vehicle_data.this, "Complete Fields properly", Toast.LENGTH_SHORT).show();
                }
                else{
                    String vName = v_name.getText().toString();
                    String vNumber = v_number.getText().toString();
                    String vColour = v_colour.getText().toString();
                    String vYear = v_year.getText().toString();
                    int vSeats = Integer.parseInt(v_seats.getText().toString());
                  /*  VehicleInfo vehicleInfo = new VehicleInfo(vName, vNumber,vSeats,vColour,vYear);
                    database.getReference().child("Drivers").child(auth.getUid().toString()).setValue(vehicleInfo);*/
                    Toast.makeText(other_vehicle_data.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(other_vehicle_data.this, BottomNavigation.class);
                    startActivity(i);
                }
            }
        });
    }
}