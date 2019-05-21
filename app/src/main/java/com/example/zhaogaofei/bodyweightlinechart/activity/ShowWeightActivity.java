package com.example.zhaogaofei.bodyweightlinechart.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.zhaogaofei.bodyweightlinechart.R;
import com.example.zhaogaofei.bodyweightlinechart.model.WeightModel;
import com.example.zhaogaofei.bodyweightlinechart.utils.SpHelper;
import com.example.zhaogaofei.bodyweightlinechart.view.WeightFormView;

public class ShowWeightActivity extends AppCompatActivity implements View.OnClickListener {
    private WeightFormView weightFormView;
    private TextView tv30;
    private TextView tv60;
    private TextView tv90;
    private TextView tvHalfYear;
    private TextView tvYear;
    private TextView tv100;
    private TextView tv200;

    private List<TextView> list;

    public static void start(Context context) {
        context.startActivity(new Intent(context, ShowWeightActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_weight);

        initView();
        initData();
    }

    private void initView() {
        weightFormView = findViewById(R.id.weight_form_view);

        tv30 = findViewById(R.id.tv_30);
        tv60 = findViewById(R.id.tv_60);
        tv90 = findViewById(R.id.tv_90);
        tvHalfYear = findViewById(R.id.tv_half_year);
        tvYear = findViewById(R.id.tv_year);
        tv30.setOnClickListener(this);
        tv60.setOnClickListener(this);
        tv90.setOnClickListener(this);
        tvHalfYear.setOnClickListener(this);
        tvYear.setOnClickListener(this);

        tv100 = findViewById(R.id.tv_100);
        tv200 = findViewById(R.id.tv_200);
        tv100.setOnClickListener(this);
        tv200.setOnClickListener(this);

    }

    private void initData() {
        List<WeightModel> weightModels = SpHelper.getInstance().getAll();

        weightFormView.setWeightList(weightModels);

        list = new ArrayList<>();
        list.add(tv30);
        list.add(tv60);
        list.add(tv90);
        list.add(tvHalfYear);
        list.add(tvYear);
        updateView(0);

        list.add(tv100);
        list.add(tv200);
        updateView(5);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_30:
                updateView(0);
                weightFormView.setMaxTime(30);
                break;
            case R.id.tv_60:
                updateView(1);
                weightFormView.setMaxTime(60);
                break;
            case R.id.tv_90:
                updateView(2);
                weightFormView.setMaxTime(90);
                break;
            case R.id.tv_half_year:
                updateView(3);
                weightFormView.setMaxTime(180);
                break;
            case R.id.tv_year:
                updateView(4);
                weightFormView.setMaxTime(365);
                break;
            case R.id.tv_100:
                updateView(5);
                weightFormView.setMaxWeight(100);
                break;
            case R.id.tv_200:
                updateView(6);
                weightFormView.setMaxWeight(200);
                break;
        }
    }

    private void updateView(int index) {
        int length;
        int i;
        if (index > 4) {
            i = 5;
            length = 6;
        } else {
            i = 0;
            length = 4;
        }
        for (int j = i; j <= length; j++) {
            TextView textView = list.get(j);
            if (j == index) {
                textView.setBackgroundColor(Color.BLUE);
                textView.setTextColor(Color.WHITE);
            } else {
                textView.setBackgroundColor(Color.WHITE);
                textView.setTextColor(Color.BLACK);
            }
        }
    }
}
