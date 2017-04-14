package com.example.asus.lab.bmp;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.example.asus.lab.MainActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


// файлы BMP с глубиной 16 и 24 бит/пиксель не имеют таблиц цветов;
// в этих файлах значения пикселей растрового массива непосредственно характеризуют значения цветов RGB

public class FileBitmapReader {
    final String LOG_TAG = "LOGGING";

    private FileInputStream fs;
    private int size = 0, _offset = 0, width = 0, height = 0;
    private MainActivity mainActivity = MainActivity.ma;

    public FileBitmapReader(File file) throws FileNotFoundException {
        fs = new FileInputStream(file);
        //Toast.makeText(mainActivity, file.toString(), Toast.LENGTH_SHORT).show();
    }

    public Bitmap read() throws BitmapException, IOException {
        Bitmap bmp;

        //считывание типа файла
        if (readBytes(2) != 0x4D42) throw new BitmapException("Неверный формат файла");
        Log.d(LOG_TAG, "считали тип файла");

        //считывание размера
        size = readBytes(4);
        if (size == -1) throw new BitmapException("Не удалось получить размер файла");
        Log.d(LOG_TAG, "считали размер");

        //пропуск пустых байтов
        if (readBytes(4) == -1) return null;
        Log.d(LOG_TAG, "пропустили пустые байты");

        //считывание адресса начала массива цветов
        if (readBytes(4) == -1) return null;
        Log.d(LOG_TAG, "считали адрес начала массива цветов");

        //считывание размера структуры
        if (readBytes(4) == -1) return null;
        Log.d(LOG_TAG, "считали размер структуры");

        //считывание ширины
        width = readBytes(4);
        if (width == -1) return null;
        Log.d(LOG_TAG, "считали ширину");

        //считывание высоты
        height = readBytes(4);
        if (height == -1) return null;
        Log.d(LOG_TAG, "считали высоту");

        int spacerSize = 0;//выравнивание

        if (((width * 3) % 4) > 0) {  //если строка кратна 4 , то нулями не докидываем
            spacerSize = 4 - (width * 3) % 4;
        }
        int length = width * height * 3 + height * spacerSize;

        if (size != length + 54) throw new BitmapException("Ошибка в заголовке файла. Не совпадает размер.");

        //считывание количества плоскостей
        if (readBytes(2) != 1) return null;
        Log.d(LOG_TAG, "считали колво плоскостей");

        //считывание количества бит на пиксель
        if (readBytes(2) != 24) throw new BitmapException("Поддерживается файл BMP только 24bit.");
        Log.d(LOG_TAG, "считали колво бит на пиксель");

        //считывание метода сжатия
        if (readBytes(4) != 0) throw new BitmapException("Поддерживается файл BMP только без сжатия.");
        Log.d(LOG_TAG, "считали метод сжатия");

        //считывание длинны растрового массива
        /*if (*/readBytes(4); //== 0) throw new BitmapException("Ошибка в заголовке файла.Размер Растрового массива.");
        Log.d(LOG_TAG, "считали длину растрового массива");

        //считывание горизонтального разрешения
        if (readBytes(4) == -1) return null;
        Log.d(LOG_TAG, "считали горизонтальное разрешение");

        //считывание вертикального разрешения
        if (readBytes(4) == -1) return null;
        Log.d(LOG_TAG, "считали вертикальное разрешение");

        //считывание количество цветов изображения
        if (readBytes(4) == -1) return null;
        Log.d(LOG_TAG, "считали колво цветов изображения");

        //считывание количества основных цветов изображения
        if (readBytes(4) == -1) return null;
        Log.d(LOG_TAG, "считали колво основных цветов изображения");

        Log.d(LOG_TAG, "Считали все байты");
        bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Log.d(LOG_TAG, "Создали новый битмап");

        for (int i = height - 1; i >= 0; i--) {
            for (int j = 0; j < width; j++) {
                int r, g, b;

                b = readBytes(1);
                if (b == -1)
                    throw new BitmapException("Ошибка чтения растрового массива.Синий цвет.");


                g = readBytes(1);
                if (g == -1)
                    throw new BitmapException("Ошибка чтения растрового массива.Зеленый цвет.");


                r = readBytes(1);
                if (r == -1)
                    throw new BitmapException("Ошибка чтения растрового массива.Желтый цвет.");

                bmp.setPixel(j, i, Color.rgb(r, g, b));
            }
            //выравнивание
            if (readBytes(spacerSize) == -1)
                throw new BitmapException("Ошибка чтения байтов выравнивания.");

        }

        Log.d(LOG_TAG, "Закончили считывание");
        fs.close();
        return bmp;
    }

    private int readBytes(int n) throws IOException {
        int result = 0;
        int offset = 0;
        for (int i = 0; i < n; i++) {
            int tByte = fs.read();
            if (tByte == -1) {
                return -1;
            }
            tByte <<= offset;
            result |= tByte;
            offset += 8;
        }
       // System.out.println("result: " + result);
        return result;
    }
}
