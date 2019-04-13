package com.example.zhaogaofei.bodyweightlinechart.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.example.zhaogaofei.bodyweightlinechart.App;
import com.example.zhaogaofei.bodyweightlinechart.model.WeightModel;

/**
 * SharedPreferences存储体重和时间值
 * key:time, value:weight
 */
public class SpHelper {
    private SharedPreferences mSp;

    private SpHelper() {
        mSp = App.getInstance().getSharedPreferences(Config.SP_KEY, Context.MODE_PRIVATE);
    }

    private static class SpHolder {
        private static final SpHelper INSTANCE = new SpHelper();
    }

    public static final SpHelper getInstance() {
        return SpHolder.INSTANCE;
    }

    public void commitString(String key, String value) {
        if (TextUtils.isEmpty(key) || TextUtils.isEmpty(value)) {
            return;
        }
        if (mSp.contains(key)) {
            mSp.edit().remove(key);
        }
        mSp.edit().putString(key, value).apply();
    }

    public String getString(String key) {
        return mSp.getString(key, "");
    }

    public boolean isContainsKey(String key) {
        return mSp.contains(key);
    }

    public List<WeightModel> getAll() {
        List<WeightModel> list = new ArrayList<>();

        Map<String, ?> all = mSp.getAll();
        Set<String> keys = all.keySet();
        for (String key : keys) {
            String value = (String) all.get(key);
            list.add(new WeightModel(key, value));
        }
        if (!list.isEmpty()) {
            Collections.sort(list, mComparator);
        }
        return list;
    }

    private static final Comparator<WeightModel> mComparator = new Comparator<WeightModel>() {
        @Override
        public int compare(WeightModel o1, WeightModel o2) {
            return o1.compareTo(o2);
        }
    };

}
