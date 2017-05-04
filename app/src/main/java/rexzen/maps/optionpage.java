package rexzen.maps;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by harishananth on 26/11/16.
 */

public class optionpage extends AppCompatActivity implements AdapterView.OnItemClickListener {
    public static final String[] titles = new String[] { "Configure","Map","Report a zone","Accident Prone Areas","Data Analytics","Assistance"};

    public static final Integer[] images = {
            R.drawable.configure, R.drawable.maps,R.drawable.report,R.drawable.areas,R.drawable.chartpie,R.drawable.tow
             };




    ListView listView;
    List<rowitem> rowItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.backlist);
        setTitle("MCAS");
        Toast.makeText(this,"First time users go to configure",Toast.LENGTH_LONG).show();
        rowItems = new ArrayList<rowitem>();
        for (int i = 0; i < titles.length; i++) {
            rowitem item = new rowitem(images[i], titles[i]);
            rowItems.add(item);
        }

        listView = (ListView) findViewById(R.id.List);
        CustomListViewAdapter adapter = new CustomListViewAdapter(this,
                R.layout.list_item, rowItems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder= new AlertDialog.Builder(optionpage.this);
                builder.setTitle("Include any message below");
                final EditText input_field=new EditText(optionpage.this);
                input_field.setBackgroundColor(Color.parseColor("#ffe8f8"));
                input_field.setCursorVisible(true);
                builder.setView(input_field);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String s=input_field.getText().toString();
                      Toast.makeText(optionpage.this,"Message will be sent.Drive Safe",Toast.LENGTH_SHORT).show();
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
        if (isNetworkAvailable(optionpage.this)) {

        } else {
            final AlertDialog.Builder myAlert = new AlertDialog.Builder(this);
            myAlert.setMessage("Internet Connection not enabled");
            myAlert.create();
            myAlert.show();
            // code
        }



    }
    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
    @Override
    public void onBackPressed()
    {
        // code here to show dialog
        super.onBackPressed();  // optional depending on your needs
        Intent newint=new Intent(this,MainActivity.class);
        startActivity(newint);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuinfalter=getMenuInflater();
        menuinfalter.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
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


    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        switch(position)
        {
            case 0: {//about
                Toast.makeText(this,"About MCAS",Toast.LENGTH_SHORT).show();
                Intent newintent = new Intent(this, developers.class);

               startActivity(newintent);
                break;
            }
            case 1: {
                //configure
                Toast.makeText(this,"Configure MCAS",Toast.LENGTH_SHORT).show();
                Intent newintent = new Intent(this, second.class);
                startActivity(newintent);
                break;
            }
            case 2:
                {   //map
                    Toast.makeText(this,"MCAS Map",Toast.LENGTH_SHORT).show();

                Intent newintent=new Intent(this,MapsActivity.class );
                               startActivity(newintent);
                break;
            }
            case 3:
            {  ///report
                Toast.makeText(this,"Write a Report",Toast.LENGTH_SHORT).show();
                Intent newintent=new Intent(this,ReportActivity.class );

                startActivity(newintent);
                break;

            }
            case 4:
            {    //zones
                Toast.makeText(this,"Accident Zones",Toast.LENGTH_SHORT).show();
                Intent newintent=new Intent(this,Getmarker.class );
                startActivity(newintent);
                break;

            }
            case 5:
            {    //zones
                Toast.makeText(this,"Data Analytics",Toast.LENGTH_SHORT).show();
                Intent newintent=new Intent(this,Datachooser.class);
                startActivity(newintent);
                break;

            }
            case 6:
            {    //zones
                Toast.makeText(this,"Roadside Assistance",Toast.LENGTH_SHORT).show();
                Intent newintent=new Intent(this,roadside.class);
                startActivity(newintent);
                break;

            }


        }




    }
    private void sendsms(String s)
    {
        String number="+919941055412";
        String message=s+" I am in a Conscious and Manageable state no need to panic";
        SmsManager manager=SmsManager.getDefault();
        manager.sendTextMessage(number,null,message,null,null);
        Toast.makeText(optionpage.this,"message has been sent",Toast.LENGTH_LONG).show();

    }

}
