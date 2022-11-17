package com.example.privatetransportservice;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BottomNavigation extends AppCompatActivity {

    FirebaseUser user;
    FirebaseAuth auth;
    FirebaseDatabase database;
    MeowBottomNavigation bottomNavigation;
    String name, mobile;
    int a=10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
        getSupportActionBar().hide();
        database=FirebaseDatabase.getInstance();
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        checkConnection();
        if(a==10) {
            DatabaseReference ref = database.getReference().child("Users/").child(user.getUid());
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    name = snapshot.child("name").getValue(String.class);
                    mobile = snapshot.child("mobile").getValue(String.class);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            bottomNavigation = findViewById(R.id.bottom_navigation);
            bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_baseline_message_24));
            bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.start));
            bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.home));
            bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.history));
            bottomNavigation.add(new MeowBottomNavigation.Model(5, R.drawable.account_circle));

            bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
                @Override
                public void onShowItem(MeowBottomNavigation.Model item) {
                    Fragment fragment = null;
                    switch (item.getId()) {
                        case 1:
                            fragment = new Inbox();
                            break;
                        case 2:
                            fragment = new start();
                            break;
                        case 3:
                            fragment = new com.example.privatetransportservice.home();
                            break;
                        case 4:
                            fragment = new history();
                            break;
                        case 5:
                            fragment = new Profile();
                            Bundle b = new Bundle();
                            b.putString("name", name);
                            b.putString("mobile", mobile);
                            fragment.setArguments(b);
                            break;
                    }
                    loadFragment(fragment);
                }
            });
            bottomNavigation.setCount(1, "10");
            bottomNavigation.show(3, true);
            bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
                @Override
                public void onClickItem(MeowBottomNavigation.Model item) {
                }
            });
            bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
                @Override
                public void onReselectItem(MeowBottomNavigation.Model item) {

                }
            });
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout,fragment)
                .commit();
    }

    public void checkConnection(){

        ConnectivityManager manger=(ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork=manger.getActiveNetworkInfo();
        if(null==activeNetwork) {
            a=0;
            Snackbar.make(findViewById(android.R.id.content), "Check Your Connection", Snackbar.LENGTH_SHORT).show();
        }
    }
}