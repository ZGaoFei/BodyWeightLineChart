package com.example.zhaogaofei.bodyweightlinechart.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zhaogaofei.bodyweightlinechart.R;

public class ShowWeightActivity extends AppCompatActivity {

    public static void start(Context context) {
        context.startActivity(new Intent(context, ShowWeightActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_weight);
    }
}
