package com.gmail.nkigumnov.visualgol.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;

import com.gmail.nkigumnov.visualgol.R;
import com.gmail.nkigumnov.visualgol.algorithms.sorting.Merge;
import com.gmail.nkigumnov.visualgol.util.Constants;
import com.gmail.nkigumnov.visualgol.util.Util;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.slider.Slider;

import java.util.Random;
import java.util.Timer;

public class MergeSort extends AppCompatActivity implements View.OnClickListener {
    private EditText editText;
    private FloatingActionButton stageBtn;
    private TextView tvDelay;
    private int childPosition = 0, groupPosition = 0;
    private int curSpeed = Constants.SPEED;
    private final int[] array = new int[8];
    private final TextView[] txtNum = new TextView[8];
    private final TextView[] mergeArray = new TextView[8];
    private final Random random = new Random();
    private Merge timerAction;
    private boolean timerIsRunning;
    private Timer sort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merge_sort);

        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            childPosition = arguments.getInt("num", 0);
            groupPosition = arguments.getInt("num_2", 0);
        }

        txtNum[0] = findViewById(R.id.txt_num1);
        txtNum[1] = findViewById(R.id.txt_num2);
        txtNum[2] = findViewById(R.id.txt_num3);
        txtNum[3] = findViewById(R.id.txt_num4);
        txtNum[4] = findViewById(R.id.txt_num5);
        txtNum[5] = findViewById(R.id.txt_num6);
        txtNum[6] = findViewById(R.id.txt_num7);
        txtNum[7] = findViewById(R.id.txt_num8);

        mergeArray[0] = findViewById(R.id.arr1);
        mergeArray[1] = findViewById(R.id.arr2);
        mergeArray[2] = findViewById(R.id.arr3);
        mergeArray[3] = findViewById(R.id.arr4);
        mergeArray[4] = findViewById(R.id.arr5);
        mergeArray[5] = findViewById(R.id.arr6);
        mergeArray[6] = findViewById(R.id.arr7);
        mergeArray[7] = findViewById(R.id.arr8);

        tvDelay = findViewById(R.id.tv_delay);
        tvDelay.setText(R.string.sec);

        findViewById(R.id.previous).setOnClickListener(this);
        findViewById(R.id.next).setOnClickListener(this);

        stageBtn = findViewById(R.id.stage);
        stageBtn.setOnClickListener(this);
        stageBtn.setImageResource(R.drawable.play);

        ((Slider) findViewById(R.id.sb_delay)).addOnChangeListener((slider, value, fromUser) -> {
            curSpeed = (int) (value * 1000);
            String s = value + " sec";
            tvDelay.setText(s);
        });

        findViewById(R.id.generate).setOnClickListener(this);
        findViewById(R.id.btn_save).setOnClickListener(this);

        editText = findViewById(R.id.edit_text);

        generate();
        sort = new Timer();
        timerAction = new Merge(this, array, -1);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.generate) {
            sort.cancel();
            generate();
            timerAction = new Merge(this, array, -1);
            sort = new Timer();
        } else if (id == R.id.btn_save) {
            Util.saveText(this, ((editText.getText().toString().equals("1 3 4 4 5 6 4 5")) ? '1' : '0'),
                    groupPosition + childPosition);
        } else if (id == R.id.previous) {
            previous();
        } else if (id == R.id.next) {
            next();
        } else if (id == R.id.stage) {
            if (timerIsRunning) {
                pause();
            } else {
                play();
            }
        }
    }

    private void previous() {
        sort.cancel();
        sort = new Timer();
        if (timerAction.timerCounter <= 0) {
            return;
        }
        timerAction.timerCounter -= 2;
        timerAction.run();
        timerIsRunning = false;
        stageBtn.setImageResource(R.drawable.play);
    }

    private void next() {
        sort.cancel();
        sort = new Timer();
        timerAction.run();
        timerIsRunning = false;
        stageBtn.setImageResource(R.drawable.play);
    }

    private void pause() {
        sort.cancel();
        sort = new Timer();
        timerIsRunning = false;
        stageBtn.setImageResource(R.drawable.play);
    }

    private void play() {
        sort.cancel();
        sort = new Timer();
        timerAction = new Merge(this, array, timerAction.timerCounter);
        sort.scheduleAtFixedRate(timerAction, 0, curSpeed);
        timerIsRunning = true;
        stageBtn.setImageResource(R.drawable.pause);
    }

    private void generate() {
        for (int i = 0; i < array.length; ++i) {
            array[i] = random.nextInt() % 10;
        }
        update();
    }

    private void update() {
        for (int i = 0; i < array.length; ++i) {
            txtNum[i].setText(String.valueOf(array[i]));
            txtNum[i].setBackgroundResource(R.drawable.rectangle_gray);
            mergeArray[i].setText("");
            mergeArray[i].setBackgroundResource(R.drawable.rectangle_white);
        }
        timerIsRunning = false;
    }

    public void setColor(final int[] colors1, final int[] colors2) {
        for (int i = 0; i < array.length; ++i) {
            txtNum[i].setBackgroundResource(colors1[i]);
            mergeArray[i].setBackgroundResource(colors2[i]);
        }
    }

    public void setText(final String[] values1, final String[] values2) {
        for (int i = 0; i < array.length; ++i) {
            txtNum[i].setText(values1[i]);
            mergeArray[i].setText(values2[i]);
        }
    }
}
