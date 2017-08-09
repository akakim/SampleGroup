package com.example.sslab.samplegroupapplication.samples.mvp;

import java.util.List;

/**
 * Created by SSLAB on 2017-08-09.
 */

public interface FindItemsInteractor {

    interface OnFinishedListener{
        void onFinished(List<String> items);
    }

    void findItems(OnFinishedListener listener);
}
