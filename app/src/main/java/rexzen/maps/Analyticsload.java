package rexzen.maps;

/**
 * Created by harishananth on 28/11/16.
 */

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by harishananth on 31/08/16.
 */
public class Analyticsload extends AppCompatActivity {

    private String user_name, room_name;
    private DatabaseReference root;
    private String temp_key;
    public Integer i,x;
    public String lat,lon;

    public Integer bc=0, dc=0, cc=0, mc=0, kc=0, total=0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstancestate) {
        super.onCreate(savedInstancestate);
        setContentView(R.layout.loader);
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {

                intentmethod();

            }

        }, 10000);
        Toast.makeText(Analyticsload.this, "Acquiring from Cloud Server please wait for 10 seconds", Toast.LENGTH_LONG).show();


        for (i = 1; i < 6; i++) {
            if (i == 1) {
//Toast.makeText(Analyticsload.this,"Chennai loop",Toast.LENGTH_SHORT).show();
                root = FirebaseDatabase.getInstance().getReference().child("Chennai");

            }
            else if (i == 2) {
               // Toast.makeText(Analyticsload.this,"Delhi loop",Toast.LENGTH_SHORT).show();

                root = FirebaseDatabase.getInstance().getReference().child("Delhi");

            }
           else if (i == 3) {
               // Toast.makeText(Analyticsload.this,"Kolkata loop",Toast.LENGTH_SHORT).show();
                root = FirebaseDatabase.getInstance().getReference().child("Kolkata");

            }
            else if (i == 4) {
               // Toast.makeText(Analyticsload.this,"Banglore loop",Toast.LENGTH_SHORT).show();
                root = FirebaseDatabase.getInstance().getReference().child("Banglore");

            }
           else if (i == 5) {
               // Toast.makeText(Analyticsload.this,"Mumbai loop",Toast.LENGTH_SHORT).show();
                root = FirebaseDatabase.getInstance().getReference().child("Mumbai");

            }
           // root = FirebaseDatabase.getInstance().getReference().child("Chennai");

            root.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                   // Toast.makeText(Analyticsload.this, "calledonchildadded", Toast.LENGTH_LONG).show();
                    append_chat_conversation(dataSnapshot);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    //Toast.makeText(Analyticsload.this, "calledonchildchanged", Toast.LENGTH_LONG).show();
                    append_chat_conversation(dataSnapshot);
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }


    }


    //


    private void append_chat_conversation(DataSnapshot dataSnapshot) {

        Iterator i = dataSnapshot.getChildren().iterator();
        while (i.hasNext()) {

            lat= (String) ((DataSnapshot)i.next()).getValue();
            lon= (String) ((DataSnapshot)i.next()).getValue();
            if(lat.contains("lat") && lon.contains("lon"))
            {
                if(Double.parseDouble(lat.replaceAll("lat",""))>12 && Double.parseDouble(lat.replaceAll("lat",""))<14 && Double.parseDouble(lon.replaceAll("lon",""))>78 && Double.parseDouble(lon.replaceAll("lon",""))<82 )
                {
                   // Toast.makeText(Analyticsload.this,"inside block chennai "+cc,Toast.LENGTH_SHORT).show();
                    cc++;
                }
                //for delhi
                else if(Double.parseDouble(lat.replaceAll("lat",""))>26 && Double.parseDouble(lat.replaceAll("lat",""))<28 && Double.parseDouble(lon.replaceAll("lon",""))>76 && Double.parseDouble(lon.replaceAll("lon",""))<78 )
                {
                    //Toast.makeText(Analyticsload.this,"inside block delhi "+dc,Toast.LENGTH_SHORT).show();
                    dc++;
                }
                //for kolkata
                else if(Double.parseDouble(lat.replaceAll("lat",""))>21 && Double.parseDouble(lat.replaceAll("lat",""))<23 && Double.parseDouble(lon.replaceAll("lon",""))>87 && Double.parseDouble(lon.replaceAll("lon",""))<89 )
                {
                   // Toast.makeText(Analyticsload.this,"inside block kolkata "+kc,Toast.LENGTH_SHORT).show();
                    kc++;
                }
                //for Banglore
                else if(Double.parseDouble(lat.replaceAll("lat",""))>12 && Double.parseDouble(lat.replaceAll("lat",""))<14 && Double.parseDouble(lon.replaceAll("lon",""))>75 && Double.parseDouble(lon.replaceAll("lon",""))<77 )
                {
                   // Toast.makeText(Analyticsload.this,"inside block banglore "+bc,Toast.LENGTH_SHORT).show();
                    bc++;
                }
                //for mumbai
                else if(Double.parseDouble(lat.replaceAll("lat",""))>18 && Double.parseDouble(lat.replaceAll("lat",""))<20 && Double.parseDouble(lon.replaceAll("lon",""))>71 && Double.parseDouble(lon.replaceAll("lon",""))<74 )
                {
                   // Toast.makeText(Analyticsload.this,"inside block mumbai "+mc,Toast.LENGTH_SHORT).show();
                    mc++;
                }


            }

        }



    }
    public void intentmethod()
    {

               total=cc+dc+mc+bc+kc;
        Intent newint=new Intent(Analyticsload.this,Analytics.class);


                newint.putExtra("cc",cc);
        newint.putExtra("dc",dc);
        newint.putExtra("kc",kc);
        newint.putExtra("mc",mc);
        newint.putExtra("bc",bc);
        newint.putExtra("total",total);
                startActivity(newint);

            }


}

