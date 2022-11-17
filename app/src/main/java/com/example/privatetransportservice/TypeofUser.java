package com.example.privatetransportservice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.privatetransportservice.Domain.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class TypeofUser extends AppCompatActivity {
    private FirebaseAuth auth;
    FirebaseDatabase database;
    int selectDriver = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_typeof_user);
        getSupportActionBar().hide();
        final LinearLayout driver  = findViewById(R.id.driver);
        final LinearLayout passenger = findViewById(R.id.passenger);
        final AppCompatButton continueBtn = findViewById(R.id.UTcontinueBtn);

        final String userFullName = getIntent().getStringExtra("userFullName");
        final String userEMail  = getIntent().getStringExtra("userEmail");
        final String userMobileNumber = getIntent().getStringExtra("userMobileNumber");
        final String userGender = getIntent().getStringExtra("userGender");

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        String id = auth.getUid().toString();

        driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                driver.setBackground(getDrawable(R.drawable.highlight));
                passenger.setBackground(getDrawable(R.drawable.round_back_dark_blue1));
                selectDriver = 1;

            }
        });
        passenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passenger.setBackground(getDrawable(R.drawable.highlight));
                driver.setBackground(getDrawable(R.drawable.round_back_dark_blue1));
                selectDriver = 2;

            }
        });


        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectDriver==1){
                   /* User user = new User(userMobileNumber,userFullName,userEMail,userGender,"driver");
                    database.getReference().child("Users").child(id).setValue(user);
//                    Toast.makeText(TypeofUser.this, "ID="+id+ " userMobile="+userMobileNumber+" Name="+userFullName+" email="+userEMail+" gender="+userGender+ " role="+user.getRole(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(TypeofUser.this, Vehicle_type.class));
                }
                else if(selectDriver==2){
                    User user = new User(userMobileNumber,userFullName,userEMail,userGender,"pillion");
                    database.getReference().child("Users").child(id).setValue(user);*/
                    startActivity(new Intent(TypeofUser.this, MainActivity.class));
                }
                else{
                    Toast.makeText(TypeofUser.this, "Plese select your role", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}