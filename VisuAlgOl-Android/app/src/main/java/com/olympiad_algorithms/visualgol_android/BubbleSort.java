package com.olympiad_algorithms.visualgol_android;

//import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class BubbleSort extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    Button bub_sort;
    Button btnSave;
    EditText edit_text;
    SeekBar SbDelay;
    TextView TvDelay;
    private int childPosition = 0;
    private int curSpeed = 1250;
    private TextView []txt_num;
    final Random random = new Random();
    private int []numbers = new int[8];
    private int []numbers_2 = new int[8];
    private Handler handler = new Handler();

    private final static String FILE_NAME = "qwerty.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubble_sort);

        Bundle arguments = getIntent().getExtras();
        if (arguments != null)
            childPosition = arguments.getInt("num", 0);

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

        TvDelay = findViewById(R.id.TvDelay);
        TvDelay.setText(R.string.sec);

        SbDelay = findViewById(R.id.SbDelay);
        SbDelay.setOnSeekBarChangeListener(this);

        bub_sort = findViewById(R.id.bub_sort);
        bub_sort.setOnClickListener(this);

        edit_text = findViewById(R.id.edit_text);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

        ContestSet();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bub_sort:
                handler.removeCallbacksAndMessages(null);
                ContestSet();
                bubble_sort();
                break;
            case R.id.btnSave:
                if (edit_text.getText().toString().equals("3"))
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

    public void bubble_sort(){
        animation_bubble();
    }

    public void animation_bubble() {
        long current = 1;
        for (int i = 0; i < numbers.length; ++i) {
            for (int j = 1; j < (numbers.length-i); ++j) {
                final int x = j, y = i;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        txt_num[x-1].setBackgroundResource(R.drawable.rectangle_orange);
                        txt_num[x].setBackgroundResource(R.drawable.rectangle_orange);
                    }
                }, curSpeed*current);
                ++current;
                if (numbers_2[j-1] > numbers_2[j]) {
                    int temp = numbers_2[j-1];
                    numbers_2[j-1] = numbers_2[j];
                    numbers_2[j] = temp;

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            txt_num[x-1].setBackgroundResource(R.drawable.rectangle_red);
                            txt_num[x].setBackgroundResource(R.drawable.rectangle_red);
                        }
                    }, curSpeed*current);
                    ++current;
                    //Swap
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            String temp = txt_num[x-1].getText().toString();
                            txt_num[x-1].setText(txt_num[x].getText().toString());
                            txt_num[x].setText(temp);
                        }
                    }, curSpeed*current);
                    ++current;
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        txt_num[x-1].setBackgroundResource(R.drawable.rectangle_gray);
                        txt_num[x].setBackgroundResource(R.drawable.rectangle_gray);
                        if (x == numbers.length - y - 1)
                            txt_num[x].setBackgroundResource(R.drawable.rectangle_dark);
                        if (x == 1 && y == numbers.length-2)
                            txt_num[0].setBackgroundResource(R.drawable.rectangle_dark);
                    }
                }, curSpeed*current);
            }
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