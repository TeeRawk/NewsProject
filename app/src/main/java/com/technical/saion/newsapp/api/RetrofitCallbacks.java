package com.technical.saion.newsapp.api;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by saion on 09.11.2015.
 */
public abstract class RetrofitCallbacks<T> implements Callback<T> {
    @Override
    public void onResponse(Response<T> response, Retrofit retrofit) {

    }

    @Override
    public void onFailure(Throwable t) {

    }
}
