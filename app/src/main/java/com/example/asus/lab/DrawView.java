package com.example.asus.lab;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import static java.lang.Math.round;
import static java.lang.StrictMath.abs;
import static java.lang.StrictMath.cos;
import static java.lang.StrictMath.pow;
import static java.lang.StrictMath.sin;
import static java.lang.StrictMath.sqrt;

/**
 * Created by a s u s on 04.03.2017.
 */

public class DrawView extends View {
    private Canvas canvas;
    private Bitmap bitmap;
    float lastX;
    float lastY;

    private Paint p;
    Paint p1;
    Paint p2;
    TextView tv;
    float x;
    float y;
    String sDown;
    String sMove;
    String sUp;


    public DrawView(Context context) {
        super(context);
        p = new Paint();
        p1 = new Paint();
        p2 = new Paint();

    }

    public DrawView(Context context, AttributeSet atts) {
        super(context, atts);
        p = new Paint();
        p.setStrokeWidth(5);
        bitmap = Bitmap.createBitmap(700, 1000, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
    }

    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmap, x, y, p);
            /*int x1=60;
            int y1=70;
            int x2=500;
            int y2=490;
            // заливка канвы цветом
            canvas.drawARGB(80, 102, 204, 255);

            // настройка кисти
            // красный цвет
            p.setColor(Color.RED);
            // толщина линии = 10
            p.setStrokeWidth(10);

            // рисуем точку (50,50)
            canvas.drawPoint(50, 50, p);

            /*for (int i = 60, j = 70; i < 500; i++, j++)
                canvas.drawPoint(i, j, p);*/


    }

    public void drawPoint(float x, float y) {
        canvas.drawPoint(x, y, p);
        /*this.x=lastX;
        this.y=lastY;*/
        invalidate();
    }public void drawPoint1(float x, float y) {
        canvas.drawPoint(x, y, p);
        p.setStrokeWidth(5);
        p.setColor(Color.BLACK);
        invalidate();
    }
    public void drawRubber(float x, float y) {
        p.setStrokeWidth(50);
        p.setColor(Color.WHITE);
        /*this.x=lastX;
        this.y=lastY;*/
        invalidate();
    }

    public void drawLineParam(int x1, float y1, int x2, float y2) {
        p.setStrokeWidth(5);
        p.setColor(Color.BLUE);
        int x;
        float y;
        float slope=(y2-y1)/(x2-x1);
        x=x1;
        float pol=(float)0.5;
        y=y1+pol;
        while(x<=x2){
            canvas.drawPoint(x,(int)y,p);
            y=y+slope;
            x=x+1;
        }
        invalidate();

    }

   /* public void drawLineBr(float x1, float y1, float x2, float y2){
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
        invalidate();
    }*/
    public void drawCircleParam(int x0, int y0, int x, int y) {
        p.setStrokeWidth(5);
        p.setColor(Color.GREEN);
        //int R=(int)sqrt(pow((x0-x), 2)+pow((y0-y),2));
        int R=(int) Math.sqrt((x-x0)*(x-x0)+(y-y0)*(y-y0));
        System.out.println("R"+R);
       // int a=1;
        double x1=0,x2, y1=0,y2;
        System.out.println("1"+" "+x1 + " " + y1);
       /* x2=x0;
        y2=y0+R;*/
        while(x1<R/(Math.sqrt(2))){
            y1=round(Math.sqrt(R*R-x1*x1));
            x1++;
            System.out.println("2"+x1 + " " + y1);
            canvas.drawPoint(x0,y0,p);
            canvas.drawPoint((float)x1,(float)y1,p);
        }
       /* while(a<360){
            a++;
            x1=x2;
            y1=y2;
            double a1=(double)a;
            x2=round(R*sin(a1))+x0;
            y2=round(R*cos(a))+y0;
           //canvas.drawPoint((float)x2,(float)y2,p);
            drawLineBr((float)x1,(float)y1,(float)x2,(float)y2);
        }*/
        invalidate();
    }


    public boolean onTouchEvent(MotionEvent event) {
            /*x = event.getX();
            y = event.getY();*/

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: // нажатие
                sDown = "Down: " + x + "," + y;
                sMove = "";
                sUp = "";
                drawPoint(event.getX(), event.getY());
                lastX = event.getX();
                lastY = event.getY();
                //drawLineBr(x,y,event.getX(),event.getY());
                break;
            case MotionEvent.ACTION_MOVE: // движение
                sMove = "Move: " + x + "," + y;
                canvas.drawLine(lastX, lastY, event.getX(), event.getY(), p);
                lastX = event.getX();
                lastY = event.getY();
                break;
            case MotionEvent.ACTION_UP: // отпускание
                canvas.drawLine(lastX, lastY, event.getX(), event.getY(), p);
                lastX = event.getX();
                lastY = event.getY();
                break;
            case MotionEvent.ACTION_CANCEL:
                sMove = "";
                sUp = "Up: " + x + "," + y;
                break;
        }
        invalidate();
        //tv.setText(sDown + "\n" + sMove + "\n" + sUp);
        return true;
    }



}
