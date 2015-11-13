package com.technical.saion.newsapp.contract;

import android.content.Context;

import com.technical.saion.newsapp.model.NewsItem;

import java.util.ArrayList;

/**
 * Created by saion on 12.11.2015.
 */
public interface Presenter extends BaseMvpPresenter<NewsView> {
    void fetchNews(Context context);

    ArrayList<NewsItem> getListOfNews(Context context);
}
