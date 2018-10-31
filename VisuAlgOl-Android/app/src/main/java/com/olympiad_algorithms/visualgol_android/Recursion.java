package com.olympiad_algorithms.visualgol_android;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static java.lang.Math.pow;

public class Recursion extends AppCompatActivity implements View.OnClickListener {

    TextView title;
    ImageView imageView;
    Button recursion;
    Button btnSave;
    EditText edit_text;
    private int childPosition = 0, groupPosition = 0;
    private TextView []txt_num;
    private int []numbers = {1,2,3,4,5};
    private Handler handler = new Handler();

    private final static String FILE_NAME = "qwerty.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recursion);

        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            childPosition = arguments.getInt("num", 1);
            groupPosition = arguments.getInt("num_2", 0);
        }

        //Toast.makeText(this, ""+cur, Toast.LENGTH_SHORT).show();

        title = findViewById(R.id.title);

        txt_num = new TextView[5];
        txt_num[0] = findViewById(R.id.txt_num1);
        txt_num[1] = findViewById(R.id.txt_num2);
        txt_num[2] = findViewById(R.id.txt_num3);
        txt_num[3] = findViewById(R.id.txt_num4);
        txt_num[4] = findViewById(R.id.txt_num5);

        imageView = findViewById(R.id.imageView);

        recursion = findViewById(R.id.recursion);
        recursion.setOnClickListener(this);

        edit_text = findViewById(R.id.edit_text);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

        ContestSet();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.recursion:
                handler.removeCallbacksAndMessages(null);
                ContestSet();
                rec();
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
        //DFS == 0, BFS == 1
        for (int i = 0; i < numbers.length; ++i) {
            txt_num[i].setText(String.valueOf(numbers[i]));
            txt_num[i].setBackgroundResource(R.drawable.rectangle_gray);
        }
        title.setText(R.string.recursion);
        recursion.setText(R.string.recursion);
    }

    public void rec() {
        animation_recursion(0);
    }

    public void animation_recursion(final int poz) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                txt_num[poz].setBackgroundResource(R.drawable.rectangle_orange);
                if (poz < 4)
                    animation_recursion(poz+1);
                long sum = 0;
                for (int i = 0; i <= 4-poz; ++i)
                    sum += (long)pow(2, i);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        txt_num[poz].setBackgroundResource(R.drawable.rectangle_gray);
                        if (poz < 4)
                            animation_recursion(poz+1);
                    }
                }, 1250*((long)pow(2,4-poz)+sum));
            }
        }, 1250);
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
}
