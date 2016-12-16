package com.example.sslab.samplegroupapplication.samples;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sslab.samplegroupapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 체크리스트의 버그를알기위해...
 */
public class SampleUpdatedActivity extends AppCompatActivity implements View.OnClickListener{

    ListView bangsSrcListview;
    TextView bangsTopTargetTextView;
    TextView bangsBottomTargetTextView;
    Button bangsAddButton;

    ListView jungsSrcListview;
    TextView jungsTopTargetTextView;
    TextView jungsBottomTargetTextView;
    Button jungsAddButton;


    ArrayList<SampleDataClass> bangsData = new ArrayList<>();
    ArrayList<SampleDataClass> jungsData = new ArrayList<>();

    BangsAdapter bangsAdapter;
    JungAdapter  jungAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_updated);
        bangsSrcListview            = (ListView)findViewById(R.id.bangsSrcListview);
        jungsSrcListview            = (ListView)findViewById(R.id.jungsSrcListview);

        bangsTopTargetTextView      = (TextView)findViewById(R.id.bangsTopTargetTextView);
        bangsBottomTargetTextView   = (TextView)findViewById(R.id.bangsBottomTargetTextView);
        jungsTopTargetTextView      = (TextView)findViewById(R.id.jungsTopTargetTextView);
        jungsBottomTargetTextView   = (TextView)findViewById(R.id.jungsBottomTargetTextView);

        bangsAddButton              =(Button)findViewById(R.id.bangsAddButton);
        jungsAddButton              =(Button)findViewById(R.id.jungsAddButton);
        bangsAddButton.setOnClickListener(this);
        jungsAddButton.setOnClickListener(this);

        bangsAdapter = new BangsAdapter(this,0,bangsData);
        jungAdapter  = new JungAdapter();


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bangsAddButton:
                break;
            case R.id.jungsAddButton:
                break;
        }
    }

    private class SampleDataClass{
        private String title;
        private Double values;

        public SampleDataClass(String title, Double values) {
            this.title = title;
            this.values = values;

        }
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Double getValues() {
            return values;
        }

        public void setValues(Double values) {
            this.values = values;
        }
    }
    private class BangsAdapter extends ArrayAdapter<SampleDataClass>{


        public BangsAdapter(Context context, int resource, List<SampleDataClass> objects) {
            super(context, 0, objects);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View v = convertView;
            if(v == null){
                v = LayoutInflater.from(getContext()).inflate(R.layout.bang_listview_item,parent);
            }
            return v;
        }

        @Override
        public void add(SampleDataClass object) {
            super.add(object);
        }
    }

    private class JungAdapter extends BaseAdapter{

        ArrayList<SampleDataClass> innerData = new ArrayList<>();

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View v = view ;
            if (v == null)
                v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.bang_listview_item,viewGroup);



            return v;
        }


        public void addItme(SampleDataClass item){

            innerData.add(item);
        }
    }

}
