package com.example.sslab.samplegroupapplication.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.example.sslab.samplegroupapplication.R;
import com.example.sslab.samplegroupapplication.samples.InspectorSamples.RecyclerFragment;
import com.example.sslab.samplegroupapplication.samples.InspectorSamples.RecyclerViewActivity;

import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.util.ArrayList;

/**
 * Created by SSLAB on 2017-03-14.
 */

public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<CustomRecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "CustomAdapter";

    ArrayList<RecyclerFragment.DataSet> arrayList;
    AQuery aQuery;
    Context context;
    public CustomRecyclerViewAdapter(ArrayList<RecyclerFragment.DataSet> arrayList,Context context) {
        this.arrayList = new ArrayList<>();
        this.arrayList.addAll(arrayList);
        aQuery = new AQuery(context);
        this.context = context;
    }

    public static  class ViewHolder extends RecyclerView.ViewHolder{
        final TextView textView;
        final ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);

            // Define click listener for the ViewHolder's View.
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
//                }
//            });
            textView = (TextView) itemView.findViewById(R.id.textView);
            imageView = (ImageView)itemView.findViewById(R.id.webImage);
        }
        public TextView getTextView() {
            return textView;
        }

        public ImageView getImageView() {
            return imageView;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.img_and_text_view, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "Element " + position + " set.");

        // set Data
        holder.getTextView().setText("position" + position + " : "+ arrayList.get(position).getName());
        Uri uri = Uri.parse(arrayList.get(position).getUrl());
        if(uri.getScheme().equals("content") || uri.getScheme().equals("file")){

            URI javaURI = URI.create(arrayList.get(position).getUrl());
            File file = new File(javaURI);
            aQuery.id(holder.getImageView()).image(file , 100).width(100, true).clicked(
                    new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            Toast.makeText(context, arrayList.get(position).getUrl(), Toast.LENGTH_SHORT).show();
                        }
                    }
            );
        }else {
            aQuery.id(holder.getImageView()).image(arrayList.get(position).getUrl(), true, true).width(100, true).clicked(
                    new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            Toast.makeText(context, arrayList.get(position).getUrl(), Toast.LENGTH_SHORT).show();
                        }
                    }
            );
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

}
