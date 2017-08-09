package com.example.sslab.samplegroupapplication.samples.mvp;

import java.util.List;

/**
 * Created by SSLAB on 2017-08-09.
 */

public class MainPresenterImpl implements MainPresenter,FindItemsInteractor.OnFinishedListener{

    private MainView mainView;
    private FindItemsInteractor findItemsInteractor;


    public MainPresenterImpl(MainView mainView, FindItemsInteractor findItemsInteractor) {
        this.mainView = mainView;
        this.findItemsInteractor = findItemsInteractor;
    }

    @Override
    public void onResume() {
        if( mainView != null ){
            mainView.showProgrees();
        }

        findItemsInteractor.findItems( this );
    }

    @Override
    public void onItemClicked(int position) {
        if( mainView != null ){
            mainView.showMessage( String.format("Position %d clicked",position + 1 ));
        }
    }

    @Override
    public void onDestroy() {
        mainView = null;
    }

    @Override
    public void onFinished(List<String> items) {
        if( mainView != null){
            mainView.setItem(items);
            mainView.hideProgress();
        }
    }

    public MainView getMainView(){
        return mainView;
    }
}
