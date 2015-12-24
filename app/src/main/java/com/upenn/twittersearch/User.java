package com.upenn.twittersearch;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Hua Zhu on 12/23/15.
 */
public class User { //store user_related information

    @SerializedName(("screen_name"))
    public String screenName;

    @SerializedName("name")
    public String userName;

    @SerializedName("profile_image_url")
    public String imageUrl;


}
