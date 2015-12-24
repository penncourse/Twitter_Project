package com.upenn.twittersearch;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.squareup.otto.Bus;

import retrofit.RestAdapter;


/**
 * Created by Hua Zhu on 12/23/15.
 */
public class MainApplication extends Application {

    private Bus bus = Buses.getBus();

    private TwitterServiceProvider serviceProvider;

    private static Context context;

    @Override
    public void onCreate(){

        super.onCreate();

        serviceProvider = new TwitterServiceProvider(buildService(), bus);

        bus.register(serviceProvider); //register to receive and produce events

        bus.register(this);

        this.setApplicationContext(getApplicationContext());

    }


    public TwitterService buildService(){

        return new RestAdapter.Builder().setEndpoint(Constants.TWITTER_URL).build().create(TwitterService.class); /*create an implementation
                                                                                                                   of TwitterService class*/

    }


    public void setApplicationContext(Context con){

        context = con;

    }

    public static Context getAppContext(){

        return context;
    }



}
