package com.upenn.twittersearch;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Element;
import org.w3c.dom.Text;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


/**
 * Created by Hua Zhu on 12/22/15.
 */
public class ResultFragment extends ListFragment {

    private Bus bus;

    private TwitterAdapter adapter;

    String request;

    private TwitterService twitterService;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_result, container, false);

        Context context= getActivity();

        adapter = new TwitterAdapter(context, new TwitterList());

        setListAdapter(adapter);

        request = getArguments().getString("Request");

        Button refreshButton = (Button) view.findViewById(R.id.refresh_button);

        refreshButton.setOnClickListener(

                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        String token = Token.tokenMap.get(MainApplication.getAppContext());

                        getBus().post(new SearchEvent(request, token));

                    }
                }

        );



        Button backButton = (Button) view.findViewById(R.id.back_button);

        backButton.setOnClickListener(

                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        FragmentManager fragmentManager;

                        fragmentManager = getActivity().getFragmentManager();

                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                        fragmentTransaction.setCustomAnimations(R.animator.slide_in, R.animator.slide_out, 0, 0);

                        fragmentTransaction.replace(R.id.frame_container, new HomeFragment());

                        fragmentTransaction.addToBackStack(null);

                        fragmentTransaction.commit();

                        Log.e("see here", "I am here");


                    }
                });



       return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        getBus().register(this);
        if (Token.tokenMap.get(MainApplication.getAppContext()) == null) {

            getBus().post(new TwitterTokenEvent());

        } else {
            String token = Token.tokenMap.get(getActivity());

            getBus().post(new SearchEvent(request, token));
        }
    }

    @Override
    public void onStop() {

        super.onStop();
        getBus().unregister(this);


    }


    @Subscribe
    public void GetTokenStartSearch(GetTwitterTokenSuccess event){

        getBus().post(new SearchEvent(request, Token.tokenMap.get(MainApplication.getAppContext())));


    }


    @Subscribe
    public void twitterResponseEvent(TwitterResponseEvent event){

        adapter.setTwitterList(event.tList);

        adapter.notifyDataSetChanged();

    }

    private Bus getBus(){

        if(bus == null){

            bus = Buses.getBus();

        }

        return bus;
    }

    static class ElementContent{

        TextView twitterContent;

        TextView twitterUser;

        ImageView twitterLogo;

    }



}
