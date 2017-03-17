package com.example.asus.lab.figure;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.asus.lab.DrawView;

/**
 * Created by a s u s on 18.03.2017.
 */

public class Optional {
    private float x1,x2,y1,y2;

    public float getX1(){
        return x1;
    }
    public void setX1(float x1){
        this.x1=x1;
    }
    public float getX2(){
        return x2;
    }
    public void setX2(float x2){
        this.x2=x2;
    }
    public float getY1(){
        return y1;
    }
    public void setY1(float y1){
        this.y1=y1;
    }
    public float getY2(){
        return y2;
    }
    public void setY2(float y2){
        this.y2=y2;
    }
    public void drawRubber(Canvas canvas , Paint p) {
        p.setStrokeWidth(50);
        p.setColor(Color.WHITE);

    }
}
