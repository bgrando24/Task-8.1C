package com.example.task_81c;

import java.util.List;

public class RequestBody {
    private String userMessage;
    private List<ChatHistory> chatHistory;

    public RequestBody(String userMessage, List<ChatHistory> chatHistory) {
        this.userMessage = userMessage;
        this.chatHistory = chatHistory;
    }
}
