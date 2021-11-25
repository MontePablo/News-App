package com.example.newsapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiHolder {
    String BASE_URL = "https://newsapi.org/";
    @GET("v2/top-headlines?country=in&apiKey=3e82273a780e4d78bd3a425fdd8dba6a")
//    @GET("v2/top-headlines?country=us&category=business&apiKey=3e82273a780e4d78bd3a425fdd8dba6a")
    Call<News> getNews();
}
