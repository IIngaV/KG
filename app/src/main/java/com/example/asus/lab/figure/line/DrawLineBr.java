package com.example.asus.lab.figure.line;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.asus.lab.settings.DrawView;

import static java.lang.StrictMath.abs;
import static java.lang.StrictMath.max;
import static java.lang.StrictMath.signum;


public class DrawLineBr {
    private float x1,x2,y1,y2,x3,y3;
    private DrawView drawView;

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

        x1=getX1();
        x2=getX2();
        y1=getY1();
        y2=getY2();

        float x=x1;
        float y=y1;
        float dx=Math.abs(x2-x1);
        float dy=Math.abs(y2-y1);
        float s1=signum(x2-x1);
        float s2=signum(y2-y1);

        boolean swap=false;
        float c;
        float dc1;
        float dc2;
        float d;
        if(dy>dx){
            d=dy;
            dy=dx;
            dx=d;
            swap=true;
        }

        c=2*dy-dx;
        dc1=2*dy;
        dc2=2*dx;
        for(int i=1;i<=dx;i++){
            canvas.drawPoint(x,y,p);
            while(c>=0){
                if(swap){
                    x=x+s1;
                }
                else{
                    y=y+s2;
                }
                c=c-dc2;
            }
            if(swap){
                y=y+s2;
            }
            else{
                x=x+s1;
            }
            c=c+dc1;
        }
    }
}
