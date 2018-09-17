package com.olympiad_algorithms.visualgol_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class BubbleSort extends AppCompatActivity {

    private TextView [] txt_num;
    private long num_of_clicks = 0;
    private int []numbers = {9,3,7,2,1,8,1,3,4};
    private Handler handler = new Handler();
    private int loop = 0;

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

        (findViewById(R.id.bub_sort)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ++num_of_clicks;
                for (int i = 0; i < numbers.length; ++i) {
                    txt_num[i].setText(String.valueOf(numbers[i]));
                    txt_num[i].setBackgroundResource(R.drawable.rectangle_gray);
                }
                handler.postDelayed(new Runnable() { public void run() { bubble_sort(num_of_clicks); } }, 500);
            }
        });
    }

    public void bubble_sort(long cur){
        animation_bubble(1, cur);
    }

    public void animation_bubble(final int n, final long cur) {
        final int m = n - 1;
        Runnable comparison = new Runnable() {
            public void run() {
                Runnable if_swap = new Runnable() {
                    public void run() {
                        Runnable after_swap = new Runnable() {
                            public void run() {
                                if (cur != num_of_clicks) return;
                                txt_num[m].setBackgroundResource(R.drawable.rectangle_gray);
                                txt_num[n].setBackgroundResource(R.drawable.rectangle_gray);
                                int j = n + 1;
                                if (j == numbers.length - loop) {
                                    txt_num[j-1].setBackgroundResource(R.drawable.rectangle_dark);
                                    j = 1;
                                    ++loop;
                                }
                                if (loop < numbers.length - 1) {
                                    animation_bubble(j, cur);
                                }
                            }
                        };
                        if (cur != num_of_clicks) return;
                        handler.postDelayed(after_swap, 1250);
                        String temp = txt_num[m].getText().toString();
                        txt_num[m].setText(txt_num[n].getText().toString()); // swap
                        txt_num[n].setText(temp);
                    }
                };
                if (cur != num_of_clicks) return;
                if (Integer.valueOf(txt_num[m].getText().toString()) > Integer.valueOf(txt_num[n].getText().toString())) {
                    handler.postDelayed(if_swap, 1250);
                    txt_num[m].setBackgroundResource(R.drawable.rectangle_red);
                    txt_num[n].setBackgroundResource(R.drawable.rectangle_red);
                }
                else {
                    txt_num[m].setBackgroundResource(R.drawable.rectangle_gray);
                    txt_num[n].setBackgroundResource(R.drawable.rectangle_gray);
                    int j = n + 1;
                    if (j == numbers.length - loop) {
                        txt_num[j-1].setBackgroundResource(R.drawable.rectangle_dark);
                        j = 1;
                        ++loop;
                    }
                    if (loop < numbers.length - 1) {
                        animation_bubble(j, cur);
                    }
                }
            }
        };
        if (cur != num_of_clicks) return;
        handler.postDelayed(comparison, 1250);
        txt_num[m].setBackgroundResource(R.drawable.rectangle_orange);
        txt_num[n].setBackgroundResource(R.drawable.rectangle_orange);
    }
}