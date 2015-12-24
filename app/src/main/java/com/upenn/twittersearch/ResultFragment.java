package com.upenn.twittersearch;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;


/**
 * Created by Hua Zhu on 12/22/15.
 */
public class ResultFragment extends ListFragment {

    private Bus bus;

    private TwitterAdapter adapter;

    String request;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_result, container, false);

        Context context= getActivity();

        adapter = new TwitterAdapter(context, new TwitterList());

        request = getArguments().getString("Request");

        Button refreshButton = (Button) view.findViewById(R.id.refresh_button);

        refreshButton.setOnClickListener( /*refresh button is used to pull latest twitter feed.
                                           There is a time interval for pulling. For example, for
                                           a hot topic, like Christmas, we need to wait for
                                           10 seconds to pull again.
                                           If we pull too frequently, the app may not be able to update
                                           feed every time.*/

                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        String token = Token.tokenMap.get(MainApplication.getAppContext()); //get credential token

                        getBus().post(new SearchEvent(request, token));


                    }
                }
        );



        Button backButton = (Button) view.findViewById(R.id.back_button);

        backButton.setOnClickListener( //back button can let us go back from the second screen to the first one.

                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        FragmentManager fragmentManager;

                        fragmentManager = getActivity().getFragmentManager();

                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                        fragmentTransaction.setCustomAnimations(R.animator.slide_in, R.animator.slide_out, 0, 0);
                                                                                             /*custom animation
                                                                                             between screen transitions
                                                                                              */

                        fragmentTransaction.replace(R.id.frame_container, new HomeFragment());

                        fragmentTransaction.addToBackStack(null);

                        fragmentTransaction.commit();

                    }
                });



        final ListView myList = (ListView) view.findViewById(android.R.id.list);

        myList.setAdapter(adapter);  //add adapter into listview

       return view;

    }



    @Override
    public void onStart() {

        super.onStart();

        getBus().register(this);

        if (Token.tokenMap.get(MainApplication.getAppContext()) == null) {

            getBus().post(new TwitterTokenEvent()); //if user does not have a token , first get a credential token

        } else {
            String token = Token.tokenMap.get(getActivity());  //if user already has a token, send search feed request

            getBus().post(new SearchEvent(request, token));
        }
    }

    @Override
    public void onStop() {

        super.onStop();

        getBus().unregister(this);

    }


    @Subscribe
    public void GetTokenStartSearch(GetTwitterTokenSuccessEvent event){ //listening the GetTwitterTokenSuccessEvent event

        getBus().post(new SearchEvent(request, Token.tokenMap.get(MainApplication.getAppContext())));

    }


    @Subscribe
    public void twitterResponseEvent(TwitterResponseEvent event){ /*listen TwitterResponseEvent, promptly put result in the
                                                                   listview*/
        adapter.setTwitterList(event.tList);

        adapter.notifyDataSetChanged();

    }

    private Bus getBus(){

        if(bus == null){

            bus = Buses.getBus();

        }

        return bus;
    }

}
