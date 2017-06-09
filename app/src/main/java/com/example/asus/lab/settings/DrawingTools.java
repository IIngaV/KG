package com.example.asus.lab.settings;


import android.graphics.Bitmap;
import android.view.MotionEvent;

import com.example.asus.lab.settings.MainActivity;

/**
 * Created by a s u s on 10.06.2017.
 */

public abstract class DrawingTools {
    private Bitmap mainBitmap;
    private Bitmap fakeBitmap;
    private int color;

    public DrawingTools(Bitmap mainBitmap, Bitmap fakeBitmap) {
        this.mainBitmap = mainBitmap;
        this.fakeBitmap = fakeBitmap;
        color = MainActivity.appSettings.getDrawingColor();
    }

    public Bitmap getMainBitmap() {
        return mainBitmap;
    }

    public Bitmap getFakeBitmap() {
        return fakeBitmap;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void transferToMainBitmap() {}


    public abstract void onTouch(MotionEvent motionEvent);
}
