package com.olympiad_algorithms.visualgol_android;

import android.graphics.Color;
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

public class Strings extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    TextView title;
    TextView Strings;
    Button strings;
    Button btnSave;
    EditText edit_text;
    SeekBar SbDelay;
    TextView TvDelay;
    private int childPosition = 0, groupPosition = 0;
    private int curSpeed = 1250;
    private TextView []txt_num;
    private TextView []st;
    private char []chars = {'a', 'b', 'a', 'c', 'a', 'b', 'a'};
    private Handler handler = new Handler();

    private final static String FILE_NAME = "qwerty.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strings);

        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            childPosition = arguments.getInt("num", 1);
            groupPosition = arguments.getInt("num_2", 0);
        }

        //Toast.makeText(this, ""+cur, Toast.LENGTH_SHORT).show();

        title = findViewById(R.id.title);

        txt_num = new TextView[7];
        txt_num[0] = findViewById(R.id.txt_num1);
        txt_num[1] = findViewById(R.id.txt_num2);
        txt_num[2] = findViewById(R.id.txt_num3);
        txt_num[3] = findViewById(R.id.txt_num4);
        txt_num[4] = findViewById(R.id.txt_num5);
        txt_num[5] = findViewById(R.id.txt_num6);
        txt_num[6] = findViewById(R.id.txt_num7);

        st = new TextView[7];
        st[0] = findViewById(R.id.s_1);
        st[1] = findViewById(R.id.s_2);
        st[2] = findViewById(R.id.s_3);
        st[3] = findViewById(R.id.s_4);
        st[4] = findViewById(R.id.s_5);
        st[5] = findViewById(R.id.s_6);
        st[6] = findViewById(R.id.s_7);

        for (int i = 0; i < st.length; ++i)
            txt_num[i].setText(String.valueOf(chars[i]));

        TvDelay = findViewById(R.id.TvDelay);
        TvDelay.setText(R.string.sec);

        SbDelay = findViewById(R.id.SbDelay);
        SbDelay.setOnSeekBarChangeListener(this);

        Strings = findViewById(R.id.Strings);

        strings = findViewById(R.id.strings);
        strings.setOnClickListener(this);

        ContestSet();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.strings:
                handler.removeCallbacksAndMessages(null);
                ContestSet();
                if (childPosition == 0)
                    prefix();
                else
                    z();
                break;
            default:
                break;
        }
    }

    public void ContestSet() {
        //prefix == 0, z == 1
        for (int i = 0; i < st.length; ++i) {
            txt_num[i].setTextColor(getResources().getColor(R.color.colorAccent));
            st[i].setText("0");
            st[i].setBackgroundResource(R.drawable.rectangle_white);
        }
        if (childPosition == 0) {
            title.setText(R.string.prefix);
            strings.setText(R.string.prefix);
            Strings.setText(R.string.prefix_fun);
        }
        else {
            title.setText(R.string.z);
            strings.setText(R.string.z);
            Strings.setText(R.string.z_fun);
        }
    }

    public void prefix() {
        animation_prefix();
    }

    public void animation_prefix() {
        st[0].setBackgroundResource(R.drawable.rectangle_2_gray);
        long current = 1;
        for (int i = 1; i < st.length; ++i) {
            final int y = i;
            for (int j = 1; j <= i; ++j) {
                final int x = j;
                handler.postDelayed(new Runnable() {
                    public void run() {
                        for (TextView u : txt_num)
                            u.setTextColor(Color.parseColor("#000000"));
                        StringBuilder s = new StringBuilder(), s1 = new StringBuilder();
                        for (int k = 0; k < x; ++k) {
                            s.append(chars[k]);
                            txt_num[k].setTextColor(Color.parseColor("#0000FF"));
                        }
                        for (int k = y - x + 1; k <= y; ++k) {
                            s1.append(chars[k]);
                            if (txt_num[k].getCurrentTextColor() == Color.parseColor("#0000FF"))
                                txt_num[k].setTextColor(Color.parseColor("#FFA500"));
                            else txt_num[k].setTextColor(Color.parseColor("#FF0000"));
                        }
                        if (s.toString().equals(s1.toString())) {
                            st[y].setText(String.valueOf(x));
                        }
                    }
                }, curSpeed*current);
                ++current;
            }
            handler.postDelayed(new Runnable() {
                public void run() {
                    for (TextView u : txt_num)
                        u.setTextColor(Color.parseColor("#000000"));
                    st[y].setBackgroundResource(R.drawable.rectangle_2_gray);
                }
            }, curSpeed*current);
            ++current;
        }
    }

    public void z() {
        animation_z();
    }

    public void animation_z() {
        st[0].setBackgroundResource(R.drawable.rectangle_2_gray);
        long current = 0;
        for (int i = 1; i < st.length; ++i) {
            final int y = i;
            int j = 0;
            for (; j+i < st.length; ++j)
            {
                final int x = j;
                ++current;
                handler.postDelayed(new Runnable() {
                    public void run() {
                        if (txt_num[x].getCurrentTextColor() == Color.parseColor("#FF0000"))
                            txt_num[x].setTextColor(Color.parseColor("#FFA500"));
                        else txt_num[x].setTextColor(Color.parseColor("#0000FF"));
                        txt_num[x+y].setTextColor(Color.parseColor("#FF0000"));
                    }
                }, curSpeed*current);
                if (chars[j] != chars[j+i]) break;
            }
            final int x = j;
            ++current;
            handler.postDelayed(new Runnable() {
                public void run() {
                    st[y].setText(String.valueOf(x));
                    st[y].setBackgroundResource(R.drawable.rectangle_2_gray);
                    for (TextView u : txt_num)
                        u.setTextColor(Color.parseColor("#000000"));
                }
            }, curSpeed*current);
            ++current;
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
        c[groupPosition+childPosition] = ch;
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
