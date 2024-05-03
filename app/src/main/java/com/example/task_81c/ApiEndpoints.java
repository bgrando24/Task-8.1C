package com.example.task_81c;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiEndpoints {
    @POST("/chat")
    Call<ResponseBody> sendMessage(@Body RequestBody requestBody);
}