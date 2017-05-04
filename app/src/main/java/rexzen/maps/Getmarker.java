package rexzen.maps;

/**
 * Created by harishananth on 28/11/16.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by harishananth on 31/08/16.
 */
public class Getmarker extends AppCompatActivity  {

    private String user_name,room_name;
    private DatabaseReference root;
    private String temp_key;
    ArrayList<String> latmarkers=new ArrayList<String>();
    ArrayList<String> lonmarkers=new ArrayList<String>();





    @Override
    protected void onCreate(@Nullable Bundle savedInstancestate)
    {
        super.onCreate(savedInstancestate);
        setContentView(R.layout.loader);


        Bundle bundle= getIntent().getExtras();
        if (bundle!= null) {

            user_name = getIntent().getExtras().get("user_name").toString();
            room_name = getIntent().getExtras().get("room_name").toString();
        }
        root = FirebaseDatabase.getInstance().getReference().child("All locations");


        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                append_chat_conversation(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
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
    private String lat,lon;

    private void append_chat_conversation(DataSnapshot dataSnapshot)
    {
        Iterator i=dataSnapshot.getChildren().iterator();
        while(i.hasNext())
        {


            lat= (String) ((DataSnapshot)i.next()).getValue();
            lon= (String) ((DataSnapshot)i.next()).getValue();
            if(lat.contains("lat") && lon.contains("lon"))
            {

                latmarkers.add(lat.replaceAll("lat",""));
                lonmarkers.add(lon.replaceAll("lon",""));

            }

        }
        Intent newint=new Intent(Getmarker.this,Marker.class);
        newint.putStringArrayListExtra("latlist",latmarkers);
        newint.putStringArrayListExtra("lonlist",lonmarkers);
        startActivity(newint);
    }
}

