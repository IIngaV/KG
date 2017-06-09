package com.example.asus.lab;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.asus.lab.Fractals.DrawFernFractals;
import com.example.asus.lab.Fractals.DrawMandelbrotFractal;
import com.example.asus.lab.Fractals.DrawPifagorFractal;
import com.example.asus.lab.Fractals.DrawPlasmaFractal;
import com.example.asus.lab.figure.Brush;
import com.example.asus.lab.figure.Limits;
import com.example.asus.lab.figure.Point;
import com.example.asus.lab.figure.circle.DrawCircleBr;
import com.example.asus.lab.figure.circle.DrawCircleParam;
import com.example.asus.lab.figure.curve.CurveBezier;
import com.example.asus.lab.figure.line.DrawLineBr;
import com.example.asus.lab.figure.line.DrawLineParam;
import com.example.asus.lab.file.ObjFile;

import java.util.ArrayList;
import java.util.Random;

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
    private Point point;
    private int scale = 0;
    MainActivity mainActivity=MainActivity.ma;
    private CurveBezier curveBezier;
    private ArrayList<PointF> pointFs;
    int kol=0;
    public double a= 3.14/2;
    private DrawPifagorFractal drawPifagorFractal;
    private DrawMandelbrotFractal drawMandelbrotFractal;
    private DrawFernFractals drawFernFractals;
    private DrawPlasmaFractal drawPlasmaFractal;

    private Paint p;

    private FloodFill floodFill;

    public Brush getBrush() {
        return brush;
    }



    public DrawView(Context context) {
        super(context);
        //p = new Paintm();
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
        drawPifagorFractal=new DrawPifagorFractal();
        drawMandelbrotFractal = new DrawMandelbrotFractal();
        drawFernFractals = new DrawFernFractals();
        drawPlasmaFractal = new DrawPlasmaFractal();
        limits=new Limits();
        point=new Point(lastX,lastY);
        int L= 200;
        floodFill = new FloodFill();

        brush = new Brush();
        bitmap = brush.getBitmap();
        canvas = brush.getCanvas();
        p = brush.getmPaint();

        pointFs = new ArrayList<>(mainActivity.col);
    }

    public int getScale() {
        return scale;
    }
    public void setScale(int scale) {
        this.scale = scale;
        setScaleX(scale);
        setScaleY(scale);
    }
    public Paint getP(){
        return p;
    }

    public void setP(Paint p){
        this.p = p;
    }

    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmap,0,0, p);
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
                else if(instrument==20){
                    drawMandelbrotFractal.drawMandelbrotFractal(canvas,p);
                }
                else if(instrument==21){
                    drawPlasmaFractal.drawPlasmaFractal(canvas);
                }
                else if(instrument==22){
                    drawFernFractals.drawFernFractals(canvas,p);
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
                }else if(instrument==19){
                    /*drawPoint(event.getX(), event.getY());
                    lastX = event.getX();
                    lastY = event.getY();*/
                    drawPifagorFractal.setX1(event.getX());
                    drawPifagorFractal.setY1(event.getY());
                    lastX = event.getX();
                    lastY = event.getY();
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
                else if(instrument== 19){
                    float L=200;
                    drawPifagorFractal.drawPifagorFractal(canvas,lastX,lastY,L,(float)a,p);
                                    }

                /*else if(instrument==20){
                    Toast.makeText(MainActivity.ma, "fr2", Toast.LENGTH_SHORT).show();
                    drawMandelbrotFractal.drawMandelbrotFractal(canvas,p);
                }*/

        }
        invalidate();
        return true;
    }
    public void drawLineBese(){
        curveBezier.draw(kol , canvas,p);
        kol = 0;
    }
    public void drawMosaic() {
        Random rand = new Random();
        //System.out.println(getmPaint().getStrokeWidth()+ "   " +bitmap.getHeight()+ "   " + bitmap.getWidth() + "   " + canvas.getHeight()+ "   " + canvas.getWidth());

        for (int i = 0; i < bitmap.getHeight(); i += (int) getP().getStrokeWidth()) {
            for (int j = 0; j < bitmap.getWidth(); j += (int) getP().getStrokeWidth()) {
                int paintColor = Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
                p.setColor(paintColor);
                Rect rect = new Rect(i, j, (int) getP().getStrokeWidth() + i, (int) getP().getStrokeWidth() + j);
                canvas.drawRect(rect, p);
            }
        }
    }

    public void clean() {
        bitmap.eraseColor(Color.WHITE);
    }
}
