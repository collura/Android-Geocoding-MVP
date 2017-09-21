package br.com.android.colluradev.locationapp.mvp.main;


import com.google.android.gms.maps.model.LatLng;
interface MVP {

    interface View {
        void getPositionCallback(LatLng latLng);
        void geocodingCallback(String s);
        void geocodingErrorCalback();
        void showAlerter(String text);
    }



    interface Presenter {
        void getPosition();
        void getPositionCallback(LatLng latLng);
        void getGeocoding(LatLng latLng);
        void geocodingCallback(String s);

        void geocodingErrorCalback();

    }


    interface Model {
        void getPosition();
        void getGeocoding(LatLng latLng);
        void geocodingCallback(String s);
        void getPositionCallback(LatLng latLng);

        void geocodingErrorCalback();

    }
}
