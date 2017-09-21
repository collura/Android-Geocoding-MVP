package br.com.android.colluradev.locationapp.mvp.main;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONObject;

import br.com.android.colluradev.locationapp.R;
import br.com.android.colluradev.locationapp.mvp.BaseView;

public class MainView extends BaseView implements
       OnMapReadyCallback
      ,MVP.View{

    private MainPresenter mainPresenter;

    private GoogleMap mMap;
    private TextView txtLocal;
    LatLng latLng;

    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mainPresenter = new MainPresenter(this);

        mapView = findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();

        txtLocal = findViewById(R.id.txtLocal);

        mainPresenter.getPosition();
    }


    @Override
    public void getPositionCallback(LatLng latLng) {
        this.latLng = latLng;
        mainPresenter.getGeocoding(latLng);
        mapView.getMapAsync(this);
    }

    @Override
    public void geocodingCallback(String s) {
        txtLocal.setText(s);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.addMarker(new MarkerOptions().position(latLng).title("Local Atual"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }

}
