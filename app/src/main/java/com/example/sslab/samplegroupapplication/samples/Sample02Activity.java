package com.example.sslab.samplegroupapplication.samples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sslab.samplegroupapplication.R;

import java.util.ArrayList;

public class Sample02Activity extends AppCompatActivity
        implements View.OnClickListener , AdapterView.OnItemSelectedListener{

    ArrayList <String> someKeys = new ArrayList<>();
    ArrayList <String> someValues = new ArrayList<>();

    Spinner spinner;
    ListView listView;
    ArrayAdapter adapter;
    ArrayAdapter spinnerAdapter;
    EditText edText;
    Button addButton1;
    Button addButton2;
    Button addButton3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample02);

        spinner     = ( Spinner )findViewById(  R.id.typeSpinner   );
        listView    = ( ListView)findViewById(  R.id.listViewSample   );
        addButton1  = (  Button )findViewById(  R.id.addButton1  );
        addButton2  = (  Button )findViewById(  R.id.addButton2  );
        addButton3  = (  Button )findViewById(  R.id.addButton3  );
        edText      = ( EditText )findViewById(R.id.edText);
        addButton1.setOnClickListener(this);
        addButton2.setOnClickListener(this);
        addButton3.setOnClickListener(this);

        String [] arrayStr = getResources().getStringArray(R.array.itemValues);
        for(int i = 0;i <arrayStr.length;i++){
            someKeys.add(String.valueOf(i));
            someValues.add(arrayStr[i]);
        }

        adapter         = new ArrayAdapter(this,android.R.layout.simple_list_item_1,someValues);
        spinnerAdapter  = new ArrayAdapter(this,android.R.layout.simple_list_item_1,someValues);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        listView.setAdapter(adapter);
        spinner.setAdapter(spinnerAdapter);
        listView.setOnItemSelectedListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        //Toast.makeText(this,"id : "+ listView.getId(),Toast.LENGTH_SHORT).show();
        Log.d(getClass().getSimpleName(),"id : "+ listView.getId());
    }

    @Override
    protected void onResume() {


        super.onResume();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.addButton1:
                someKeys.add(""+view.getId());
                String str = edText.getText().toString();
                someValues.add(str);

                adapter.notifyDataSetChanged();
                spinnerAdapter.notifyDataSetChanged();
                listView.smoothScrollToPosition(someValues.size());
                break;

            case R.id.addButton2:
                someKeys.add(""+view.getId());
                someValues.add("button2");
                adapter.notifyDataSetChanged();
                spinnerAdapter.notifyDataSetChanged();
                listView.smoothScrollToPosition(someValues.size());
                break;

            case R.id.addButton3:
                someKeys.add(""+view.getId());
                someValues.add("SUN");
                adapter.notifyDataSetChanged();
                spinnerAdapter.notifyDataSetChanged();
                listView.smoothScrollToPosition(someValues.size());
                break;

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        Toast.makeText(this,"adapterView ID:"+adapterView.getId()+"Values :"+someValues.get(i),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(this,"nothingSelected",Toast.LENGTH_SHORT).show();
    }
}
