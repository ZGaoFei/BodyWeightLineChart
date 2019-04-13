package com.example.zhaogaofei.bodyweightlinechart.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhaogaofei.bodyweightlinechart.R;
import com.example.zhaogaofei.bodyweightlinechart.model.WeightModel;

public class WeightAdapter extends RecyclerView.Adapter<WeightAdapter.WeightHolder> {
    private Context mContext;
    private List<WeightModel> mList;

    public WeightAdapter(Context context) {
        mList = new ArrayList<>();

        mContext = context;
    }

    public void addWeight(String time, String weight) {
        mList.add(new WeightModel(time, weight));
        notifyDataSetChanged();
    }

    public void addWeight(WeightModel model) {
        if (model != null) {
            mList.add(model);
            notifyDataSetChanged();
        }
    }

    public void addWeights(List<WeightModel> list) {
        if (list != null && !list.isEmpty()) {
            mList.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void updateWeights(List<WeightModel> list) {
        if (list != null && !list.isEmpty()) {
            mList.clear();
            mList.addAll(list);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public WeightHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_weight_layout, viewGroup, false);
        return new WeightHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeightHolder weightHolder, int i) {
        weightHolder.tvWeight.setText(mList.get(i).getWeight());
        weightHolder.tvTime.setText(mList.get(i).getTime());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class WeightHolder extends RecyclerView.ViewHolder {

        private TextView tvTime;
        private TextView tvWeight;

        public WeightHolder(@NonNull View itemView) {
            super(itemView);

            tvTime = itemView.findViewById(R.id.tv_item_time);
            tvWeight = itemView.findViewById(R.id.tv_item_weight);
        }
    }
}
