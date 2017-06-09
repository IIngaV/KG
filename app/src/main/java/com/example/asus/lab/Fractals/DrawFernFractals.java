package com.example.asus.lab.Fractals;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by a s u s on 09.06.2017.
 */

public class DrawFernFractals {
      /* public DrawFernFractals(int x0, int y0, int x1, int y1) {
       // super(x0, y0, x1, color, y1);
        paint = new Paint();
        //paint.setColor(color);
    }*/

    public void drawFernFractals(Canvas canvas, Paint paint) {
        int h = 640;
        int w = 640;
        double x = 0;
        double y = 0;

        for (int i = 0; i < 20_000; i++) {
            double tmpx, tmpy;
            double r = Math.random();
            if (r <= 0.01) {
                tmpx = 0;
                tmpy = 0.16 * y;
            } else if (r <= 0.08) {
                tmpx = 0.2 * x - 0.26 * y;
                tmpy = 0.23 * x + 0.22 * y + 1.6;
            } else if (r <= 0.15) {
                tmpx = -0.15 * x + 0.28 * y;
                tmpy = 0.26 * x + 0.24 * y + 0.44;
            } else {
                tmpx = 0.85 * x + 0.04 * y;
                tmpy = -0.04 * x + 0.85 * y + 1.6;
            }
            x = tmpx;
            y = tmpy;

            canvas.drawPoint((int) Math.round(w / 2 + x * w / 11),
                    (int) Math.round(h - y * h / 11), paint);
        }
    }


}
