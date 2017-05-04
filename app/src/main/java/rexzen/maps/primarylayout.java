package rexzen.maps;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class primarylayout extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.primaryrecy,null);

        List<data> data = fill_with_data();

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        Recycler_View_Adapter adapter = new Recycler_View_Adapter(data, getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(itemAnimator);


        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        switch (position)
                        {
                            case 0:
                                Intent nejintent=new Intent(getActivity(),developers.class);
                                startActivity(nejintent);
                                break;
                            case 1:
                                Intent intent=new Intent(getActivity(),seconcl.class);
                                startActivity(intent);
                                break;
                            case 2:
                                if(IncomingSms.lat!=null) {
                                    Intent nintent = new Intent(getActivity(), MapsActivity.class);
                                    startActivity(nintent);
                                    break;
                                }
                            case 3:
                                Intent neintent=new Intent(getActivity(),ReportActivitycl.class);
                                neintent.putExtra("firsel",5);
                                startActivity(neintent);
                                break;
                            case 4:
                                Intent neointent=new Intent(getActivity(),Getmarker.class);
                                startActivity(neointent);
                                break;
                            case 5:
                                Intent nepintent=new Intent(getActivity(),Datachooser.class);
                                startActivity(nepintent);
                                break;
                            case 6:
                                Intent neqintent=new Intent(getActivity(),roadside.class);
                                startActivity(neqintent);
                                break;

                        }
                        // do whatever
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        switch (position)
                        {
                            case 1:
                                Toast.makeText(getActivity(),"item one long",Toast.LENGTH_SHORT).show();
                                break;
                        }
                        // do whatever
                    }
                })
        );
        return view;

    }
    public List<data> fill_with_data() {

        List<data> data = new ArrayList<>();

       // data.add(new data("Dev", "Details on the developers of Tcpride app", R.drawable.smallmob));
        data.add(new data("Configure App", "First time users must visit this section to configue the phones along with their Tcpride system ", R.drawable.smallset));
        data.add(new data("Alert Map", "Look into this section in case you have recieved an alert from the registered device", R.drawable.smallmap));
        data.add(new data("Reports", "A section where the users of the app can interact amongst themselves and report the various unmotorable areas", R.drawable.smallrepo));
        data.add(new data("Accident prone zones", "This opens up a map showing various markers all over the world.Each of it pertains to a user ", R.drawable.smalldang));
        data.add(new data("Data Analytics", "Data Analytics part of the app where users can view the various aspects of data Analytics ", R.drawable.smallanaly));
        data.add(new data("Roadside Assistance", "Users can make use of the roadside assistance feature in this module", R.drawable.smalltow));
        return data;
    }
    public class Asyncmaintable extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... params) {
            OkHttpClient client = new OkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\n    \"type\":\"insert\",\n    \"args\":{\n        \"table\":\"maintable\",\n        \"objects\":[\n            {\"regno\":\"TN10R7753\",\n            \"lat\":\""+IncomingSms.lat+"\",\n            \"lon\":\""+IncomingSms.lon+"\",\n            \"status\":\"unattended\",\n            \"type\":\"ambulance\",\n            \"details\":\"TVS Victor blue\"\n            }\n        ]\n    }    \n}    \n    \n");
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

        @Override
        protected void onPostExecute(Void aVoid) {
            //Toast.makeText(this,"check",Toast.LENGTH_SHORT).show();
            super.onPostExecute(aVoid);
        }
    }


}