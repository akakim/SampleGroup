package com.example.sslab.samplegroupapplication.fragment;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.example.sslab.samplegroupapplication.R;
import com.example.sslab.samplegroupapplication.adapter.NotificationAdapter;
import com.example.sslab.samplegroupapplication.widget.SwipeRefreshLayoutBottom;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SampleSwipeBotttomLayer.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SampleSwipeBotttomLayer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SampleSwipeBotttomLayer extends Fragment implements SwipeRefreshLayoutBottom.OnRefreshListener{

    private static final int         PER_PAGE = 20;

    private AppCompatActivity appCompatActivity;
    private int                      mPage = 0;
    private SwipeRefreshLayoutBottom mSwipeRefreshLayout;


    private List<String> mNames;
    ArrayAdapter<String> adapter;
    private ListView mListView;
    NotificationAdapter notificationAdapter;

    private Handler mHandler;

    private FrameLayout mRootLayout;




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SampleSwipeBotttomLayer() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SampleSwipeBotttomLayer.
     */
    // TODO: Rename and change types and number of parameters
    public static SampleSwipeBotttomLayer newInstance(String param1, String param2) {
        SampleSwipeBotttomLayer fragment = new SampleSwipeBotttomLayer();
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


        mHandler    =   new Handler();
        mNames      =   new ArrayList<>();
        String [] codse = Locale.getISOCountries();

        for(String code : codse){
            Locale locale = new Locale("",code);
            String str = locale.getDisplayName(Locale.KOREA);
            mNames.add(str);
        }

        String[] array = makeForPage(mPage);
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, array);


        mSwipeRefreshLayout = new SwipeRefreshLayoutBottom(getContext());
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mSwipeRefreshLayout.setColorSchemeResources(R.color.yellow, R.color.blue);
        mListView = new ListView(getContext());
        mSwipeRefreshLayout.addView(mListView);

        mListView.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v  = inflater.inflate(R.layout.fragment_sample_swipe_botttom_layer, container, false);

        if(v instanceof FrameLayout) {
            mRootLayout = (FrameLayout) v;
            mRootLayout.setForeground(new ColorDrawable(Color.BLACK));
            mRootLayout.getForeground().setAlpha(0);

            mRootLayout.addView(mSwipeRefreshLayout);
            mListView.setAdapter(adapter);
        }
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
    public void onRefresh() {
        mRootLayout.getForeground().setAlpha(50);
        mSwipeRefreshLayout.setRefreshing(true);
        getActivity().setProgressBarIndeterminateVisibility(true);

        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                mRootLayout.getForeground().setAlpha(0);
                mSwipeRefreshLayout.setRefreshing(false);
                getActivity().setProgressBarIndeterminateVisibility(false);
                mPage++;
                String[] array = makeForPage(mPage);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, array);

                int index = mListView.getFirstVisiblePosition();
                View v = mListView.getChildAt(0);
                int top = (v == null) ? 0 : v.getTop();

                mListView.setAdapter(adapter);
                mListView.setSelectionFromTop(index, top);

                mListView.post(new Runnable() {

                    @Override
                    public void run() {
                        mListView.smoothScrollByOffset(PER_PAGE);
                    }
                });

            }
        },1000);


    }

    private String[] makeForPage(int page) {

        int size = Math.min(PER_PAGE * (page + 1), mNames.size());

        String[] res = new String[size];

        for (int i = 0; i < size; i++) {
            res[i] = mNames.get(i);
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
