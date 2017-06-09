package com.example.asus.lab.settings;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.lab.R;
import com.example.asus.lab.bmp.BitmapException;
import com.example.asus.lab.bmp.FileIO;
import com.example.asus.lab.bmp.OpenFileDialog;
import com.example.asus.lab.bmp.S_Log;
import com.example.asus.lab.figure.line.DrawLineBr;
import com.example.asus.lab.snake.GameActivity;
import com.skydoves.colorpickerview.ColorPickerView;

import static java.lang.Math.abs;

public class MainActivity extends AppCompatActivity /*implements OnTouchListener*/ {

    TextView tv;
    float x;
    float y;
    float x1;
    float y1;
    int x3, x4, y3, y4;
    Canvas canvas;
    Paint p;
    View v;
    public static int col = 0;
    public  static int colFake = 0;
    Button buttonBezier;
    String sDown;
    String sMove;
    String sUp;
    DrawView drawView;
    DrawLineBr dlb;
    public static MainActivity ma;
    public static AppSettings appSettings;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        S_Log.initDebugLog(this.getPackageName(),true);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawView = (DrawView) findViewById(R.id.view);
        buttonBezier=(Button)findViewById(R.id.buttonBezier);
        buttonBezier.setVisibility(View.GONE);
        ma = MainActivity.this;
        appSettings = new AppSettings();
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id)
            {
                case R.id.action_begin:
                    Toast toast4 = Toast.makeText(getApplicationContext(),
                            "Начало",
                            Toast.LENGTH_SHORT);
                    toast4.setGravity(Gravity.CENTER, 0, 0);
                    toast4.show();
                    DrawView.instrument=1;
                    //drawView.drawPoint(x, y);
                    return true;
                case R.id.action_rubber:
                    Toast toast5= Toast.makeText(getApplicationContext(),
                            "Стерка",
                            Toast.LENGTH_SHORT);
                    toast5.setGravity(Gravity.CENTER, 0, 0);
                    toast5.show();
                    DrawView.instrument=9;
                    ///drawView.drawRubber(x, y);
                    return true;

                case R.id.action_line_br:
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Линия (Брезенхем)!",
                            Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    DrawView.instrument=2;
                    //dlb.drawLineBr(canvas, p);
                    return true;

                case R.id.action_line_param:
                    Toast toast1 = Toast.makeText(getApplicationContext(),
                            "Линия(параметрически)!",
                            Toast.LENGTH_SHORT);
                    toast1.setGravity(Gravity.CENTER, 0, 0);
                    toast1.show();
                    DrawView.instrument=3;
                    //drawView.drawLineParam(x3,y,x4,y1);
                    return true;
                case R.id.action_circle_br:
                    Toast toast2 = Toast.makeText(getApplicationContext(),
                            "Окружность (Брезенхем)!",
                            Toast.LENGTH_SHORT);
                    toast2.setGravity(Gravity.CENTER, 0, 0);
                    toast2.show();
                    DrawView.instrument=5;
                    //drawView.drawLineBr();
                    return true;
                case R.id.action_circle_param:
                    Toast toast3=Toast.makeText(getApplicationContext(),
                            "Окружность(параметрически)!",
                    Toast.LENGTH_SHORT);
                    toast3.setGravity(Gravity.CENTER, 0, 0);
                    toast3.show();
                    DrawView.instrument=4;
                   // drawView.drawCircleParam(x3,y3,x4,y4);
                    return true;

                case R.id.action_curve_bezier:
                    DrawView.instrument=6;
                    buttonBezier.setVisibility(View.VISIBLE);
                    buttonBezier.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            drawView.drawLineBese();
                        }
                    });
                   // onClick(v);
                    return true;
                case R.id.action_head:
                    Toast toast7=Toast.makeText(getApplicationContext(),
                            "Голова!",
                            Toast.LENGTH_SHORT);
                    toast7.setGravity(Gravity.CENTER, 0, 0);
                    toast7.show();
                    DrawView.instrument=7;
                    // drawView.drawCircleParam(x3,y3,x4,y4);
                    return true;
                case R.id.action_save:
                    /*Toast toast8=Toast.makeText(getApplicationContext(),
                            "Сохранить!",
                            Toast.LENGTH_SHORT);
                    toast8.setGravity(Gravity.CENTER, 0, 0);
                    toast8.show();
                    DrawView.instrument=8;*/
                    Toast.makeText(ma, "Запись на файл начата.", Toast.LENGTH_SHORT).show();
                    FileIO f = new FileIO();
                    f.writeBMP(drawView.getBitmap());
                    Toast.makeText(ma, "Сохранение успешно!", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_limits:
                    Toast toast10=Toast.makeText(getApplicationContext(),
                            "Рамка!",
                            Toast.LENGTH_SHORT);
                    toast10.setGravity(Gravity.CENTER, 0, 0);
                    toast10.show();
                    DrawView.instrument=10;
                    return true;
                case R.id.action_read:
                    OpenFileDialog ofd3 = new OpenFileDialog(this, new OpenFileDialog.OnFileNameSetListener() {
                        @Override
                        public void onSet(String fileName) {
                            //Toast.makeText(ma, getString(R.string.openingFileStart), Toast.LENGTH_SHORT).show();
                            FileIO f = new FileIO();
                            Bitmap bmp = null;
                            try {
                                bmp = f.readBMP(fileName);
                            } catch (BitmapException e) {
                                e.printStackTrace();
                            }
                            if (bmp != null) {
                                Toast.makeText(ma, "Считывание завершено.\nНачата отрисовка.", Toast.LENGTH_SHORT).show();

                                drawView.setBitmap(bmp);
                                drawView.invalidate();

                                //_onTouch.getHandlerOffset().update();
                                Toast.makeText(ma, "Готово.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ma, "Не удалось считать файл.", Toast.LENGTH_SHORT).show();
                                //Toast.makeText(mainActivity, bmp.getHeight() + bmp.getWidth(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    ofd3.show();
                    return true;
                case R.id.action_color_picker:
                    LayoutInflater inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
                    View layout = inflater.inflate(R.layout.color_picker, (ViewGroup)findViewById(R.id.new_color_picker));
                    final AlertDialog.Builder builder = new AlertDialog.Builder(this)
                            .setView(layout);
                    final AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    final ColorPickerView colorPickerView = (ColorPickerView) alertDialog.findViewById(R.id.colorPickerView);
                    colorPickerView.setColorListener(new ColorPickerView.ColorListener() {
                        @Override
                        public void onColorSelected(int color) {
                            drawView.getP().setColor(colorPickerView.getColor());
                        }
                    });
                    Button button = (Button)layout.findViewById(R.id.color_picker_button);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alertDialog.cancel();
                        }
                    });
                    return true;
                /*case R.id.action_line_ermit:
                    onTouch.setHandler(new DrawErmit(d));
                    break;
                case R.id.action_b_spline:
                    SetValueDialog ssd1 = new SetValueDialog(ma, new SetValueDialog.OnScaleSetListener() {

                        public void onSet(float scale) {
                            _onTouch.setHandler(new DrawBSpline(_pd, (int) scale));
                        }
                    }, "Порядок сплайна", 3, 2, 10);
                    ssd1.show();
                    break;
                case R.id.action_nurbs_spline:
                    SetValueDialog ssd2 = new SetValueDialog(ma, new SetValueDialog.OnScaleSetListener() {

                        public void onSet(float scale) {
                            _onTouch.setHandler(new DrawNURBSSpline(_pd, (int) scale));
                        }
                    }, "Порядок NURBS сплайна", 3, 2, 10);
                    ssd2.show();
                    break;*/
                case R.id.action_color_picker1:
                    LayoutInflater inflater2 = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
                    View layout2 = inflater2.inflate(R.layout.color_picker, (ViewGroup)findViewById(R.id.new_color_picker));
                    final AlertDialog.Builder builder2 = new AlertDialog.Builder(this)
                            .setView(layout2);
                    final AlertDialog alertDialog2 = builder2.create();
                    alertDialog2.show();
                    final ColorPickerView colorPickerView2 = (ColorPickerView) alertDialog2.findViewById(R.id.colorPickerView);
                    colorPickerView2.setColorListener(new ColorPickerView.ColorListener() {
                        @Override
                        public void onColorSelected(int color) {
                            drawView.getP().setColor(colorPickerView2.getColor());
                        }
                    });
                    Button button2 = (Button)layout2.findViewById(R.id.color_picker_button);
                    button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alertDialog2.cancel();
                        }
                    });

                    final ColorPickerView colorPickerView3 = (ColorPickerView) alertDialog2.findViewById(R.id.colorPickerView);
                    colorPickerView3.setColorListener(new ColorPickerView.ColorListener() {
                        @Override
                        public void onColorSelected(int color) {
                            drawView.getP().setColor(colorPickerView3.getColor());
                        }
                    });
                    Button button3 = (Button)layout2.findViewById(R.id.color_picker_button);
                    button3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alertDialog2.cancel();
                        }
                    });
                    return true;
                case R.id.action_brush_width:
                    LayoutInflater inflater1 = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
                    View layout1 = inflater1.inflate(R.layout.settings_dialog, (ViewGroup)findViewById(R.id.new_brush_root));
                    final AlertDialog.Builder builder1 = new AlertDialog.Builder(this)
                            .setView(layout1);
                    final AlertDialog alertDialog1 = builder1.create();
                    alertDialog1.show();
                    final EditText brushSize = (EditText)layout1.findViewById(R.id.new_brush_size_editText);
                    Button button1 = (Button)layout1.findViewById(R.id.button_new_brush_size);
                    button1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Paint newMPaint = drawView.getP();
                            newMPaint.setStrokeWidth(Float.parseFloat(String.valueOf(brushSize.getText())));
                            drawView.setP(newMPaint);
                            alertDialog1.cancel();
                        }
                    });
                    return true;
                case R.id.nav_scale:
                    LayoutInflater inflater3 = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
                    View layout3 = inflater3.inflate(R.layout.scale_dialog, (ViewGroup)findViewById(R.id.scale_dialog_root_element));
                    final AlertDialog.Builder builder3 = new AlertDialog.Builder(this)
                            .setView(layout3);
                    final AlertDialog alertDialog3 = builder3.create();
                    alertDialog3.show();
                    SeekBar sb = (SeekBar)layout3.findViewById(R.id.scale_dialog_seekbar);
                    final TextView tv = (TextView)layout3.findViewById(R.id.scale_dialog_textView);
                    tv.setText("1");
                    Button button4 = (Button)layout3.findViewById(R.id.scale_dialog_button);
                    final int[] scale = {0};
                    sb.setMax(25);
                    sb.setProgress(1);
                    sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                            tv.setText(String.valueOf(progress));
                            scale[0] = progress;
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {

                        }
                    });
                    button4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            drawView.setScale(scale[0]);
                            alertDialog3.cancel();
                        }
                    });
                case R.id.action_clean:
                    Toast.makeText(ma, "Экран очищен.", Toast.LENGTH_SHORT).show();
                    drawView.clean();
                case R.id.action_mosaic:
                    DrawView.instrument = 11;
                    Toast.makeText(ma, getString(R.string.takeMosaic), Toast.LENGTH_SHORT).show();
                    drawView.drawMosaic();
                case R.id.draw_pifagor_fractal:
                    Toast toast9=Toast.makeText(getApplicationContext(),
                            "Фрактал Пифагора!",
                            Toast.LENGTH_SHORT);
                    toast9.setGravity(Gravity.CENTER, 0, 0);
                    toast9.show();
                    DrawView.instrument=19;
                    return true;
                case R.id.draw_mandelbrot_fractal:
                    Toast toast11=Toast.makeText(getApplicationContext(),
                            "Фрактал Mandelbrot!",
                            Toast.LENGTH_SHORT);
                    toast11.setGravity(Gravity.CENTER, 0, 0);
                    toast11.show();
                    DrawView.instrument=20;
                    return true;
                case R.id.draw_plasma:
                    Toast toast12=Toast.makeText(getApplicationContext(),
                            "Plasma!",
                            Toast.LENGTH_SHORT);
                    toast12.setGravity(Gravity.CENTER, 0, 0);
                    toast12.show();
                    DrawView.instrument=21;
                    return true;
                case R.id.draw_fern:
                    Toast toast13=Toast.makeText(getApplicationContext(),
                            "Fern!",
                            Toast.LENGTH_SHORT);
                    toast13.setGravity(Gravity.CENTER, 0, 0);
                    toast13.show();
                    DrawView.instrument=22;
                    return true;
                case R.id.action_line_ermit:
                    Toast toast14=Toast.makeText(getApplicationContext(),
                            "Кривая Эрмита!",
                            Toast.LENGTH_SHORT);
                    toast14.setGravity(Gravity.CENTER, 0, 0);
                    toast14.show();
                    DrawView.instrument=23;
                    return true;
                case R.id.action_b_spline:
                    Toast toast15=Toast.makeText(getApplicationContext(),
                            "B-Spline!",
                            Toast.LENGTH_SHORT);
                    toast15.setGravity(Gravity.CENTER, 0, 0);
                    toast15.show();
                    DrawView.instrument=24;
                    return true;
                case R.id.action_nurbs_spline:
                    Toast toast16=Toast.makeText(getApplicationContext(),
                            "NURBSpline!",
                            Toast.LENGTH_SHORT);
                    toast16.setGravity(Gravity.CENTER, 0, 0);
                    toast16.show();
                    DrawView.instrument=25;
                    return true;
            }

        return super.onOptionsItemSelected(item);
    }

}



