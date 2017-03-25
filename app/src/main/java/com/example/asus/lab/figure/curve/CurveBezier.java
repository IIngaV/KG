package com.example.asus.lab.figure.curve;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.Log;


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

    public void draw(Canvas canvas , Paint p){
        for(float t=0; t<=1; t+=0.00001){
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
        points.clear();
        }

        public void addPoint(float x, float y){
            PointF p = new PointF(x,y);
            points.add(p);
        }
    }