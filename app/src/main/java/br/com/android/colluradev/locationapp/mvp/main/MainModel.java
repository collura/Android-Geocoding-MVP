package br.com.android.colluradev.locationapp.mvp.main;


import android.content.Context;
import com.google.android.gms.maps.model.LatLng;
import br.com.android.colluradev.locationapp.api.locationAPI.GetLocation;
import br.com.android.colluradev.locationapp.api.volleyAPI.VolleyClass;

public class MainModel implements MVP.Model {

    private MainPresenter mainPresenter;
    private Context context;
    private VolleyClass volleyClass;

    public MainModel ( MainPresenter mainPresenter ) {
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




/// --- GSON CLASSES
    
    public class GoogleGeoCodeResponse {
        public String status ;
        public results[] results ;
        public GoogleGeoCodeResponse() {}
    }

    public class results{
        public String formatted_address ;
        public geometry geometry ;
        public String[] types;
        public address_component[] address_components;
    }

    class address_component{
        public String long_name;
        public String short_name;
        public String[] types ;
    }

    class geometry{
        public bounds bounds;
        public String location_type ;
        public location location;
        public bounds viewport;
    }

    class bounds {
        public location northeast ;
        public location southwest ;
    }

    class location{
        public String lat ;
        public String lng ;
    }

}
