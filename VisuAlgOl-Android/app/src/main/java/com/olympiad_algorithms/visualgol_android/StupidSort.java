package com.olympiad_algorithms.visualgol_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
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

public class StupidSort extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    Button st_sort;
    Button generate;
    Button btnSave;
    EditText edit_text;
    SeekBar SbDelay;
    TextView TvDelay;
    private int childPosition = 0;
    private int curSpeed = 1250;
    private TextView []txt_num;
    final Random random = new Random();
    private int []numbers = new int[7];
    private int []numbers_2 = new int[7];
    private Handler handler = new Handler();

    private final static String FILE_NAME = "qwerty.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stupid_sort);

        Bundle arguments = getIntent().getExtras();
        if (arguments != null)
            childPosition = arguments.getInt("num", 0);

        txt_num = new TextView[7];
        txt_num[0] = findViewById(R.id.txt_num1);
        txt_num[1] = findViewById(R.id.txt_num2);
        txt_num[2] = findViewById(R.id.txt_num3);
        txt_num[3] = findViewById(R.id.txt_num4);
        txt_num[4] = findViewById(R.id.txt_num5);
        txt_num[5] = findViewById(R.id.txt_num6);
        txt_num[6] = findViewById(R.id.txt_num7);

        for (int i = 0; i < numbers.length; ++i)
            numbers[i] = random.nextInt() % 10;

        TvDelay = findViewById(R.id.TvDelay);
        TvDelay.setText(R.string.sec);

        SbDelay = findViewById(R.id.SbDelay);
        SbDelay.setOnSeekBarChangeListener(this);

        st_sort = findViewById(R.id.st_sort);
        st_sort.setOnClickListener(this);

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
            case R.id.st_sort:
                handler.removeCallbacksAndMessages(null);
                ContestSet();
                stupid_sort();
                break;
            case R.id.generate:
                handler.removeCallbacksAndMessages(null);
                for (int i = 0; i < numbers.length; ++i)
                    numbers[i] = random.nextInt() % 10;
                ContestSet();
                break;
            case R.id.btnSave:
                if (edit_text.getText().toString().equals("1 2 3"))
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

    public void stupid_sort(){
        animation_stupid();
    }

    public void animation_stupid() {
        long current = 1;
        for (int i = 1; i < numbers.length; ++i) {
            final int x = i;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    txt_num[x-1].setBackgroundResource(R.drawable.rectangle_orange);
                    txt_num[x].setBackgroundResource(R.drawable.rectangle_orange);
                }
            }, curSpeed*current);
            ++current;
            if (numbers_2[i-1] > numbers_2[i]) {
                int temp = numbers_2[i-1];
                numbers_2[i-1] = numbers_2[i];
                numbers_2[i] = temp;
                i = 0;

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
                }
            }, curSpeed*current);
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < numbers.length; ++i)
                    txt_num[i].setBackgroundResource(R.drawable.rectangle_dark);
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
