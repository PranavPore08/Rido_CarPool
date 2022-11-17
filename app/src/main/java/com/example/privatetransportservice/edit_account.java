package com.example.privatetransportservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.privatetransportservice.Domain.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class edit_account extends AppCompatActivity {
    private FirebaseAuth auth;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
        getSupportActionBar().hide();
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        String id = auth.getUid().toString();
        final EditText nameEt = findViewById(R.id.signUpName);
        final EditText mobEmailEt = findViewById(R.id.emailEt);

        final AppCompatButton signUpButton = findViewById(R.id.signUpBtn);

        final String userMobileNumber = getIntent().getStringExtra("userMobileNumber");

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             /*   final String name = nameEt.getText().toString();
                final String mobEmail = mobEmailEt.getText().toString();
                User user = new User(userMobileNumber,name,mobEmail);
                database.getReference().child("Users").child(id).setValue(user);*/
                Intent intent = new Intent(edit_account.this, BottomNavigation.class);
                Toast.makeText(edit_account.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
}