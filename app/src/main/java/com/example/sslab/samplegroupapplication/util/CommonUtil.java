package com.example.sslab.samplegroupapplication.util;

import android.util.Log;

import java.text.DecimalFormat;

/**
 * Created by SSLAB on 2016-11-16.
 */
public class CommonUtil {
    private static final String TAG = CommonUtil.class.getSimpleName();
    public static String nullToStringZero( String sTarget ) {
        if(sTarget==null)
            return "0";
        if(sTarget.equals("null")||sTarget.equals(""))
            return "0";

        return sTarget;
    }


    /**
     * replace
     *
     * src_의 값 중에서 fnd을 찾아 rep로 바꿔주는 함수 java.lang.String.replace(char c1, char
     * c2)와 유사하나 이 함수는 스트링을 바꿀 수 있다.
     *
     * @param src_
     *            소스 스트링
     * @param fnd
     *            찾을 스트링
     * @param rep
     *            바꿀 스트링
     *
     * @return 변환된 스트링
     */
    public static final String replace(Object src_, Object fnd, Object rep) {
        if (src_ == null) {
            return null;
        }
        String line = String.valueOf(src_);
        String oldString = String.valueOf(fnd);
        String newString = String.valueOf(rep);
        int i = 0;
        if ((i = line.indexOf(oldString, i)) >= 0) {
            char[] line2 = line.toCharArray();
            char[] newString2 = newString.toCharArray();
            int oLength = oldString.length();
            StringBuffer buf = new StringBuffer(line2.length);
            buf.append(line2, 0, i).append(newString2);
            i += oLength;
            int j = i;
            while ((i = line.indexOf(oldString, i)) > 0) {
                buf.append(line2, j, i - j).append(newString2);
                i += oLength;
                j = i;
            }
            buf.append(line2, j, line2.length - j);
            return buf.toString();
        }
        return line;
    }

    /**
     * 콤마 제거
     *
     * @param sTarget
     * @return
     */
    public static String removeComma(String sTarget){
        return sTarget.replace(",", "");
    }

    public static String unformatNum( CharSequence num ) {
        String res = "";
        try {
            res = num.toString().replaceAll( ",", "" );
        } catch ( Exception e ) {

        }
        return res;
    }

    public static String formatDate( String str ) {
        String date = "";
        if ( str == null || str.equals( "null" ) ) {
            return "";
        }
        try {
            if ( str.matches( "^[0-9]*$" ) ) {
                StringBuilder b = new StringBuilder( str );
                b.insert( 4, "-" );
                b.insert( 7, "-" );
                date = b.toString();
            } else {
                return str;
            }
        } catch ( Exception e ) {
            return str;
        }
        return date + "";
    }

    public static String formatNum(String num){
        String res ="";
        DecimalFormat format = new DecimalFormat("###,###,###,###,###");
        try{
            if(num.length() != 0)
                res = format.format(castStrToLong(num));
        }catch (Exception e){
            Log.e(TAG, "Failed to Format Number : " + e.getMessage() );
        }
        return res;
    }


    public static String formatNum( long num ) {
        String res = "";
        DecimalFormat format = new DecimalFormat( "###,###,###,###,###" );
        try {
            res = format.format( num );
        } catch ( Exception e ) {
            Log.d( "Tag", "Failed to Format Number : " + e.getMessage() );
        }
        return res;
    }

    public static String unformatNumOrZero( String num ) {
        try {
            num = num.replaceAll( ",", "" );
        } catch ( Exception e ) {
            return "0";
        }
        return num;
    }

    public static long castStrToLong( String sStr ) {
        if ( sStr == null || sStr.length() == 0 )
            return 0;
        try {
            return Long.parseLong( unformatNum( sStr ) );
        } catch ( Exception e ) {
            Log.e( TAG, "Failed to Cast String as Long : " + e.getMessage() );
        }
        return 0;
    }
}
