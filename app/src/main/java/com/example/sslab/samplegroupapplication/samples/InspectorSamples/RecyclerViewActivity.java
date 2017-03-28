package com.example.sslab.samplegroupapplication.samples.InspectorSamples;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sslab.samplegroupapplication.R;

public class RecyclerViewActivity extends AppCompatActivity implements RecyclerFragment.OnFragmentInteractionListener,
        View.OnClickListener{

    final String recyclerFragmentTAG = "RecyclerViewFragment";
    Button showGalleryButton;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);


        showGalleryButton = ( Button )findViewById( R.id.showGalleryButton);
        showGalleryButton.setOnClickListener( this );
        fragmentManager  = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container,new RecyclerFragment(),recyclerFragmentTAG).commit();


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.showGalleryButton:
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory( Intent.CATEGORY_OPENABLE );
                i.setType( "image/*" );
                startActivityForResult( i, 3 );

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case 3:

                fragmentManager.findFragmentByTag(recyclerFragmentTAG).onActivityResult(requestCode,resultCode,data);
                break;
        }
    }
}
