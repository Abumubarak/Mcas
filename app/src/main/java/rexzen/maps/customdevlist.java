package rexzen.maps;

/**
 * Created by harishananth on 29/11/16.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by harishananth on 11/10/16.
 */

public class customdevlist extends ArrayAdapter<ourlist> {

    Context context;

    public customdevlist(Context context, int resourceId,
                                      List<ourlist> items) {
        super(context, resourceId, items);
        this.context = context;
    }

    /*private view holder class*/
    private class ViewHolder {
        TextView name;
        TextView reg;
        TextView dept;
        TextView clgname;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        ourlist ourlist = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.customlist, null);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.devname);
            holder.reg=(TextView)convertView.findViewById(R.id.reg);
            holder.dept=(TextView)convertView.findViewById(R.id.dept);
            holder.clgname=(TextView)convertView.findViewById(R.id.clg);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.name.setText(ourlist.getname());
        holder.reg.setText(ourlist.getsubone());
        holder.dept.setText(ourlist.getsubtwo());
        holder.clgname.setText(ourlist.getsubthree());

        return convertView;
    }
}

