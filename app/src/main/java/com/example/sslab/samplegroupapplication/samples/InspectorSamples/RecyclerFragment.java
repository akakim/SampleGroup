package com.example.sslab.samplegroupapplication.samples.InspectorSamples;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sslab.samplegroupapplication.R;
import com.example.sslab.samplegroupapplication.adapter.CustomRecyclerViewAdapter;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RecyclerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RecyclerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecyclerFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String GALLERY_PATH = "PATH";
    public static final String LAYOUT_DIRECTION = "LAYOUT_DIRECTION";

    // TODO: Rename and change types of parameters
    private String galleryPath;
    private String layoutDirection;

    private OnFragmentInteractionListener mListener;

    private final String TAG = getClass().getSimpleName();

    ArrayList<DataSet> arrayList = new ArrayList<>();

    protected RecyclerView mRecyclerView;
    protected CustomRecyclerViewAdapter mAdapter;



    public RecyclerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecyclerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecyclerFragment newInstance(String param1, String param2) {
        RecyclerFragment fragment = new RecyclerFragment();
        Bundle args = new Bundle();
        args.putString(GALLERY_PATH, param1);
        args.putString(LAYOUT_DIRECTION, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            galleryPath = getArguments().getString(GALLERY_PATH);
            layoutDirection = getArguments().getString(LAYOUT_DIRECTION);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler, container, false);

        mRecyclerView = ( RecyclerView ) view.findViewById( R.id.recyclerView );

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);

            arrayList.add(new DataSet("http://mblogthumb2.phinf.naver.net/20150924_149/roland02_1443061508520D92db_PNG/007.png?type=w2","fate1"));
            arrayList.add(new DataSet("http://cfile22.uf.tistory.com/image/16237B054BF68D1E02BC93",""));
            arrayList.add(new DataSet("https://upload.wikimedia.org/wikipedia/commons/thumb/2/2f/Hubble_ultra_deep_field.jpg/200px-Hubble_ultra_deep_field.jpg","fate1"));
            arrayList.add(new DataSet("http://cfile21.uf.tistory.com/image/127B0E054BF68D1C3EBE6A",""));
            arrayList.add(new DataSet("http://cfile6.uf.tistory.com/image/17350A304C751B71087955",""));
            arrayList.add(new DataSet("http://cfile27.uf.tistory.com/image/26034C3B56CACE2D145888",""));
            arrayList.add(new DataSet("http://img.etnews.com/photonews/1602/777039_20160228135739_489_0002.jpg",""));
            arrayList.add(new DataSet("http://img.etnews.com/photonews/1602/777039_20160228135739_489_0001.jpg",""));
            arrayList.add(new DataSet("https://image-proxy.namuwikiusercontent.com/r/http%3A%2F%2Fblzgdapipro-a.akamaihd.net%2Fhero%2Fgenji%2Ficon-portrait.png",""));
            arrayList.add(new DataSet("https://image-proxy.namuwikiusercontent.com/r/http%3A%2F%2Fblzgdapipro-a.akamaihd.net%2Fhero%2Freaper%2Ficon-portrait.png",""));


            mAdapter = new CustomRecyclerViewAdapter(arrayList,getContext());
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.setLayoutManager(gridLayoutManager);



        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case 3:
                if(data != null && data.getData() != null){
                    Uri uri = data.getData();
                    Toast.makeText(getContext(),"onActivityResult 3",Toast.LENGTH_SHORT).show();
                    arrayList.add(new DataSet(data.toString(),"addedData"));
                    mAdapter.notifyDataSetChanged();
                }
                break;
        }
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


    public class DataSet{
        private String url;
        private String Name;

        public DataSet(String url, String name) {
            this.url = url;
            Name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }
    }

}
