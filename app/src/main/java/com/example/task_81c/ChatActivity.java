package com.example.task_81c;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

//    helper function
    public void addMessage(String message, boolean isUserMessage) {
        chatAdapter.addChatMessage(message, isUserMessage);
        chatAdapter.notifyItemInserted(chatMessages.size() - 1);
    }

    //        relevant components
    private RecyclerView recyclerView;
    private ChatAdapter chatAdapter;
    private List<ChatMessage> chatMessages = new ArrayList<>();
    private Button sendNewMessageButton;
    private EditText newMessageEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chat);

//        initialise HTTP handler
        RequestHandler handler = new RequestHandler();

//        assign components
        recyclerView = findViewById(R.id.chatRecyclerView);
        chatAdapter = new ChatAdapter();
        recyclerView.setAdapter(chatAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new VerticalSpacingDecoration(10));  // adjust vertical spacing between chat bubbles
        sendNewMessageButton = findViewById(R.id.sendNewMessageButton);
        newMessageEditText = findViewById(R.id.newMessageEditText);

//        send message when user clicks send button
        sendNewMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = newMessageEditText.getText().toString();
                addMessage(message, true);
                newMessageEditText.setText("");
                handler.sendMessage(message, new RequestHandler.SendMessageCallback() {
                    @Override
                    public void onMessageReceived(String responseMessage) {
                        addMessage(responseMessage, false);
                    }
                });
            }
        });

    }
}