package br.com.android.colluradev.locationapp.mvp.main;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tapadoo.alerter.Alerter;

import br.com.android.colluradev.locationapp.R;

class MainView extends AppCompatActivity implements
        OnMapReadyCallback
        , MVP.View {

    private MainPresenter mainPresenter;
    private LatLng latLng;
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mainPresenter = new MainPresenter(this);

        mapView = findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();

        mainPresenter.getPosition();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        GoogleMap mMap = googleMap;

        final Marker userMarker = mMap.addMarker(new MarkerOptions().position(latLng).title("You"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        userMarker.setDraggable(true);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);

        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {}
            @Override
            public void onMarkerDrag(Marker marker) {}
            @Override
            public void onMarkerDragEnd(Marker marker) {
               mainPresenter.getGeocoding(marker.getPosition());
            }
        });

        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                mainPresenter.getGeocoding(latLng);
                userMarker.setPosition(latLng);
                return false;
            }
        });
    }

    @Override
    public void getPositionCallback(LatLng latLng) {
        this.latLng = latLng;
        mainPresenter.getGeocoding(latLng);
        mapView.getMapAsync(this);
    }

    @Override
    public void geocodingCallback(String string) {
        showAlerter(string);
    }

    @Override
    public void geocodingErrorCalback() {
        showAlerter(getResources().getString(R.string.local_desconhecido));
    }

    @Override
    public void showAlerter(String text) {
        Alerter.create(this)
                .setDuration(100000)
                .setText(text)
                .setBackgroundColorInt(getResources().getColor(R.color.black))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Alerter.hide();
                    }
                })
                .show();
    }

}
