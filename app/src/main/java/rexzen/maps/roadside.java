package rexzen.maps;

import android.*;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.audiofx.BassBoost;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.security.acl.Permission;
import java.util.Calendar;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class roadside extends AppCompatActivity {
    private static Integer count = 0;
    private static String det = "",task="";

    private LocationListener locationListener;
    private LocationManager locationManager;
    ImageView ambu, police, petrol, bat, tow, flat;
    private static String lats = "12.989386", lons = "80.229669";
    final Calendar c = Calendar.getInstance();
    int Year = c.get(Calendar.YEAR);
    int Month = c.get(Calendar.MONTH) + 1;
    int day = c.get(Calendar.DAY_OF_MONTH);
    String details = "TN 10 R 7754, Bajaj Platina, Black color, Owner-Harish Ananth";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roadside);
        setTitle("Choose the required Assistance");
        Toast.makeText(this, "day " + day + " month " + Month + " Year " + Year, Toast.LENGTH_SHORT).show();

        ambu = (ImageView) findViewById(R.id.ambu);
        police = (ImageView) findViewById(R.id.police);
        petrol = (ImageView) findViewById(R.id.jerry);
        bat = (ImageView) findViewById(R.id.battery);
        tow = (ImageView) findViewById(R.id.tow);
        flat = (ImageView) findViewById(R.id.flat);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if (count == 0) {
                    Toast.makeText(roadside.this, "latitude " + location.getLatitude() + " longitude " + location.getLongitude(), Toast.LENGTH_LONG).show();
                    count++;
                    Intent newint = new Intent(roadside.this, rafirebase.class);
                    newint.putExtra("lat", lats);
                    newint.putExtra("lon", lons);
                    newint.putExtra("details", details);
                    newint.putExtra("parent", det);
                    startActivity(newint);
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);

            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET}, 10);
            }
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        } else {
            // locset();
            //getlocation();
        }
        flat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(roadside.this, "Flat Tyre", Toast.LENGTH_SHORT).show();
                task = "flat tyre";
                new Asyncroadside().execute();
                new Asyncmaintable().execute();
                locset();
            }
        });
        tow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(roadside.this, "Tow/Recovery Services", Toast.LENGTH_SHORT).show();
                task = "tow needed";
                new Asyncroadside().execute();
                new Asyncmaintable().execute();
                locset();
            }
        });
        bat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(roadside.this, "Jump Start needed", Toast.LENGTH_SHORT).show();
                task = "battery issue";
                new Asyncroadside().execute();
                new Asyncmaintable().execute();
                locset();
            }
        });
        petrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(roadside.this, "Fuel Required", Toast.LENGTH_SHORT).show();
                task = "fuel needed";
                new Asyncroadside().execute();
                new Asyncmaintable().execute();
            }
        });
        police.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(roadside.this, "Police Alert", Toast.LENGTH_SHORT).show();
                task = "police assitance";
                new Asyncroadside().execute();
                new Asyncmaintable().execute();
            }
        });
        ambu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(roadside.this, "Medical Services", Toast.LENGTH_SHORT).show();
                det = "ambulance needed";
                new Asyncroadside().execute();
                new Asyncmaintable().execute();
                locset();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 10:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    //locset(); //getlocation();
                    return;
        }
    }

    private void locset() {
        //lats=19.12833716;
        // lon=72.86419614;
        locsend();
    }

    private void getlocation() {

        locationManager.requestLocationUpdates("gps", 5000, 0, locationListener);
    }

    private void locsend() {
        Toast.makeText(roadside.this, "latitude " + lats + " longitude " + lons, Toast.LENGTH_LONG).show();
        count++;
        Intent newint = new Intent(roadside.this, rafirebase.class);
        newint.putExtra("lat", lats);
        newint.putExtra("lon", lons);
        newint.putExtra("details", details);
        newint.putExtra("parent", det);
        startActivity(newint);
    }

    public class Asyncroadside extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            OkHttpClient client = new OkHttpClient();
            IncomingSms.lat = "13.333";

            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\n    \"type\":\"insert\",\n    \"args\":{\n        \"table\":\"road\",\n        \"objects\":[\n            {\"regno\":\"" + seconcl.regs + "\",\n            \"lat\":\"" + IncomingSms.lat + "\",\n            \"lon\":\"80.9765\",\n            \"status\":\"unattended\",\n            \"details\":\"TVS 100cc blue\",\n            \"service\":\""+task+"\"\n            }\n        ]\n    }    \n}    \n    \n");
            Request request = new Request.Builder()
                    .url("https://data.erratic39.hasura-app.io/v1/query")
                    .post(body)
                    .addHeader("authorization", "Bearer p1c5ut3lfoishbel66ns5fwsf40ez3yt")
                    .addHeader("content-type", "application/json")
                    .addHeader("cache-control", "no-cache")
                    .addHeader("postman-token", "46d0c959-04fd-dac3-4a4f-06dd4314cc30")
                    .build();
            try {
                Response response = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public class Asyncmaintable extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            OkHttpClient client = new OkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\n    \"type\":\"insert\",\n    \"args\":{\n        \"table\":\"maintable\",\n        \"objects\":[\n            {\"regno\":\"" + seconcl.regs + "\",\n            \"lat\":\"" + lats + "\",\n            \"lon\":\"" + lons + "\",\n            \"status\":\"unattended\",\n            \"type\":\"roadside\",\n            \"details\":\"" + seconcl.detail + "\"\n            }\n        ]\n    }    \n}    \n    \n");
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


    }
}
