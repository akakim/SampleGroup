package com.example.sslab.samplegroupapplication.widget;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import com.example.sslab.samplegroupapplication.common.CommonUtil;
import com.example.sslab.samplegroupapplication.R;

/**
 * Created by SSLAB on 2016-11-22.
 */

public class EditNumber extends EditText implements View.OnFocusChangeListener {
    public EditNumber(Context context, AttributeSet attrs ) {
            super( context, attrs );
            setInputType( InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED );        // xml에서 인풋타입 설정하지 않아도 숫자만
//            setGravity(Gravity.CENTER_VERTICAL|Gravity.RIGHT);
            setBackground( context.getDrawable( R.drawable.textfield_selector ) );        // xml에서 백그라운드 설정하지 않아도 됨
            setOnFocusChangeListener( this );        // 포커스 변경시 숫자에 컴마 넣어주고 빼주고

        }

    @Override
    public Editable getText() {
        if ( hasFocus() )
            return super.getText();
        else {
            if ( super.getText().toString().length() > 0 )
                return new SpannableStringBuilder( CommonUtil.unformatNum( super.getText() ) );    // 컴마가 붙은 상태일 때 빼서 돌려줌
            else
                return super.getText();
        }
        }

    @Override
    public void onFocusChange( View view, boolean b ) {
            String text = getText().toString();
            if ( b ) {
                 setText( CommonUtil.unformatNum( text ) );
            } else {
                  setText( CommonUtil.formatNum( text ) );
            }
    }


}