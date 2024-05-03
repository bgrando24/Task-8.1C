package com.example.task_81c;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestHandler {
    private static final String DEBUG_TAG = "DEBUG_LOG";
    private static final String baseURL = "http://10.0.2.2:5000/";
    private Retrofit retrofit;
    private List<ChatHistory> chatHistory;

    public RequestHandler() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().readTimeout(10, java.util.concurrent.TimeUnit.MINUTES).build())
                .build();
        this.chatHistory = new ArrayList<>(); // Initialize chatHistory
    }

    public interface SendMessageCallback {
        void onMessageReceived(String message);
    }

    public void sendMessage(String userMessage, SendMessageCallback callback) {
        Log.d(DEBUG_TAG, "Sending message: " + userMessage);
        ApiEndpoints apiEndpoints = retrofit.create(ApiEndpoints.class);
        Call<ResponseBody> call = apiEndpoints.sendMessage(new RequestBody(userMessage, this.chatHistory));
        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                Log.d(DEBUG_TAG, "Received response: " + response.body().message);
                if (response.isSuccessful() && response.body() != null) {
                    chatHistory.add(new ChatHistory(userMessage, response.body().message)); // update chat history
                    callback.onMessageReceived(response.body().message);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                Log.e(DEBUG_TAG, "Failed to send message: " + throwable.getMessage());
                callback.onMessageReceived("Failed to send message");
            }
        });
    }
}
