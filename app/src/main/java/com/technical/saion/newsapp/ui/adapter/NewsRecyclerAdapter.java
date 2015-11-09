package com.technical.saion.newsapp.ui.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.technical.saion.newsapp.R;
import com.technical.saion.newsapp.model.NewsItem;

import java.util.ArrayList;

/**
 * Created by saion on 09.11.2015.
 */
public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.MainViewHolder> {
    private ArrayList<NewsItem> mNewsItems;
    private static final String EXTRA_CUSTOM_TABS_SESSION = "android.support.customtabs.extra.SESSION";
    private static final String EXTRA_CUSTOM_TABS_TOOLBAR_COLOR = "android.support.customtabs.extra.TOOLBAR_COLOR";
    private Context mContext;

    public NewsRecyclerAdapter(Context context,ArrayList<NewsItem> newsItems)
    {   this.mContext=context;
        this.mNewsItems=newsItems;

    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.recycler_view_item, parent, false);
        return new MainViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder,  int position) {
        final NewsItem newsItem=mNewsItems.get(position);
        holder.title.setText(newsItem.getTitle());
        holder.date.setText(newsItem.getDate());
        holder.description.setText(newsItem.getDesc());

        if(!"null".equals(newsItem.getImageLink()))
        {
            Picasso.with(mContext).load(newsItem.getImageLink()).into(holder.image);
        }
        holder.container.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
            @Override
            public void onClick(View v) {
                String url = newsItem.getWebURL();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                Bundle extras = new Bundle();
                extras.putBinder(EXTRA_CUSTOM_TABS_SESSION,
                       null );
                intent.putExtra(EXTRA_CUSTOM_TABS_TOOLBAR_COLOR, mContext.getResources().getColor(R.color.colorPrimary));
                intent.putExtras(extras);
                mContext.startActivity(intent,extras);
            }
        });



    }

    @Override
    public int getItemCount() {
        return mNewsItems.size();
    }


    protected class MainViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ImageView image;
        private TextView description;
        private TextView date;
        private CardView container;


        public MainViewHolder(View itemView) {
            super(itemView);

            title =(TextView)itemView.findViewById(R.id.recycler_view_item_title);
            image =(ImageView)itemView.findViewById(R.id.recycler_view_item_image);
            description =(TextView)itemView.findViewById(R.id.recycler_view_item_desc);
            date=(TextView) itemView.findViewById(R.id.recycler_view_item_date);
            container=(CardView) itemView.findViewById(R.id.recycler_view_item_card);
        }

    }
}
