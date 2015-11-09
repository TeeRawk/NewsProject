package com.technical.saion.newsapp.contract;

import android.content.Context;
import android.support.annotation.NonNull;

import com.technical.saion.newsapp.model.NewsItem;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by saion on 09.11.2015.
 */
public class NewsContract {

    public interface Presenter extends BaseMvpPresenter<View> {
        void initNews(boolean loadIfEmpty);
        void fetchNews(Context context);
        void onNewsClicked(NewsItem newsItem);
        ArrayList<NewsItem> getListOfNews(Context context);

    }

    public interface View extends BaseMvpView
    {
        void showNews(List<NewsItem> newsList);
        void showProgress();
        void hideProgress();
        void showInfoMessage(@NonNull String message);
        void showError();

    }
}
