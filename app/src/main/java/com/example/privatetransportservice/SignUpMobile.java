package com.example.privatetransportservice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SignUpMobile extends AppCompatActivity {


    FirebaseAuth auth;
    @Override
    public void onStart() {
        super.onStart();
        auth=FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser!=null){
            Intent i=new Intent(this,Service_Selection.class);
            startActivity(i);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_mobile);
        checkConnection();
        getSupportActionBar().hide();
        final EditText mobileN = findViewById(R.id.signUpMobileNumber);
        final AppCompatButton continueButton = findViewById(R.id.signUpContinue);
        final ImageView back_arrow = findViewById(R.id.signUpback_arrow);
        final ProgressBar progressBar = findViewById(R.id.signUpProgressbar);
        
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String mobile = mobileN.getText().toString().trim();
                if(mobile.length()==10){
                    progressBar.setVisibility(View.VISIBLE);
                    continueButton.setVisibility(View.INVISIBLE);

                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            "+91" + mobile,
                            60,
                            TimeUnit.SECONDS,
                            SignUpMobile.this,
                            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                    progressBar.setVisibility(View.GONE);
                                    continueButton.setVisibility(View.VISIBLE);
//                                    Toast.makeText(SignUpMobile.this, "On Complete Listener", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {
                                    progressBar.setVisibility(View.GONE);
                                    continueButton.setVisibility(View.VISIBLE);
                                    Toast.makeText(SignUpMobile.this,  e.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    progressBar.setVisibility(View.GONE);
                                    continueButton.setVisibility(View.VISIBLE);
                                    Toast.makeText(SignUpMobile.this, "OTP sent successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), OTPVerification.class);
                                    intent.putExtra("mobileSignUp", mobile);
                                    intent.putExtra("backendOtp", s);
                                    startActivity(intent);
                                }
                            }
                    );


//                    Intent intent = new Intent(SignUpMobile.this, OTPVerification.class);
//                    intent.putExtra("mobileSignUp", mobile);
//                    startActivity(intent);
                }
                else{
                    Toast.makeText(SignUpMobile.this, "Enter Valid Mobile Number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    public void checkConnection(){
        ConnectivityManager manger=(ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork=manger.getActiveNetworkInfo();
        if(null==activeNetwork) {
            Snackbar.make(findViewById(android.R.id.content), "Check Your Connection", Snackbar.LENGTH_SHORT).show();
        }
    }
}