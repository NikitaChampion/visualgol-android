package com.olympiad_algorithms.visualgol_android;

//import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SelectionSort extends AppCompatActivity implements View.OnClickListener {

    Button sel_sort;
    Button btnSave;
    EditText edit_text;
    private TextView [] txt_num;
    private long num_of_clicks = 0;
    private int []numbers = {7,2,1,3,9,0,8,3,4};
    private int []numbers_2 = {7,2,1,3,9,0,8,3,4};
    private Handler handler = new Handler();
    int maxim = 10, j1 = 0, j2 = 0;

    private final static String FILE_NAME = "qwerty.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_sort);

        num_of_clicks = 0;

        txt_num = new TextView[9];
        txt_num[0] = findViewById(R.id.txt_num1);
        txt_num[1] = findViewById(R.id.txt_num2);
        txt_num[2] = findViewById(R.id.txt_num3);
        txt_num[3] = findViewById(R.id.txt_num4);
        txt_num[4] = findViewById(R.id.txt_num5);
        txt_num[5] = findViewById(R.id.txt_num6);
        txt_num[6] = findViewById(R.id.txt_num7);
        txt_num[7] = findViewById(R.id.txt_num8);
        txt_num[8] = findViewById(R.id.txt_num9);

        for (int i = 0; i < numbers.length; ++i) {
            txt_num[i].setText(String.valueOf(numbers[i]));
        }

        sel_sort = findViewById(R.id.sel_sort);
        sel_sort.setOnClickListener(this);

        edit_text = findViewById(R.id.edit_text);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sel_sort:
                ++num_of_clicks;
                for (int i = 0; i < numbers.length; ++i) {
                    txt_num[i].setText(String.valueOf(numbers[i]));
                    txt_num[i].setBackgroundResource(R.drawable.rectangle_gray);
                    numbers_2[i] = numbers[i];
                }
                handler.postDelayed(new Runnable() { public void run() { selection_sort(num_of_clicks); } }, 600);
                break;
            case R.id.btnSave:
                if (edit_text.getText().toString().equals("1 2 3"))
                    saveText('1');
                else saveText('0');
            default:
                break;
        }
    }

    public void selection_sort(long cur){
        animation_selection(cur);
    }

    public void animation_selection(final long cur) {
        long current = 0;
        for (int i = 0; i < numbers.length; ++i)
        {
            final int y = i;
            maxim = 10;
            j1 = i;
            if (cur != num_of_clicks) return;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    txt_num[y].setBackgroundResource(R.drawable.rectangle_red);
                    j2 = y;
                }
            }, 1250*current);
            ++current;
            for (int j = i+1; j < numbers.length; ++j) {
                final int x = j;
                if (cur != num_of_clicks) return;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (cur != num_of_clicks) return;
                        txt_num[x].setBackgroundResource(R.drawable.rectangle_orange);
                    }
                }, 1250*current);
                if (numbers_2[j] < maxim) {
                    maxim = numbers_2[j];
                    j1 = j;
                }
                ++current;
                if (cur != num_of_clicks) return;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (Integer.parseInt(txt_num[j2].getText().toString()) > Integer.parseInt(txt_num[x].getText().toString())) {
                            txt_num[j2].setBackgroundResource(R.drawable.rectangle_gray);
                            j2 = x;
                            txt_num[x].setBackgroundResource(R.drawable.rectangle_red);
                        }
                        else {
                            txt_num[x].setBackgroundResource(R.drawable.rectangle_gray);
                        }
                    }
                }, 1250*current);
                ++current;
            }
            int u = numbers_2[i];
            numbers_2[i] = numbers_2[j1];
            numbers_2[j1] = u;

            if (cur != num_of_clicks) return;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (cur != num_of_clicks) return;
                    String temp = txt_num[y].getText().toString();
                    txt_num[y].setText(txt_num[j2].getText().toString()); // swap
                    txt_num[j2].setText(temp);
                    txt_num[j2].setBackgroundResource(R.drawable.rectangle_gray);
                    txt_num[y].setBackgroundResource(R.drawable.rectangle_dark);
                }
            }, 1250*current);
            ++current;
        }
    }

    static String convertStreamToString(FileInputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    public String loadText() {
        try {
            FileInputStream fin = openFileInput(FILE_NAME);
            String str = convertStreamToString(fin);
            fin.close();
            return str;
        } catch(IOException ex) {
            //Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            StringBuilder curBuilder = new StringBuilder();
            for (int i = 0; i < 100; ++i)
                curBuilder.append('0');
            return curBuilder.toString();
        }
    }
    public void saveText(char ch) {
        char[] c = loadText().toCharArray();
        c[2] = ch;
        String str = new String(c);
        try {
            FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(str.getBytes());
            fos.close();
        } catch(IOException ex) {
            //Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        if (ch == '1') Toast.makeText(this, "Right answer, text saved", Toast.LENGTH_SHORT).show();
        else Toast.makeText(this, "Wrong answer, try again", Toast.LENGTH_SHORT).show();
    }
}