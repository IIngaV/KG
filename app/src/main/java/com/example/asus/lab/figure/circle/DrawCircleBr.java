package com.example.asus.lab.figure.circle;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;


public class DrawCircleBr {
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
    public void drawCircleBr(Canvas canvas , Paint p){
        p.setStrokeWidth(5);
        p.setColor(Color.YELLOW);

        float r=(float)sqrt(pow((x2-x1), 2)+pow((y2-y1),2));
        int X=0, Y=(int)r;
        int f=1-Y;
        canvas.drawPoint(x1,y1+r,p);
        canvas.drawPoint(x1,y1-r,p);
        canvas.drawPoint(x1+r,y1,p);
        canvas.drawPoint(x1-r,y1,p);
        while(X<=Y){
            if(f>0){
                Y=Y-1;
                f=f+2*(X-Y)+5;
            }
            else {
                f =f+ 2 * X + 3;
                //X=X+1;
            }
            X=X+1;
                canvas.drawPoint(x1 + X, y1 + Y, p);
                canvas.drawPoint(x1 - X, y1 + Y, p);
                canvas.drawPoint(x1 + X, y1 - Y, p);
                canvas.drawPoint(x1 - X, y1 - Y, p);
                canvas.drawPoint(x1 + Y, y1 + X, p);
                canvas.drawPoint(x1 - Y, y1 + X, p);
                canvas.drawPoint(x1 + Y, y1 - X, p);
                canvas.drawPoint(x1 - Y, y1 - X, p);


        }


    }
}
