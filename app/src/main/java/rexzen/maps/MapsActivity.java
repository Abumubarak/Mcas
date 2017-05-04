package rexzen.maps;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    public double latd,lond;
    String details;
    String regno;

    BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        if(IncomingSms.lat!=null) {
            new Asyncmaintable().execute();
            new Asyncambutable().execute();
        }
        SharedPreferences sharedPref= getSharedPreferences("mypref", MODE_PRIVATE);
        latd= Double.parseDouble(IncomingSms.lat);
        lond= Double.parseDouble(IncomingSms.lon);
       // lat= Double.parseDouble(sharedPref.getString("lat","33"));
       // lon= Double.parseDouble(sharedPref.getString("lat","33"));


        Toast.makeText(this,"lat"+latd+"long"+lond+"details"+details,Toast.LENGTH_LONG).show();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);





    }
    @Override
    public void onBackPressed()
    {
        // code here to show dialog
        super.onBackPressed();  // optional depending on your needs
        Intent newint=new Intent(this,firebaseact.class);
        startActivity(newint);
    }






    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;



        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(latd, lond);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }
    public class Asyncmaintable extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... params) {
            OkHttpClient client = new OkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\n    \"type\":\"insert\",\n    \"args\":{\n        \"table\":\"maintable\",\n        \"objects\":[\n            {\"regno\":\""+IncomingSms.regno+"\",\n            \"lat\":\""+IncomingSms.lat+"\",\n            \"lon\":\""+IncomingSms.lon+"\",\n            \"status\":\"unattended\",\n            \"type\":\"ambulance\",\n            \"details\":\""+IncomingSms.details+"\"\n            }\n        ]\n    }    \n}    \n    \n");
            Request request = new Request.Builder()
                    .url("https://data.erratic39.hasura-app.io/v1/query")
                    .post(body)
                    .addHeader("authorization", "Bearer p1c5ut3lfoishbel66ns5fwsf40ez3yt")
                    .addHeader("content-type", "application/json")
                    .addHeader("cache-control", "no-cache")
                    .addHeader("postman-token", "0ad55fa2-8236-b212-4478-22e382769634")
                    .build();

            try {
                Response response = client.newCall(request).execute();
                String s = response.body().string();
                //tv.setText(s);
                Log.e("f", s);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            //Toast.makeText(this,"check",Toast.LENGTH_SHORT).show();
            super.onPostExecute(aVoid);
        }
    }
    public class Asyncambutable extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... params) {
            OkHttpClient client = new OkHttpClient();

            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\n    \"type\":\"insert\",\n    \"args\":{\n        \"table\":\"ambulance\",\n        \"objects\":[\n            {\"regno\":\""+IncomingSms.regno+"\",\n            \"lat\":\""+IncomingSms.lat+"\",\n            \t\"lon\":\""+IncomingSms.lon+"\",\n            \t\"status\":\"unattended\",\n            \t\"details\":\""+IncomingSms.details+"\",\n            \t\"Hospital\":\"nil\"\n            }\n        ]\n    }\n}");
            Request request = new Request.Builder()
                    .url("https://data.erratic39.hasura-app.io/v1/query")
                    .post(body)
                    .addHeader("authorization", "Bearer p1c5ut3lfoishbel66ns5fwsf40ez3yt")
                    .addHeader("content-type", "application/json")
                    .addHeader("cache-control", "no-cache")
                    .addHeader("postman-token", "cc96825a-fde8-b65f-8c2e-1cf5af74103f")
                    .build();

            try {
                Response response = client.newCall(request).execute();
                String s = response.body().string();
                //tv.setText(s);
                Log.e("f", s);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            //Toast.makeText(this,"check",Toast.LENGTH_SHORT).show();
            super.onPostExecute(aVoid);
        }
    }
}
