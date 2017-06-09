package com.example.asus.lab.bmp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.ListView;
import android.widget.Toast;

import com.example.asus.lab.settings.MainActivity;

public class OpenFileDialog extends AlertDialog.Builder {
    private MainActivity mainActivity = MainActivity.ma;
    public interface OnFileNameSetListener {
        void onSet(String fileName);
    }

    String[] names;
    OnFileNameSetListener mListener;

    public OpenFileDialog(Activity activity, OnFileNameSetListener l) {
        super(activity);

        mListener = l;

        setTitle("Имя файла");
        Toast.makeText(mainActivity, "ur", Toast.LENGTH_SHORT).show();
        FileIO f = new FileIO();
        names = f.listFiles();
        setSingleChoiceItems(names, -1, null); //Если мы в значение выбранного элемента передаем -1,
                                                // то в списке не будет выбранного элемента.

        setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ListView lv = ((AlertDialog) dialog).getListView();
                        if (lv != null) {
                            String fileName = names[lv.getCheckedItemPosition()];
                            mListener.onSet(fileName);
                            dialog.dismiss();
                        }
                    }
                });
        create();
    }
}

