package com.example.newsapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    ApiHolder apiHolder;
    static RetrofitClient instance=null;
    private RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiHolder.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiHolder = retrofit.create(ApiHolder.class);
    }

    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public ApiHolder getApiHolder() {
        return apiHolder;
    }
}
