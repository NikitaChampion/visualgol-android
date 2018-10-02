package com.olympiad_algorithms.visualgol_android;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Z_fun extends AppCompatActivity implements View.OnClickListener {

    Button z_fun;
    private TextView[] txt_num;
    private TextView[] z;
    private char[] chars = {'a', 'b', 'a', 'c', 'a', 'b', 'a'};
    private long num_of_clicks = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_z_fun);

        txt_num = new TextView[7];
        txt_num[0] = findViewById(R.id.txt_num1);
        txt_num[1] = findViewById(R.id.txt_num2);
        txt_num[2] = findViewById(R.id.txt_num3);
        txt_num[3] = findViewById(R.id.txt_num4);
        txt_num[4] = findViewById(R.id.txt_num5);
        txt_num[5] = findViewById(R.id.txt_num6);
        txt_num[6] = findViewById(R.id.txt_num7);

        z = new TextView[7];
        z[0] = findViewById(R.id.z_1);
        z[1] = findViewById(R.id.z_2);
        z[2] = findViewById(R.id.z_3);
        z[3] = findViewById(R.id.z_4);
        z[4] = findViewById(R.id.z_5);
        z[5] = findViewById(R.id.z_6);
        z[6] = findViewById(R.id.z_7);

        for (int i = 0; i < z.length; ++i) {
            z[i].setText("0");
            txt_num[i].setText(String.valueOf(chars[i]));
        }

        z_fun = findViewById(R.id.z_fun);
        z_fun.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.z_fun:
                ++num_of_clicks;
                for (int i = 0; i < z.length; ++i) {
                    z[i].setText("0");
                    txt_num[i].setTextColor(Color.parseColor("#000000"));
                    z[i].setBackgroundResource(R.drawable.rectangle_white);
                }
                handler.postDelayed(new Runnable() {
                    public void run() {
                        z(num_of_clicks);
                    }
                }, 600);
                break;
            default:
                break;
        }
    }

    public void z(long cur) {
        animation_z(cur);
    }

    public void animation_z(final long cur) {
        z[0].setBackgroundResource(R.drawable.rectangle_2_gray);
        long current = 0;
        for (int i = 1; i < z.length; ++i) {
            final int y = i;
            int j = 0;
            for (; j+i < z.length; ++j)
            {
                final int x = j;
                ++current;
                if (cur != num_of_clicks) return;
                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (cur != num_of_clicks) return;
                        if (txt_num[x].getCurrentTextColor() == Color.parseColor("#FF0000"))
                            txt_num[x].setTextColor(Color.parseColor("#FFA500"));
                        else txt_num[x].setTextColor(Color.parseColor("#0000FF"));
                        txt_num[x+y].setTextColor(Color.parseColor("#FF0000"));
                    }
                }, 2000*current);
                if (chars[j] != chars[j+i]) break;
            }
            final int x = j;
            ++current;
            handler.postDelayed(new Runnable() {
                public void run() {
                    if (cur != num_of_clicks) return;
                    z[y].setText(String.valueOf(x));
                    z[y].setBackgroundResource(R.drawable.rectangle_2_gray);
                    for (TextView u : txt_num)
                        u.setTextColor(Color.parseColor("#000000"));
                }
            }, 2000*current);
            ++current;
        }
    }
}
