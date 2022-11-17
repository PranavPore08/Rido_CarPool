package com.example.privatetransportservice;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.privatetransportservice.Domain.VehicleInfo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Vehicle_Info extends AppCompatDialogFragment {
    int seats;

    private EditText v_name,v_no;
    FirebaseDatabase database;
    FirebaseAuth auth;

    public Vehicle_Info(int seats) {
        this.seats = seats;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.layout_dialogue,null);
        v_name = view.findViewById(R.id.v_name);
        v_no= view.findViewById(R.id.v_number);
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();


        builder.setView(view)
                .setTitle("Vehicle Information")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String vName = v_name.getText().toString();
                        String vNumber = v_no.getText().toString();
                        int vSeats =  seats;
                        VehicleInfo vehicleInfo = new VehicleInfo(vName,vNumber,vSeats);
                        database.getReference().child("Drivers").child(auth.getUid().toString()).setValue(vehicleInfo);
                    }
                });
        v_name=view.findViewById(R.id.v_name);
        v_no=view.findViewById(R.id.v_number);
        return builder.create();
    }
}
