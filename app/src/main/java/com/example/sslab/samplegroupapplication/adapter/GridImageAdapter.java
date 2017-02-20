package com.example.sslab.samplegroupapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.androidquery.AQuery;
import com.example.sslab.samplegroupapplication.R;
import com.example.sslab.samplegroupapplication.data.GridDirectoryItem;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by SSLAB on 2017-02-20.
 */

public class GridImageAdapter extends RecyclerView.Adapter<GridImageAdapter.ViewHolder> {

    private AQuery aq;

    private ArrayList<GridDirectoryItem> arrayItem;
    private OnImgClickListener onImageClickListener;

    public GridImageAdapter( ArrayList<GridDirectoryItem> arrayItem){
        this.arrayItem = new ArrayList<>();

        this.arrayItem.addAll(arrayItem);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        aq = new AQuery( parent.getContext() );
        View v = LayoutInflater.from( parent.getContext() ).inflate( R.layout.grid_image_item, parent, false);
        return new ViewHolder( v );
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final GridDirectoryItem item = arrayItem.get( position );
        aq.id( holder.getImageView() ).image( new File( item.getPath() ), 250 ).clicked(new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                if ( onImageClickListener != null ) {
                    onImageClickListener.onPicture( item );
                }
            }
        } );
    }

    @Override
    public int getItemCount() {
        return arrayItem.size();
    }


    public void setOnImgClickListener( OnImgClickListener l ) {
        this.onImageClickListener = l;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = ( ImageView )itemView.findViewById( R.id.img );
        }

        public ImageView getImageView(){
            return imageView;
        }
    }

    public interface OnImgClickListener {
        void onPicture(GridDirectoryItem item);
    }
}
