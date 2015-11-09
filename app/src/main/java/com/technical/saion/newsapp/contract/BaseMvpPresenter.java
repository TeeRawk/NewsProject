package com.technical.saion.newsapp.contract;

import com.technical.saion.newsapp.model.NewsItem;

/**
 * Created by saion on 09.11.2015.
 */
public interface BaseMvpPresenter <BaseMvpView> {
       void attachView(NewsContract.View view);
        void detachView (NewsContract.View view);
}
