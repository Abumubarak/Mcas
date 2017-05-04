package rexzen.maps;

/**
 * Created by harishananth on 02/01/17.
 */

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;


import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class seconcl extends AppCompatActivity {

    public ImageView doneb;
    EditText id,reg,details;
    public static String ids="+919176767255",regs="TN10AW8201",detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        id=(EditText)findViewById(R.id.editText);
        reg=(EditText)findViewById(R.id.editText2);
        details=(EditText)findViewById(R.id.detailsbox);
detail=details.getText().toString();

        doneb=(ImageView)findViewById(R.id.done);
        doneb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ids=id.getText().toString();
                regs=reg.getText().toString();

                SharedPreferences sharedPref= getSharedPreferences("mypref", 0);
                SharedPreferences.Editor editor= sharedPref.edit();
                editor.putString("id",ids);
                editor.putString("reg",regs);
                editor.commit();
new Asyncreg().execute();
                Intent newint=new Intent(seconcl.this,optionnav.class);
                startActivity(newint);


            }
        });
    }
    public class Asyncreg extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... params) {
            OkHttpClient client = new OkHttpClient();

            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\n    \"type\":\"insert\",\n    \"args\":{\n        \"table\":\"Registration\",\n        \"objects\":[\n            {\"Reg_No\":\""+regs+"\",\n            \"Device_ID\":\""+ids+"\"}\n        ]\n        \n    }\n}");
            Request request = new Request.Builder()
                    .url("https://data.erratic39.hasura-app.io/v1/query")
                    .post(body)
                    .addHeader("authorization", "Bearer p1c5ut3lfoishbel66ns5fwsf40ez3yt")
                    .addHeader("content-type", "application/json")
                    .addHeader("cache-control", "no-cache")
                    .addHeader("postman-token", "b56e32bb-100c-112d-da47-beff5b2cb542")
                    .build();

            try {
                Response response = client.newCall(request).execute();
                String s=response.body().string();
                Log.e("f",s);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
