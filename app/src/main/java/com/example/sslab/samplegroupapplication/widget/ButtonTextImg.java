package com.example.sslab.samplegroupapplication.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.bumptech.glide.Glide;
import com.example.sslab.samplegroupapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ButtonTextImg extends RelativeLayout {
    private final int VERTICAL = 0;
    private final int HORIZONTAL = 1;


    protected TypedArray array;
    @BindView( R.id.img )
    public ImageView img;
    @BindView( R.id.textNormal )
    public TextView text;

    private AQuery aq;

    public ButtonTextImg(Context context, AttributeSet attrs ) {
        super( context, attrs );
        int orientation = 0;
        aq = new AQuery( context );
        array = context.getTheme().obtainStyledAttributes( attrs, R.styleable.ButtonTextImg, 0, 0 );

        LayoutInflater.from( context ).inflate( R.layout.button_text_image_vertical, this, true );
//        try {
//            orientation = array.getInt( R.styleable.ButtonTextImg_orientation, 0 );
//            switch ( orientation ) {
//                case VERTICAL:
//                    LayoutInflater.from( context ).inflate( R.layout.button_text_image_vertical, this, true );
//                    break;
//                case HORIZONTAL:
//                    LayoutInflater.from( context ).inflate( R.layout.button_text_image_horizontal, this, true );
//                    break;
//                default:
//                    LayoutInflater.from( context ).inflate( R.layout.button_text_image_vertical, this, true );
//                    break;
//            }
//        } catch ( Exception e ) {
//            LayoutInflater.from( context ).inflate( R.layout.button_text_image_vertical, this, true );
//        }
        ButterKnife.bind( this );

        int maxLine = array.getInt( R.styleable.ButtonTextImg_max_text_lines, 3 );
        text.setMaxLines( maxLine );
        try {
            aq.id( img ).image( array.getDrawable( R.styleable.ButtonTextImg_src ) );
//            Glide.with( context ).load( array.getResourceId( R.styleable.ButtonTextImg_src, 0 ) ).into( img );
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        try {
            float imgSize = array.getDimension( R.styleable.ButtonTextImg_img_size, 10 );
            aq.id( img ).height( ( int ) imgSize, false ).width( ( int ) imgSize, false );
        } catch ( Exception e ) {
        }
        try {
            String str = array.getString( R.styleable.ButtonTextImg_text );
            if ( str == null || str.length() == 0 ) {
                text.setVisibility( GONE );
            } else {
                text.setVisibility( VISIBLE );
                text.setText( str );
            }
        } catch ( Exception e ) {
        }
        try {
            int textColor = array.getColor( R.styleable.ButtonTextImg_text_color, Color.BLACK );
            text.setTextColor( textColor );
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        try {
            float textSize = array.getDimension( R.styleable.ButtonTextImg_text_size, 10 );
            text.setTextSize( TypedValue.COMPLEX_UNIT_PX, textSize );
        } catch ( Exception e ) {

        }
        try {
            float textMargin = array.getDimension( R.styleable.ButtonTextImg_text_margin, 1 );
//			Log.d( "Tag", "margin " + textMargin + " / " + text.getText().toString() );
            if ( orientation == 0 ) {
                text.setPadding( 0, ( int ) textMargin, 0, 0 );
            } else {
                text.setPadding( ( int ) textMargin, 0, 0, 0 );
            }
        } catch ( Exception e ) {

        }
        try {
            if ( array.getBoolean( R.styleable.ButtonTextImg_anim_img, false ) ) {
                final AnimationDrawable frameAnimation = ( AnimationDrawable ) img.getDrawable();
                post( new Runnable() {
                    public void run() {
                        frameAnimation.start();
                    }
                } );
            }
        } catch ( Exception e ) {

        }

    }

    @Override
    public void setSelected( boolean selected ) {
        super.setSelected( selected );
        img.setSelected( selected );
    }

    public void setText( String string ) {
        text.setText( string );
        text.setVisibility( VISIBLE );
    }

    public void setTextColor( int color ) {
        text.setTextColor( color );
    }

    public void setTextColor( String color ) {
        text.setTextColor( Color.parseColor( color ) );
    }

    public void setImage( int resId ) {
//        img.setImageResource( resId );
//        aq.id( img ).image( resId );
        Glide.with( getContext() ).load( resId ).into( img );
    }
}
