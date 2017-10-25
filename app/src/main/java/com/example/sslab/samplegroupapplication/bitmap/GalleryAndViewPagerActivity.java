package com.example.sslab.samplegroupapplication.bitmap;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sslab.samplegroupapplication.R;
import com.example.sslab.samplegroupapplication.fragment.FolderGridLayoutFragment;

public class GalleryAndViewPagerActivity extends AppCompatActivity implements FolderGridLayoutFragment.OnFragmentInteractionListener{

    private final int requestCode = 1;
    public static final int PIC_GALLERY = 100;
    Button showBtn;
    FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_and_view_pager);
        showBtn = ( Button ) findViewById( R.id.showBtn );
        showBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                Uri takenFileUri = Uri.fromFile( new File( file ) );
//                Intent itt = new Intent( MediaStore.ACTION_IMAGE_CAPTURE );
//                itt.putExtra( MediaStore.EXTRA_OUTPUT, takenFileUri );
//                startActivityForResult( itt, 1 );

                fm = getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.container_layout, FolderGridLayoutFragment.getInstance(null),FolderGridLayoutFragment.class.getSimpleName())
                .commit();
            }
        }) ;

        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ){
            checkPermission();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkPermission(){
        int permitRead = ActivityCompat.checkSelfPermission(this , Manifest.permission.READ_EXTERNAL_STORAGE );
        if( permitRead != PackageManager.PERMISSION_GRANTED ){
            requestPermissions (new String[]{Manifest.permission.READ_EXTERNAL_STORAGE  }, PIC_GALLERY );
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
