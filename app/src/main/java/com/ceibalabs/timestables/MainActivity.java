package com.ceibalabs.timestables;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SeekBar timesTableSeekBar;
    ListView timesTableListView;
    ArrayAdapter<String> arrayAdapter;
    int initialValue = 10;
    int maxValue = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.timesTableSeekBar = (SeekBar) findViewById(R.id.timesTableSeekBar);
        this.timesTableListView = (ListView) findViewById(R.id.timesTableListView);

        timesTableSeekBar.setMax(this.maxValue);
        //timesTableSeekBar.setMin(1);
        timesTableSeekBar.setProgress(this.initialValue);

        timesTableSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int min = 1;
                int timesTable;

                //set min in progress if you are using an android version lower than 26
                if(progress < min){
                    timesTable = min;
                    timesTableSeekBar.setProgress(min);
                } else {
                    timesTable = progress;
                }

                Log.i("Seekbar value", Integer.toString(timesTable));
                generateTimesTable(timesTable);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        this.generateTimesTable(this.initialValue);
    }

    public void generateTimesTable(int timesTable){
        ArrayList<String> timesTableContent = new ArrayList<String>();
        for(int i=1; i<=10; i++){
            int result = i*timesTable;
            timesTableContent.add(Integer.toString(result));
        }
        this.arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, timesTableContent);
        this.timesTableListView.setAdapter(arrayAdapter);
    }
}
