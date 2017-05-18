package com.example.sslab.samplegroupapplication.widget;

import android.content.Context;
import android.graphics.Color;
import android.renderscript.Element;
import android.security.keystore.KeyInfo;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;

import com.example.sslab.samplegroupapplication.util.CommonUtil;
import com.example.sslab.samplegroupapplication.R;

import java.text.DecimalFormat;

/**
 * Created by SSLAB on 2016-11-22.
 */

public class EditNumber extends EditText implements View.OnFocusChangeListener, TextWatcher {
    final String TAG = getClass().getSimpleName();
    public EditNumber(Context context, AttributeSet attrs ) {
        super( context, attrs );
        setInputType( InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED );        // xml에서 인풋타입 설정하지 않아도 숫자만
        setBackground( context.getDrawable( R.drawable.textfield_selector ) );        // xml에서 백그라운드 설정하지 않아도 됨
        setTextColor(Color.BLACK);
        setOnFocusChangeListener( this );        // 포커스 변경시 숫자에 컴마 넣어주고 빼주고

    }

    @Override
    public void onFocusChange( View view, boolean b ) {
        Log.d(TAG,"onFocusChange : "+ this.hashCode());
        Log.d(TAG,"onFocusChange " + b );
        if( b ){

            if("0".equals(this.getText().toString())){
                Log.d(TAG,"onFocusChange clear()" );
                getText().clear();
            }else {
                Log.d(TAG,"onFocusChange not clear" );

            }
        }else {
            if("".equals(this.getText().toString())){
                setText("0");
                Log.d(TAG,"onFocusChange clear()" );

            }
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        // TODO Auto-generated method stub
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {
        // TODO Auto-generated method stub
    }


    private EditText mEditText;
    String strAmount = "tmp"; // 임시 저장값 (콤마)
    CharSequence cs = "string";


    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {    //텍스트가 변경될때마다 실행



        if (!s.toString().equals(strAmount)) { // StackOverflow 방지

            //mEditText.setText(strAmount);
        strAmount = makeStringComma(s.toString().replace(",", ""));
        this.setText(strAmount);
        Editable e = this.getText();
        Selection.setSelection(e, strAmount.length());

        }
    }

    protected String makeStringComma(String str) {    // 천단위 콤마 처리
        if (str.length() == 0)
            return "";
        if(str.equals("-"))
            return "-";
        if(str.equals("-0"))
            return "-0";

        long value = Long.parseLong(str);
        DecimalFormat format = new DecimalFormat("###,###");
        return format.format(value);
    }


}