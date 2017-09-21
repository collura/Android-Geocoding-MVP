package br.com.android.colluradev.locationapp.mvp.main;


import com.google.android.gms.maps.model.LatLng;

import org.json.JSONObject;
interface MVP {

    interface View {
        void getPositionCallback(LatLng latLng);
        void geocodingCallback(String s);

    }



    interface Presenter {
        void getPosition();
        void getPositionCallback(LatLng latLng);
        void getGeocoding(LatLng latLng);
        void geocodingCallback(String s);
    }


    interface Model {
        void getPosition();
        void getGeocoding(LatLng latLng);
        void geocodingCallback(String s);
        void getPositionCallback(LatLng latLng);
    }
}
