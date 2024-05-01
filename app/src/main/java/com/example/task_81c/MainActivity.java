package com.example.task_81c;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

//        navigate to chat screen
        Button startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(v -> {
            Intent startChatIntent = new Intent(this, ChatActivity.class);
            startActivity(startChatIntent);
        });
    }
}