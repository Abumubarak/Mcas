package rexzen.maps;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarBadge;
import com.roughike.bottombar.OnMenuTabClickListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class optionnav extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  {


    BottomBar bottomBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optionnav);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("TcpRide");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabnav);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder= new AlertDialog.Builder(optionnav.this);
                builder.setTitle("Include any message below");
                final EditText input_field=new EditText(optionnav.this);
                input_field.setBackgroundColor(Color.parseColor("#ffe8f8"));
                input_field.setCursorVisible(true);
                builder.setView(input_field);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String s=input_field.getText().toString();
                        Toast.makeText(optionnav.this,"Message will be sent.Drive Safe",Toast.LENGTH_SHORT).show();
                        sendsms(s);

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                    }
                });
                builder.show();


            }
        });
        if(IncomingSms.lat!=null && IncomingSms.lon!=null)
        {
            Toast.makeText(optionnav.this,"check",Toast.LENGTH_LONG).show();
            new Asyncmaintable().execute();
        }
        if (isNetworkAvailable(optionnav.this)) {

        } else {
            final AlertDialog.Builder myAlert = new AlertDialog.Builder(this);
            myAlert.setMessage("Internet Connection not enabled");
            myAlert.create();
            myAlert.show();
            // code
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        bottomBar=BottomBar.attach(this,savedInstanceState);
        bottomBar.setItemsFromMenu(R.menu.menu_main, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                if(menuItemId==R.id.Bottombarone)
                {
                   primarylayout f=new primarylayout();
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_optionnav,f).commit();
                }
                if(menuItemId==R.id.Bottombartwo)
                {
                   second p=new second();
                    getFragmentManager().beginTransaction().replace(R.id.content_optionnav,p).commit();
                }
                if(menuItemId==R.id.Bottombarthree)
                {
                   ReportActivity ph=new ReportActivity();
                    getFragmentManager().beginTransaction().replace(R.id.content_optionnav,ph).commit();
                }
                if(menuItemId==R.id.Bottombarfour)
                {
                    Instructions mail=new Instructions();
                    getFragmentManager().beginTransaction().replace(R.id.content_optionnav,mail).commit();
                }
                if(menuItemId==R.id.Bottombarfive)
                {
                   lastfrag mail=new lastfrag();
                    getFragmentManager().beginTransaction().replace(R.id.content_optionnav,mail).commit();
                }
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {

            }
        });
        bottomBar.mapColorForTab(0,"#F44336");
        bottomBar.mapColorForTab(1,"#9C27B0");
        bottomBar.mapColorForTab(2,"#03A9F4");
        bottomBar.mapColorForTab(3,"#009688");
        bottomBar.mapColorForTab(4,"#FF4081");

        BottomBarBadge unread;
        unread=bottomBar.makeBadgeForTabAt(3,"#FF0000",13);
        unread.show();



    }
    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.optionnav, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.About:
                Toast.makeText(this,"About",Toast.LENGTH_SHORT).show();
                Intent newintent = new Intent(this, developers.class);
                startActivity(newintent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {//config
            Toast.makeText(optionnav.this,"Configure MCAS",Toast.LENGTH_SHORT).show();
            Intent newintent = new Intent(optionnav.this, second.class);
            startActivity(newintent);
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {//acczone
            Toast.makeText(optionnav.this,"Accident Zones",Toast.LENGTH_SHORT).show();
            Intent newintent=new Intent(optionnav.this,Getmarker.class );
            startActivity(newintent);

        } else if (id == R.id.nav_slideshow) {//analytics
            Toast.makeText(optionnav.this,"Data Analytics",Toast.LENGTH_SHORT).show();
            Intent newintent=new Intent(optionnav.this,Datachooser.class);
            startActivity(newintent);

        } else if (id == R.id.nav_manage) {//24*7
            Toast.makeText(optionnav.this,"Roadside Assistance",Toast.LENGTH_SHORT).show();
            Intent newintent=new Intent(optionnav.this,roadside.class);
            startActivity(newintent);

        } else if (id == R.id.nav_share) {//report
            Toast.makeText(optionnav.this,"Write a Report",Toast.LENGTH_SHORT).show();
            Intent newintent=new Intent(optionnav.this,ReportActivitycl.class );
            startActivity(newintent);

        } else if (id == R.id.nav_send) {//about
            Toast.makeText(optionnav.this,"About MCAS",Toast.LENGTH_SHORT).show();
            Intent newintent = new Intent(optionnav.this, developers.class);

            startActivity(newintent);


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void sendsms(String s)
    {
        String number="+919941055412";
        String message=s+" I am in a Conscious and Manageable state no need to panic";
        SmsManager manager=SmsManager.getDefault();
        manager.sendTextMessage(number,null,message,null,null);
        Toast.makeText(optionnav.this,"message has been sent",Toast.LENGTH_LONG).show();

    }
    public class Asyncmaintable extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... params) {
            OkHttpClient client = new OkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\n    \"type\":\"insert\",\n    \"args\":{\n        \"table\":\"maintable\",\n        \"objects\":[\n            {\"regno\":\"TN10R7753\",\n            \"lat\":\""+IncomingSms.lat+"\",\n            \"lon\":\""+IncomingSms.lon+"\",\n            \"status\":\"unattended\",\n            \"type\":\"ambulance\",\n            \"details\":\"TVS Victor blue\"\n            }\n        ]\n    }    \n}    \n    \n");
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
}
