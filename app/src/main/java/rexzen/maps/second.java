package rexzen.maps;

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

public class second extends Fragment {

    public ImageView doneb;
    EditText id,reg;
    public String ids="+919176767255",regs="TN10AW8201";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.activity_second,null);
        id=(EditText)view.findViewById(R.id.editText);
        reg=(EditText)view.findViewById(R.id.editText2);


        doneb=(ImageView) view.findViewById(R.id.done);
        doneb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ids=id.getText().toString();
                regs=reg.getText().toString();

                SharedPreferences sharedPref= getActivity().getSharedPreferences("mypref", 0);
                SharedPreferences.Editor editor= sharedPref.edit();
                editor.putString("id",ids);
                editor.putString("reg",regs);
                editor.commit();

                Intent newint=new Intent(getActivity(),optionnav.class);
                startActivity(newint);

            }
        });
        return view;
    }

}
