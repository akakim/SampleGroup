package com.example.sslab.samplegroupapplication.samples.mvp;

import android.os.Handler;

import java.util.Arrays;
import java.util.List;

/**
 * Created by SSLAB on 2017-08-09.
 */

public class FindItemsInteractorImpl implements FindItemsInteractor {
    @Override
    public void findItems(final OnFinishedListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
              listener.onFinished(createArrayList());
            }
        },2000) ;
    }


    private List<String> createArrayList(){
        return Arrays.asList(
                "item1",
                "item2",
                "item3",
                "item4",
                "item5",
                "item6",
                "item7",
                "item8"
        );
    }
}
