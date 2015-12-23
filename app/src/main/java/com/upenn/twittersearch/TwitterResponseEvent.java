package com.upenn.twittersearch;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Hua Zhu on 12/23/15.
 */
public class TwitterResponseEvent {

    public ArrayList<Twitter> twitterList;

    public TwitterList tList;


    public  TwitterResponseEvent(ArrayList<Twitter> twitterList){

        this.twitterList = twitterList;

    }

    public TwitterResponseEvent(TwitterList list){

        this.tList = list;

    }


}
