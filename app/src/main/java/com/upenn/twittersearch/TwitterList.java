package com.upenn.twittersearch;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Hua Zhu on 12/23/15.
 */
public class TwitterList { //arrayList for containing Twitter objects

    @SerializedName("statuses")
    public ArrayList<Twitter> twitters;

}
