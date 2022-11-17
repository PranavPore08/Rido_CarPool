package com.example.privatetransportservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class Register extends AppCompatActivity {

    private boolean passwordShowing = false;
    private  boolean conPasswordShowing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        final EditText nameEt = findViewById(R.id.signUpName);
        final EditText mobEmailEt = findViewById(R.id.emailEt);


        final AppCompatButton signUpButton = findViewById(R.id.signUpBtn);

        final String userMobileNumber = getIntent().getStringExtra("userMobileNumber");

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = nameEt.getText().toString();
                final String mobEmail = mobEmailEt.getText().toString();
                Intent intent = new Intent(Register.this, Gender_selection.class);
                intent.putExtra("userFullName", name );
                intent.putExtra("userEmail", mobEmail);
                intent.putExtra("userMobileNumber",userMobileNumber);
                startActivity(intent);

            }
        });

    }
}