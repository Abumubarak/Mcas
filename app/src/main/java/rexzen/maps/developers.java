package rexzen.maps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class developers extends AppCompatActivity {
    public static final String[] name = new String[] { "Harish Ananth",
            "Abdullah M", "Sandeep Kumar"};
    public static final String[] reg = new String[] { "SVCE",
            "SVCE", "SVCE"};
    public static final String[] dept  = new String[] { "IT DEPARTMENT",
            "IT DEPARTMENT", "ECE DEPARTMENT"};
    public static final String[] clg = new String[] { "SRI VENKATESWARA COLLEGE OF ENGINEERING",
            "SRI VENKATESWARA COLLEGE OF ENGINEERING", "SRI VENKATESWARA COLLEGE OF ENGINEERING"};

    ListView listView;
    List<ourlist> ourlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developers);
        ourlist=new ArrayList<ourlist>();
        for(int i=0;i<name.length;i++)
        {
            ourlist item=new ourlist(name[i],reg[i],dept[i],clg[i]);
            ourlist.add(item);
        }
        listView=(ListView)findViewById(R.id.devlist);
       customdevlist adapter=new customdevlist(this,R.layout.customlist,ourlist);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0:
                        Toast.makeText(developers.this,"Have a Great Day",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(developers.this,"Have a Great Day",Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(developers.this,"Have a Great Day",Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(developers.this,"Have a Great Day",Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(developers.this,"Have a Great Day",Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        Toast.makeText(developers.this,"Have a Great Day",Toast.LENGTH_SHORT).show();
                        break;
                    case 6:
                        Toast.makeText(developers.this,"Have a Great Day",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
}
