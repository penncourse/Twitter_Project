package com.upenn.twittersearch;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomeFragment extends Fragment {

    private EditText search;

    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

       // context = super.getActivity().getApplicationContext();

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        search = (EditText) view.findViewById(R.id.search_hashtag);

        final String search_word = search.getText().toString();

        Button search_button = (Button) view.findViewById(R.id.search_twitter_button);

        search_button.setOnClickListener(

                new View.OnClickListener() {

                    @Override
                    public void onClick(View v){

                        if(!TextUtils.isEmpty(search.getText())){

                            ResultFragment resultFragment = new ResultFragment();

                            Bundle data;

                            data = new Bundle();

                            data.putString("Request", search.getText().toString());

                            resultFragment.setArguments(data);

                            FragmentManager  fragmentManager;

                            fragmentManager = getActivity().getFragmentManager();

                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                            fragmentTransaction.setCustomAnimations(R.animator.slide_in, R.animator.slide_out, 0, 0);

                            fragmentTransaction.replace(R.id.frame_container, resultFragment);

                            fragmentTransaction.addToBackStack(null);

                            fragmentTransaction.commit();



                        }
                        else{

                            String text = "Please input a valid hashtag to search";


                        }

                    }

                }
        );


        return view;

    }



}
