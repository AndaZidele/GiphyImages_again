package com.example.giphyimagesapp_again_2025.serviceapi;

import com.example.giphyimagesapp_again_2025.models.ImageM;
import com.example.giphyimagesapp_again_2025.models.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ImageApiService {
    @GET("trending")
    Call<Result> getTrendingGifImages(@Query("api_key") String apiKey);

}
