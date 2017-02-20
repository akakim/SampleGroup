package com.example.sslab.samplegroupapplication.fragment;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.example.sslab.samplegroupapplication.R;
import com.example.sslab.samplegroupapplication.adapter.GridImageAdapter;
import com.example.sslab.samplegroupapplication.data.GridDirectoryItem;
import com.example.sslab.samplegroupapplication.imageFileView.ImageShowActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SFolderGridLayoutFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SFolderGridLayoutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SFolderGridLayoutFragment extends Fragment {
    AQuery aQuery;
    GridLayout gridLayout;
    GridImageAdapter adapter;
    ArrayList<GridDirectoryItem> items = new ArrayList<>();
    final String TAG = this.getClass().getSimpleName();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SFolderGridLayoutFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SFolderGridLayoutFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SFolderGridLayoutFragment newInstance(String param1, String param2) {
        SFolderGridLayoutFragment fragment = new SFolderGridLayoutFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = ( View )inflater.inflate(R.layout.fragment_sfolder_grid_layout, container, false);
//        v.setLayoutManager( new GridLayoutManager( acv, 2 ) );

      //  adapter = new GridImageAdapter( items );

        gridLayout = ( GridLayout ) v.findViewById( R.id.gridLayout );
        aQuery = new AQuery(getContext() );
        checkPermission();
        Log.d(TAG,"onCreateView()");
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults ) {
        super.onRequestPermissionsResult( requestCode, permissions, grantResults );
        boolean isGranted = true;
        if ( requestCode == 100 ) {
            for ( int res : grantResults ) {
                if ( res != PackageManager.PERMISSION_GRANTED ) {
                    isGranted = false;
                }
            }
            if ( isGranted )
                getFolders();
            else {
                Dialog dialog = new Dialog(this.getContext());
                dialog.setTitle("권환확인필요");
                dialog.setOnDismissListener(new Dialog.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        SFolderGridLayoutFragment.this.getActivity().finish();
                    }
                });

            }
        }
    }

    private boolean hasFolder( String name ){
        for ( GridDirectoryItem item : items ) {
            if ( item.getName().equals( name ) ) {
                return true;
            }
        }
        return false;
    }

    private void checkPermission() {
        int permitRead = ActivityCompat.checkSelfPermission( getContext(), Manifest.permission.READ_EXTERNAL_STORAGE );
        if ( permitRead != PackageManager.PERMISSION_GRANTED ) {
            requestPermissions( new String[]{ Manifest.permission.READ_EXTERNAL_STORAGE }, 100 );
        } else {
            getFolders();
        }
    }

    private void getFolders(){
        // contennns provier에서 사용할 문자열 선택
        String [] cols = new String [] { MediaStore.Images.Media.DATA,MediaStore.Images.Media.BUCKET_DISPLAY_NAME};
        FragmentActivity activity = this.getActivity();

        if (activity == null || activity.getContentResolver() == null ){
            return;
        }

        Cursor cursor = activity.getContentResolver().query( MediaStore.Images.Media.EXTERNAL_CONTENT_URI,cols,null,null,MediaStore.Images.Media.DATE_ADDED);
        while ( cursor.moveToNext() ){
            try {
                String file = cursor.getString( cursor.getColumnIndex( MediaStore.Images.Media.DATA ) );
                String folderName = cursor.getString( cursor.getColumnIndex( MediaStore.Images.Media.BUCKET_DISPLAY_NAME ) );
                // 중복제거
                if ( !hasFolder( folderName ) ) {
                    items.add( new GridDirectoryItem( folderName, getPath( file ), new File( file ) ) );
                }
            } catch ( Exception e ) {
                e.printStackTrace();
            }
        }
        cursor.close();

        int row = 0;
        int column = 0;

        int count= 0;
        for(GridDirectoryItem item : items ) {
            final int idx = count;
            View v = LayoutInflater.from(this.getContext()).inflate(R.layout.square_image_item, null);
//            GridLayout.Spec rowSpec = GridLayout.spec(row);
//            GridLayout.Spec columnSpec = GridLayout.spec(column);
//
//            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(rowSpec, columnSpec);
//            layoutParams.width = ( int )getResources().getDimension(R.dimen._40sdp);
            Log.d(TAG, "itemCount");
            Log.d(TAG, "Name : " + item.getName());
            Log.d(TAG, "Path : " + item.getPath());
//            Log.d(TAG,"Name : "+item.getName());
            ImageView img = (ImageView) v.findViewById(R.id.img);
            TextView textView = (TextView) v.findViewById(R.id.text);
            aQuery.id(img).image(item.getThumbnail(), 500).width(250);
            v.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Log.d(TAG,"is OnClicked");
                    Intent i = new Intent(getContext(), ImageShowActivity.class);
                    i.putExtra("Path", items.get(idx).getPath());
                    startActivity( i );
                }
            });
            aQuery.id(textView).text(item.getName());
            gridLayout.addView(v, count++);

        }

    }

    private String getPath( String path ) { // 파일 이름을 제외한 폴더 루트만 받아오기
        ArrayList<String> array = new ArrayList<>( Arrays.asList( path.split( "/" ) ) );
        array.remove( array.size() - 1 );
        String res = "";
        for ( String str : array ) {
            res += str + "/";
        }
        return res;
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
