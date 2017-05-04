package rexzen.maps;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by harishananth on 26/11/16.
 */

public class ReportActivity extends Fragment {

    String[] days={"Chennai","Delhi","Mumbai","Kolkata","Banglore"};

    Integer r,x,y,z,t;
    @SuppressLint("NewApi")
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.report,null);

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
                                Intent nejintent=new Intent(getActivity(),Chat_Report.class);
                                nejintent.putExtra("room_name","Chennai Report");
                                startActivity(nejintent);
                                break;
                            case 1:
                                Intent intent=new Intent(getActivity(),Chat_Report.class);
                                intent.putExtra("room_name","Delhi Report");
                                startActivity(intent);
                                break;
                            case 2:
                                Intent nintent=new Intent(getActivity(),Chat_Report.class);
                                nintent.putExtra("room_name","Mumbai Report");
                                startActivity(nintent);
                                break;
                            case 3:
                                Intent neintent=new Intent(getActivity(),Chat_Report.class);
                                neintent.putExtra("room_name","Kolkata Report");
                                startActivity(neintent);
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

        data.add(new data("Chennai", "To view reports for chennai region", R.drawable.chennai));
        data.add(new data("Delhi", "Repors section for Delhi region", R.drawable.delhi));
        data.add(new data("Mumbai", "Reports section for Mumbai Region", R.drawable.kolkata));
        data.add(new data("Kolkata", "Reports section for kolkata region", R.drawable.mumbai));

        return data;
    }




}
