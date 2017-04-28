package com.example.asus.lab.figure.circle;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.asus.lab.figure.line.DrawLineParam;

import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.round;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

/**
 * Created by a s u s on 11.03.2017.
 */

public class DrawCircleParam {
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
    public void drawCircleParam(Canvas canvas , Paint p){

        float X, Y;
        float X0=x1;
        float Y0=y1;
        float r=(float)sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
        for(X=0;X<Math.round(r/(sqrt(2.0)));X++) {
            Y =/*(float)*/Math.round(sqrt(r * r - X * X));
            draw(X0,Y0, X, Y,canvas, p);
        }
        }

       /* int R=(int) Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
        System.out.println("R"+R);
        int a=1;
        float x1=0,x2=0, y1=0,y2=0;
        System.out.println("1"+" "+x1 + " " + y1);*/
       /* x2=x0;
        y2=y0+R;*/
       /* while(x1<R/(Math.sqrt(2))){
            y1=round(Math.sqrt(R*R-x1*x1));
            x1++;
            System.out.println("2"+x1 + " " + y1);
            canvas.drawPoint(x1,y1,p);
            canvas.drawPoint((float)x1,(float)y1,p);
        }
        while(a<360){
            a++;
            x1=x2;
            y1=y2;
            double a1=(double)a;
            x2=round(R*sin(a1))+x1;
            y2=round(R*cos(a))+y1;
           //canvas.drawPoint((float)x2,(float)y2,p);
            drawLineParam.drawLineParam(canvas, p);
        }*/
       public void draw(float X0, float Y0, float X, float Y,Canvas canvas, Paint p){
               canvas.drawPoint(X0+X,Y0+Y,p);
               canvas.drawPoint(X0+Y,Y0+X,p);
               canvas.drawPoint(X0+Y,Y0-X,p);
               canvas.drawPoint(X0+X,Y0-Y,p);
               canvas.drawPoint(X0-X,Y0-Y,p);
               canvas.drawPoint(X0-Y,Y0-X,p);
               canvas.drawPoint(X0-Y,Y0+X,p);
               canvas.drawPoint(X0-X,Y0+Y,p);

       }
    }

