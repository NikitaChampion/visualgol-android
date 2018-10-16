package com.olympiad_algorithms.visualgol_android;

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

public class BubbleSort extends AppCompatActivity implements View.OnClickListener {

    Button bub_sort;
    Button btnSave;
    EditText edit_text;
    private TextView [] txt_num;
    private long num_of_clicks = 0;
    private int []numbers = {7,2,1,3,9,0,8,3,4};
    private int []numbers_2 = {7,2,1,3,9,0,8,3,4};
    private Handler handler = new Handler();

    private final static String FILE_NAME = "content.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubble_sort);

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

        bub_sort = findViewById(R.id.bub_sort);
        bub_sort.setOnClickListener(this);

        edit_text = findViewById(R.id.edit_text);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bub_sort:
                ++num_of_clicks;
                for (int i = 0; i < numbers.length; ++i) {
                    txt_num[i].setText(String.valueOf(numbers[i]));
                    txt_num[i].setBackgroundResource(R.drawable.rectangle_gray);
                    numbers_2[i] = numbers[i];
                }
                handler.postDelayed(new Runnable() { public void run() { bubble_sort(num_of_clicks); } }, 600);
                break;
            case R.id.btnSave:
                if (edit_text.getText().toString().equals("1 2 3"))
                    saveText('1');
                else saveText('0');
            default:
                break;
        }
    }

    public void bubble_sort(long cur){
        animation_bubble(cur);
    }

    public void animation_bubble(final long cur) {
        long current = 0;
        for (int i = 0; i < numbers.length; ++i)
        {
            for (int j = 1; j < (numbers.length-i); ++j) {
                final int x = j, y = i;
                if (cur != num_of_clicks) return;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (cur != num_of_clicks) return;
                        txt_num[x-1].setBackgroundResource(R.drawable.rectangle_orange);
                        txt_num[x].setBackgroundResource(R.drawable.rectangle_orange);
                    }
                }, 1250*current);
                if (numbers_2[j-1] > numbers_2[j]) {
                    int temp = numbers_2[j-1];
                    numbers_2[j-1] = numbers_2[j];
                    numbers_2[j] = temp;

                    ++current;
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
                        }
                    }, 1250*current);
                }
                ++current;
                if (cur != num_of_clicks) return;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (cur != num_of_clicks) return;
                        txt_num[x-1].setBackgroundResource(R.drawable.rectangle_gray);
                        txt_num[x].setBackgroundResource(R.drawable.rectangle_gray);
                        if (x == numbers.length - y - 1)
                            txt_num[x].setBackgroundResource(R.drawable.rectangle_dark);
                        if (x == 1 && y == numbers.length-2)
                            txt_num[x-1].setBackgroundResource(R.drawable.rectangle_dark);
                    }
                }, 1250*current);
            }
        }
    }
    public String loadText() {
        FileInputStream fin = null;
        try {
            fin = openFileInput(FILE_NAME);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            return new String(bytes);
        }
        catch(IOException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            StringBuilder curBuilder = new StringBuilder();
            for (int i = 0; i < 100; ++i)
                curBuilder.append('0');
            return curBuilder.toString();
        }
        finally {
            try {
                if (fin != null)
                    fin.close();
            } catch (IOException ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void saveText(char ch) {
        char[] c = loadText().toCharArray();
        c[1] = ch;
        String str = new String(c);
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(str.getBytes());
        }
        catch(IOException ignored) { }
        finally {
            try {
                if (fos != null)
                    fos.close();
            }
            catch(IOException ignored) { }
        }
        Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show();
    }
}