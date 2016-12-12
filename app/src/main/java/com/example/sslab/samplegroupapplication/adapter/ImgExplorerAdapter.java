package com.example.sslab.samplegroupapplication.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.example.sslab.samplegroupapplication.data.ImgExplorerItem;

import java.util.ArrayList;

/**
 * Created by SSLAB on 2016-12-12.
 */

public class ImgExplorerAdapter extends ArrayAdapter<ImgExplorerItem> {
    private boolean isListView;

    public ImgExplorerAdapter(Context context, ArrayList<ImgExplorerItem> objects, boolean isListView ) {
        super( context, 0, objects );
        this.isListView = isListView;
    }

}
