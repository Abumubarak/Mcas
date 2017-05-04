package rexzen.maps;

/**
 * Created by harishananth on 25/11/16.
 */

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class firebaseact extends AppCompatActivity {

    MediaPlayer mp;
    private String name="MCAS";

    private DatabaseReference root= FirebaseDatabase.getInstance().getReference().getRoot();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mp=MediaPlayer.create(firebaseact.this,R.raw.alert);
        mp.start();


       /* add_room =(ImageView)findViewById(R.id.create);
        room_name=(EditText)findViewById(R.id.editText);
        lv=(ListView)findViewById(R.id.listView);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list_of_rooms);
        lv.setAdapter(arrayAdapter);*/


                Map<String,Object> map= new HashMap<String,Object>();
        map.put(name,"");
                root.updateChildren(map);


        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Set<String> set = new HashSet<String>();

                Iterator i =dataSnapshot.getChildren().iterator();
                while(i.hasNext())
                {
                    set.add(((DataSnapshot)i.next()).getKey());
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
    });



        Handler mHandler = new Handler();
            mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {

                Intent intent= new Intent(getApplicationContext(),Chat_Room.class);
                startActivity(intent);
            }

        }, 1000);
    }



}
