package com.example.sslab.samplegroupapplication.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.example.sslab.samplegroupapplication.R;
import com.example.sslab.samplegroupapplication.data.GridImageItem;
import com.example.sslab.samplegroupapplication.data.PostFile;
import com.example.sslab.samplegroupapplication.imageFileView.ImageShowActivity;
import com.example.sslab.samplegroupapplication.widget.DialogBuilder;

import java.io.File;
import java.util.ArrayList;


//TODO: 이미지 갯수가 많아지만 GC를 학대하니 RecyclerView로 변경하는게 좋아보인다.

/**
 *
 */
public class ImageGridLayoutFragment extends Fragment {


    ArrayList<GridImageItem> arrayImage = new ArrayList<>();
    final String TAG = this.getClass().getSimpleName();
    AQuery aQuery;
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            return false;
        }
    });

    private static ImageGridLayoutFragment fragment = null;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "Path";
    //private static String filePath;
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String filePath;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private GridLayout gridLayout;
    public ImageGridLayoutFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ImageGridLayoutFragment getInstance(Bundle bundle) {
        if( fragment == null ) {
            fragment = new ImageGridLayoutFragment();
        }
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            filePath = getArguments().getString(ARG_PARAM1,"");
        }

        aQuery = new AQuery( getContext() );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View v = inflater.inflate(R.layout.fragment_blank, container, false);
        gridLayout = (GridLayout)v.findViewById(R.id.gridLayout);


        getImages();
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



    private void getImages() {
        String[] cols = new String[]{ MediaStore.Images.Media.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME };
        Cursor cursor = getActivity().getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                cols ,
                MediaStore.Images.Media.DATA + " like ? ",
                new String[]{ "%" + filePath + "%" },
                MediaStore.Images.Media.DATE_ADDED
        );

        while ( cursor.moveToNext() ) {
            try {
                String filePath = cursor.getString( cursor.getColumnIndex( MediaStore.Images.Media.DATA ) );
                String imageName = cursor.getString( cursor.getColumnIndex( MediaStore.Images.Media.BUCKET_DISPLAY_NAME ) );
                arrayImage.add( new GridImageItem( imageName, filePath ) );
            } catch ( Exception e ) {
                e.printStackTrace();
            }
        }
        cursor.close();
        for(int i =0;i<arrayImage.size();i++){
            GridImageItem item = arrayImage.get(i);
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
            final File file = new File(item.getPath());
            aQuery.id( img ).image(file,125).width(225).clicked(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // show Dialog
                    android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getContext());

                    builder.setTitle("파일업로드하시겠습니까")
                    .setPositiveButton("확인", new AlertDialog.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
//                                    new FileUpLoad("URL",)
                                    final String TEST_URL = "http://192.168.0.101:8080/AndroidPushTestServer/getImageFile";
                                    final String FOR_PARK_URL ="http://125.176.35.110:8079/upload";
                                    new PostFile( getContext(),FOR_PARK_URL,file,"",handler,1000).execute();
                                }
                            });
                    android.support.v7.app.AlertDialog alertDialog = builder.create();
                    alertDialog.setTitle("파일업로드하시겠습니까");



                    if(!alertDialog.isShowing()){
                        alertDialog.show();
                    }

                }
            });

            aQuery.id(textView).text(item.getName());
            gridLayout.addView(v, i);
        }


    }

    @Override
    public void onDestroy() {
        handler = null;
        super.onDestroy();
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
