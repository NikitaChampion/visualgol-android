package com.gmail.nkigumnov.visualgol;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Random;

public class StupidSort extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    Button stSort;
    Button generate;
    Button save;
    EditText editText;
    TextView tvDelay;
    SeekBar sbDelay;
    private int childPosition = 0, groupPosition = 0;
    private int curSpeed = Constants.SPEED;
    private TextView[] txt_num;
    final Random random = new Random();
    private final int[] numbers = new int[7];
    private final int[] numbers_2 = new int[7];
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stupid_sort);

        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            childPosition = arguments.getInt("num", 0);
            groupPosition = arguments.getInt("num_2", 0);
        }

        txt_num = new TextView[7];
        txt_num[0] = findViewById(R.id.txt_num1);
        txt_num[1] = findViewById(R.id.txt_num2);
        txt_num[2] = findViewById(R.id.txt_num3);
        txt_num[3] = findViewById(R.id.txt_num4);
        txt_num[4] = findViewById(R.id.txt_num5);
        txt_num[5] = findViewById(R.id.txt_num6);
        txt_num[6] = findViewById(R.id.txt_num7);

        for (int i = 0; i < numbers.length; ++i) {
            numbers[i] = random.nextInt() % 10;
        }

        tvDelay = findViewById(R.id.TvDelay);
        tvDelay.setText(R.string.sec);

        sbDelay = findViewById(R.id.SbDelay);
        sbDelay.setOnSeekBarChangeListener(this);

        stSort = findViewById(R.id.st_sort);
        stSort.setOnClickListener(this);

        generate = findViewById(R.id.generate);
        generate.setOnClickListener(this);

        save = findViewById(R.id.btnSave);
        save.setOnClickListener(this);

        editText = findViewById(R.id.edit_text);

        contestSet();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.st_sort) {
            handler.removeCallbacksAndMessages(null);
            contestSet();
            stupid_sort();
        } else if (id == R.id.generate) {
            handler.removeCallbacksAndMessages(null);
            for (int i = 0; i < numbers.length; ++i)
                numbers[i] = random.nextInt() % 10;
            contestSet();
        } else if (id == R.id.btnSave) {
            if (editText.getText().toString().equals("1 2 3")) {
                Util.saveText(this, '1', groupPosition + childPosition);
            } else {
                Util.saveText(this, '0', groupPosition + childPosition);
            }
        }
    }

    public void contestSet() {
        for (int i = 0; i < numbers.length; ++i) {
            txt_num[i].setText(String.valueOf(numbers[i]));
            txt_num[i].setBackgroundResource(R.drawable.rectangle_gray);
            numbers_2[i] = numbers[i];
        }
    }

    public void stupid_sort() {
        animation_stupid();
    }

    public void animation_stupid() {
        long current = 1;
        for (int i = 1; i < numbers.length; ++i) {
            final int x = i;
            handler.postDelayed(() -> {
                txt_num[x - 1].setBackgroundResource(R.drawable.rectangle_orange);
                txt_num[x].setBackgroundResource(R.drawable.rectangle_orange);
            }, curSpeed * current);
            ++current;
            if (numbers_2[i - 1] > numbers_2[i]) {
                int temp = numbers_2[i - 1];
                numbers_2[i - 1] = numbers_2[i];
                numbers_2[i] = temp;
                i = 0;

                handler.postDelayed(() -> {
                    txt_num[x - 1].setBackgroundResource(R.drawable.rectangle_red);
                    txt_num[x].setBackgroundResource(R.drawable.rectangle_red);
                }, curSpeed * current);
                ++current;
                //Swap
                handler.postDelayed(() -> {
                    String temp1 = txt_num[x - 1].getText().toString();
                    txt_num[x - 1].setText(txt_num[x].getText().toString());
                    txt_num[x].setText(temp1);
                }, curSpeed * current);
                ++current;
            }
            handler.postDelayed(() -> {
                txt_num[x - 1].setBackgroundResource(R.drawable.rectangle_gray);
                txt_num[x].setBackgroundResource(R.drawable.rectangle_gray);
            }, curSpeed * current);
        }
        handler.postDelayed(() -> {
            for (int i = 0; i < numbers.length; ++i)
                txt_num[i].setBackgroundResource(R.drawable.rectangle_dark);
        }, curSpeed * current);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        curSpeed = progress;
        String s = progress / 1000. + " sec";
        tvDelay.setText(s);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}