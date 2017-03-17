package com.example.asus.lab.figure.line;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by a s u s on 11.03.2017.
 */

public class DrawLineParam {
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

    public void drawLineParam(Canvas canvas , Paint p){
        p.setStrokeWidth(5);
        p.setColor(Color.RED);
        float max = Math.max(Math.abs(x2 - x1),Math.abs(y2 - y1));
        float t = 1/(max); //шаг приращения

        if (x1 < x2 && y1 < y2) {
            float x = x1;
            float y = y1;
            while (x <= x2 && y <= y2) {
                canvas.drawPoint(x, y, p);
                x = x + t * (x2 - x1);
                y = y + t * (y2 - y1);
            }
        } else if (x1 > x2 && y1 < y2){
            float x = x1;
            float y = y1;
            while (x >= x2 && y <= y2) {
                canvas.drawPoint(x, y, p);
                x = x - t * (x1 - x2);
                y = y + t * (y2 - y1);
            }
        } else if (x1 < x2 && y1 > y2){
            float x = x2;
            float y = y2;
            while (x >= x1 && y <= y1) {
                canvas.drawPoint(x, y, p);
                x = x - t * (x2 - x1);
                y = y + t * (y1 - y2);
            }
        } else if (x1 > x2 && y1 > y2){
            float x = x2;
            float y = y2;
            while (x <= x1 && y <= y1) {
                canvas.drawPoint(x, y, p);
                x = x + t * (x1 - x2);
                y = y + t * (y1 - y2);
            }
        }

        /*float absx=abs(x2-x1);
        float absy=abs(y2-y1);
        float spec=absx;
        if(absy>absx){
            spec=absy;
        }
        float dx=(x2-x1)/spec;
        float dy=(y2-y1)/spec;
        float x=x1,y=y1;
        int l=255;
        int k=0;
        int j=0;

        for(int i=0; i<(int)spec;i++){
            p.setColor(Color.rgb(l-5,j+5,k+5));
            canvas.drawPoint(x,y,p);
            x+=dx;
            y+=dy;
        }
*/

    }
}
