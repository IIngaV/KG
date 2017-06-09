package com.example.asus.lab.bmp;

import android.graphics.Bitmap;
import android.widget.Toast;

import com.example.asus.lab.settings.MainActivity;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

//картинка идет как бы перевернутая вверх ногами.
// То есть сначала записана нижняя строка, потом предпоследняя и так далее до самого верха.
// И, в-третьих, как написано в [1], если размер строки растра не кратен 4,
// то она дополняется от 1 до 3 пустыми (нулевыми) байтами, чтобы длина строки оказалась кратна параграфу.


// файлы BMP с глубиной 16 и 24 бит/пиксель не имеют таблиц цветов;
// в этих файлах значения пикселей растрового массива непосредственно характеризуют значения цветов RGB

public class FileBitmapWriter {
    private OutputStream fs;


    public FileBitmapWriter(File file)  {
        System.err.println(" FileBitmapWriter(File file) ");
try {
    Toast.makeText(MainActivity.ma, "file: " + file.toString(), Toast.LENGTH_SHORT).show();
    fs = new BufferedOutputStream( new FileOutputStream(file));
    Toast.makeText(MainActivity.ma, "777", Toast.LENGTH_SHORT).show();
}
catch(Exception e){
    e.printStackTrace();
}
        System.err.println(" FileBitmapWriter(File file) end");
    }

    public boolean write(Bitmap bmp) throws IOException {
        int width = 400/*bmp.getWidth()*/;
        int height = 600/*bmp.getHeight()*/;
        int spacerSize = 0;//выравнивание
        if (((width * 3) % 4) > 0) {  //если строка кратна 4 , то нулями не докидываем
            spacerSize = 4 - (width * 3) % 4;
        }
        //int spacerSize = 4 - (width * 3) % 4; //выравнивание
        int length = width * height * 3 + height * spacerSize;
        int size = length + 54;

        //тип файла
        writeBytes(0x4D42, 2);
        //размер
        writeBytes(size, 4);
        //пустые байты
        writeBytes(0, 4);
        //адрес начала массива цветов
        writeBytes(54, 4);
        //размер структуры
        writeBytes(40, 4);
        //ширина
        writeBytes(width, 4);
        //высота
        writeBytes(height, 4);
        //количество плоскостей
        writeBytes(1, 2);
        //количество бит на пиксель
        writeBytes(24, 2);
        //метод сжатия
        writeBytes(0, 4);
        //длина растрового массива
        writeBytes(0, 4);
        //горизонтальное разрешения
        writeBytes(0, 4);
        //вертикальное разрешения
        writeBytes(0, 4);
        //количество цветов изображения
        writeBytes(0, 4);
        //количество основных цветов изображения
        writeBytes(0, 4);
        System.err.println("for (int i = height - 1; i >= 0; i--)");
        System.err.println("height="+height);
        System.err.println("width="+width);
        for (int i = height - 1; i >= 0; i--) {
            System.err.println("i="+i);
            for (int j = 0; j < width; j++) {
                System.err.println("j="+j);
                int color = bmp.getPixel(j, i);
                if (color == 0) {  //0 - прозрачный цвет
                    writeBytes(0xFFFFFF, 3); // поэтому пишем белый цвет в файл
                } else {
                    writeBytes(color & 0xFF, 1);
                    writeBytes((color >> 8) & 0xFF, 1);
                    writeBytes((color >> 16) & 0xFF, 1);
                }

            }

            //System.err.println(" writeBytes(0, spacerSize);)");

            writeBytes(0, spacerSize);
        }

        fs.close();
        return true;
    }

    private void writeBytes(int value, int n) throws IOException {
        for (int i = 0; i < n; i++) {
            int tByte = value & 0xFF;
            fs.write(tByte);
            value >>= 8; //битовый сдвиг на 8
        }
    }
}
