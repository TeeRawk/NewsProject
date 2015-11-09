package com.technical.saion.newsapp.presenter;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.technical.saion.newsapp.api.ApiFactory;
import com.technical.saion.newsapp.api.RetrofitCallbacks;
import com.technical.saion.newsapp.contract.NewsContract;
import com.technical.saion.newsapp.database.table.NewsTable;
import com.technical.saion.newsapp.interfaces.NewsService;
import com.technical.saion.newsapp.model.NewsItem;

import java.util.ArrayList;
import java.util.Iterator;

import retrofit.Call;
import retrofit.Retrofit;

/**
 * Created by saion on 09.11.2015.
 */
public class NewsPresenter implements NewsContract.Presenter{
    private  NewsService mNewsService;
    private ArrayList<NewsItem> mNewsItems;
    private Cursor data;

    public NewsPresenter ()
    {
        mNewsService= ApiFactory.getNewsService();
    }


    @Override
    public void initNews(boolean loadIfEmpty) {


    }

    @Override
    public void fetchNews(final Context context) {
        Call<JsonObject> call = mNewsService.news();

        call.enqueue(new RetrofitCallbacks<JsonObject>() {
            @Override
            public void onResponse(retrofit.Response<JsonObject> response, Retrofit retrofit) {
                if (response.isSuccess()) {

                    JsonObject object = response.body();
                    Gson gson = new Gson();
                    ArrayList<NewsItem> newsItems = new ArrayList<NewsItem>();
                    JsonArray array = object.getAsJsonArray("NewsItem");
                    Iterator<JsonElement> iterator = array.iterator();

                    while (iterator.hasNext()) {
                        JsonElement json2 = iterator.next();
                        NewsItem item = (NewsItem) gson.fromJson(json2, (Class<?>) NewsItem.class);

                        newsItems.add(item);
                    }
                    NewsTable.clear(context);

                    NewsTable.save(context, newsItems);



                }
            }
        });
    }

    @Override
    public void onNewsClicked(NewsItem newsItem) {

    }

    @Override
    public ArrayList<NewsItem> getListOfNews(Context context) {
        mNewsItems=new ArrayList<NewsItem>();
        mNewsItems.clear();
        data = context.getContentResolver().query(NewsTable.URI,
                null, null, null, null);

           if (data.moveToFirst()) {
               while (data.moveToNext()) {
                   String title = data.getString(data.getColumnIndex("title"));
                   String desc = data.getString(data.getColumnIndex("desc"));
                   String link = data.getString(data.getColumnIndex("link"));
                   String date = data.getString(data.getColumnIndex("date"));
                   String web = data.getString(data.getColumnIndex("web"));

                   NewsItem newsItem = new NewsItem(title, desc, date, link,web);
                   mNewsItems.add(newsItem);
               }
           }


           return mNewsItems;
    }


    @Override
    public void attachView(NewsContract.View view) {
    }

    @Override
    public void detachView(NewsContract.View view) {
        if (data!=null)
        {
            data.close();
        }

    }
}
