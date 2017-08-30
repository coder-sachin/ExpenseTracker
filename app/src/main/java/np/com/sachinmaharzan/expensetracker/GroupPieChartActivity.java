package np.com.sachinmaharzan.expensetracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class GroupPieChartActivity extends AppCompatActivity {

    PieChart pieChart;
    private static String TAG="PIe  chart ";
    DatabaseHelper databaseHelper;
    float[] yData;
    String[] xData;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_pie_chart);
        id=getIntent().getIntExtra("g_id",0);
        Log.i(TAG, "id in piechart: "+id);
        pieChart= (PieChart) findViewById(R.id.piechart);


        //pieChart.setRotation(true);

        pieChart.setHoleRadius(50f);
        pieChart.setTransparentCircleAlpha(100);
        pieChart.setTransparentCircleRadius(58f);
        pieChart.setCenterText("Expenses");
        pieChart.setCenterTextSize(20);
        //pieChart.setUsePercentValues(true);


        addDataSet();

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {



            }

            @Override
            public void onNothingSelected() {

            }
        });
    }

    private void addDataSet() {

        databaseHelper=new DatabaseHelper(this);
        int a=databaseHelper.getGexpenseList(id).size();
        ArrayList<Gexpense> list=new ArrayList<Gexpense>();
        list = databaseHelper.getGexpenseList(id);

        yData= new float[a];
        xData=new String[a];

        for (int i=0;i< a;i++){
            Gexpense e;
            e=list.get(i);
            yData[i]=e.gexpense_amt;
            xData[i]=e.gexpense_desc;

        }
        ArrayList<PieEntry> yEntrys= new ArrayList<>();
        ArrayList<String> xEntrys=new ArrayList<>();

        for(int i=0;i< yData.length;i++){

            yEntrys.add(new PieEntry(yData[i],xData[i]));
        }
        /*

        for(int i=0;i<xData.length;i++){
            xEntrys.add(xData[i]);

        }
        */

        PieDataSet pieDataSet= new PieDataSet(yEntrys,"");
        pieDataSet.setSliceSpace(4);
        pieDataSet.setValueTextSize(12);

/*
        ArrayList<Integer> colors=new ArrayList<>();
        colors.add(Color.GRAY);
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.YELLOW);
        colors.add(Color.MAGENTA);
        */

        //  pieDataSet.setColors(colors);

        /// Legend legend=pieChart.getLegend();
        //legend.setForm(Legend.LegendForm.CIRCLE);

        PieData pieData=new PieData(pieDataSet);
        // pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setColors(ColorTemplate.PASTEL_COLORS);
        pieChart.setData(pieData);
        pieChart.animateXY(3000,2000);
        pieChart.invalidate();


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        addDataSet();
    }

    @Override
    protected void onResume() {
        super.onResume();
        addDataSet();
    }
}
