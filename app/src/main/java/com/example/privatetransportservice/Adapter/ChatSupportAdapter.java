package com.example.privatetransportservice.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.privatetransportservice.Domain.ChatModal;
import com.example.privatetransportservice.R;

import java.util.ArrayList;

public class ChatSupportAdapter extends RecyclerView.Adapter {
    private ArrayList<ChatModal> chatModals;
    private Context context;

    public ChatSupportAdapter(ArrayList<ChatModal> chatModals, Context context) {
        this.chatModals = chatModals;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case 0:
                view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_msg_support, parent,false);
                return  new UserViewHolder(view);
            case 1:
                view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.bot_message_support,parent,false);
                return new BotViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatModal chatModal = chatModals.get(position);
        switch (chatModal.getSender()){
            case "user":
                ((UserViewHolder)holder).userSupport.setText(chatModal.getMessage());
                break;
            case "bot":
                ((BotViewHolder)holder).botSupport.setText(chatModal.getMessage());
                break;

        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (chatModals.get(position).getSender()){
            case "user":
                return 0;
            case "bot":
                return 1;
            default:
                return -1;
        }
    }

    @Override
    public int getItemCount() {
       return chatModals.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder{
        TextView userSupport;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            userSupport = itemView.findViewById(R.id.idUserMsg);

        }
    }

    public static class BotViewHolder extends RecyclerView.ViewHolder{
        TextView botSupport;
        public BotViewHolder(@NonNull View itemView) {
            super(itemView);
            botSupport = itemView.findViewById(R.id.idBotSupport);
        }
    }
}
