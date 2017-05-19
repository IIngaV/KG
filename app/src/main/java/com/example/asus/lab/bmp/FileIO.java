package com.example.asus.lab.bmp;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.example.asus.lab.MainActivity;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

public class FileIO {
    final String LOG_TAG = "LOGGING";

    private MainActivity mainActivity = MainActivity.ma;

    File sdPath;
    String sdPathw;

    public FileIO() {
        // проверяем доступность SD
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Log.d(LOG_TAG, "SD-карта не доступна: " + Environment.getExternalStorageState());
            Toast.makeText(mainActivity, "15", Toast.LENGTH_SHORT).show();
            return;
        }
        else{Toast.makeText(mainActivity, "9", Toast.LENGTH_SHORT).show();}
        // получаем путь к SD
        //sdPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        /*sdPath = Environment.getExternalStorageDirectory();
        // добавляем свой каталог к пути
        Toast.makeText(mainActivity, "путь: " + sdPath.toString(), Toast.LENGTH_SHORT).show();
        sdPath = new File(sdPath.getAbsolutePath() + "/" + "draw");*/
        //String sdPathw = Environment.getExternalStorageDirectory().getPath();
        sdPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String state = Environment.getExternalStorageState();
        Toast.makeText(mainActivity, "путь: " + sdPath, Toast.LENGTH_SHORT).show();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            Toast.makeText(mainActivity, "ert: " + state, Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(mainActivity, "uyt: " + state, Toast.LENGTH_SHORT).show();
        }


    }

    public String[] listFiles() {
        File[] files = sdPath.listFiles();
        if (files == null) return null;
        String[] names = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            names[i] = files[i].getName();
        }
        return names;
    }

    private File openFile(String fileName) throws IOException {
        File sdFile = new File(sdPath, fileName);
        Toast.makeText(mainActivity, "kruto ", Toast.LENGTH_SHORT).show();
        if (sdFile.exists()) {
            sdFile.createNewFile();
        }
        /* if (!sdFile.exists()) {
            Toast.makeText(mainActivity, "vbh ", Toast.LENGTH_SHORT).show();
            Toast.makeText(mainActivity, sdFile.toString(), Toast.LENGTH_SHORT).show();
            Toast.makeText(mainActivity, sdPath.toString(), Toast.LENGTH_SHORT).show();
            boolean ehjl;

            Toast.makeText(mainActivity, "negbwf ", Toast.LENGTH_SHORT).show();
            ehjl = sdFile.createNewFile();
            Toast.makeText(mainActivity, sdPath.toString(), Toast.LENGTH_SHORT).show();
            if (sdFile.createNewFile()){
                Toast.makeText(mainActivity, "ehjl2 ", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(mainActivity, "ehjl3 ", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(mainActivity, "ehjl ", Toast.LENGTH_SHORT).show();
            Log.d("Создание файла,если нет",LOG_TAG);
            Toast.makeText(mainActivity, "Файл не ", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(mainActivity, "Файл ", Toast.LENGTH_SHORT).show();
        }*/
        return sdFile;
    }

    public boolean writeBMP(Bitmap bmp) {
        //формируем объект File, который содержит путь к файлу
        boolean check = false;
        try {
            Date date = new Date();
            DateFormat df = DateFormat.getDateTimeInstance();
            String fileName = df.format(date)+".bmp";
            Toast.makeText(mainActivity, fileName, Toast.LENGTH_SHORT).show();
            File sdFile = openFile(fileName);
            Toast.makeText(mainActivity, "66", Toast.LENGTH_SHORT).show();
            Toast.makeText(mainActivity, sdFile.toString(), Toast.LENGTH_SHORT).show();
            FileBitmapWriter fbw = new FileBitmapWriter(sdFile);
            System.err.println("  Toast.makeText(mainActivity, \"12.\", Toast.LENGTH_SHORT).show(); ");
            Toast.makeText(mainActivity, "12.", Toast.LENGTH_SHORT).show();

            System.err.println(" check = fbw.write(bmp); ");
            check = fbw.write(bmp);
            System.err.println(" Файл записан на SD: \" + sdFile.getAbsolutePath(), Toast.LENGTH_SHORT).show() ");
            Toast.makeText(mainActivity,"Файл записан на SD: " + sdFile.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return check;
    }


    public Bitmap readBMP(String fileName) throws BitmapException {
        // формируем объект File, который содержит путь к файлу
        Bitmap bmp = null;
        try {
            File sdFile = openFile(fileName);
            Log.d("Открыли файл",LOG_TAG);
            FileBitmapReader fbr = new FileBitmapReader(sdFile);
            Log.d("Создали объект ридера",LOG_TAG);
            bmp = fbr.read();
            Log.d("Считали бмп",LOG_TAG);
        }catch (IOException e) {
            e.printStackTrace();
        }
        return bmp;
    }
}
