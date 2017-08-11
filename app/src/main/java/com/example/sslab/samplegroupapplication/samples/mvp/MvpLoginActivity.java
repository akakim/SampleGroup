package com.example.sslab.samplegroupapplication.samples.mvp;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import com.example.sslab.samplegroupapplication.R;
import com.example.sslab.samplegroupapplication.widget.DilatingProgressDialog;

public class MvpLoginActivity extends AppCompatActivity implements LoginView, View.OnClickListener{

    DilatingProgressDialog dialog;

    EditText idEdit;
    EditText passwordEdit;

    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp_login);

        dialog = new DilatingProgressDialog(this);

        idEdit = ( EditText )findViewById( R.id.idEdit );
        passwordEdit = ( EditText ) findViewById( R.id.passwordEdit );
        findViewById( R.id.loginBtn ) .setOnClickListener( this ) ;

        presenter = new LoginPresenterImpl(this);
    }


    @Override
    protected void onResume() {
        super.onResume();

    }


    @Override
    protected void onStop() {

        hideProgress();
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {

        presenter.validCredentials( idEdit.getText().toString(),passwordEdit.getText().toString() );
    }

    @Override
    public void showProgress() {
        if( dialog != null && !dialog.isShowing() ){
            dialog.show();
        }
    }

    @Override
    public void hideProgress() {
        if(dialog!= null && dialog.isShowing()){
            dialog.dismiss();
        }

    }

    @Override
    public void setUserNameError() {
        idEdit.setError("userName error");
    }

    @Override
    public void setPasswordError() {
        passwordEdit.setError("userPassword error");
    }

    @Override
    public void navigateToHome() {
        startActivity( new Intent(this, MvpMainActivity.class ) );
    }
}
