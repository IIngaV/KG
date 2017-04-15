package com.example.asus.lab;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;


public class Brush {
    private Paint mPaint;
    private Canvas canvas;
    private Bitmap bitmap;

    public  Brush(){
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(2);
        bitmap = Bitmap.createBitmap(2000,2000, Bitmap.Config.ARGB_8888);//Это означает, что на каждый из 4-х ARGB-компонентов пиксела (альфа, красный, зеленый, голубой) выделяется по 8 бит (= 1 байт).
        // Следовательно, пиксел будет весить 4 байта.
        canvas = new Canvas(bitmap);
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public Paint getmPaint() {
        return mPaint;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
        canvas = new Canvas(bitmap);
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public void setmPaint(Paint mPaint) {
        this.mPaint = mPaint;
    }
}
