package com.example.asus.lab.Fractals;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

/**
 * Created by a s u s on 09.06.2017.
 */

public class DrawPlasmaFractal {
    private Paint paint = new Paint();
    public int width= 400;
    public int height=400;
    public void drawPlasmaFractal(Canvas canvas) {
        Random random = new Random();
        double c1 = random.nextFloat();
        double c2 = random.nextFloat();
        double c3 = random.nextFloat();
        double c4 = random.nextFloat();
        draw(canvas, width / 2, height / 2, height / 2, 1, c1, c2, c3, c4);
    }

    public void draw(Canvas canvas, double x, double y, double size, double stddev, double c1, double c2, double c3, double c4) {
        Random random = new Random();
        if (size < 0.2) {
            return;
        }
        double cM = ((c1 + c2 + c3 + c4) / 4.0 + stddev * random.nextFloat());
        int color = Color.HSVToColor(new float[]{(float) (cM * 360), 0.8f, 0.8f});
        paint.setColor(color);
        canvas.drawPoint((float) x, (float) y, paint);

        double cT = ((c1 + c2) / 2.0);    // top
        double cB = ((c3 + c4) / 2.0);    // bottom
        double cL = ((c1 + c3) / 2.0);    // left
        double cR = ((c2 + c4) / 2.0);    // right

        draw(canvas, x - size / 2, y - size / 2, size / 2, stddev / 2, cL, cM, c3, cB);
        draw(canvas, x + size / 2, y - size / 2, size / 2, stddev / 2, cM, cR, cB, c4);
        draw(canvas, x - size / 2, y + size / 2, size / 2, stddev / 2, c1, cT, cL, cM);
        draw(canvas, x + size / 2, y + size / 2, size / 2, stddev / 2, cT, c2, cM, cR);
    }
}
