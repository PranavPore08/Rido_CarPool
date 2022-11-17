package com.example.privatetransportservice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.privatetransportservice.Adapter.ChatSupportAdapter;
import com.example.privatetransportservice.Domain.ChatModal;
import com.example.privatetransportservice.Domain.MsgModal;
import com.example.privatetransportservice.Domain.RetrofitAPI;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SuportAssistant extends AppCompatActivity {

    private RecyclerView supportRV;
    private EditText userMsgEdt;
    private FloatingActionButton sendMsgFAB;
    private final String BOT_KEY = "bot";
    private final String USER_KEY = "user";
    private ArrayList<ChatModal> chatModals;
    private ChatSupportAdapter chatSupportAdapter;
    private ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_suport_assistant);
        supportRV = findViewById(R.id.idRVChats);
        userMsgEdt = findViewById(R.id.idEdtMessage);
        sendMsgFAB = findViewById(R.id.idFABSend);
        chatModals = new ArrayList<>();
        chatSupportAdapter = new ChatSupportAdapter(chatModals,this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        supportRV.setLayoutManager(manager);
        supportRV.setAdapter(chatSupportAdapter);


        supportRV.smoothScrollToPosition(supportRV.getAdapter().getItemCount());
//        supportRV.scrollToPosition(chatModals.size() - 1);

        sendMsgFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userMsgEdt.getText().toString().isEmpty()){
                    Toast.makeText(SuportAssistant.this, "Please Enter Message", Toast.LENGTH_SHORT).show();
                    return;
                }
                getResponse(userMsgEdt.getText().toString());
                userMsgEdt.setText("");



            }
        });

    }

    public void getResponse(String message){
        chatModals.add(new ChatModal(message,USER_KEY));
        chatSupportAdapter.notifyDataSetChanged();
        String url= "http://api.brainshop.ai/get?bid=160501&key=NJHfP4A9ePzDeSzL&uid=[uid]&msg="+message;
        String BASE_URL = "http://api.brainshop.ai/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<MsgModal> call = retrofitAPI.getMessage(url);


        call.enqueue(new Callback<MsgModal>() {
            @Override
            public void onResponse(Call<MsgModal> call, Response<MsgModal> response) {
                if(response.isSuccessful()){
                    MsgModal modal = response.body();
                    chatModals.add(new ChatModal(modal.getCnt(),BOT_KEY));
                    chatSupportAdapter.notifyDataSetChanged();
                    supportRV.smoothScrollToPosition(supportRV.getAdapter().getItemCount()-1);

                }
            }

            @Override
            public void onFailure(Call<MsgModal> call, Throwable t) {

                chatModals.add(new ChatModal("Please revert your question",BOT_KEY));
                chatSupportAdapter.notifyDataSetChanged();
                Toast.makeText(SuportAssistant.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                supportRV.smoothScrollToPosition(supportRV.getAdapter().getItemCount()-1);
            }  
        });

    }
}