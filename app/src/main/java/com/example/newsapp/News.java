package com.example.newsapp;

public class News {
//     @SerializedName("articles")
     Articles[] articles;

    String totalResults,status;

     public class Source{
          String id, name;
     }

    public class Articles {
        Source source;
        String title,author,url,urlToImage,publishedAt,content,description;
    }
}
