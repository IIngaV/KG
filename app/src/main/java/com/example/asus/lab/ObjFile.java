package com.example.asus.lab;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Environment;
import android.util.Log;

import com.example.asus.lab.figure.line.DrawLineParam;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ObjFile {
    final String LOG_TAG = "LOGGING";
    private String file = "123.obj";
    private ArrayList<Point> verts;
    private ArrayList<String[]> faces;
    private File sdPath;


    public ObjFile(){
        verts = new ArrayList<>();
        faces = new ArrayList<>();

        // проверяем доступность SD
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Log.d(LOG_TAG, "SD-карта не доступна: " + Environment.getExternalStorageState());
            return;
        }
        // получаем путь к SD
        sdPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
    }

    public String getFile(){
        return file;
    }

    public void setFile(String file){
        this.file = file;
    }


    public boolean readFile() {
        // формируем объект File, который содержит путь к файлу
        File sdFile = new File(sdPath, file);
        try {
            // открываем поток для чтения
            BufferedReader br = new BufferedReader(new FileReader(sdFile));
            String line = "";
            // читаем содержимое
            while ((line = br.readLine()) != null) {
                //System.out.println(line);
                if (line.length() != 0) {
                    if (line.charAt(0) == 'v' && line.charAt(1) == ' ') {
                        String[] coordinates = line.split(" ");
                        Float x = (Float.parseFloat(coordinates[1]) + 1) * 340;
                        Float y = (-Float.parseFloat(coordinates[2]) + 1) * 430;
                        verts.add(new Point(x, y));
                    }
                    if (line.charAt(0) == 'f' && line.charAt(1) == ' ') {
                        String[] temp = line.split(" ");
                        String[] faces = new String[3];
                        for (int i = 0; i < 3; i++) {
                            faces[i] = temp[i + 1].split("/")[0];
                        }
                        this.faces.add(faces);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean drawObj(Canvas canvas, Paint mPaint) {
        for (int i = 0; i < faces.size() - 1; i++) {
            for (int j = 0; j < 3; j++) {

                canvas.drawLine(verts.get(Integer.parseInt(faces.get(i)[j]) - 1).getX(),
                        verts.get(Integer.parseInt(faces.get(i)[j]) - 1).getY(),
                        verts.get(Integer.parseInt(faces.get(i)[(j + 1) % 3]) - 1).getX(),
                        verts.get(Integer.parseInt(faces.get(i)[(j + 1) % 3]) - 1).getY(),
                        mPaint);
            }
        }
        return true;
    }
}
