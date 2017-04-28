package com.example.asus.lab.figure.curve;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.Log;
import android.widget.Toast;


import com.example.asus.lab.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class CurveBezier {
    final String LOG = "logging";
    private float x,y;
    private Point point;

    MainActivity mainActivity;
    private List<PointF> points;
    public CurveBezier(){
        points=new ArrayList<>(mainActivity.col);
    }

    public void draw(int kol,Canvas canvas , Paint p){
        /*for(float t=0; t<=1; t+=0.001){
            PointF arr[]=new PointF[mainActivity.colFake];
            for(int i=0; i<mainActivity.colFake; i++){
                arr[i]= points.get(i);
            }
            for(int j=mainActivity.colFake-2; j>=0;j--){
                for(int i=0; i<=j; i++){
                    arr[i].x= (int) (arr[i].x+t*(arr[i+1].x-arr[i].x));
                    arr[i].y= (int) (arr[i].y+t*(arr[i+1].y-arr[i].y));
                }
            }
            canvas.drawPoint(arr[0].x,arr[0].y,p);
            mainActivity.col = mainActivity.colFake;
        }
        points.clear();*/
        PointF q0 = points.get(0);
        PointF q1 = points.get(points.size() - 1);
        Paint paint = new Paint();
        for(float t=0; t<=1; t+=0.001){
            PointF arr[]=new PointF[kol];
            for(int i=0; i<kol; i++){
                arr[i]= points.get(i);
            }
            for(int j=kol-2; j>=0;j--){
                for(int i=0; i<=j; i++){
                    arr[i].x=arr[i].x+t*(arr[i+1].x-arr[i].x);
                    arr[i].y=arr[i].y+t*(arr[i+1].y-arr[i].y);
                }
            }
            p.setStrokeWidth(1);
            //paint.setColor(Color.BLACK);
            canvas.drawPoint(Math.round(arr[0].x),Math.round(arr[0].y),p);
        }
        p.setStrokeWidth(1);
        //paint.setColor(Color.BLACK);
        canvas.drawPoint(q0.x,q0.y,paint);
        canvas.drawPoint(q1.x,q1.y,paint);
        String n = q0.x + " " + q0.y;
        String n1 = q1.x + " " + q1.y;
        paint.setColor(Color.RED);
        canvas.drawPoint(q0.x,q0.y,paint);

        Toast.makeText(MainActivity.ma, n, Toast.LENGTH_SHORT).show();
        Toast.makeText(MainActivity.ma, n1, Toast.LENGTH_SHORT).show();
        //count = 0;
        points.clear();
        }

        public void addPoint(float x, float y){
            PointF p = new PointF(x,y);
            points.add(p);
        }
    }