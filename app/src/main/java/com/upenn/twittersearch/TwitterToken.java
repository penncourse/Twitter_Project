package com.upenn.twittersearch;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Hua Zhu on 12/23/15.
 */
public class TwitterToken {

    @SerializedName("token_type")
    public String tokenType;

    @SerializedName("access_token")
    public String accessToken;

}
