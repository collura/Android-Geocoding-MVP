package br.com.android.colluradev.locationapp.mvp.main;


import android.content.Context;

import com.google.android.gms.maps.model.LatLng;

import br.com.android.colluradev.locationapp.api.locationAPI.GetLocation;
import br.com.android.colluradev.locationapp.api.volleyAPI.VolleyClass;

public class MainModel implements MVP.Model {

    private MainPresenter mainPresenter;
    private Context context;
    private VolleyClass volleyClass;

    MainModel ( MainPresenter mainPresenter ) {
        this.mainPresenter = mainPresenter;
        this.context = mainPresenter.context;
        volleyClass = new VolleyClass(this, mainPresenter.context);
    }


    @Override
    public void getPosition() {
        new GetLocation( this, this.context );
    }

    @Override
    public void getPositionCallback(LatLng latLng) {
        mainPresenter.getPositionCallback(latLng);
    }

    @Override
    public void getGeocoding(LatLng latLng) {
        volleyClass.getGeocoding(latLng);
    }

    @Override
    public void geocodingCallback(String s) {
        mainPresenter.geocodingCallback(s);
    }

}
