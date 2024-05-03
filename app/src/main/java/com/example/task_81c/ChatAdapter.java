package com.example.task_81c;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ChatMessage> chatMessages = new ArrayList<>();

    public ChatAdapter() {}

    public void addChatMessage(String message, boolean isUserMessage) {
        this.chatMessages.add(new ChatMessage(message, isUserMessage));
    }

    @Override
    public int getItemViewType(int position) {
        return this.chatMessages.get(position).isUserMessage ? 1 : 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
//        determine if should be user chat bubble or AI chat bubble
        if (viewType == 1) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_bubble_right, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_bubble_left, parent, false);
        }
        return new ChatViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ChatViewHolder) holder).bind(chatMessages.get(position));
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    public static class ChatViewHolder extends RecyclerView.ViewHolder {
        TextView chatBubbleTextView;

        public ChatViewHolder(View itemView, int viewType) {
            super(itemView);
            if (viewType == 1) {
                chatBubbleTextView = itemView.findViewById(R.id.chatBubbleRightTextView);
            } else {
                chatBubbleTextView = itemView.findViewById(R.id.chatBubbleLeftTextView);
            }
        }

        public void bind(ChatMessage chatMessage) {
            chatBubbleTextView.setText(chatMessage.message);
        }
    }


}
