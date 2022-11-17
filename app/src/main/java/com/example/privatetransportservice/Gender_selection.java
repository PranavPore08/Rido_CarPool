package com.example.privatetransportservice;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.privatetransportservice.Domain.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Gender_selection extends AppCompatActivity {
    ImageView male,female;
//    int Male=0,Female=0;
    Dialog dialog;
    AppCompatButton nextButton ;
    int genderSelect=0;
    private FirebaseAuth auth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender_selection);
        male=findViewById(R.id.male);
        female=findViewById(R.id.female);
        nextButton = findViewById(R.id.genderNext);
        getSupportActionBar().hide();

        final String userFullName = getIntent().getStringExtra("userFullName");
        final String userEMail  = getIntent().getStringExtra("userEmail");
        final String userMobileNumber = getIntent().getStringExtra("userMobileNumber");
        //final String userGender = getIntent().getStringExtra("userGender");

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        String id = auth.getUid().toString();

        dialog=new Dialog(this);
        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               male.setBackground(getDrawable(R.drawable.highlight));
               genderSelect=1;
               female.setBackground(getDrawable(R.drawable.round_back_dark_blue1));
               female.getTranslationY();
            }
        });
        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genderSelect=2;
                male.setBackground(getDrawable(R.drawable.round_back_dark_blue1));
                female.setBackground(getDrawable(R.drawable.highlight));
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(genderSelect==1) {
                    Intent intent= new Intent(Gender_selection.this, Service_Selection.class);
                    String userGender="Male";
                    User user = new User(userMobileNumber,userFullName,userEMail,userGender);
                    database.getReference().child("Users").child(id).setValue(user);
                    startActivity(intent);
                }
                else if(genderSelect==2){
                    Intent intent= new Intent(Gender_selection.this, BottomNavigation.class);
                    String userGender="Female";
                    User user = new User(userMobileNumber,userFullName,userEMail,userGender);
                    database.getReference().child("Users").child(id).setValue(user);
                    Toast.makeText(Gender_selection.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Gender_selection.this, "Please select gender", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



//    public void next(View view) {
//        if(Male==1){
//            Toast.makeText(this, "Male", Toast.LENGTH_SHORT).show();
//            openDialog();
//        }
//        else if(Female==1){
//            Toast.makeText(this, "Female", Toast.LENGTH_SHORT).show();
//            dialog.setContentView(R.layout.popup_layout);
//            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//
//            ImageView imageViewclose=dialog.findViewById(R.id.img_close);
//            Button btn=dialog.findViewById(R.id.btn_ok);
//            dialog.show();
//            imageViewclose.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dialog.dismiss();
//                }
//            });
//            btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dialog.dismiss();
//                    //Login Page
//                }
//            });
//        }
//
//    }
//
//    public void openDialog(){
//        Vehicle_Info vehicle_info=new Vehicle_Info();
//        vehicle_info.show(getSupportFragmentManager(),"layout dialogue");
//    }
}