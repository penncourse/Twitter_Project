package com.upenn.twittersearch;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

/**
 * Created by Hua Zhu on 12/23/15.
 */


public class TwitterAdapter extends BaseAdapter {

    private Context context;

    private TwitterList twitterList;

    public TwitterAdapter (Context context, TwitterList twitterList){

        this.context = context;

        this.twitterList = twitterList;

    }

    public void setTwitterList(TwitterList twitterList){

        this.twitterList = twitterList;

    }

    @Override
    public int getCount() {
        if (twitterList.twitters != null) {
            return twitterList.twitters.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position)
    {
        return null;
    }

    @Override
    public long getItemId(int position)
    {

        return 0;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View rowView = convertView;

        final Element element;

        if(rowView == null){

            LayoutInflater inflater = ((Activity) context).getLayoutInflater();

            rowView = inflater.inflate(R.layout.list_element, parent, false);

            element = new Element();

            element.twitterContent = (TextView) rowView.findViewById(R.id.twitter_text);

            element.user = (TextView) rowView.findViewById(R.id.twitter_user);

            element.logo = (ImageView) rowView.findViewById(R.id.user_image);

            rowView.setTag(element);

        }
        else{


            element = (Element) rowView.getTag();

        }


        element.twitterContent.setText(twitterList.twitters.get(position).text);

        element.user.setText(twitterList.twitters.get(position).user.userName);

        Picasso.with(context).load(twitterList.twitters.get(position).user.imageUrl).into(element.logo);

        return rowView;


    }


    static class Element{

        TextView twitterContent;

        TextView user;

        ImageView logo;

    }


}
