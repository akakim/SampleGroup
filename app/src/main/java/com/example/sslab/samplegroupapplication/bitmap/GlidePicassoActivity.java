package com.example.sslab.samplegroupapplication.bitmap;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sslab.samplegroupapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * TODO : Glide Annotation Generation에 대해 알아낼것.
 */
public class GlidePicassoActivity extends AppCompatActivity {


    @BindView(R.id.glideImage)
    public ImageView glidImage;


    @BindView(R.id.glideInfo)
    public TextView glideInfo;

    @BindView(R.id.picassoImage)
    public ImageView picassoImage;

    @BindView(R.id.picassoInfo)
    public TextView picassoInfo;

    String sampleURL = "https://static.pexels.com/photos/67636/rose-blue-flower-rose-blooms-67636.jpeg";

    @BindView(R.id.simpleList)
    public RecyclerView simpleList;


    ArrayList<String> stringItems = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_picasso);
        ButterKnife.bind(this);




        Picasso.with(this).load(sampleURL).into(picassoImage);
        picassoInfo.setText("피카소 이미지 ");


        for( int i = 0; i<20;i++){
            stringItems.add( " test " + i ) ;
        }
//        Glide.with(this).load(sampleURL).into(glidImage);
//        glideInfo.setText("글리이드 이미지");

    }

    private class SimpleAdapter extends RecyclerView.Adapter<ViewHolder>{

        private int ADD_ITEM = 0;
        @Override
        public int getItemViewType(int position) {
            if( position == ADD_ITEM){
                return ADD_ITEM;
            }else {
                return position;
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view;


            if( viewType == ADD_ITEM) {

                view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
                return new ViewHolder(view);
            }else {
                view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
                return new ViewHolder(view);
            }



        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return stringItems.size();
        }
    }


//    private
    private class AddImageViewHolder extends RecyclerView.ViewHolder{

        public AddImageViewHolder(View itemView) {
            super(itemView);
        }
    }
    private class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }


//    @SuppressLint("ValidFragment")
//    public class MyFragment extends Fragment{
//
//    }
    private class MyFragmentPagerAdapter extends FragmentStatePagerAdapter{

        ArrayList<MyFragment> fragments = new ArrayList<>();
        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);

        }

        @Override
        public Fragment getItem(int position) {


            FragmentManager fragmentManager = getSupportFragmentManager();

            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
