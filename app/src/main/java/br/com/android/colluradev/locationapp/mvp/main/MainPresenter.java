package br.com.android.colluradev.locationapp.mvp.main;


import android.content.Context;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONObject;
public class MainPresenter implements MVP.Presenter {

    private MainView mainView;
    Context context;
    private MainModel mainModel;


    public  MainPresenter ( MainView mainView ) {
        this.mainView = mainView;
        this.context = mainView;
        mainModel = new MainModel( this );
    }


    @Override
    public void getPosition() {
        mainModel.getPosition();
    }


    @Override
    public void getPositionCallback(LatLng latLng) {
        mainView.getPositionCallback(latLng);
    }

    @Override
    public void getGeocoding(LatLng latLng) {
        mainModel.getGeocoding(latLng);
    }

    @Override
    public void geocodingCallback(String s) {
        mainView.geocodingCallback(s);
    }
}
