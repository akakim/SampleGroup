package com.example.sslab.samplegroupapplication.imageFileView;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.sslab.samplegroupapplication.R;
import com.example.sslab.samplegroupapplication.fragment.ImageGridLayoutFragment;
import com.example.sslab.samplegroupapplication.fragment.SFolderGridLayoutFragment;

public class ImageShowActivity extends AppCompatActivity implements ImageGridLayoutFragment.OnFragmentInteractionListener{

    final String TAG = this.getClass().getSimpleName();
    Toolbar toolbar;
    FragmentManager fragmentManager;
    ImageGridLayoutFragment imageGridLayoutFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_show);

        toolbar = ( Toolbar ) findViewById( R.id.toolbar );

        toolbar.setTitle( "ImageShow" );

        setSupportActionBar( toolbar );
        toolbar.setCollapsible( true );
        toolbar.setNavigationIcon(R.mipmap.headline);

        Bundle bundle = new Bundle();
        String path = getIntent().getStringExtra("Path");
        if (path == null){
            path = "";
        }
        bundle.putString("Path",path);
        Log.d(TAG,path);

        imageGridLayoutFragment = new ImageGridLayoutFragment();
        imageGridLayoutFragment.setArguments( bundle );
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace( R.id.container_layout, imageGridLayoutFragment,null).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
