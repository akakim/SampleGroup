package com.example.sslab.samplegroupapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.sslab.samplegroupapplication.data.HistoryDetailItem;
import com.example.sslab.samplegroupapplication.R;
import java.util.List;

/**
 * Created by spaceman on 16. 7. 26..
 */
public class HistoryDetailAdapter extends ArrayAdapter<HistoryDetailItem> {
	public HistoryDetailAdapter(Context context, List<HistoryDetailItem> objects ) {
		super( context, 0, objects );
	}

	@Override
	public View getView( int position, View convertView, ViewGroup parent ) {
		View v = convertView;
		RelativeLayout.LayoutParams params;
		if ( v == null ) {
			v = LayoutInflater.from( getContext() ).inflate( R.layout.history_detail_item, parent, false );
			params = new RelativeLayout.LayoutParams(v.getLayoutParams());
			params.setMargins(0,0,1,0);
			v.setLayoutParams(params);
		}
		final HistoryDetailItem item = getItem( position );
		if ( item != null ) {
			TextView textLeft = (TextView) v.findViewById( R.id.textLeft );
			TextView textRight = (TextView) v.findViewById( R.id.textRight );
			textLeft.setText( item.left );
			textRight.setText( item.right);
		}
		return v;
	}
}
