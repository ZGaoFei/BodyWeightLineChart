package com.example.zhaogaofei.bodyweightlinechart.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zhaogaofei.bodyweightlinechart.R;
import com.example.zhaogaofei.bodyweightlinechart.utils.SpHelper;

public class AddWeightActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEtWeight;
    private EditText mEtTime;

    private Button mBtAdd;
    private Button mBtShow;

    private RecyclerView mRecycleView;
    private WeightAdapter mAdapter;

    public static void start(Context context) {
        context.startActivity(new Intent(context, AddWeightActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_weight);

        initView();

        setRecycleView();
    }

    private void initView() {
        mEtWeight = findViewById(R.id.et_weight_value);
        mEtTime = findViewById(R.id.et_time_value);

        mBtAdd = findViewById(R.id.bt_add);
        mBtShow = findViewById(R.id.bt_show);
        mBtAdd.setOnClickListener(this);
        mBtShow.setOnClickListener(this);

        mRecycleView = findViewById(R.id.rl_weight);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_add:
                addWeight();
                break;
            case R.id.bt_show:
                ShowWeightActivity.start(AddWeightActivity.this);
                break;
        }
    }

    private void addWeight() {
        String weight = mEtWeight.getText().toString().trim();
        String time = mEtTime.getText().toString().trim();
        if (TextUtils.isEmpty(weight) || TextUtils.isEmpty(time)) {
            Toast.makeText(this, "体重和时间都需要填写", Toast.LENGTH_SHORT).show();
            return;
        }
        SpHelper.getInstance().commitString(time, weight);
        mAdapter.updateWeights(SpHelper.getInstance().getAll());
    }

    private void setRecycleView() {
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new WeightAdapter(this);
        mRecycleView.setAdapter(mAdapter);
        mAdapter.updateWeights(SpHelper.getInstance().getAll());
    }

}
