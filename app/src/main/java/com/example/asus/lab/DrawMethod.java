package com.example.asus.lab;

import android.graphics.Bitmap;

import com.example.asus.lab.figure.Paint.Paintm;

/**
 * Created by a s u s on 02.06.2017.
 */

public abstract class DrawMethod {
    public abstract void draw(Bitmap bmp, int[] points, Paintm paint);

    protected boolean checkLimits(Bitmap bmp, int x, int y) {
        int width = bmp.getWidth();
        int heigth = bmp.getHeight();
        return (x >= 0 && x < width
                && y >= 0 && y < heigth);
    }
}
