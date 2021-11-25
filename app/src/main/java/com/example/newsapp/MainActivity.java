package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NewsItemClicked{
    NewsListAdapter mAdapter;
    MainActivity mainActivityContext;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        mainActivityContext = this;
        fetchApiData();
    }
    private MainActivity getMainActivityContext(){
        return mainActivityContext;
    }
    private void fetchApiData(){
        Call<News> call=RetrofitClient.getInstance().getApiHolder().getNews();
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(@NonNull Call<News> call, Response<News> response) {
                if (response.isSuccessful()) {
                    News.Articles[] articles=response.body().articles;
                    mAdapter=new NewsListAdapter(getMainActivityContext());
                    mAdapter.updateNews(articles);

                    recyclerView.setAdapter(mAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getMainActivityContext()));
                }
                else{
                    Toast.makeText(getApplicationContext(), "responseCode:"+response.code(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    public void onItemClicked(News.Articles item) {

        String url =item.url;
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(url));
    }
}