package com.example.sslab.samplegroupapplication.samples.mvp;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.sslab.samplegroupapplication.R;
import com.example.sslab.samplegroupapplication.widget.DilatingProgressDialog;

import java.util.List;

public class MvpMainActivity extends AppCompatActivity implements MainView, AdapterView.OnItemClickListener {

    ListView groupLIst;
    private MainPresenter presenter;
    DilatingProgressDialog dialog ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp_main);

        groupLIst = ( ListView ) findViewById( R.id.groupLIst );
        groupLIst.setOnItemClickListener( this );
        presenter = new MainPresenterImpl( this , new FindItemsInteractorImpl() );

//        presenter.getMain
    }


    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }


    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        presenter.onItemClicked( position );
    }

    @Override
    public void showProgrees() {

        if( dialog != null && !dialog.isShowing()){
            dialog.show();
        }

        groupLIst.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {

        if(dialog != null && dialog.isShowing() ){
            dialog.dismiss();
        }
        groupLIst.setVisibility(View.VISIBLE);
    }

    @Override
    public void setItem(List<String> items) {
        groupLIst.setAdapter( new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, items ));

    }

    @Override
    public void showMessage(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("알림").setMessage(message).setPositiveButton("확인",new AlertDialog.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setCancelable(false);
        builder.show();
    }
}
