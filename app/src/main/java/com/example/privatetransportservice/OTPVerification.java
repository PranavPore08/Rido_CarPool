package com.example.privatetransportservice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class OTPVerification extends AppCompatActivity {

    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(s.length()>0 ){

                if(selectedEtPosition==0){
                    selectedEtPosition = 1;
                    showKeyboard(otpEt2);
                }
                else if(selectedEtPosition==1) {
                    selectedEtPosition=2;
                    showKeyboard(otpEt3);
                }
                else if(selectedEtPosition==2){
                    selectedEtPosition=3;
                    showKeyboard(otpEt4);
                }
                else if(selectedEtPosition==3){
                    selectedEtPosition=4;
                    showKeyboard(otpEt5);
                }
                else if(selectedEtPosition==4){
                    selectedEtPosition=5;
                    showKeyboard(otpEt6);
                }

            }
        }
    };

    private EditText otpEt1, otpEt2, otpEt3, otpEt4, otpEt5, otpEt6;
    private TextView resendButton;
    private boolean resendEnabled;
    private int resendTime = 60;
    private int selectedEtPosition = 0;
    private boolean userExist = false;
    private ProgressBar progressBar;
    FirebaseDatabase database;
    String backendOtp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverification);
        getSupportActionBar().hide();

        otpEt1 = findViewById(R.id.otpEt1);
        otpEt2 = findViewById(R.id.otpEt2);
        otpEt3 = findViewById(R.id.otpEt3);
        otpEt4 = findViewById(R.id.otpEt4);
        otpEt5 = findViewById(R.id.otpEt5);
        otpEt6 = findViewById(R.id.otpEt6);
        resendButton = findViewById(R.id.resendButton);
        database = FirebaseDatabase.getInstance();

//        final TextView otpEmail = findViewById(R.id.otpEmail);
        final TextView otpMobile = findViewById(R.id.otpMobile);

//        final String getEmail = getIntent().getStringExtra("email");
        final String getMobile = getIntent().getStringExtra("mobileSignUp");
        backendOtp = getIntent().getStringExtra("backendOtp");

        AppCompatButton verifyButton = findViewById(R.id.OTPVerify);
        progressBar= findViewById(R.id.otpProgressbar);

//        otpEmail.setText(getEmail);
        otpMobile.setText(getMobile);


        otpEt1.addTextChangedListener(textWatcher);
        otpEt2.addTextChangedListener(textWatcher);
        otpEt3.addTextChangedListener(textWatcher);
        otpEt4.addTextChangedListener(textWatcher);
        otpEt5.addTextChangedListener(textWatcher);
        otpEt6.addTextChangedListener(textWatcher);

        showKeyboard(otpEt1);

        startCountDownTimer();

        DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("Users");
        ref.orderByChild("mobile").equalTo(getMobile).addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                if(dataSnapshot.exists()) {
                    //username exist
//                    Toast.makeText(OTPVerification.this, "Mobile Number is exist", Toast.LENGTH_SHORT).show();
                    userExist = true;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(OTPVerification.this, "Error Occurred", Toast.LENGTH_SHORT).show();
            }
        });

        resendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(resendEnabled){
                    Toast.makeText(OTPVerification.this, "Resend Button clicked", Toast.LENGTH_SHORT).show();
                    // handle new resend code here
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            "+91" + getMobile,
                            60,
                            TimeUnit.SECONDS,
                            OTPVerification.this,
                            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
//                                    Toast.makeText(OTPVerification.this, "On complete listener", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {
                                    Toast.makeText(OTPVerification.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    backendOtp = s;
                                    Toast.makeText(OTPVerification.this, "OTP sent successfully", Toast.LENGTH_SHORT).show();
                                }
                            }
                    );
                    startCountDownTimer();
                }
            }
        });

        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String generateOtp = otpEt1.getText().toString()+otpEt2.getText().toString()+otpEt3.getText().toString()+otpEt4.getText().toString()+otpEt5.getText().toString()+otpEt6.getText().toString();
                if(generateOtp.length()==6){
                    // handle otp verification here
                    if(backendOtp!=null){
                        progressBar.setVisibility(View.VISIBLE);
                        verifyButton.setVisibility(View.INVISIBLE);

                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(backendOtp, generateOtp);
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        progressBar.setVisibility(View.GONE);
                                        verifyButton.setVisibility(View.VISIBLE);
                                        
                                        if(task.isSuccessful()){
                                            if(userExist){
                                                Intent intent = new Intent(OTPVerification.this, Service_Selection.class);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                startActivity(intent);
                                            }
                                            else {
                                                Intent intent = new Intent(OTPVerification.this, Register.class);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                intent.putExtra("userMobileNumber", getMobile);
                                                startActivity(intent);
                                            }
                                        }
                                        else{
                                            Toast.makeText(OTPVerification.this, "Please check internet connection", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                    else{
                        Toast.makeText(OTPVerification.this, "Please check your internet connection", Toast.LENGTH_SHORT).show();
                    }
                    
                }
                else {
                    Toast.makeText(OTPVerification.this, "Please enter all digits", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void showKeyboard(EditText otpET){
        otpET.requestFocus();

        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(otpET, InputMethodManager.SHOW_IMPLICIT);
    }

    private void startCountDownTimer(){
        resendEnabled = false;
        resendButton.setTextColor(Color.parseColor("#99000000"));

        new CountDownTimer(resendTime*1000, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                resendButton.setText("Resend Code ("+(millisUntilFinished/1000)+")");
            }

            @Override
            public void onFinish() {
                resendEnabled = true;
                resendButton.setText("Resend Code");
                resendButton.setTextColor(getResources().getColor(R.color.primary));
            }
        }.start();
    }



    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_DEL){
            if(selectedEtPosition == 5){
                selectedEtPosition = 4;
                showKeyboard(otpEt5);
            }
            else if(selectedEtPosition == 4){
                selectedEtPosition = 3;
                showKeyboard(otpEt4);
            }
            else if(selectedEtPosition == 3){
                selectedEtPosition = 2;
                showKeyboard(otpEt3);
            }
            else if(selectedEtPosition == 2){
                selectedEtPosition = 1;
                showKeyboard(otpEt2);
            }
            else if(selectedEtPosition==1){
                selectedEtPosition = 0;
                showKeyboard(otpEt1);
            }

            return true;
        }
        else {
            return super.onKeyUp(keyCode, event);
        }

    }
}