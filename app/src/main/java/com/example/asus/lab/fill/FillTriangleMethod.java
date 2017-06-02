package com.example.asus.lab.fill;

import android.graphics.Bitmap;

import com.example.asus.lab.figure.Paint.Paintm;
import com.example.asus.lab.figure.circle.DrawCircleBr;

/**
 * Created by a s u s on 02.06.2017.
 */

public class FillTriangleMethod  extends DrawCircleBr {
    DrawCircleBr dcbr = new DrawCircleBr();

    public void draw(Bitmap bmp, int[] points, Paintm paint) {
        points = points.clone();
        int color = paint.getColor();
        if (points[1] > points[3]) {
            swap(points, 0, 2);
            swap(points, 1, 3);
        }
        if (points[1] > points[5]) {
            swap(points, 0, 4);
            swap(points, 1, 5);
        }
        if (points[3] > points[5]) {
            swap(points, 2, 4);
            swap(points, 3, 5);
        }

        int total_height = points[5] - points[1];

        for (int i = 0; i < total_height; i++) {
            boolean second_half = i > points[3] - points[1] || points[3] == points[1];
            int segment_height = second_half ? points[5] - points[3] : points[3] - points[1];
            float alpha = (float) i / total_height;
            float beta = (float) (i - (second_half ? points[3] - points[1] : 0)) / segment_height; // be careful: with above conditions no division by zero here
            int A = (int) (points[0] + (points[4] - points[0]) * alpha);
            int B = second_half ? (int) (points[2] + (points[4] - points[2]) * beta) : (int) (points[0] + (points[2] - points[0]) * beta);
            if (A > B) {
                int t = A;
                A = B;
                B = t;
            }
            for (int j = A; j <= B; j++) {
                if (checkLimits(bmp, j, points[1] + i)) {
                    bmp.setPixel(j, points[1] + i, color);
                }
            }
        }
    }

    public void swap(int[] points, int i1, int i2) {
        int t = points[i1];
        points[i1] = points[i2];
        points[i2] = t;
    }
}
