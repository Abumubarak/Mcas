package rexzen.maps;

/**
 * Created by harishananth on 27/11/16.
 */

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Highlight;
import com.github.mikephil.charting.utils.PercentFormatter;

import java.util.ArrayList;

import static android.R.attr.duration;

public class Analytics extends AppCompatActivity {

    private RelativeLayout activity_main;
    private PieChart mChart;
    //display pie chart
    Float d=4.3f;
    Integer tot,cx,dx,kx,bx,mx;
    Float cc,dc,mc,kc,bc,total;
    ArrayList<Float> yd=new ArrayList<Float>();
    private String[] xData={"Chennai","Delhi","Mumbai","Kolkata","Banglore"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.analytics);
        Bundle extras=getIntent().getExtras();
        tot= extras.getInt("total");
        total =(float) tot;
        cx= extras.getInt("cc");
        cc=(float) cx;
        cc=(cc/total)*100;
        dx=extras.getInt("dc");
        dc=(float) dx;
        dc=(dc/total)*100;
        kx=extras.getInt("kc");
        kc=(float) kx;
        kc=(kc/total)*100;
        mx=extras.getInt("mc");
        mc=(float) mx;
        mc=(mc/total)*100;
        bx=extras.getInt("bc");
        bc=(float) bx;
        bc=(bc/total)*100;





           //Toast.makeText(this,"total is "+total,Toast.LENGTH_LONG).show();
       // Toast.makeText(this,"cc is "+cc+dc+mc+kc+bc,Toast.LENGTH_SHORT).show();

        yd.add(cc);
        yd.add(dc);
        yd.add(mc);
        yd.add(kc);
        yd.add(bc);



        //adding values to array list


        activity_main = (RelativeLayout) findViewById(R.id.mylayout);
        mChart = new PieChart(this);
        //add pie chart to main acivity
        activity_main.addView(mChart);
        activity_main.setBackgroundColor(Color.WHITE);

        //configure pie chart
        mChart.setUsePercentValues(true);
        mChart.setDescription("Real Time Accident Analytics");

        //enable hole and configure

        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColorTransparent(true);
        mChart.setHoleRadius(7);
        mChart.setTransparentCircleRadius(10);


        //enable rotation of chart by touch

        mChart.setRotationAngle(0);
        mChart.setRotationEnabled(true);


        //set chart value selected listenr
        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                //display msg when value selected
                if (e== null)
                    return;

                Toast.makeText(Analytics.this,xData[e.getXIndex()] +"=" + e.getVal() +"%" ,Toast.LENGTH_SHORT).show();




            }

            @Override
            public void onNothingSelected() {

            }
        });




        //add data
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {

                addData();

            }

        }, 300);

       // addData();

        //customize legends

        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7);
        l.setYEntrySpace(5);

    }


    private void addData(){
        ArrayList<Entry> yVals1=new ArrayList<Entry>();

        for(int i=0; i< 5;i++)
            yVals1.add(new Entry((yd.get(i)), i));


        ArrayList<String> xVals=new ArrayList<String>();

        for(int i=0; i< xData.length;i++)
            xVals.add(xData[i]);

        //create pie data set
        PieDataSet dataSet=new PieDataSet(yVals1,"Legend");
        dataSet.setSliceSpace(3);
        dataSet.setSelectionShift(5);

        //add many colors

        ArrayList<Integer> colors=new ArrayList<Integer>();

        for(int c: ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for(int c:ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for(int c: ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for(int c: ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);

        //initiate pie data objects now
        PieData data=new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.GRAY);

        mChart.setData(data);
        //undo all highlights
        mChart.highlightValues(null);

        //update pie chart
        mChart.invalidate();





    }


}
