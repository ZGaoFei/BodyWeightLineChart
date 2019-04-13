package com.example.zhaogaofei.bodyweightlinechart;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.zhaogaofei.bodyweightlinechart.activity.AddWeightActivity;
import com.example.zhaogaofei.bodyweightlinechart.activity.ShowWeightActivity;

public class MainActivity extends AppCompatActivity {
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        initView();
    }

    private void initView() {
        findViewById(R.id.bt_add_weight).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddWeightActivity.start(mContext);
            }
        });

        findViewById(R.id.bt_show_weight).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowWeightActivity.start(mContext);
            }
        });
    }
}
