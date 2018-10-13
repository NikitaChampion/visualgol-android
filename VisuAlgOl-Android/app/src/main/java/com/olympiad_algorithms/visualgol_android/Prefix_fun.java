package com.olympiad_algorithms.visualgol_android;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Prefix_fun extends AppCompatActivity implements View.OnClickListener {

    Button pr_fun;
    private TextView[] txt_num;
    private TextView[] pr;
    private char[] chars = {'a', 'b', 'a', 'c', 'a', 'b', 'a'};
    private long num_of_clicks = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prefix_fun);

        txt_num = new TextView[7];
        txt_num[0] = findViewById(R.id.txt_num1);
        txt_num[1] = findViewById(R.id.txt_num2);
        txt_num[2] = findViewById(R.id.txt_num3);
        txt_num[3] = findViewById(R.id.txt_num4);
        txt_num[4] = findViewById(R.id.txt_num5);
        txt_num[5] = findViewById(R.id.txt_num6);
        txt_num[6] = findViewById(R.id.txt_num7);

        pr = new TextView[7];
        pr[0] = findViewById(R.id.pr_1);
        pr[1] = findViewById(R.id.pr_2);
        pr[2] = findViewById(R.id.pr_3);
        pr[3] = findViewById(R.id.pr_4);
        pr[4] = findViewById(R.id.pr_5);
        pr[5] = findViewById(R.id.pr_6);
        pr[6] = findViewById(R.id.pr_7);

        for (int i = 0; i < pr.length; ++i) {
            pr[i].setText("0");
            txt_num[i].setText(String.valueOf(chars[i]));
        }

        pr_fun = findViewById(R.id.pr_fun);
        pr_fun.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pr_fun:
                ++num_of_clicks;
                for (int i = 0; i < pr.length; ++i) {
                    pr[i].setText("0");
                    txt_num[i].setTextColor(Color.parseColor("#000000"));
                    pr[i].setBackgroundResource(R.drawable.rectangle_white);
                }
                handler.postDelayed(new Runnable() {
                    public void run() {
                        prefix(num_of_clicks);
                    }
                }, 600);
                break;
            default:
                break;
        }
    }

    public void prefix(long cur) {
        animation_prefix(cur);
    }

    public void animation_prefix(final long cur) {
        pr[0].setBackgroundResource(R.drawable.rectangle_2_gray);
        long current = 0;
        for (int i = 1; i < pr.length; ++i) {
            final int y = i;
            for (int j = 1; j <= i; ++j) {
                final int x = j;
                if (cur != num_of_clicks) return;
                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (cur != num_of_clicks) return;
                        for (TextView u : txt_num)
                            u.setTextColor(Color.parseColor("#000000"));
                        String s = "", s1 = "";
                        if (cur != num_of_clicks) return;
                        for (int k = 0; k < x; ++k) {
                            s += chars[k];
                            txt_num[k].setTextColor(Color.parseColor("#0000FF"));
                        }
                        if (cur != num_of_clicks) return;
                        for (int k = y - x + 1; k <= y; ++k) {
                            s1 += chars[k];
                            if (txt_num[k].getCurrentTextColor() == Color.parseColor("#0000FF"))
                                txt_num[k].setTextColor(Color.parseColor("#FFA500"));
                            else txt_num[k].setTextColor(Color.parseColor("#FF0000"));
                        }
                        if (cur != num_of_clicks) return;
                        if (s.equals(s1)) {
                            pr[y].setText(String.valueOf(x));
                        }
                    }
                }, 2000*current);
                ++current;
            }
            handler.postDelayed(new Runnable() {
                public void run() {
                    if (cur != num_of_clicks) return;
                    for (TextView u : txt_num)
                        u.setTextColor(Color.parseColor("#000000"));
                    pr[y].setBackgroundResource(R.drawable.rectangle_2_gray);
                }
            }, 2000*current);
            ++current;
        }
    }
}
