package com.technical.saion.newsapp.api;

import android.support.annotation.NonNull;

import com.squareup.okhttp.OkHttpClient;
import com.technical.saion.newsapp.interfaces.NewsService;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by saion on 09.11.2015.
 */
public class ApiFactory {

    private static final String BASE_URL ="http://timesofindia.indiatimes.com";

    private static final OkHttpClient CLIENT=new OkHttpClient();

    @NonNull
    public static NewsService getNewsService()
    {return getRetrofit().create(NewsService.class);}

    private static Retrofit getRetrofit()
    {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(CLIENT)
                .build();
        return retrofit;
    }
}
