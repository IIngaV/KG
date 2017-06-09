package com.example.asus.lab.settings;

import android.graphics.Color;

/**
 * Created by a s u s on 10.06.2017.
 */

public class AppSettings {

        private int bitmapWidth;
        private int bitmapHeight;

        private int defaultTool;

        //private boolean isObj
        private boolean isObjFilling;
        private boolean isObjRandomColor;



        private int drawingColor;

        private boolean isScrollEnabled;
        private boolean isFillEnabled;

        private boolean isLineColorApprox;


        AppSettings() {
            bitmapWidth = 2000;
            bitmapHeight = 2000;

            defaultTool = 1;


            isObjFilling = true;
            isObjRandomColor = true;


            drawingColor = Color.BLACK;

            isScrollEnabled = false;
            isFillEnabled = false;

            isLineColorApprox = false;

        }

        int getBitmapWidth() {
            return bitmapWidth;
        }

        void setBitmapWidth(int bitmapWidth) {
            this.bitmapWidth = bitmapWidth;
        }

        int getBitmapHeight() {
            return bitmapHeight;
        }

        void setBitmapHeight(int bitmapHeight) {
            this.bitmapHeight = bitmapHeight;
        }



        public int getDefaultTool() {
            return defaultTool;
        }


        public boolean isObjFilling() {
            return isObjFilling;
        }

        public void setObjFilling(boolean objFilling) {
            this.isObjFilling = objFilling;
        }

        public boolean isObjRandomColor() {
            return isObjRandomColor;
        }

        public void setObjRandomColor(boolean objRandomColor) {
            isObjRandomColor = objRandomColor;
        }



        public int getDrawingColor() {
            return drawingColor;
        }

        public void setDrawingColor(int drawingColor) {
            this.drawingColor = drawingColor;
        }


        public boolean isScrollEnabled() {
            return isScrollEnabled;
        }

        public void setScroll(boolean isEnabled) {
            isScrollEnabled = isEnabled;
        }

        public boolean isFillEnabled() {
            return isFillEnabled;
        }

        public void setFill(boolean isEnabled) {
            isFillEnabled = isEnabled;
        }


        public boolean isLineColorApprox() {
            return isLineColorApprox;
        }

        public void setLineColorApprox(boolean lineColorApprox) {
            isLineColorApprox = lineColorApprox;
        }


    }

