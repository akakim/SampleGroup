package com.example.sslab.samplegroupapplication.samples.mvp;

import java.util.List;

/**
 * Created by SSLAB on 2017-08-09.
 */

public interface MainView {

    void showProgrees();
    void hideProgress();
    void setItem(List<String> items );
    void showMessage (String message );
}
