package com.example.asus.lab;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.asus.lab.figure.circle.DrawCircleBr;
import com.example.asus.lab.figure.circle.DrawCircleParam;
import com.example.asus.lab.figure.curve.CurveBezier;
import com.example.asus.lab.figure.line.DrawLineBr;
import com.example.asus.lab.figure.line.DrawLineParam;

import java.util.ArrayList;

import static java.lang.Math.round;
import static java.lang.StrictMath.abs;

/**
 * Created by a s u s on 04.03.2017.
 */

public class DrawView extends View {
    private Canvas canvas;
    private Brush brush;
    private Bitmap bitmap;
    //public static Bitmap bitmap;
    public static int instrument=0;
    float lastX;
    float lastY;
    private DrawLineBr drawLineBr;
    private DrawLineParam drawLineParam;
    private DrawCircleParam drawCircleParam;
    private DrawCircleBr drawCircleBr;
    private ObjFile objFile;
    private Limits limits;

    MainActivity mainActivity=MainActivity.ma;
    private CurveBezier curveBezier;
    private ArrayList<PointF> pointFs;
    int kol=0;
    boolean qwe = false;
    //public static int in=MainActivity.col;
    private Paint p;

    private FloodFill floodFill;

    public Brush getBrush() {
        return brush;
    }



    public DrawView(Context context) {
        super(context);
        //p = new Paint();
//

    }
    public Bitmap getBitmap(){
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap){
        brush.setBitmap(bitmap);
        this.bitmap = brush.getBitmap();
        canvas = new Canvas(bitmap);
        invalidate();
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        p = new Paint();
        p.setStrokeWidth(5);
        bitmap = Bitmap.createBitmap(700, 1000, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        drawLineBr=new DrawLineBr();
        drawLineParam=new DrawLineParam();
        drawCircleParam=new DrawCircleParam();
        drawCircleBr = new DrawCircleBr();
        objFile=new ObjFile();
        curveBezier=new CurveBezier();
        limits=new Limits();

        floodFill = new FloodFill();

        brush = new Brush();
        bitmap = brush.getBitmap();
        canvas = brush.getCanvas();
        p = brush.getmPaint();

        pointFs = new ArrayList<>(mainActivity.col);
    }



    public Paint getP(){
        return p;
    }

    public void setP(Paint p){
        this.p = p;
    }

    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmap,0,0, p);
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
    }


    public boolean onTouchEvent(MotionEvent event) {
            /*x = event.getX();
            y = event.getY();*/

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: // нажатие
                if(instrument==1){
                drawPoint(event.getX(), event.getY());
                lastX = event.getX();
                lastY = event.getY();
               }else if(instrument==2){
                    if(kol==0){
                        drawLineBr.setX1(event.getX());
                        drawLineBr.setY1(event.getY());
                    }
                    else if(kol==1){
                        drawLineBr.setX2(event.getX());
                        drawLineBr.setY2(event.getY());
                    }
                }else if(instrument==3){
                    if(kol==0){
                        drawLineParam.setX1(event.getX());
                        drawLineParam.setY1(event.getY());
                    }
                    else if(kol==1){
                        drawLineParam.setX2(event.getX());
                        drawLineParam.setY2(event.getY());
                    }
                }
                else if(instrument==4){
                    if(kol==0){
                        drawCircleParam.setX1(event.getX());
                        drawCircleParam.setY1(event.getY());
                    }
                    else if(kol==1){
                        drawCircleParam.setX2(event.getX());
                        drawCircleParam.setY2(event.getY());
                    }
                }
                else if(instrument==5){
                    if(kol==0){
                        drawCircleBr.setX1(event.getX());
                        drawCircleBr.setY1(event.getY());
                    }
                    else if(kol==1){
                        drawCircleBr.setX2(event.getX());
                        drawCircleBr.setY2(event.getY());
                    }
                }
                else if(instrument==9){
                    drawPoint(event.getX(), event.getY());
                    lastX = event.getX();
                    lastY = event.getY();
                }
                else if(instrument==7){
                   objFile.readFile();
                    objFile.drawObj(canvas,p);
                }
                else if(instrument==6) {
                    Toast.makeText(MainActivity.ma, String.valueOf(kol), Toast.LENGTH_SHORT).show();
                    curveBezier.addPoint(event.getX(),event.getY());
                }
                else if(instrument==10){
                    if(kol==0){
                        limits.setX1(event.getX());
                        limits.setY1(event.getY());
                    }
                    else if(kol==1){
                        limits.setX2(event.getX());
                        limits.setY2(event.getY());
                    }
                    else if(kol==2){
                        limits.setX3(event.getX());
                        limits.setY3(event.getY());
                    }
                }
            case MotionEvent.ACTION_MOVE: // движение
                if(instrument==1) {
                    canvas.drawLine(lastX, lastY, event.getX(), event.getY(), p);
                    lastX = event.getX();
                    lastY = event.getY();
                }else if(instrument==9){
                    p.setStrokeWidth(50);
                    p.setColor(Color.WHITE);
                    canvas.drawLine(lastX, lastY, event.getX(), event.getY(), p);
                    lastX = event.getX();
                    lastY = event.getY();
                }

                break;
            case MotionEvent.ACTION_UP: // отпускание
                if(instrument==1){
                canvas.drawLine(lastX, lastY, event.getX(), event.getY(), p);
                lastX = event.getX();
                lastY = event.getY();
                }
                else if(instrument==2){
                   if(kol==0){ kol++;}
                    else if(kol==1){
                       drawLineBr.drawLineBr( canvas,p);
                       kol=0;
                   }
                }
                else if(instrument==3){
                    if(kol==0){ kol++;}
                    else if(kol==1){
                        drawLineParam.drawLineParam(canvas,p);
                        kol=0;
                    }
                }
                else if(instrument==4){
                    if(kol==0){ kol++;}
                    else if(kol==1){
                        drawCircleParam.drawCircleParam(canvas,p);
                        kol=0;
                    }
                }
                else if(instrument==5){
                    if(kol==0){ kol++;}
                    else if(kol==1){
                        drawCircleBr.drawCircleBr(canvas,p);
                        kol=0;
                    }
                }
                else if(instrument==10){
                    if((kol==0)||(kol==1)){ kol++;}
                    else if(kol==2){
                        limits.drawLimits(brush,canvas,p);
                        //limits.fill(canvas, p);
                        kol=0;
                    }
                }
                else if(instrument==9){
                    p.setStrokeWidth(50);
                    p.setColor(Color.WHITE);
                    canvas.drawLine(lastX, lastY, event.getX(), event.getY(), p);
                    lastX = event.getX();
                    lastY = event.getY();
                } else if(instrument == 6){
                    /*if(!qwe) {
                        curveBezier.addPoint(event.getX(), event.getY());
                        mainActivity.col--;
                        if(mainActivity.col ==0){
                            qwe = true;
                        }
                    }else {
                        curveBezier.draw(canvas, p);
                        qwe = false;
                    }*/
                    this.kol++;
                }

        }
        invalidate();
        return true;
    }
    public void drawLineBese(){
        curveBezier.draw(kol , canvas,p);
        kol = 0;
    }
}
