package com.upenn.twittersearch;

/**
 * Created by Hua Zhu on 12/22/15.
 */

import com.google.gson.annotations.SerializedName;

public class Twitter {
    //use gson to pare Json

    @SerializedName("created_at")
    public String createdAt;

    @SerializedName("id")
    public String id;

    @SerializedName("text")
    public String text;

    @SerializedName("in_reply_to_status_id")
    public String replyToStatusId;

    @SerializedName("in_reply_to_user_id")
    public String replyToUserId;

    @SerializedName("in_reply_to_screen_name")
    public String replyToScreenName;

    @SerializedName("user")
    public User user;


    @Override
    public String  toString(){
        return text;
    }



}
