package com.example.asus.lab.figure.Paint;

/**
 * Created by a s u s on 26.05.2017.
 */

public class PaintId {

    int _color1, _color2,_id;

    public void setColor(int color) {
        _color1 = color;
    }

    public void setColor2(int color) {
        _color2 = color;
    }

    public int getColor() {
        return _color1;
    }

    public int getColor2() {
        return _color2;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        _id = id;
    }

    public Paintm clone() {
        Paintm p = new Paintm();
        p.setColor(_color1);
        p.setColor2(_color2);
        p.setId(_id);
        return p;
    }
}

