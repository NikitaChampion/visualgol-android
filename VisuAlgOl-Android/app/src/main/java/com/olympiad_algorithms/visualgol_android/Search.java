package com.olympiad_algorithms.visualgol_android;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import static java.lang.Math.abs;

public class Search extends AppCompatActivity implements View.OnClickListener {

    TextView title;
    Button search;
    Button btnSave;
    EditText edit_text;
    private int cur = 0, groupPosition = 0;
    private int num_of_clicks = 0;
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
            cur = arguments.getInt("num", 1);
            groupPosition = arguments.getInt("num_2", 0);
        }

        //Toast.makeText(this, ""+cur, Toast.LENGTH_SHORT).show();

        num_of_clicks = 0;

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

        for (int i = 0; i < numbers.length; ++i)
            numbers[i] = random.nextInt() % 10;
        if (cur == 0)
            WhatToFind = numbers[abs(random.nextInt())%numbers.length];
        else
            WhatToFind = numbers_2[abs(random.nextInt())%numbers_2.length];

        search = findViewById(R.id.search);
        search.setOnClickListener(this);

        edit_text = findViewById(R.id.edit_text);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

        ContestSet();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search:
                ++num_of_clicks;
                num_of_clicks %= 1e6;
                ContestSet();
                if (cur == 0)
                    handler.postDelayed(new Runnable() { public void run() { linear_search(num_of_clicks); } }, 600);
                else
                    handler.postDelayed(new Runnable() { public void run() { binary_search(num_of_clicks); } }, 600);
                break;
            case R.id.btnSave:
                if (edit_text.getText().toString().equals("....."))
                    saveText('1');
                else
                    saveText('0');
            default:
                break;
        }
    }

    public void ContestSet() {
        //linear search == 0, binary search == 1
        if (cur == 0) {
            title.setText(R.string.lin_search);
            for (int i = 0; i < numbers.length; ++i) {
                txt_num[i].setText(String.valueOf(numbers[i]));
                txt_num[i].setBackgroundResource(R.drawable.rectangle_search_1);
            }
            search.setText(R.string.lin_search);
        }
        else {
            title.setText(R.string.bin_search);
            for (int i = 0; i < numbers_2.length; ++i) {
                txt_num[i].setText(String.valueOf(numbers_2[i]));
                txt_num[i].setBackgroundResource(R.drawable.rectangle_search_1);
            }
            search.setText(R.string.bin_search);
        }
        txt_num_find.setText(String.valueOf(WhatToFind));
        txt_num_find.setBackgroundResource(R.drawable.rectangle_search_1);
    }

    public void linear_search(long cur){
        animation_lin(cur);
    }

    public void animation_lin(final long cur) {
        long current = 0;
        for (int i = 0; i < numbers.length; ++i) {
            final int x = i;
            if (cur != num_of_clicks) return;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (cur != num_of_clicks) return;
                    txt_num[x].setBackgroundResource(R.drawable.rectangle_search_3);
                    txt_num_find.setBackgroundResource(R.drawable.rectangle_search_4);
                }
            }, 1250*current);
            ++current;
            if (cur != num_of_clicks) return;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (cur != num_of_clicks) return;
                    if (numbers[x] == WhatToFind)
                        txt_num[x].setBackgroundResource(R.drawable.rectangle_search_4);
                    else {
                        txt_num[x].setBackgroundResource(R.drawable.rectangle_search_2);
                        txt_num_find.setBackgroundResource(R.drawable.rectangle_search_1);
                    }
                }
            }, 1250*current);
            if (numbers[i] == WhatToFind) return;
            ++current;
        }
    }

    public void binary_search(long cur){
        animation_bin(cur);
    }

    public void animation_bin(final long cur) {
        long current = 0;
        int l = -1, r = numbers_2.length, mid;
        while (l < r-1)
        {
            mid = (l+r)/2;
            final int x = mid;
            if (cur != num_of_clicks) return;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (cur != num_of_clicks) return;
                    txt_num[x].setBackgroundResource(R.drawable.rectangle_search_3);
                    txt_num_find.setBackgroundResource(R.drawable.rectangle_search_4);
                }
            }, 1250*current);
            ++current;
            if (cur != num_of_clicks) return;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (cur != num_of_clicks) return;
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
            }, 1250*current);
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
        c[groupPosition+cur] = ch;
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