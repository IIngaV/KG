package com.example.asus.lab.figure;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.asus.lab.DrawView;
import com.example.asus.lab.FloodFill;
import com.example.asus.lab.figure.line.DrawLineBr;

import static java.lang.StrictMath.signum;

/**
 * Created by a s u s on 15.04.2017.
 */

public class Limits {
    private float x1,x2,x3, y1,y2,y3;
    private DrawView drawView;
    private DrawLineBr drawLineBr;

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
    public float getX3(){
        return x3;
    }
    public void setX3(float x3){
        this.x3=x3;
    }
    public float getY3(){
        return y3;
    }
    public void setY3(float y3){
        this.y3=y3;
    }

    public void drawLimits(Brush brush, Canvas canvas , Paint p){
        FloodFill floodFill = new FloodFill();
        floodFill.setColor(Color.BLUE);
        drawLineBr=new DrawLineBr();
        float x1=getX1();
        float x2=getX2();
        float y2=getY2();
        float y1= getY1();
        float x3=getX3();
        float y3=getY3();
        float d=Math.round(Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1)));

        x2=x1+d;
        y2=y1+d;
        drawLineBr.setX1(x1);
        drawLineBr.setX2(x2);
        drawLineBr.setY1(y1);
        drawLineBr.setY2(y2);
        drawLineBr.drawLineBr(canvas, p);

        drawLineBr.setX1(x1);
        drawLineBr.setX2(x3);
        drawLineBr.setY1(y1);
        drawLineBr.setY2(y1);
        drawLineBr.drawLineBr(canvas, p);

        drawLineBr.setX2(x1);
        drawLineBr.setY2(y3);
        drawLineBr.drawLineBr(canvas, p);

        drawLineBr.setX1(x1);
        drawLineBr.setY1(y1);
        drawLineBr.setX2(x1);
        drawLineBr.setY2(y2);
        drawLineBr.drawLineBr(canvas, p);

        drawLineBr.setX1(x3);
        drawLineBr.setX2(x3);
        drawLineBr.setY1(y3);
        drawLineBr.setY2(y1);
        drawLineBr.drawLineBr(canvas, p);

        drawLineBr.setX1(x1);
        drawLineBr.setY1(y3);
        drawLineBr.setY2(y3);
        drawLineBr.drawLineBr(canvas, p);

        drawLineBr.setX1(x3);
        drawLineBr.setY1(y1);
        drawLineBr.setX2(x3-d);
        drawLineBr.setY2(y1+d);
        drawLineBr.drawLineBr(canvas, p);

        drawLineBr.setX1(x3-d);
        drawLineBr.setY1(y1+d);
        drawLineBr.setX2(x2);
        drawLineBr.setY2(y2);
        drawLineBr.drawLineBr(canvas, p);

        drawLineBr.setX1(x2);
        drawLineBr.setY1(y2);
        drawLineBr.setX2(x1+d);
        drawLineBr.setY2(y3-d);
        drawLineBr.drawLineBr(canvas, p);

        drawLineBr.setX1(x3-d);
        drawLineBr.setY1(y1+d);
        drawLineBr.setX2(x3-d);
        drawLineBr.setY2(y3-d);
        drawLineBr.drawLineBr(canvas, p);

        drawLineBr.setX1(x1+d);
        drawLineBr.setY1(y3-d);
        drawLineBr.setX2(x3-d);
        drawLineBr.setY2(y3-d);
        drawLineBr.drawLineBr(canvas, p);

        drawLineBr.setX1(x3);
        drawLineBr.setY1(y3);
        drawLineBr.setX2(x3-d);
        drawLineBr.setY2(y3-d);
        drawLineBr.drawLineBr(canvas, p);

        drawLineBr.setX1(x1);
        drawLineBr.setY1(y3);
        drawLineBr.setX2(x1+d);
        drawLineBr.setY2(y3-d);
        drawLineBr.drawLineBr(canvas, p);
        p.setColor(Color.RED);
        //canvas.drawPoint((x3-d+x2)/2,(y3-d+y2)/2,p);
       /** canvas.drawPoint((x1+x2)/2,(y1+y3)/2,p);
        canvas.drawPoint((x3+x3-d)/2,(y1+y3)/2,p);
        canvas.drawPoint((x1+x3)/2,(y3+y3-d)/2,p);
        canvas.drawPoint((x1+x3)/2,(y1+y1+d)/2,p);*/
        //new FloodFill((x1+x2)/2,(y1+y3)/2);

        //new SeedFill(Color.YELLOW).fillBackground((int) ((x3 - d + x2) / 2), (int) ((y3 - d + y2) / 2));
        DrawLineBr lb = new DrawLineBr();
        lb.setX1((x1+x2)/2);
        lb.setY1(((y1+y3)/2));
        drawLineBr.drawLineBr(canvas,p);
        floodFill.setX((int)lb.getX1());
        floodFill.setY((int)lb.getY1());
        floodFill.fillBackground(brush);

        lb.setX1((x3+x3-d)/2);
        lb.setY1((y1+y3)/2);
        drawLineBr.drawLineBr(canvas,p);
        floodFill.setX((int)lb.getX1());
        floodFill.setY((int)lb.getY1());
        floodFill.fillBackground(brush);

        lb.setX1((x1+x3)/2);
        lb.setY1((y3+y3-d)/2);
        drawLineBr.drawLineBr(canvas,p);
        floodFill.setX((int)lb.getX1());
        floodFill.setY((int)lb.getY1());
        floodFill.fillBackground(brush);

        lb.setX1((x1+x3)/2);
        lb.setY1((y1+y1+d)/2);
        drawLineBr.drawLineBr(canvas,p);
        floodFill.setX((int)lb.getX1());
        floodFill.setY((int)lb.getY1());
        floodFill.fillBackground(brush);
    }
    /*public void fill(Canvas canvas , Paintm p){
        new SeedFill(Color.YELLOW).fillBackground((int) ((x3 - Math.round(Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1))) + x2) / 2), (int) ((y3 - Math.round(Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1))) + y2) / 2));
    }*/
}
