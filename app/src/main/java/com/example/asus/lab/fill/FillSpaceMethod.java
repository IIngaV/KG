package com.example.asus.lab.fill;

import android.graphics.Bitmap;

import com.example.asus.lab.figure.Paint.Paintm;
import com.example.asus.lab.figure.circle.DrawCircleBr;

import java.util.ArrayDeque;

/**
 * Created by a s u s on 02.06.2017.
 */

public class FillSpaceMethod extends DrawCircleBr{
    public void draw(Bitmap bmp, int[] points, Paintm paint) {
        int x, y, bufXL, bufXR;
        ArrayDeque<Integer> qX = new ArrayDeque<Integer>();
        ArrayDeque<Integer> qY = new ArrayDeque<Integer>();

        int color = paint.getColor();
        int bufColor = bmp.getPixel(points[0], points[1]);
        if (color == bufColor) return;

        qX.push(points[0]);
        qY.push(points[1]);

        while (!qX.isEmpty()) {
            x = qX.pop();
            y = qY.pop();

            if (checkLimits(bmp, x, y))
                bmp.setPixel(x, y, color);

            //проход вправо
            bufXR = x + 1;
            while (checkLimits(bmp, bufXR, y) && bmp.getPixel(bufXR, y) == bufColor) {
                bmp.setPixel(bufXR, y, color);
                bufXR++;
            }

            //проход влево
            bufXL = x - 1;
            while (checkLimits(bmp, bufXL, y) && bmp.getPixel(bufXL, y) == bufColor) {
                bmp.setPixel(bufXL, y, color);
                bufXL--;
            }

            //ниже
            y = y - 1;
            int j = 0;
            while (j < 2) {
                for (int i = bufXL + 1; i < bufXR; i++) {
                    if (checkLimits(bmp, i, y)) {
                        if (bmp.getPixel(i, y) == bufColor) {
                            if (checkLimits(bmp, i + 1, y)) {
                                if (bmp.getPixel(i + 1, y) != bufColor || i + 1 == bufXR) {
                                    qX.push(i);
                                    qY.push(y);
                                }
                            } else {
                                if (i + 1 == bufXR) {
                                    qX.push(i);
                                    qY.push(y);
                                }
                            }
                        }
                    }
                }
                j++;
                y+=2;
            }
        }
    }
}
