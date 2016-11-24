package com.example.sslab.samplegroupapplication.samples;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.sslab.samplegroupapplication.R;

import java.util.ArrayList;
import java.util.List;

public class MultiLineTextListViewActivity extends AppCompatActivity {

    ArrayList<FourNumberOfText> aboveListViewItmes=  new ArrayList<>();
    ArrayList<FourNumberOfText> belowListViewItems=  new ArrayList<>();
    ListViewAdatper aboveListAdapter;
    ListViewAdatper belowListAdapter;

    ListView aboveListView;
    ListView belowListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_line_text_list_view);


        aboveListView = (ListView)findViewById(R.id.aboveListView);
        belowListView = (ListView)findViewById(R.id.belowListView);
        //aboveListAdapter = new ListViewAdatper(this,)

       for(int i =0;i<2;i++){

           String first  = i+" 번째 \n";
           String second = i+" 번째 \n";
           String third  = i+" 번째 \n";
           String forth  = i+" 번째 \n";

           for(int j =i;j<4;j++){
               second += j+ "번째 \n";
           }

           for(int k =i;k<5;k++){
               third += k+ "번째 \n";
           }

           aboveListViewItmes.add(new FourNumberOfText(first,second,third,forth));
       }

        for(int x = 0;x<6;x++){
            String first  = x+" 번째";
            String second = (x+1)+" 번째 ";
            String third  = (x+2)+" 번째 ";
            String forth  = (x+3)+" 번째 ";
            belowListViewItems.add(new FourNumberOfText(first,second,third,forth));
        }


    }


    private class ListViewAdatper extends ArrayAdapter<FourNumberOfText> {

        public ListViewAdatper(Context context, int resource, List<FourNumberOfText> objects) {
            super(context, resource, objects);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = super.getView(position, convertView, parent);
            final FourNumberOfText item = getItem(position);



            return v;
        }
    }

    private class FourNumberOfText{
        private String first;
        private String second;
        private String third;
        private String fourth;

        public FourNumberOfText(String first, String second, String third, String fourth) {
            this.first = first;
            this.second = second;
            this.third = third;
            this.fourth = fourth;
        }

        public String getFirst() {
            return first;
        }

        public void setFirst(String first) {
            this.first = first;
        }

        public String getSecond() {
            return second;
        }

        public void setSecond(String second) {
            this.second = second;
        }

        public String getThird() {
            return third;
        }

        public void setThird(String third) {
            this.third = third;
        }

        public String getFourth() {
            return fourth;
        }

        public void setFourth(String fourth) {
            this.fourth = fourth;
        }
    }

    private void setListViewHeight( final ListView listView ) {
        listView.post( new Runnable() {
            @Override
            public void run() {
                ListAdapter listAdapter = listView.getAdapter();
                if ( listAdapter == null ) {
                    return;
                }
                int totalHeight = 0;
                int desiredWidth = View.MeasureSpec.makeMeasureSpec( listView.getWidth(), View.MeasureSpec.AT_MOST );
                for ( int i = 0; i < listAdapter.getCount(); i++ ) {
                    View listItem = listAdapter.getView( i, null, listView );
                    listItem.measure( desiredWidth, View.MeasureSpec.UNSPECIFIED );
                    totalHeight += listItem.getMeasuredHeight();
                }
                LinearLayout.LayoutParams params = ( LinearLayout.LayoutParams ) listView.getLayoutParams();
                params.height = totalHeight + ( listView.getDividerHeight() * ( listAdapter.getCount() - 1 ) + listView.getPaddingBottom() + listView.getPaddingTop() );
                listView.setLayoutParams( params );
                listView.requestLayout();
            }
        } );
    }
}
