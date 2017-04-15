package com.example.asus.lab;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Stack;


/*public class SeedFill {

    private Stack<Point> stack;
    private int fillColor;
    private int oldColor;
    private int[]pts;
    private boolean ready = false;
    private DrawView drawView;


    public SeedFill(int color) {
        stack = new Stack<>();
        fillColor = color;
    }

    public int getColor() {
        return fillColor;
    }


    /*public void setColor(int color) {
        fillColor = color;
    }*/


  /*  public void fillBackground(int x, int y) {
        //Bitmap mainBitmap = drawView.getBitmap();
        //oldColor = mainBitmap.getPixel(x, y);
        //if (oldColor == fillColor) return;
        stack.push(new Point(x, y));
        recursiveFill();
    }

    public boolean checkL(Bitmap bmp, int x, int y) {
        if (x < bmp.getWidth() && x > 0 && y > 0 && y < bmp.getHeight())
            return true;
        return false;
    }

    public void recursiveFill() {
        Stack<Integer> pxls = new Stack<Integer>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        Bitmap bmp = DrawView.bitmap;
        if(!ready) {
            Point a = stack.pop();
            int x = a.x;
            int y = a.y;
            pxls.add(x);
            pxls.add(y);
            while (pxls.size() != 0) {
                while (checkL(bmp, x, y) && bmp.getPixel(x, y) == Color.WHITE) {
                    x--;
                }
                x++;

                if (checkL(bmp, x, y) && bmp.getPixel(x, y + 1) == Color.WHITE) {
                    pxls.push(x);//стек
                    pxls.push(y + 1);
                }
                if (checkL(bmp, x, y) && bmp.getPixel(x, y - 1) == Color.WHITE) {
                    pxls.push(x);
                    pxls.push(y - 1);
                }
                //bmp.setPixel(x, y, Color.RED);
                x++;
                while (checkL(bmp, x, y) && bmp.getPixel(x, y) == Color.WHITE) {
                    bmp.setPixel(x, y, Color.RED);
                    list.add(x);
                    list.add(y);
                    if (bmp.getPixel(x, y + 1) == Color.WHITE && bmp.getPixel(x - 1, y + 1) != Color.WHITE) {
                        pxls.push(x);
                        pxls.push(y + 1);
                    }
                    if (bmp.getPixel(x, y - 1) == Color.WHITE && bmp.getPixel(x - 1, y - 1) != Color.WHITE) {
                        pxls.push(x);
                        pxls.push(y - 1);
                    }
                    x++;
                }
            }
            ready = true;
            for(int i =0;i<list.size();i++ ){
                pts[i]= list.get(1);
            }
        }
        bmp.setPixels(pts,0,0,0,0,bmp.getWidth(),bmp.getHeight());
        /*if(stack.empty()) return;
        Bitmap mainBitmap = drawView.getBitmap();
        Point point = stack.pop();
        int bitmapWidth = mainBitmap.getWidth();
        int bitmapHeight = mainBitmap.getHeight();
        int x = point.x;
        int y = point.y;

        mainBitmap.setPixel(x,y,fillColor);
        System.out.println("Красит " + x + " " + y);
        if(mainBitmap.getPixel(x+1,y) == oldColor && x+1 < bitmapWidth-1 && y < bitmapHeight-1 && x+1 >=1 && y>=1){
            stack.push(new Point(x+1,y));
            System.out.println("Поместили " + (x+1) + " " + y);
        }
        if(mainBitmap.getPixel(x-1,y) == oldColor && x-1 < bitmapWidth-1 && y < bitmapHeight-1 && x-1 >=1 && y>=1){
            stack.push(new Point(x-1,y));
            System.out.println("Красит " + (x-1) + " " + y);
        }
        if(mainBitmap.getPixel(x,y+1) == oldColor && x < bitmapWidth-1 && y+1 < bitmapHeight-1 && x >=1 && y+1 >=1){
            stack.push(new Point(x,y+1));
            System.out.println("Красит " + x + " " + (y+1));
        }
        if(mainBitmap.getPixel(x,y-1) == oldColor && x < bitmapWidth-1 && y-1 < bitmapHeight-1 && x >=1 && y-1 >=1){
            stack.push(new Point(x,y-1));
            System.out.println("Красит " + x + " " + (y-1));
        }
        System.out.println("========================");
        recursiveFill();
*/

  /* }

    public void onTouch(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                fillBackground(x, y);
                break;
        }
    }
}*/
