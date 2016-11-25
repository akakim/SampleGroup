package com.example.sslab.samplegroupapplication.widget;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.sslab.samplegroupapplication.util.CommonUtil;

/**
 * Created by SSLAB on 2016-11-16.
 */
public class TextNumber extends TextView {
    String dataString;


    public TextNumber(Context context) {
        super(context);
//        setBackground( context.getDrawable( R.drawable.textfield_selector ) );
    }

    public TextNumber(Context context, AttributeSet attrs) {
        super(context, attrs);
//        setBackground( context.getDrawable( R.drawable.textfield_selector ) );
    }

    @Override
    public void setText(CharSequence text, BufferType type) {

        if(text.toString().contains(","))
            super.setText(text, type);
        else {
            super.setText(CommonUtil.formatNum(text.toString()),type);
        }

    }

    @Override
    public CharSequence getText() {

        if(super.getText().toString().length()>0){
            return new SpannableStringBuilder(CommonUtil.unformatNum(super.getText()));
        }else {
            return super.getText();
        }
    }

    public String getData(){
        return dataString;

    }
}
