package com.upenn.twittersearch;

import com.squareup.otto.Bus;

/**
 * Created by Hua Zhu on 12/23/15.
 */
public class Buses {

    //static variable makes sure that every time we work on the same object

    static Bus bus = null;

    public static Bus getBus(){

        if(bus == null){

            bus = new Bus();

        }

        return bus;

    }

}
