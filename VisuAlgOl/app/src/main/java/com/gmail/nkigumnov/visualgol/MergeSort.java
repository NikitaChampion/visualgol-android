package com.gmail.nkigumnov.visualgol;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.EditText;

import com.gmail.nkigumnov.visualgol.util.Constants;
import com.gmail.nkigumnov.visualgol.util.Util;

import java.util.Random;

import static java.lang.Math.ceil;
import static java.lang.Math.log;
import static java.lang.Math.pow;

public class MergeSort extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    private EditText editText;
    private TextView TvDelay;
    private int childPosition = 0, groupPosition = 0;
    private int curSpeed = Constants.SPEED;
    private TextView[] txt_num;
    private TextView[] arr;
    final Random random = new Random();
    private final int[] numbers = new int[8];
    private final int[] numbers_2 = new int[8];
    private final int[] numbers_3 = new int[8];
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merge_sort);

        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            childPosition = arguments.getInt("num", 0);
            groupPosition = arguments.getInt("num_2", 0);
        }

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

        ((SeekBar) findViewById(R.id.SbDelay)).setOnSeekBarChangeListener(this);
        findViewById(R.id.m_sort).setOnClickListener(this);
        findViewById(R.id.generate).setOnClickListener(this);
        findViewById(R.id.btnSave).setOnClickListener(this);

        editText = findViewById(R.id.edit_text);

        contestSet();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.m_sort) {
            handler.removeCallbacksAndMessages(null);
            contestSet();
            merge_sort();
        } else if (id == R.id.generate) {
            handler.removeCallbacksAndMessages(null);
            for (int i = 0; i < numbers.length; ++i)
                numbers[i] = random.nextInt() % 10;
            contestSet();
        } else if (id == R.id.btnSave) {
            if (editText.getText().toString().equals("1 3 4 4 5 6 4 5")) {
                Util.saveText(this, '1', groupPosition + childPosition);
            } else {
                Util.saveText(this, '0', groupPosition + childPosition);
            }
        }
    }

    public void contestSet() {
        for (int i = 0; i < numbers.length; ++i) {
            txt_num[i].setText(String.valueOf(numbers[i]));
            txt_num[i].setBackgroundResource(R.drawable.rectangle_2_gray);
            arr[i].setText("0");
            numbers_2[i] = numbers[i];
            numbers_3[i] = numbers[i];
        }
    }

    public void merge_sort() {
        animation_merge();
    }

    public void animation_merge() {
        int step = 1;
        long current = 1;
        while (step <= ceil(log(txt_num.length))) {
            int np;
            for (int i = 0; i < txt_num.length; i = np) {
                np = i + (int) pow(2, step);
                int fp = i, cp = i, s1 = i + (int) pow(2, step - 1), sp = s1;
                final int fir_poz = fp, sec_poz = sp, next_poz = np;
                handler.postDelayed(() -> {
                    txt_num[fir_poz].setBackgroundResource(R.drawable.rectangle_gray);
                    txt_num[sec_poz].setBackgroundResource(R.drawable.rectangle_gray);
                }, curSpeed * current);
                ++current;
                while (fp < s1 && sp < np) {
                    if (numbers_2[fp] <= numbers_2[sp]) {
                        final int f_poz = fp, cur_poz = cp;
                        handler.postDelayed(() -> {
                            txt_num[f_poz].setBackgroundResource(R.drawable.rectangle_2_gray);
                            if (f_poz < sec_poz - 1)
                                txt_num[f_poz + 1].setBackgroundResource(R.drawable.rectangle_gray);
                            arr[cur_poz].setText(txt_num[f_poz].getText().toString());
                        }, curSpeed * current);
                        numbers_3[cp] = numbers_2[fp];
                        ++fp;
                    } else {
                        final int s_poz = sp, cur_poz = cp;
                        handler.postDelayed(() -> {
                            txt_num[s_poz].setBackgroundResource(R.drawable.rectangle_2_gray);
                            if (s_poz < next_poz - 1)
                                txt_num[s_poz + 1].setBackgroundResource(R.drawable.rectangle_gray);
                            arr[cur_poz].setText(txt_num[s_poz].getText().toString());
                        }, curSpeed * current);
                        numbers_3[cp] = numbers_2[sp];
                        ++sp;
                    }
                    ++cp;
                    ++current;
                }
                while (fp < s1) {
                    final int f_poz = fp, cur_poz = cp;
                    handler.postDelayed(() -> {
                        txt_num[f_poz].setBackgroundResource(R.drawable.rectangle_2_gray);
                        if (f_poz < sec_poz - 1)
                            txt_num[f_poz + 1].setBackgroundResource(R.drawable.rectangle_gray);
                        arr[cur_poz].setText(txt_num[f_poz].getText().toString());
                    }, curSpeed * current);
                    numbers_3[cp] = numbers_2[fp];
                    ++fp;
                    ++cp;
                    ++current;
                }
                while (sp < np) {
                    final int s_poz = sp, cur_poz = cp;
                    handler.postDelayed(() -> {
                        txt_num[s_poz].setBackgroundResource(R.drawable.rectangle_2_gray);
                        if (s_poz < next_poz - 1)
                            txt_num[s_poz + 1].setBackgroundResource(R.drawable.rectangle_gray);
                        arr[cur_poz].setText(txt_num[s_poz].getText().toString());
                    }, curSpeed * current);
                    numbers_3[cp] = numbers_2[sp];
                    ++sp;
                    ++cp;
                    ++current;
                }
                if (np - i >= 0) System.arraycopy(numbers_3, i, numbers_2, i, np - i);
                handler.postDelayed(() -> {
                    for (int i1 = fir_poz; i1 < next_poz; ++i1) {
                        txt_num[i1].setText(arr[i1].getText().toString());
                        arr[i1].setText("0");
                    }
                }, curSpeed * current);
                ++current;
            }
            ++step;
        }
        handler.postDelayed(() -> {
            for (TextView textView : txt_num) {
                textView.setBackgroundResource(R.drawable.rectangle_dark);
                textView.setTextColor(getResources().getColor(R.color.white));
            }
        }, curSpeed * current);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        curSpeed = progress;
        String s = progress / 1000. + " sec";
        TvDelay.setText(s);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}