package com.example.sslab.samplegroupapplication.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.LayoutInflaterCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.example.sslab.samplegroupapplication.R;
import com.example.sslab.samplegroupapplication.widget.ButtonTextAndImg;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;



public class FolderGridLayoutFragment extends Fragment {
    private static FolderGridLayoutFragment fragment = null;
    private AQuery aq;
    private GridLayout gridLayout;
    ArrayList<GridFolderItem> item = new ArrayList<>();

    // TODO: Rename and change types of parameters
    private OnFragmentInteractionListener mListener;

    public FolderGridLayoutFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters
     * @return A new instance of fragment FolderGridLayoutFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FolderGridLayoutFragment getInstance(Bundle bundle) {
        if( fragment == null ) {
            fragment = new FolderGridLayoutFragment();
        }
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_folder_grid_layout, container, false);
        aq= new AQuery( getContext() );
        gridLayout = ( GridLayout )v.findViewById( R.id.gridLayout );
        getFolders();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                gridLayout.setBackgroundColor(0xCACACA);
            }
        };
        runnable.run();
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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

    private void getFolders() {
        String[] cols = new String[]{ MediaStore.Images.Media.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME };

        if( getActivity() == null || getActivity().getContentResolver() == null){
            return;
        }
        Cursor cursor = getActivity().getContentResolver().query( MediaStore.Images.Media.EXTERNAL_CONTENT_URI, cols,null,null,MediaStore.Images.Media.DATE_ADDED );
        while ( cursor.moveToNext() ){
            String file = cursor.getString( cursor.getColumnIndex( MediaStore.Images.Media.DATA ) );
            String folderName = cursor.getString( cursor.getColumnIndex( MediaStore.Images.Media.BUCKET_DISPLAY_NAME ) );
            if ( !hasFolder( folderName )){
                item.add( new GridFolderItem(folderName,getPath(file),new File( file )));
            }
        }

        int row = 0;
        int column = 0;
        for(int k = 0; k<item.size();k++){
            if( k % gridLayout.getColumnCount() == 0){
                if ( k != 0 ){
                    row++ ;
                }
            }

            GridLayout.Spec rowSpec = GridLayout.spec(row,1);
            GridLayout.Spec colmnSpec = GridLayout.spec(column,1,0.5f);

            column = ( column + 1 ) % gridLayout.getColumnCount();

            GridLayout.LayoutParams params= new GridLayout.LayoutParams(rowSpec , colmnSpec );
            float width = getResources().getDimension( R.dimen._40sdp);
            float height = getResources().getDimension(R.dimen._50sdp);
            float divide = getResources().getDimension(R.dimen._1sdp);
            int intDivider = ( int ) divide ;
            params.width = ( int ) width ;
            params.height = ( int )height ;
            params.setMargins(intDivider,intDivider,intDivider,intDivider);
            View v = null;

            v = aq.inflate(v,R.layout.button_text_image,null);
            aq.id(v.findViewById(R.id.img)).image( item.get(k).getFolder() ,( int ) width );

            TextView textView = ( TextView ) v.findViewById( R.id.text);
            //aq.id( v.findViewById(R.id.img).getId() ).image( item.get(k).getFolder() ,250);
            textView.setText( item.get(k).getFolderName() );

            v.setOnClickListener( new View.OnClickListener(){
                @Override
                public void onClick(View v){

                }
            });
            gridLayout.addView(v,params);
        }
    }

    private String getPath( String filePath ){
        StringBuilder builder = new StringBuilder();
        String path [] = filePath.split("/");
        for(int i =0;i<path.length-1;i++){
            builder.append(path[i]);
            builder.append("/");
        }
        return builder.toString();
    }
    private boolean hasFolder( String name ) {
        for ( GridFolderItem item : this.item ) {
            if ( item.getFolderName().equals( name ) ) {
                return true;
            }
        }
        return false;
    }


    private class GridFolderItem{
        private String folderName;
        private String folderPath;
        private File folder;

        public GridFolderItem(String folderName, String folderPath, File folder) {
            this.folderName = folderName;
            this.folderPath = folderPath;
            this.folder = folder;
        }

        public String getFolderName() {
            return folderName;
        }

        public void setFolderName(String folderName) {
            this.folderName = folderName;
        }

        public String getFolderPath() {
            return folderPath;
        }

        public void setFolderPath(String folderPath) {
            this.folderPath = folderPath;
        }

        public File getFolder() {
            return folder;
        }

        public void setFolder(File folder) {
            this.folder = folder;
        }
    }
}
