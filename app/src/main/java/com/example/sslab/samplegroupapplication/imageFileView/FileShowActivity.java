package com.example.sslab.samplegroupapplication.imageFileView;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ListView;

import com.example.sslab.samplegroupapplication.R;
import com.example.sslab.samplegroupapplication.fragment.SFolderGridLayoutFragment;
import com.example.sslab.samplegroupapplication.fragment.SFolderListLayoutFragment;

public class FileShowActivity extends AppCompatActivity implements SFolderListLayoutFragment.OnFragmentInteractionListener,
    SFolderGridLayoutFragment.OnFragmentInteractionListener{

    Toolbar toolbar;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_show);

        Bundle bundle = getIntent().getExtras();
        String mode = bundle.getString("Mode","");

        toolbar = ( Toolbar ) findViewById( R.id.toolbar );

        toolbar.setTitle( mode );

        setSupportActionBar( toolbar );
        toolbar.setCollapsible( true );
        toolbar.setNavigationIcon(R.mipmap.headline);

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace( R.id.container_layout, new SFolderGridLayoutFragment (),null).commit();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
