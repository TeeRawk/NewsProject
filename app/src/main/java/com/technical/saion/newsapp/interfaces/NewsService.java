package com.technical.saion.newsapp.interfaces;

import com.google.gson.JsonObject;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by saion on 09.11.2015.
 */
public interface NewsService {

    @GET("/feeds/newsdefaultfeeds.cms?feedtype=sjson")
    Call<JsonObject> news();
}
