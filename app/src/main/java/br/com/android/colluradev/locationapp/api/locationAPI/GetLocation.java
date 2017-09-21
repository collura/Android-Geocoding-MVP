package br.com.android.colluradev.locationapp.api.locationAPI;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import br.com.android.colluradev.locationapp.mvp.main.MainModel;
public class GetLocation implements OnSuccessListener, OnFailureListener {

    private FusedLocationProviderClient fusedLocationProviderClient;
    private LatLng latLng;
    private MainModel mainModel;

    public GetLocation(MainModel mainModel, Context context) {
        this.mainModel = mainModel;
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.i("Tag", "Sem Permissão");
        }
        else {
            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(this)
                    .addOnFailureListener(this);
        }
    }

    @Override
    public void onSuccess(Object o) {
        Location l = ( Location ) o;
        if( l != null ) {
            latLng = new LatLng(l.getLatitude(), l.getLongitude());
            mainModel.getPositionCallback(latLng);
        } else {
            Log.i("Tag", "Location == Null");
        }
    }


    @Override
    public void onFailure(@NonNull Exception e) {
        Log.i("Tag", "Falha na operação de Obter a localização.");

    }
}
