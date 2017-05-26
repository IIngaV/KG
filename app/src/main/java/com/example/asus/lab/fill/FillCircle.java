package com.example.asus.lab.fill;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.asus.lab.figure.Paint.Paint;
import com.example.asus.lab.figure.circle.DrawCircleBr;

/**
 * Created by a s u s on 26.05.2017.
 */

public class FillCircle extends DrawCircleBr {
    public void draw(Canvas canvas, int[] points, Paint paint) {
        int color = paint.getColor();
        int color2 = paint.getColor2();
        int r = (int) Math.sqrt((points[2] - points[0]) * (points[2] - points[0]) + (points[3] - points[1]) * (points[3] - points[1]));
        int x = 0, y = r, f = 1 - r;
        fill4Lines(canvas, points[0], points[1], x, y, color2);
        while (x <= y) {
            if (f > 0) {
                y--;
                f += 2 * (x - y) + 5;
            } else {
                f += 2 * x + 3;
            }
            x++;
            fill4Lines(canvas, points[0], points[1], x, y, color2);
        }

        x = 0;
        y = r;
        f = 1 - r;
        drawD( x, y,canvas, paint);
        while (x <= y) {
            if (f > 0) {
                y--;
                f += 2 * (x - y) + 5;
            } else {
                f += 2 * x + 3;
            }
            x++;
            setPixel(canvas, points[0], points[1], x, y, color);
        }
    }

    private void fillLine(Canvas canvas, int x1, int x2, int y, int color) {
        for (int i = x1 + 1; i < x2; i++) {
            if (checkLimits(canvas, i, y)) {
                canvas.setPixel(i, y, color);
            }
        }
    }

    private void fill4Lines(Canvas canvas, int x0, int y0, int x, int y, int color) {
        fillLine(canvas, x0 - x, x0 + x, y0 + y, color);

        fillLine(canvas, x0 - y, x0 + y, y0 + x, color);

        fillLine(canvas, x0 - y, x0 + y, y0 - x, color);

        fillLine(canvas, x0 - x, x0 + x, y0 - y, color);
    }
}
