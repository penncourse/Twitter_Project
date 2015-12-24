package com.upenn.twittersearch;

/**
 * Created by Hua Zhu on 12/23/15.
 */
public class SearchEvent {

    public final String hashtag;

    public final String token;

    public SearchEvent(String hashtag, String token){

        this.hashtag = hashtag;

        this.token = token;

    }

}
