package com.example.drashya;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.example.drashya.database.AppDatabase;
import com.example.drashya.database.DataPoint;
import com.example.drashya.utils.JsonUtils;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lineChart = findViewById(R.id.lineChart);
        loadDataAndDisplayChart();
    }

    private void loadDataAndDisplayChart() {
        List<DataPoint> dataPoints = AppDatabase.getInstance(this).dataPointDao().getAll();
        ArrayList<Entry> entries = new ArrayList<>();

        for (DataPoint dataPoint : dataPoints) {
            entries.add(new Entry(dataPoint.getX(), dataPoint.getY()));
        }

        LineDataSet dataSet = new LineDataSet(entries, "Sample Data");
        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);
        lineChart.invalidate();
    }
}
