package rexzen.maps;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by harishananth on 02/01/17.
 */
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class testclass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testlayout);
        new Asyncinsert().execute();

    }

    public class Asyncinsert extends AsyncTask<Void, Void, Void> {
        TextView tv;

        @Override
        protected Void doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();

            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\n    \"type\":\"insert\",\n    \"args\":{\n        \"table\":\"maintable\",\n        \"objects\":[\n            {\"regno\":\"TN10R7751\",\n            \"lat\":\"12.97686\",\n            \"lon\":\"80.9765\",\n            \"status\":\"attended\",\n            \"type\":\"roadside\",\n            \"details\":\"TVS 100cc blue\"\n            }\n        ]\n    }    \n}    \n    \n");
            Request request = new Request.Builder()
                    .url("https://data.erratic39.hasura-app.io/v1/query")
                    .post(body)
                    .addHeader("authorization", "Bearer p1c5ut3lfoishbel66ns5fwsf40ez3yt")
                    .addHeader("content-type", "application/json")
                    .addHeader("cache-control", "no-cache")
                    .addHeader("postman-token", "0ad55fa2-8236-b212-4478-22e382769634")
                    .build();

            try {
                Response response = client.newCall(request).execute();
                String s = response.body().string();
                //tv.setText(s);
                Log.e("f", s);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}

