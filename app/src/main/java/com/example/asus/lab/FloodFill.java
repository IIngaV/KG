package com.example.asus.lab;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.MotionEvent;

import java.util.Stack;


public class FloodFill extends Brush {

    private Stack<Point> stack;
    private int fillColor;

    private int x, y;

    private Bitmap bmp;

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Bitmap getBmp() {
        return bmp;
    }

    public void setBmp(Bitmap bmp) {
        this.bmp = bmp;
    }

    public void setX(int x) {
        this.x = x;
    }

    public FloodFill() {
        stack = new Stack<>();
        bmp = super.getBitmap();
        //fillColor = MainActivity.appSettings.getDrawingColor();
    }

    public int getColor() {
        return fillColor;
    }

    public void setColor(int color) {
        fillColor = color;
    }

    public Bitmap fillBackground(Brush brush) {
        Bitmap mainBitmap = brush.getBitmap();
        int bitmapWidth = mainBitmap.getWidth();
        int bitmapHeight = mainBitmap.getHeight();

        Point point;
        boolean spanAbove, spanBelow;
        int oldColor = mainBitmap.getPixel(x, y);

        if(oldColor == fillColor) return mainBitmap;
        stack.clear();
        stack.push(new Point(x, y));

        while(stack.size() > 0) {
            point = stack.pop();
            x = point.x;
            y = point.y;
            while(x >= 0 && mainBitmap.getPixel(x, y) == oldColor) x--;
            x++;

            spanAbove = spanBelow = false;
            while(x < bitmapWidth && mainBitmap.getPixel(x, y) == oldColor) {
                mainBitmap.setPixel(x, y, fillColor);

                if(!spanAbove && y > 0 && mainBitmap.getPixel(x, y - 1) == oldColor) {
                    stack.push(new Point(x, y - 1));
                    spanAbove = true;
                }
                else if(spanAbove && y > 0 && mainBitmap.getPixel(x, y - 1) != oldColor) spanAbove = false;

                if(!spanBelow && y < bitmapHeight - 1 && mainBitmap.getPixel(x, y + 1) == oldColor) {
                    stack.push(new Point(x, y + 1));
                    spanBelow = true;
                }
                else if(spanBelow && y < bitmapHeight - 1 && mainBitmap.getPixel(x, y + 1) != oldColor) spanBelow = false;

                x++;
            }
        }
        return mainBitmap;
    }

   /* public void onTouch(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                fillBackground(x, y);
                break;
        }
    }*/
}