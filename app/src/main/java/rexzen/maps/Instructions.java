package rexzen.maps;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by harishananth on 01/01/17.
 */
public class Instructions extends Fragment
{
    TextView title,desc;
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.instructions, null);
    title=(TextView)view.findViewById(R.id.title);
    desc=(TextView)view.findViewById(R.id.desc);
    title.setText("Tcpride");
    desc.setText("Tcpride is an IoT enabled \n system developed for motorcyclists\n to ensure their overall\n safety by providing timely \n help whenever required");
    return view;
}
}