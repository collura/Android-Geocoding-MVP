package br.com.android.colluradev.locationapp.api.volleyAPI;


import android.content.Context;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import org.json.JSONObject;

import br.com.android.colluradev.locationapp.mvp.main.MainModel;
public class VolleyClass {

    private JsonObjectRequest jsonObjectRequest;
    private Context context;
    private RequestQueue rq;
    private MainModel mainModel;
    private String url;
    private MainModel.GoogleGeoCodeResponse result;
    private Gson gson;

    public VolleyClass (MainModel mainModel, Context context){
        this.context = context;
        this.mainModel = mainModel;
        gson = new Gson();
    }


    public void getGeocoding(LatLng latLng) {
        url = "https://maps.googleapis.com/maps/api/geocode/json?latlng="+latLng.latitude+","+latLng.longitude+"&key=AIzaSyBzQdf77GyEOrCqlLNtjAE3pR1uAT4LiFE";
        jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        String obj = response.toString();
                        result = gson.fromJson(obj, MainModel.GoogleGeoCodeResponse.class);
                        callback(result.results[0].formatted_address);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Tag", "onErrorResponse : " + error.getMessage());
                    }
                });

        if (rq == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            rq = Volley.newRequestQueue(context.getApplicationContext());
        }
        rq.add(jsonObjectRequest);
    }



    void callback ( String s ) {
        mainModel.geocodingCallback ( s );
    }

}
