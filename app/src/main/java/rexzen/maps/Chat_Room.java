package rexzen.maps;

/**
 * Created by harishananth on 25/11/16.
 */

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
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

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by harishananth on 31/08/16.
 */
public class Chat_Room extends AppCompatActivity  {
    private EditText input_msg;
    private ImageView button_send_msg;
    private TextView chat_conversation;
    private String user_name,room_name;
    private DatabaseReference root,root2;
    private String temp_key;
    final Calendar c = Calendar.getInstance();
    int Year = c.get(Calendar.YEAR);
    int Month = c.get(Calendar.MONTH) + 1;
    int day = c.get(Calendar.DAY_OF_MONTH);
    Integer x=1;




    @Override
    protected void onCreate(@Nullable Bundle savedInstancestate)
    {
        super.onCreate(savedInstancestate);

        SharedPreferences sharedPref= getSharedPreferences("mypref", 0);
        user_name=sharedPref.getString("id","no id");
        room_name=sharedPref.getString("reg","no reg");

        //for chennai

        if(Double.parseDouble(IncomingSms.lat)>12.000 && Double.parseDouble(IncomingSms.lat)<14.000 && Double.parseDouble(IncomingSms.lon)>78.000 && Double.parseDouble(IncomingSms.lon)<82.000 )
        {
            x=1;
        }
        //for delhi
        else if(Double.parseDouble(IncomingSms.lat)>26.000 && Double.parseDouble(IncomingSms.lat)<28.000 && Double.parseDouble(IncomingSms.lon)>76.000 && Double.parseDouble(IncomingSms.lon)<78.000 )
        {
            x=2;
        }
        //for kolkata
        else if(Double.parseDouble(IncomingSms.lat)>21 && Double.parseDouble(IncomingSms.lat)<23 && Double.parseDouble(IncomingSms.lon)>87 && Double.parseDouble(IncomingSms.lon)<89 )
        {
            x=3;
        }
        //for Banglore
        else if(Double.parseDouble(IncomingSms.lat)>12 && Double.parseDouble(IncomingSms.lat)<14 && Double.parseDouble(IncomingSms.lon)>75 && Double.parseDouble(IncomingSms.lon)<77 )
        {
            x=4;
        }
        //for mumbai
        else if(Double.parseDouble(IncomingSms.lat)>12 && Double.parseDouble(IncomingSms.lat)<20 && Double.parseDouble(IncomingSms.lon)>70 && Double.parseDouble(IncomingSms.lon)<73 )
        {
            x=5;
        }
        else
        {
            Toast.makeText(Chat_Room.this,"The latitude longitude doesnt belong to any city",Toast.LENGTH_LONG).show();
        }

        //chennai lat 12-14,lon 78 to 82
        //delhi lat 26-28
        switch(x)
        {
            case 1:
            {
                root = FirebaseDatabase.getInstance().getReference().child("Chennai");
                Map<String,Object> map= new HashMap<String, Object>();
                temp_key = root.push().getKey();
                root.updateChildren(map);
                DatabaseReference message_root= root.child(temp_key);
                Map<String,Object> map2=new HashMap<String, Object>();
                map2.put("latitude","lat"+IncomingSms.lat);
                map2.put("longitude","lon"+IncomingSms.lon);
                message_root.updateChildren(map2);
                break;
            }
            case 2:
            {
                root = FirebaseDatabase.getInstance().getReference().child("Delhi");
                Map<String,Object> map= new HashMap<String, Object>();
                temp_key = root.push().getKey();
                root.updateChildren(map);
                DatabaseReference message_root= root.child(temp_key);
                Map<String,Object> map2=new HashMap<String, Object>();
                map2.put("latitude","lat"+IncomingSms.lat);
                map2.put("longitude","lon"+IncomingSms.lon);
                message_root.updateChildren(map2);
                break;
            }
            case 3:
            {
                root = FirebaseDatabase.getInstance().getReference().child("Kolkata");
                Map<String,Object> map= new HashMap<String, Object>();
                temp_key = root.push().getKey();
                root.updateChildren(map);
                DatabaseReference message_root= root.child(temp_key);
                Map<String,Object> map2=new HashMap<String, Object>();
                map2.put("latitude","lat"+IncomingSms.lat);
                map2.put("longitude","lon"+IncomingSms.lon);
                message_root.updateChildren(map2);
                break;
            }
            case 4:
            {
                root = FirebaseDatabase.getInstance().getReference().child("Banglore");
                Map<String,Object> map= new HashMap<String, Object>();
                temp_key = root.push().getKey();
                root.updateChildren(map);
                DatabaseReference message_root= root.child(temp_key);
                Map<String,Object> map2=new HashMap<String, Object>();
                map2.put("latitude","lat"+IncomingSms.lat);
                map2.put("longitude","lon"+IncomingSms.lon);
                message_root.updateChildren(map2);
                break;
            }
            case 5:
            {
                root = FirebaseDatabase.getInstance().getReference().child("Mumbai");
                Map<String,Object> map= new HashMap<String, Object>();
                temp_key = root.push().getKey();
                root.updateChildren(map);
                DatabaseReference message_root= root.child(temp_key);
                Map<String,Object> map2=new HashMap<String, Object>();
                map2.put("latitude","lat"+IncomingSms.lat);
                map2.put("longitude","lon"+IncomingSms.lon);
                message_root.updateChildren(map2);
                break;
            }
        }

        root2 = FirebaseDatabase.getInstance().getReference().child("All locations");
        Map<String,Object> map3= new HashMap<String, Object>();
        temp_key = root2.push().getKey();
        root2.updateChildren(map3);
        DatabaseReference message_root2= root2.child(temp_key);
        Map<String,Object> map4=new HashMap<String, Object>();
        map4.put("latitude","lat"+IncomingSms.lat);
        map4.put("longitude","lon"+IncomingSms.lon);
        message_root2.updateChildren(map4);





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
              Intent intent= new Intent(getApplicationContext(),Tweetcl.class);
        startActivity(intent);


    }
    private String chat_msg,chat_user_name;

    private void append_chat_conversation(DataSnapshot dataSnapshot)
    {
        Iterator i=dataSnapshot.getChildren().iterator();
        while(i.hasNext())
        {
            chat_msg= (String) ((DataSnapshot)i.next()).getValue();
            chat_user_name= (String) ((DataSnapshot)i.next()).getValue();

            //chat_conversation.append(chat_user_name +" : "+chat_msg + "\n");
        }
    }





}

