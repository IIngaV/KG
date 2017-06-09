package com.example.asus.lab.Fractals;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by a s u s on 09.06.2017.
 */

public class DrawMandelbrotFractal {

    public void drawMandelbrotFractal(Canvas canvas, Paint paint) {
        //canvas.drawRGB(0, 0, 0);
        int n = 255;
        int max = 10;
        for (int ix = 0; ix < 399; ix++) {
            for (int iy = 0; iy <299; iy++) {
                double x = 0.0;
                double y = 0.0;
                double cx = 0.002 * (ix - 720);
                double cy = 0.002 * (iy - 150);
                int i;
                for (i = 1; i < n; i++) {
                    double x1 = x * x - y * y + cx;
                    double y1 = 2 * x * y + cy;
                    if ((x1 > max) || (y1 > max)) {
                        break;
                    }
                    x = x1;
                    y = y1;
                }
                if (i >= n) {
                    paint.setColor(Color.RED);
                    canvas.drawPoint(ix, iy, paint);
                } else {
                    paint.setColor(Color.rgb(255, 255 - i, 255 - i));
                    canvas.drawPoint(ix, iy, paint);
                }
            }
        }
    }
}

