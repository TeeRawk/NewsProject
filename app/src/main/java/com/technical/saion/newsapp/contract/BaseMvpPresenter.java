package com.technical.saion.newsapp.contract;


/**
 * Created by saion on 09.11.2015.
 */
public interface BaseMvpPresenter<View> {
    void attachView(View newsView);

    void detachView(View newsView);
}
