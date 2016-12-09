package com.example.sslab.samplegroupapplication.widget;

import android.content.Context;
import android.renderscript.Element;
import android.security.keystore.KeyInfo;
import android.text.Editable;
import android.text.InputType;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;

import com.example.sslab.samplegroupapplication.util.CommonUtil;
import com.example.sslab.samplegroupapplication.R;

/**
 * Created by SSLAB on 2016-11-22.
 */

public class EditNumber extends EditText implements View.OnFocusChangeListener {
    public EditNumber(Context context, AttributeSet attrs ) {
            super( context, attrs );

            setInputType( InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED );        // xml에서 인풋타입 설정하지 않아도 숫자만
            setBackground( context.getDrawable( R.drawable.textfield_selector ) );        // xml에서 백그라운드 설정하지 않아도 됨

            setOnFocusChangeListener( this );        // 포커스 변경시 숫자에 컴마 넣어주고 빼주고

        }

    @Override
    public Editable getText() {
        SpannableStringBuilder builder;

        if ( hasFocus() )
            return super.getText();
        else {
            builder = new SpannableStringBuilder(super.getText());
            if( "".equals(builder.toString())){
                builder.append("0",0,1);
                return builder;
            }
            else if ( super.getText().toString().length() > 0 )
                return builder;    // 컴마가 붙은 상태일 때 빼서 돌려줌
            else
                return super.getText();
        }
    }

    @Override
    public void setGravity(int gravity) {
        super.setGravity(gravity);
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