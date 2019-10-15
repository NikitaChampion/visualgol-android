package com.olympiad_algorithms.visualgol_android;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import static java.lang.Math.abs;

public class Search extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    TextView title;
    TextView Search;
    TextView task;
    Button search;
    Button generate;
    Button btnSave;
    EditText edit_text;
    SeekBar SbDelay;
    TextView TvDelay;
    private int childPosition = 0, groupPosition = 0;
    private int curSpeed = 1250;
    private TextView []txt_num;
    private TextView txt_num_find;
    final Random random = new Random();
    private int []numbers = new int[8];
    private int []numbers_2 = {1,2,3,4,5,6,7,8};
    private int WhatToFind;
    private Handler handler = new Handler();

    private final static String FILE_NAME = "qwerty.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            childPosition = arguments.getInt("num", 1);
            groupPosition = arguments.getInt("num_2", 0);
        }

        //Toast.makeText(this, ""+cur, Toast.LENGTH_SHORT).show();

        title = findViewById(R.id.title);

        txt_num = new TextView[8];
        txt_num[0] = findViewById(R.id.txt_num1);
        txt_num[1] = findViewById(R.id.txt_num2);
        txt_num[2] = findViewById(R.id.txt_num3);
        txt_num[3] = findViewById(R.id.txt_num4);
        txt_num[4] = findViewById(R.id.txt_num5);
        txt_num[5] = findViewById(R.id.txt_num6);
        txt_num[6] = findViewById(R.id.txt_num7);
        txt_num[7] = findViewById(R.id.txt_num8);
        txt_num_find = findViewById(R.id.txt_num10);

        TvDelay = findViewById(R.id.TvDelay);
        TvDelay.setText(R.string.sec);

        SbDelay = findViewById(R.id.SbDelay);
        SbDelay.setOnSeekBarChangeListener(this);

        Search = findViewById(R.id.Search);

        task = findViewById(R.id.task);

        for (int i = 0; i < numbers.length; ++i)
            numbers[i] = random.nextInt() % 10;
        if (childPosition == 0)
            WhatToFind = numbers[abs(random.nextInt())%numbers.length];
        else
            WhatToFind = numbers_2[abs(random.nextInt())%numbers_2.length];

        search = findViewById(R.id.search);
        search.setOnClickListener(this);

        generate = findViewById(R.id.generate);
        generate.setOnClickListener(this);

        edit_text = findViewById(R.id.edit_text);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

        ContestSet();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search:
                handler.removeCallbacksAndMessages(null);
                ContestSet();
                if (childPosition == 0)
                    linear_search();
                else
                    binary_search();
                break;
            case R.id.generate:
                handler.removeCallbacksAndMessages(null);
                for (int i = 0; i < numbers.length; ++i)
                    numbers[i] = random.nextInt() % 10;
                if (childPosition == 0)
                    WhatToFind = numbers[abs(random.nextInt())%numbers.length];
                else
                    WhatToFind = numbers_2[abs(random.nextInt())%numbers_2.length];
                ContestSet();
                break;
            case R.id.btnSave:
                if (childPosition == 0 && edit_text.getText().toString().equals("5"))
                    saveText('1');
                else if (childPosition == 1 && edit_text.getText().toString().equals("4"))
                    saveText('1');
                else
                    saveText('0');
                break;
            default:
                break;
        }
    }

    public void ContestSet() {
        //linear search == 0, binary search == 1
        if (childPosition == 0) {
            title.setText(R.string.lin_search);
            for (int i = 0; i < numbers.length; ++i) {
                txt_num[i].setText(String.valueOf(numbers[i]));
                txt_num[i].setBackgroundResource(R.drawable.rectangle_search_1);
            }
            Search.setText(R.string.lin_s);
            task.setText(R.string.task8);
        }
        else {
            title.setText(R.string.bin_search);
            for (int i = 0; i < numbers_2.length; ++i) {
                txt_num[i].setText(String.valueOf(numbers_2[i]));
                txt_num[i].setBackgroundResource(R.drawable.rectangle_search_1);
            }
            Search.setText(R.string.bin_s);
            task.setText(R.string.task9);
        }
        txt_num_find.setText(String.valueOf(WhatToFind));
        txt_num_find.setBackgroundResource(R.drawable.rectangle_search_1);
    }

    public void linear_search() {
        animation_lin();
    }

    public void animation_lin() {
        for (int i = 0; i < numbers.length; ++i) {
            final int x = i;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    txt_num[x].setBackgroundResource(R.drawable.rectangle_search_3);
                    txt_num_find.setBackgroundResource(R.drawable.rectangle_search_4);
                }
            }, curSpeed*(2*i+1));
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (numbers[x] == WhatToFind)
                        txt_num[x].setBackgroundResource(R.drawable.rectangle_search_4);
                    else {
                        txt_num[x].setBackgroundResource(R.drawable.rectangle_search_2);
                        txt_num_find.setBackgroundResource(R.drawable.rectangle_search_1);
                    }
                }
            }, curSpeed*(2*i+2));
            if (numbers[i] == WhatToFind) return;
        }
    }

    public void binary_search() {
        animation_bin();
    }

    public void animation_bin() {
        long current = 1;
        int l = -1, r = numbers_2.length, mid;
        while (l < r-1)
        {
            mid = (l+r)/2;
            final int x = mid;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    txt_num[x].setBackgroundResource(R.drawable.rectangle_search_3);
                    txt_num_find.setBackgroundResource(R.drawable.rectangle_search_4);
                }
            }, curSpeed*current);
            ++current;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (numbers_2[x] < WhatToFind) {
                        for (int i = 0; i <= x; ++i) {
                            txt_num[i].setBackgroundResource(R.drawable.rectangle_search_2);
                        }
                        txt_num_find.setBackgroundResource(R.drawable.rectangle_search_1);
                    }
                    else if (numbers_2[x] > WhatToFind) {
                        for (int i = txt_num.length-1; i >= x; --i) {
                            txt_num[i].setBackgroundResource(R.drawable.rectangle_search_2);
                        }
                        txt_num_find.setBackgroundResource(R.drawable.rectangle_search_1);
                    }
                    else
                        txt_num[x].setBackgroundResource(R.drawable.rectangle_search_4);
                }
            }, curSpeed*current);
            if (numbers_2[mid] == WhatToFind) return;
            ++current;
            if (numbers_2[mid] < WhatToFind) l = mid;
            else r = mid;
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
        c[groupPosition+childPosition] = ch;
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

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        curSpeed = progress;
        String s = String.valueOf(progress/1000.)+" sec";
        TvDelay.setText(s);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}