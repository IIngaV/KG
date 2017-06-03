package com.example.asus.lab.fill;

import android.graphics.Bitmap;

import com.example.asus.lab.CheckLimitsMethod;
import com.example.asus.lab.figure.Paint.Paintm;
import com.example.asus.lab.figure.circle.DrawCircleBr;

/**
 * Created by a s u s on 02.06.2017.
 */

public class FillRectangularMethod extends DrawCircleBr{
    public void draw(Bitmap bmp, int[] points, Paintm paint) {
        int color = paint.getColor();
        int color2 = paint.getColor2();
        int x1, y1, x2, y2;
        if (points[2] > points[0]) {
            x1 = points[0];
            x2 = points[2];
        } else {
            x1 = points[2];
            x2 = points[0];
        }
        if (points[3] > points[1]) {
            y1 = points[1];
            y2 = points[3];
        } else {
            y1 = points[3];
            y2 = points[1];
        }

        for (int i = x1; i <= x2; i++) {
            if (checkLimits(bmp, i, y1)) {
                bmp.setPixel(i, y1, color);
            }
            if (checkLimits(bmp, i, y2)) {
                bmp.setPixel(i, y2, color);
            }
        }

        for (int i = y1 + 1; i < y2; i++) {
            if (checkLimits(bmp, x1, i)) {
                bmp.setPixel(x1, i, color);
            }
            if (checkLimits(bmp, x2, i)) {
                bmp.setPixel(x2, i, color);
            }
        }

        for (int i = y1 + 1; i < y2; i++) {
            for (int j = x1 + 1; j < x2; j++) {
                if (checkLimits(bmp, j, i)) {
                    bmp.setPixel(j, i, color2);
                }
            }
        }

    }
}
