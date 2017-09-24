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
public class GetLocation {

    private MainModel mainModel;

    public GetLocation(MainModel mainModel, Context context) {
        this.mainModel = mainModel;
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.i("Tag", "Not Permission");
        }
        else {
            fusedLocationProviderClient.getLastLocation()
                .addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if( location != null ) {
                            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                            getPositionCallback(latLng);
                        } else {
                            //your callback error
                            Log.i("Tag", "Location == Null");
                        }
                    }
                })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //your callback error
                        Log.i("Tag", "Fail to get Location.");

                    }
                });
            }
    }


    private void getPositionCallback (LatLng latLng) {
        mainModel.getPositionCallback(latLng);
    }
}
