package com.example.newsapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class NewsListAdapter extends RecyclerView.Adapter<NewsViewHolder> {
    News.Articles[] items;
    NewsItemClicked listener;
    NewsListAdapter(NewsItemClicked listener){
        this.listener=listener;
    }
    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news,parent,false);
        NewsViewHolder viewHolder=new NewsViewHolder(view);
        view.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onItemClicked(items[viewHolder.getAdapterPosition()]);
                    }
                }
        );
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News.Articles currentItem=items[position];
        holder.titleView.setText(currentItem.title);
        holder.descriptionView.setText(currentItem.description);
        holder.dateView.setText(currentItem.publishedAt.substring(0,10));
        holder.timeView.setText(currentItem.publishedAt.substring(11,16));
        holder.sourceView.setText(currentItem.source.name);
//        if(currentItem.urlToImage!=null){
            Glide.with(holder.itemView.getContext()).load(currentItem.urlToImage).into(holder.imageView);
//        }
    }

    @Override
    public int getItemCount() {
        return items.length;
    }
    void updateNews(News.Articles[] updatedNews){
        items=updatedNews;
        notifyDataSetChanged();
    }
}
class NewsViewHolder extends RecyclerView.ViewHolder {
    TextView titleView;
    ImageView imageView;
    TextView descriptionView;
    TextView dateView;
    TextView timeView;
    TextView sourceView;
    public NewsViewHolder(@NonNull View itemView) {
        super(itemView);
        titleView=itemView.findViewById(R.id.itemTitle);
        imageView=itemView.findViewById(R.id.image);
        descriptionView=itemView.findViewById(R.id.description);
        dateView=itemView.findViewById(R.id.date);
        timeView=itemView.findViewById(R.id.time);
        sourceView=itemView.findViewById(R.id.source);

    }
}
interface NewsItemClicked{
    void onItemClicked(News.Articles item);
}
