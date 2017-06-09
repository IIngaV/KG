package com.example.asus.lab.Fractals;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by a s u s on 09.06.2017.
 */

public class DrawPifagorFractal {
    private float x, y;
    public float max=10;
    public double a= 3.14/2;

    public float getX1(){
        return x;
    }
    public void setX1(float x){
        this.x=x;
    }
    public float getY1(){
        return y;
    }
    public void setY1(float y){
        this.y=y;
    }
    public void drawPifagorFractal (Canvas canvas, float x, float y, float L,float a,Paint paint){
        if(L > max) {
            L*=0.7;
            canvas.drawLine(x,y,x+(float)(L*Math.cos(a)),y-(float)(L*Math.sin(a)),paint);
            x= (float) (x+L*Math.cos(a));
            y=(float)(y-L*Math.sin(a));

            // рекурсивный вызов
            drawPifagorFractal(canvas,x,y,L,(float)(a+(3.14/5)),paint);
            drawPifagorFractal(canvas,x,y,L,(float)(a-(3.14/5)),paint);
        }
    }
}
