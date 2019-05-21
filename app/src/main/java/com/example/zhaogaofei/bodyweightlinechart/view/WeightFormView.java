package com.example.zhaogaofei.bodyweightlinechart.view;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.zhaogaofei.bodyweightlinechart.model.WeightModel;
import com.example.zhaogaofei.bodyweightlinechart.utils.Utils;

public class WeightFormView extends View {
    private static final int DEFAULT_SCREEN_SPACE = 20; // 屏幕两边的间距
    private static final int DEFAULT_LINE_WIDTH = 5; // 坐标轴线的宽度
    private static final int DEFAULT_HEIGHT_ALL = 100; // 默认纵坐标表示100kg高度
    private static final int DEFAULT_TIME = 30; // 默认显示30天的数据

    private int mScreenWidth;
    private int mScreenHeight;
    private int mMaxWeight = DEFAULT_HEIGHT_ALL;
    private int mMaxTime = DEFAULT_TIME;

    private Paint mPaint;

    private List<WeightModel> mList;

    public WeightFormView(Context context) {
        this(context, null);
    }

    public WeightFormView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WeightFormView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mScreenWidth = Utils.getScreenWidth((Activity) context);
        mScreenHeight = Utils.getScreenHeight((Activity) context);

        mList = new ArrayList<>();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    public void setWeightList(List<WeightModel> list) {
        if (list != null && !list.isEmpty()) {
            mList.addAll(list);
            postInvalidate();
        }
    }

    public void clear() {
        if (mList != null) {
            mList.clear();
            postInvalidate();
        }
    }

    /**
     * 设置最大体重值，即纵坐标的高度
     * 最小体重值为100kg
     */
    public void setMaxWeight(int weight) {
        if (weight >= DEFAULT_HEIGHT_ALL && this.mMaxWeight != weight) {
            this.mMaxWeight = weight;
            postInvalidate();
        }
    }

    /**
     * 设置最大时间值，即横坐标的长度
     * 最小时间值为30天
     */
    public void setMaxTime(int time) {
        if (time >= DEFAULT_TIME && this.mMaxTime != time) {
            this.mMaxTime = time;
            postInvalidate();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawAxis(canvas);

        if (mList == null || mList.isEmpty()) {
            return;
        } else {
            drawLines(canvas);
        }
    }

    /**
     * 画坐标轴
     *
     * 坐标轴的横坐标长度为屏幕的宽度减去两边的间距
     * 坐标轴的竖坐标长度为屏幕高度的一半
     */
    private void drawAxis(Canvas canvas) {
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(DEFAULT_LINE_WIDTH);
        mPaint.setStyle(Paint.Style.FILL);

        // 横坐标
        canvas.drawLine(DEFAULT_SCREEN_SPACE, mScreenHeight / 2, mScreenWidth - DEFAULT_SCREEN_SPACE, mScreenHeight / 2, mPaint);
        // 竖坐标
        canvas.drawLine(DEFAULT_SCREEN_SPACE * 2, DEFAULT_SCREEN_SPACE, DEFAULT_SCREEN_SPACE * 2, mScreenHeight / 2 + DEFAULT_SCREEN_SPACE, mPaint);
    }

    /**
     * 画体重线
     */
    private void drawLines(Canvas canvas) {
        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(DEFAULT_LINE_WIDTH);
        mPaint.setColor(Color.RED);

        Path path = getPath();
        canvas.translate(DEFAULT_SCREEN_SPACE, 0);
        canvas.drawPath(path, mPaint);
    }

    private Path getPath() {
        Path path = new Path();

        // 获取时间的间距的方法为，当前的时间跟第一天的时间进行对比，得到间距
        // 同时获得当前时间的体重值
        WeightModel weightModel = mList.get(0);
        String beforeTime = weightModel.getTime();
        String weight = weightModel.getWeight();

        int timeLocation = DEFAULT_SCREEN_SPACE;
        // 定义路径的起点
        path.moveTo(timeLocation, getWeightHeight(weight));
        for (int i = 1; i < mList.size(); i++) {
            WeightModel model = mList.get(i);
            String currentTime = model.getTime();

            // 计算出与上一个时间之间的差值
            int timeSpace = Utils.getTimeSpace(beforeTime, currentTime);
            String currentWeight = model.getWeight();
            beforeTime = currentTime;

            // 计算出当前时间所在的位置
            timeLocation += getTimeWidth(timeSpace);

            path.lineTo(timeLocation, getWeightHeight(currentWeight));

            Log.e("zgf", "===timeSpace====" + timeSpace + "==currentWeight===" + currentWeight);
        }
        return path;
    }

    /**
     * 当前体重值与最大体重值对比，计算应该占用坐标轴的高度
     */
    private float getWeightHeight(String weight) {
        float aFloat = Float.parseFloat(weight);
        int halfHeight = mScreenHeight / 2;
        int height = (int) (halfHeight - aFloat / mMaxWeight * halfHeight) + DEFAULT_SCREEN_SPACE;
        return height;
    }

    /**
     * 根据最大显示时间计算出当前时间差占用横坐标的宽度
     */
    private float getTimeWidth(int space) {
        float width = (mScreenWidth - DEFAULT_SCREEN_SPACE * 2) / mMaxTime * space;
        return width;
    }
}
