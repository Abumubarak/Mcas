package rexzen.maps;

/**
 * Created by harishananth on 27/11/16.
 */

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
public class Chat_Report extends AppCompatActivity  {
    private EditText input_msg;
    private ImageView button_send_msg;
    private TextView chat_conversation;
    private String user_name="report",room_name;
    private DatabaseReference root;
    private String temp_key;




    @Override
    protected void onCreate(@Nullable Bundle savedInstancestate)
    {
        super.onCreate(savedInstancestate);
        setContentView(R.layout.chat_room);
        button_send_msg=(ImageView)findViewById(R.id.send);
        input_msg=(EditText)findViewById(R.id.msg);
        chat_conversation=(TextView)findViewById(R.id.chat);

        Bundle bundle= getIntent().getExtras();
        if (bundle!= null) {

           // user_name = getIntent().getExtras().get("user_name").toString();
            room_name = getIntent().getExtras().get("room_name").toString();
        }
        root = FirebaseDatabase.getInstance().getReference().child(room_name);

        button_send_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> map= new HashMap<String, Object>();
                temp_key = root.push().getKey();
                root.updateChildren(map);
                DatabaseReference message_root= root.child(temp_key);
                Map<String,Object> map2=new HashMap<String, Object>();
                map2.put("name","user "+user_name);
                map2.put("msg","chat "+input_msg.getText().toString());

                message_root.updateChildren(map2);


            }
        });
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
    private String chat_msg,chat_user_name;

    private void append_chat_conversation(DataSnapshot dataSnapshot)
    {
        Iterator i=dataSnapshot.getChildren().iterator();
        while(i.hasNext())
        {
            chat_msg= (String) ((DataSnapshot)i.next()).getValue();
            chat_user_name= (String) ((DataSnapshot)i.next()).getValue();

          /* if(chat_msg.contains("chat ") && chat_user_name.contains("user ")) {
               String chat=chat_msg.replaceAll("chat","");
               String username=user_name.replaceAll("user","");*/
               chat_conversation.append(chat_user_name + " : " + chat_msg + "\n");
           }

    }
}
