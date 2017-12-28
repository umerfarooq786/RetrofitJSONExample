package com.example.umerfarooq.retrofitjsonexample;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Umer Farooq on 10/31/2017.
 */

public interface Api {
    String BASE_URL="http://192.168.173.1/myapp/public/api/";
    @GET("articles")
    Call<List<Articles>> getArticles();
}
