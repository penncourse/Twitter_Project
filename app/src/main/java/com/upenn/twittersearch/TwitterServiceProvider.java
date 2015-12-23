package com.upenn.twittersearch;

import com.squareup.otto.Bus;

import android.util.Base64;
import android.util.Log;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import retrofit.Callback;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Hua Zhu on 12/23/15.
 */
public class TwitterServiceProvider {

    private static String accessToken = null;

    private TwitterService twitterService;

    private Bus bus;

    public TwitterServiceProvider (TwitterService twitterService, Bus bus){

        this.twitterService = twitterService;

        this.bus = bus;

    }



    public static String getBase64String(String value) throws UnsupportedEncodingException {
        return Base64.encodeToString(value.getBytes("UTF-8"), Base64.NO_WRAP);
    }



    @Subscribe
   public void getToken(TwitterTokenEvent event) throws UnsupportedEncodingException {

       twitterService.getToken("Basic " + Base64.encodeToString(Constants.CREDENTIALS.getBytes("UTF-8"), Base64.NO_WRAP),
               "client_credentials", new Callback<TwitterToken>() {
                   @Override
                   public void success(TwitterToken twitterToken, Response response) {

                       accessToken = twitterToken.accessToken;

                       Token.tokenMap.put(MainApplication.getAppContext(), accessToken);

                       bus.post(new GetTwitterTokenSuccess());


                   }

                   @Override
                   public void failure(RetrofitError retrofitError) {

                       bus.post(new SearchTwitterFailed());

                   }
               });
   }




    @Subscribe
    public void loadTwitters(SearchEvent event){

        Log.e(event.hashtag, Token.tokenMap.get(MainApplication.getAppContext()) +"100");

        twitterService.getTwitterList("Bearer " + Token.tokenMap.get(MainApplication.getAppContext()), event.hashtag,


                new Callback<TwitterList>() {


                    @Override
                    public void success(TwitterList response, Response originResponse) {

                        bus.post(new TwitterResponseEvent(response));

                    }

                    @Override
                    public void failure(RetrofitError retrofitError) {

                        bus.post(new SearchTwitterFailed());

                    }
                }


                );


            }


        }
