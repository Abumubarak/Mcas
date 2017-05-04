package rexzen.maps;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by harishananth on 26/11/16.
 */

public class Datachooser extends Activity {
    ListView listView;

    String[] days={"City Wise Analytics","Weekly Analytics","State Wise Analytics","Monthly Analytics","Road Wise Analytics"};

    Integer r,x,y,z,t;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reportlist);

        listView = (ListView) findViewById(R.id.listView);


   // Toast.makeText(this,"Module will be updated shortly.Sorry for the inconvenience as this is a beta version",Toast.LENGTH_LONG).show();

        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,new ArrayList<String>()));
        new Async().execute();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position) {
                    case 0: {//citywise
                        Toast.makeText(Datachooser.this, "City Wise Analytics", Toast.LENGTH_SHORT).show();
                        Intent newint = new Intent(Datachooser.this,Analyticsload.class);
                        newint.putExtra("room_name","Chennai");
                        startActivity(newint);
                        break;
                    }
                    case 1: { //weekly
                        Toast.makeText(Datachooser.this,"Module will be updated shortly.Sorry for the inconvenience as this is a beta version",Toast.LENGTH_LONG).show();
                        break;
                    }
                    case 2: { //m
                        Toast.makeText(Datachooser.this,"Module will be updated shortly.Sorry for the inconvenience as this is a beta version",Toast.LENGTH_LONG).show();
                        break;
                    }
                    case 3: { //kolkata
                        Toast.makeText(Datachooser.this,"Module will be updated shortly.Sorry for the inconvenience as this is a beta version",Toast.LENGTH_LONG).show();
                        break;
                    }
                    case 4: { //roadwise
                        Intent newint=new Intent(Datachooser.this,Roadwise.class);
                        startActivity(newint);
                        break;
                    }
                    case 5: { //roadwise
                        Intent newint=new Intent(Datachooser.this,Roadwise.class);
                        startActivity(newint);
                        break;
                    }


                }

            }
        });


    }

    public class Async extends AsyncTask<Void ,String,String>
    {

        ArrayAdapter<String> arrayAdapter;
        int count;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            arrayAdapter= (ArrayAdapter<String>) listView.getAdapter();

            count=0;

        }

        @Override
        protected String doInBackground(Void... voids) {

            for(String value:days)
            {
                publishProgress(value);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            return "Added successfully";
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);

            arrayAdapter.add(values[0]);
            count++;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

        }
    }


}
