package com.example.sslab.samplegroupapplication.data;

import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by SSLAB on 2016-12-26.
 */

public class MessageCustomize implements Parcelable {

    protected MessageCustomize(Parcel in) {
    }

    public static final Creator<MessageCustomize> CREATOR = new Creator<MessageCustomize>() {
        @Override
        public MessageCustomize createFromParcel(Parcel in) {
            return new MessageCustomize(in);
        }

        @Override
        public MessageCustomize[] newArray(int size) {
            return new MessageCustomize[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
