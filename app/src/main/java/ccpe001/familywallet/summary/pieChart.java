package ccpe001.familywallet.summary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import ccpe001.familywallet.R;

public class pieChart extends AppCompatActivity {

    float transaction[]={150.0f,235.50f,187.50f};
    String category[]={"food","fuel","other"};
    int i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        setupPieChart();
    }

    private void setupPieChart(){
        List<PieEntry> pieEntries=new ArrayList<>();

        for( i = 0; i < transaction.length; i++){}
        pieEntries.add(new PieEntry(transaction[i], category[i]));

        PieDataSet dataSet = new PieDataSet(pieEntries,"Transaction of Categories");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data=new PieData(dataSet);

        PieChart chart = (PieChart) findViewById(R.id.chart);
        chart.setData(data);
        chart.invalidate();

    }
}
