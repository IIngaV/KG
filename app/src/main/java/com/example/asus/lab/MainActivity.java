package com.example.asus.lab;

import android.app.AlertDialog;
import android.app.usage.UsageEvents;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.TransactionTooLargeException;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnTouchListener;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.lab.figure.line.DrawLineBr;

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

    String sDown;
    String sMove;
    String sUp;
    DrawView drawView;
    DrawLineBr dlb;
    MainActivity ma;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /*tv = new TextView(this);
        tv.setOnTouchListener(this);*/

        drawView = (DrawView) findViewById(R.id.view);
        //setContentView(tv);
        //setContentView(new DrawView(this));
    }

    /*@Override
    public boolean onTouch(View view,MotionEvent event) {
        x = event.getX();
        y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: // нажатие
                sDown = "Down: " + x + "," + y;
                sMove = ""; sUp = "";
                break;
            case MotionEvent.ACTION_MOVE: // движение
                sMove = "Move: " + x + "," + y;
                break;
            case MotionEvent.ACTION_UP: // отпускание
            case MotionEvent.ACTION_CANCEL:
                sMove = "";
                sUp = "Up: " + x + "," + y;
                break;
        }
        tv.setText(sDown + "\n" + sMove + "\n" + sUp);
        return true;
    }*/

    //@Override
    /*protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawView(this));*/
        /*//setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


    //}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public void onClick(View v) {
           AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Выберите количество точек")
                   // .setMessage("Покормите кота!")
                   // .setIcon(R.drawable.ic_menu_camera)
                    .setCancelable(false)
                    .setNegativeButton("2",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    col=2;
                                    colFake = 2;
                                    Toast toast6=Toast.makeText(getApplicationContext(),
                                            "Кривая Безье! 2 точки",
                                            Toast.LENGTH_SHORT);
                                    toast6.setGravity(Gravity.CENTER, 0, 0);
                                    toast6.show();
                                    DrawView.instrument=6;
                                    dialog.cancel();
                                }
                            })
                    .setPositiveButton("5",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        col=5;
                        colFake = 5;
                        Toast toast6=Toast.makeText(getApplicationContext(),
                                "Кривая Безье! 5 точек",
                                Toast.LENGTH_SHORT);
                        toast6.setGravity(Gravity.CENTER, 0, 0);
                        toast6.show();
                        DrawView.instrument=6;
                        dialog.cancel();
                    }
                })
                    .setNeutralButton("3",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    col=3;
                                    colFake = 3;
                                    Toast toast6=Toast.makeText(getApplicationContext(),
                                            "Кривая Безье! 3 точки",
                                            Toast.LENGTH_SHORT);
                                    toast6.setGravity(Gravity.CENTER, 0, 0);
                                    toast6.show();
                                    DrawView.instrument=6;
                                    dialog.cancel();
                                }
                            });
            System.out.print(col);
            AlertDialog alert = builder.create();
            alert.show();

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

                    /*Intent intent = new Intent(this, ActivityBrezier.class);
                    startActivity(intent);*/
                    DrawView.instrument=6;
                    onClick(v);
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
             }

        return super.onOptionsItemSelected(item);
    }

}



