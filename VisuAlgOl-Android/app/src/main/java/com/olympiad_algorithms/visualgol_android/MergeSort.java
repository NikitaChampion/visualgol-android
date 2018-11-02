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

import static java.lang.Math.ceil;
import static java.lang.Math.log;
import static java.lang.Math.pow;

public class MergeSort extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    Button m_sort;
    Button generate;
    Button btnSave;
    EditText edit_text;
    SeekBar SbDelay;
    TextView TvDelay;
    private int childPosition = 0;
    private int curSpeed = 1250;
    private TextView []txt_num;
    private TextView []arr;
    final Random random = new Random();
    private int []numbers = new int[8];
    private int []numbers_2 = new int[8];
    private int []numbers_3 = new int[8];
    private Handler handler = new Handler();

    private final static String FILE_NAME = "qwerty.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merge_sort);

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

        arr = new TextView[8];
        arr[0] = findViewById(R.id.arr1);
        arr[1] = findViewById(R.id.arr2);
        arr[2] = findViewById(R.id.arr3);
        arr[3] = findViewById(R.id.arr4);
        arr[4] = findViewById(R.id.arr5);
        arr[5] = findViewById(R.id.arr6);
        arr[6] = findViewById(R.id.arr7);
        arr[7] = findViewById(R.id.arr8);

        for (int i = 0; i < numbers.length; ++i)
            numbers[i] = random.nextInt() % 10;

        for (TextView anArr : arr)
            anArr.setText("0");

        TvDelay = findViewById(R.id.TvDelay);
        TvDelay.setText(R.string.sec);

        SbDelay = findViewById(R.id.SbDelay);
        SbDelay.setOnSeekBarChangeListener(this);

        m_sort = findViewById(R.id.m_sort);
        m_sort.setOnClickListener(this);

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
            case R.id.m_sort:
                handler.removeCallbacksAndMessages(null);
                ContestSet();
                merge_sort();
                break;
            case R.id.generate:
                handler.removeCallbacksAndMessages(null);
                for (int i = 0; i < numbers.length; ++i)
                    numbers[i] = random.nextInt() % 10;
                ContestSet();
                break;
            case R.id.btnSave:
                if (edit_text.getText().toString().equals("1 2 4 3"))
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
            txt_num[i].setBackgroundResource(R.drawable.rectangle_2_gray);
            arr[i].setText("0");
            numbers_2[i] = numbers[i];
            numbers_3[i] = numbers[i];
        }
    }

    public void merge_sort(){
        animation_merge();
    }

    public void animation_merge() {
        int step = 1;
        long current = 1;
        while (step <= ceil(log(txt_num.length))) {
            int np;
            for (int i = 0; i < txt_num.length; i = np) {
                np = i+(int)pow(2, step);
                int fp = i, cp = i, s1 = i+(int)pow(2, step-1), sp = s1;
                final int fir_poz = fp, sec_poz = sp, next_poz = np;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        txt_num[fir_poz].setBackgroundResource(R.drawable.rectangle_gray);
                        txt_num[sec_poz].setBackgroundResource(R.drawable.rectangle_gray);
                    }
                }, curSpeed*current);
                ++current;
                while (fp < s1 && sp < np) {
                    if (numbers_2[fp] <= numbers_2[sp]) {
                        final int f_poz = fp, cur_poz = cp;
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                txt_num[f_poz].setBackgroundResource(R.drawable.rectangle_2_gray);
                                if (f_poz < sec_poz-1)
                                    txt_num[f_poz+1].setBackgroundResource(R.drawable.rectangle_gray);
                                arr[cur_poz].setText(txt_num[f_poz].getText().toString());
                            }
                        }, curSpeed*current);
                        numbers_3[cp] = numbers_2[fp];
                        ++fp;
                    }
                    else {
                        final int s_poz = sp, cur_poz = cp;
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                txt_num[s_poz].setBackgroundResource(R.drawable.rectangle_2_gray);
                                if (s_poz < next_poz-1)
                                    txt_num[s_poz+1].setBackgroundResource(R.drawable.rectangle_gray);
                                arr[cur_poz].setText(txt_num[s_poz].getText().toString());
                            }
                        }, curSpeed*current);
                        numbers_3[cp] = numbers_2[sp];
                        ++sp;
                    }
                    ++cp;
                    ++current;
                }
                while (fp < s1) {
                    final int f_poz = fp, cur_poz = cp;
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            txt_num[f_poz].setBackgroundResource(R.drawable.rectangle_2_gray);
                            if (f_poz < sec_poz-1)
                                txt_num[f_poz+1].setBackgroundResource(R.drawable.rectangle_gray);
                            arr[cur_poz].setText(txt_num[f_poz].getText().toString());
                        }
                    }, curSpeed*current);
                    numbers_3[cp] = numbers_2[fp];
                    ++fp;
                    ++cp;
                    ++current;
                }
                while (sp < np) {
                    final int s_poz = sp, cur_poz = cp;
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            txt_num[s_poz].setBackgroundResource(R.drawable.rectangle_2_gray);
                            if (s_poz < next_poz-1)
                                txt_num[s_poz+1].setBackgroundResource(R.drawable.rectangle_gray);
                            arr[cur_poz].setText(txt_num[s_poz].getText().toString());
                        }
                    }, curSpeed*current);
                    numbers_3[cp] = numbers_2[sp];
                    ++sp;
                    ++cp;
                    ++current;
                }
                for (int j = i; j < np; ++j)
                    numbers_2[j] = numbers_3[j];
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = fir_poz; i < next_poz; ++i) {
                            txt_num[i].setText(arr[i].getText().toString());
                            arr[i].setText("0");
                        }
                    }
                }, curSpeed*current);
                ++current;
            }
            ++step;
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < txt_num.length; ++i) {
                    txt_num[i].setBackgroundResource(R.drawable.rectangle_dark);
                    txt_num[i].setTextColor(getResources().getColor(R.color.white));
                }
            }
        }, curSpeed*current);
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