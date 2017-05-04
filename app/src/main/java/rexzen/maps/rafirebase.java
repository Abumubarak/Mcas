package rexzen.maps;

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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by harishananth on 31/08/16.
 */
public class rafirebase extends AppCompatActivity  {

    private DatabaseReference root;
    private String temp_key;
    String lat,lon,details,parent;




    @Override
    protected void onCreate(@Nullable Bundle savedInstancestate)
    {
        super.onCreate(savedInstancestate);
        //setContentView(R.layout.chat_room);


        Bundle bundle= getIntent().getExtras();
        if (bundle!= null) {
            lat= String.valueOf(bundle.getDouble("lat"));
            lon= String.valueOf(bundle.getDouble("lon"));
            details=bundle.getString("details");
            parent=bundle.getString("parent");
        }
        root = FirebaseDatabase.getInstance().getReference().child("roadassist");

                Map<String,Object> map= new HashMap<String, Object>();
                temp_key = root.push().getKey();
                root.updateChildren(map);
                DatabaseReference message_root= root.child(temp_key);
                Map<String,Object> map2=new HashMap<String, Object>();
                map2.put("lat",lat);
                map2.put("lon",lon);
        map2.put("details ",parent+" "+details);
        message_root.updateChildren(map2);

        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
               // append_chat_conversation(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
               // append_chat_conversation(dataSnapshot);
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
        Intent intent=new Intent(rafirebase.this,optionnav.class);
        startActivity(intent);

    }
    private String chat_msg,chat_user_name;

   /* private void append_chat_conversation(DataSnapshot dataSnapshot)
    {
        Iterator i=dataSnapshot.getChildren().iterator();
        while(i.hasNext())
        {
            chat_msg= (String) ((DataSnapshot)i.next()).getValue();
            chat_user_name= (String) ((DataSnapshot)i.next()).getValue();

        }
    }*/
}
