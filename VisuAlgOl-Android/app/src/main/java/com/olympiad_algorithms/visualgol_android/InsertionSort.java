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
import java.util.Random;

public class InsertionSort extends AppCompatActivity implements View.OnClickListener {

    Button ins_sort;
    Button btnSave;
    EditText edit_text;
    private int childPosition = 0;
    private int num_of_clicks = 0;
    private TextView []txt_num;
    final Random random = new Random();
    private int []numbers = new int[8];
    private int []numbers_2 = new int[8];
    private Handler handler = new Handler();

    private final static String FILE_NAME = "qwerty.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertion_sort);

        Bundle arguments = getIntent().getExtras();
        if (arguments != null)
            childPosition = arguments.getInt("num", 0);

        num_of_clicks = 0;

        txt_num = new TextView[8];
        txt_num[0] = findViewById(R.id.txt_num1);
        txt_num[1] = findViewById(R.id.txt_num2);
        txt_num[2] = findViewById(R.id.txt_num3);
        txt_num[3] = findViewById(R.id.txt_num4);
        txt_num[4] = findViewById(R.id.txt_num5);
        txt_num[5] = findViewById(R.id.txt_num6);
        txt_num[6] = findViewById(R.id.txt_num7);
        txt_num[7] = findViewById(R.id.txt_num8);

        for (int i = 0; i < numbers.length; ++i)
            numbers[i] = random.nextInt() % 10;

        ins_sort = findViewById(R.id.ins_sort);
        ins_sort.setOnClickListener(this);

        edit_text = findViewById(R.id.edit_text);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

        ContestSet();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ins_sort:
                ++num_of_clicks;
                num_of_clicks %= 1e6;
                ContestSet();
                insertion_sort(num_of_clicks);
                break;
            case R.id.btnSave:
                if (edit_text.getText().toString().equals("3 4 2 1"))
                    saveText('1');
                else saveText('0');
                break;
            default:
                break;
        }
    }

    public void ContestSet() {
        for (int i = 0; i < numbers.length; ++i) {
            txt_num[i].setText(String.valueOf(numbers[i]));
            txt_num[i].setBackgroundResource(R.drawable.rectangle_gray);
            numbers_2[i] = numbers[i];
        }
    }

    public void insertion_sort(long cur){
        animation_insertion(cur);
    }

    public void animation_insertion(final long cur) {
        long current = 1;
        for (int i = 0; i < numbers.length; ++i)
        {
            final int y = i;
            for (int j = i; j >= 1; --j) {
                final int x = j;
                if (cur != num_of_clicks) return;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (cur != num_of_clicks) return;
                        txt_num[x-1].setBackgroundResource(R.drawable.rectangle_orange);
                        txt_num[x].setBackgroundResource(R.drawable.rectangle_orange);
                    }
                }, 1250*current);
                ++current;
                if (numbers_2[j-1] > numbers_2[j]) {
                    int temp = numbers_2[j-1];
                    numbers_2[j-1] = numbers_2[j];
                    numbers_2[j] = temp;

                    if (cur != num_of_clicks) return;
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (cur != num_of_clicks) return;
                            txt_num[x-1].setBackgroundResource(R.drawable.rectangle_red);
                            txt_num[x].setBackgroundResource(R.drawable.rectangle_red);
                        }
                    }, 1250*current);
                    ++current;
                    if (cur != num_of_clicks) return;
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (cur != num_of_clicks) return;
                            String temp = txt_num[x-1].getText().toString();
                            txt_num[x-1].setText(txt_num[x].getText().toString()); // swap
                            txt_num[x].setText(temp);
                            txt_num[x].setBackgroundResource(R.drawable.rectangle_purple);
                        }
                    }, 1250*current);
                    ++current;
                }
                else break;
            }
            if (cur != num_of_clicks) return;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (cur != num_of_clicks) return;
                    for (int u = 0; u <= y; ++u)
                        txt_num[u].setBackgroundResource(R.drawable.rectangle_purple);
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
        c[childPosition] = ch;
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