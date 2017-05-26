package com.example.asus.lab.figure.curve;

import android.graphics.Bitmap;

import com.example.asus.lab.figure.Paint.Paint;

/**
 * Created by a s u s on 26.05.2017.
 */

public class CurveNURBSSpline {
        int _rank;
        int _n;
        int[] _t;
        int[] _w;

        public CurveNURBSSpline(int rank) {
            _rank = rank;
            int[] w = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
            _w = w;
        }


        public void draw(Bitmap bmp, int[] points, Paint paint) {
            int color = paint.getColor();
            _n = points.length / 3;
            _t = new int[_n + _rank + 1];

            int k = 0;
            while (k <= _rank) {
                _t[k++] = 0;
            }
            while (k < _n) {
                _t[k] = k - _rank + 1;
                k++;
            }
            int temp = _n - _rank + 2;
            while (k <= _n + _rank) {
                _t[k] = temp;
                k++;
            }
            float step = .001F;
            float t = .0F;
            float resXch, resXzn, resYch, resYzn;
            while (t <= temp) {
                resXch = 0;
                resXzn = 0;
                resYch = 0;
                resYzn = 0;
                for (int j = 0, i = 0; j < _n; j++, i += 3) {
                    float f = N(j, _rank, t);
                    resXch += points[i] * f * _w[j];
                    resYch += points[i + 1] * f * _w[j];

                    resXzn += f * _w[j];
                    resYzn += f * _w[j];
                }

                try {
                    bmp.setPixel((int) (resXch / resXzn), (int) (resYch / resYzn), color);
                } catch (Exception e) {
                }
                t += step;
            }

        }


        private float N(int i, int k, float t) {
            if (k == 0) {
                if (t >= _t[i] && t < _t[i + 1]) {
                    return 1;
                } else {
                    return 0;
                }
            } else {
                float ch1 = (t - _t[i]) * N(i, k - 1, t);
                int znam1 = (_t[i + k] - _t[i]);

                if (znam1 == 0) {
                    ch1 = 0;
                } else {
                    ch1 /= znam1;
                }

                float ch2 = (_t[i + k + 1] - t) * N(i + 1, k - 1, t);
                int znam2 = (_t[i + k + 1] - _t[i + 1]);

                if (znam2 == 0) {
                    ch2 = 0;
                } else {
                    ch2 /= znam2;
                }

                return ch1 + ch2;
            }
        }

    }

