package com.olympiad_algorithms.visualgol_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class BubbleSort extends AppCompatActivity {

    private TextView [] txt_num;
    private int []numbers = {9,3,7,2,1,8,1,8,4};
    private Handler handler = new Handler();
    int loop = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubble_sort);

        txt_num = new TextView[9];
        txt_num[0]= findViewById(R.id.txt_num1);
        txt_num[1]= findViewById(R.id.txt_num2);
        txt_num[2]= findViewById(R.id.txt_num3);
        txt_num[3]= findViewById(R.id.txt_num4);
        txt_num[4]= findViewById(R.id.txt_num5);
        txt_num[5]= findViewById(R.id.txt_num6);
        txt_num[6]= findViewById(R.id.txt_num7);
        txt_num[7]= findViewById(R.id.txt_num8);
        txt_num[8]= findViewById(R.id.txt_num9);

        for (int i = 0; i < numbers.length; ++i){
            txt_num[i].setText(String.valueOf(numbers[i]));
        }

        (findViewById(R.id.ssort)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < numbers.length; ++i){
                    txt_num[i].setText(String.valueOf(numbers[i]));
                }
                bubble_sort();
            }
        });
    }

    public void bubble_sort(){
        animation_bubble(1);
    }

    public void animation_bubble(final int n) {
        final int m = n - 1;
        Runnable firstDel = new Runnable() {
            Runnable secondDel = new Runnable() {
                Runnable thirdDel = new Runnable() {
                    public void run() {
                        txt_num[m].setBackgroundResource(R.drawable.rectangle_gray);
                        txt_num[n].setBackgroundResource(R.drawable.rectangle_gray);
                        int j = n + 1;
                        if (j == numbers.length - loop) {
                            txt_num[j-1].setBackgroundResource(R.drawable.rectangle_dark);
                            j = 1;
                            ++loop;
                        }
                        if (loop < numbers.length - 1) {
                            animation_bubble(j);
                        }
                    }
                };
                public void run() {
                    handler.postDelayed(thirdDel, 1500);
                    String temp = txt_num[m].getText().toString();
                    txt_num[m].setText(txt_num[n].getText().toString());
                    txt_num[n].setText(temp);
                }
            };
            public void run() {
                if (Integer.valueOf(txt_num[m].getText().toString()) > Integer.valueOf(txt_num[n].getText().toString())) {
                    handler.postDelayed(secondDel, 1500);
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
                        animation_bubble(j);
                    }
                }
            }
        };
        handler.postDelayed(firstDel, 1500);
        txt_num[m].setBackgroundResource(R.drawable.rectangle_orange);
        txt_num[n].setBackgroundResource(R.drawable.rectangle_orange);
    }
}