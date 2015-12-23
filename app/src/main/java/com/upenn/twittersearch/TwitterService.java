package com.upenn.twittersearch;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by Hua Zhu on 12/23/15.
 */




public interface TwitterService {

    @GET(Constants.TWITTER_JSON)
    public void getTwitterList(@Header("Authorization")String token, @Query("q")String hashtag, Callback<TwitterList> callback);


    @FormUrlEncoded
    @POST("/oauth2/token")
    public void getToken(@Header("Authorization")String authorization, @Field("grant_type")String type, Callback<TwitterToken> response);


}




