package com.example.asus.lab.figure.line;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.asus.lab.DrawView;

import static java.lang.StrictMath.abs;

/**
 * Created by a s u s on 11.03.2017.
 */

public class DrawLineBr {
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

    public void drawLineBr(Canvas canvas , Paint p){
        p.setStrokeWidth(5);
        p.setColor(Color.RED);
        float absx=abs(x2-x1);
        float absy=abs(y2-y1);
        float spec=absx;
        if(absy>absx){
            spec=absy;
        }
        float dx=(x2-x1)/spec;
        float dy=(y2-y1)/spec;
        float x=x1,y=y1;
        for(int i=0; i<(int)spec;i++){
            canvas.drawPoint(x,y,p);
            x+=dx;
            y+=dy;
        }

    }
}
