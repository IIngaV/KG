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

    public FileIO() {
        // проверяем доступность SD
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Log.d(LOG_TAG, "SD-карта не доступна: " + Environment.getExternalStorageState());
            return;
        }
        // получаем путь к SD
        sdPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

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
        if (!sdFile.exists()) {
            sdFile.createNewFile();
            Log.d("Создание файла,если нет",LOG_TAG);
            //Toast.makeText(mainActivity, "Файл не ", Toast.LENGTH_SHORT).show();
        }
        return sdFile;
    }

    public boolean writeBMP(Bitmap bmp) {
        //формируем объект File, который содержит путь к файлу
        boolean check = false;
        try {
            Date date = new Date();
            DateFormat df = DateFormat.getDateTimeInstance();
            String fileName = df.format(date);

            File sdFile = openFile(fileName);
            FileBitmapWriter fbw = new FileBitmapWriter(sdFile);
            check = fbw.write(bmp);
            Log.d(LOG_TAG, "Файл записан на SD: " + sdFile.getAbsolutePath());
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
