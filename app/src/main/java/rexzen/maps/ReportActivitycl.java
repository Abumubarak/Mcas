package rexzen.maps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by harishananth on 02/01/17.
 */

public class ReportActivitycl extends AppCompatActivity {
    ListView listView;

    String[] days={"Chennai","Delhi","Mumbai","Kolkata","Banglore"};

    Integer r,x,y,z,t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reportlist);
        listView = (ListView) findViewById(R.id.listView);



        listView.setAdapter(new ArrayAdapter<String>(ReportActivitycl.this,android.R.layout.simple_list_item_1,days));
        // listView.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,new ArrayList<String>()));
        //new Async().execute();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position) {
                    case 0: {//chennai
                        Toast.makeText(ReportActivitycl.this, "Chennai Zone", Toast.LENGTH_SHORT).show();
                        Intent newint = new Intent(ReportActivitycl.this,Chat_Report.class);
                        newint.putExtra("room_name","Chennai Report");
                        startActivity(newint);
                        break;
                    }
                    case 1: { //delhi
                        Toast.makeText(ReportActivitycl.this, "Delhi Zone", Toast.LENGTH_SHORT).show();
                        Intent newint = new Intent(ReportActivitycl.this,Chat_Report.class);
                        newint.putExtra("room_name","Delhi Report");
                        startActivity(newint);
                        break;
                    }
                    case 2: { //mumbai
                        Toast.makeText(ReportActivitycl.this, "Mumbai Zone", Toast.LENGTH_SHORT).show();
                        Intent newint = new Intent(ReportActivitycl.this,Chat_Report.class);
                        newint.putExtra("room_name","Mumbai Report");
                        startActivity(newint);
                        break;
                    }
                    case 3: { //kolkata
                        Toast.makeText(ReportActivitycl.this,"Kolkata Zone", Toast.LENGTH_SHORT).show();
                        Intent newint = new Intent(ReportActivitycl.this,Chat_Report.class);
                        newint.putExtra("room_name","Kolkata Report");
                        startActivity(newint);
                        break;
                    }
                    case 4: { //banglore
                        Toast.makeText(ReportActivitycl.this, "Banglore Zone", Toast.LENGTH_SHORT).show();
                        Intent newint = new Intent(ReportActivitycl.this,Chat_Report.class);
                        newint.putExtra("room_name","Banglore Report");
                        startActivity(newint);
                        break;
                    }


                }

            }
        });
    }
}
