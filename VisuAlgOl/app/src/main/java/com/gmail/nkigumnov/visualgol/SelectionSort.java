package com.gmail.nkigumnov.visualgol;

import androidx.appcompat.app.AppCompatActivity;

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

public class SelectionSort extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    Button sel_sort;
    Button generate;
    Button btnSave;
    EditText edit_text;
    SeekBar SbDelay;
    TextView TvDelay;
    private int childPosition = 0;
    private int curSpeed = Constants.SPEED;
    private int poz = 0;
    private TextView[] txt_num;
    final Random random = new Random();
    private final int[] numbers = new int[8];
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_sort);

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

        sel_sort = findViewById(R.id.sel_sort);
        sel_sort.setOnClickListener(this);

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
            case R.id.sel_sort:
                handler.removeCallbacksAndMessages(null);
                ContestSet();
                selection_sort();
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
            txt_num[i].setBackgroundResource(R.drawable.rectangle_gray);
        }
    }

    public void selection_sort() {
        animation_selection();
    }

    public void animation_selection() {
        long current = 1;
        for (int i = 0; i < numbers.length; ++i) {
            final int y = i;
            handler.postDelayed(() -> {
                txt_num[y].setBackgroundResource(R.drawable.rectangle_red);
                poz = y;
            }, curSpeed * current);
            ++current;
            for (int j = i + 1; j < numbers.length; ++j) {
                final int x = j;
                handler.postDelayed(() -> txt_num[x].setBackgroundResource(R.drawable.rectangle_orange), curSpeed * current);
                ++current;
                handler.postDelayed(() -> {
                    if (Integer.parseInt(txt_num[x].getText().toString()) < Integer.parseInt(txt_num[poz].getText().toString())) {
                        if (poz != y)
                            txt_num[poz].setBackgroundResource(R.drawable.rectangle_gray);
                        else
                            txt_num[poz].setBackgroundResource(R.drawable.rectangle_purple);
                        poz = x;
                        txt_num[poz].setBackgroundResource(R.drawable.rectangle_red);
                    } else
                        txt_num[x].setBackgroundResource(R.drawable.rectangle_gray);
                }, curSpeed * current);
                ++current;
            }
            //Swap
            handler.postDelayed(() -> {
                String temp = txt_num[y].getText().toString();
                txt_num[y].setText(txt_num[poz].getText().toString());
                txt_num[poz].setText(temp);
                txt_num[poz].setBackgroundResource(R.drawable.rectangle_gray);
                txt_num[y].setBackgroundResource(R.drawable.rectangle_dark);
            }, curSpeed * current);
            ++current;
        }
    }

    static String convertStreamToString(FileInputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    public String loadText() {
        try {
            FileInputStream fin = openFileInput(Constants.FILE_NAME);
            String str = convertStreamToString(fin);
            fin.close();
            return str;
        } catch (IOException ex) {
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
            FileOutputStream fos = openFileOutput(Constants.FILE_NAME, MODE_PRIVATE);
            fos.write(str.getBytes());
            fos.close();
        } catch (IOException ex) {
            //Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        if (ch == '1') Toast.makeText(this, "Right answer, text saved", Toast.LENGTH_SHORT).show();
        else Toast.makeText(this, "Wrong answer, try again", Toast.LENGTH_SHORT).show();
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