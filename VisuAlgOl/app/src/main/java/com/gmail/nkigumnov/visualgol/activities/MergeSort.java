package com.gmail.nkigumnov.visualgol.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.EditText;

import com.gmail.nkigumnov.visualgol.R;
import com.gmail.nkigumnov.visualgol.algorithms.sorting.Merge;
import com.gmail.nkigumnov.visualgol.util.Constants;
import com.gmail.nkigumnov.visualgol.util.Util;

import java.util.Random;
import java.util.Timer;

public class MergeSort extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    private EditText editText;
    private TextView tvDelay;
    private int childPosition = 0, groupPosition = 0;
    private int curSpeed = Constants.SPEED;
    private final int[] array = new int[8];
    private final TextView[] txtNum = new TextView[8];
    private TextView[] mergeArray = new TextView[8];
    private final Random random = new Random();
    private Merge timerAction;
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
        findViewById(R.id.stop).setOnClickListener(this);
        findViewById(R.id.continue_).setOnClickListener(this);

        ((SeekBar) findViewById(R.id.sb_delay)).setOnSeekBarChangeListener(this);
        findViewById(R.id.sort).setOnClickListener(this);
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
        if (id == R.id.sort) {
            sort.cancel();
            update();
            sort = new Timer();
            timerAction = new Merge(this, array, -1);
            sort.scheduleAtFixedRate(timerAction, 0, curSpeed);
        } else if (id == R.id.generate) {
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
        } else if (id == R.id.stop) {
            stop();
        } else if (id == R.id.continue_) {
            continue_();
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
    }

    private void next() {
        sort.cancel();
        sort = new Timer();
        timerAction.run();
    }

    private void stop() {
        sort.cancel();
        sort = new Timer();
    }

    private void continue_() {
        sort.cancel();
        sort = new Timer();
        timerAction = new Merge(this, array, timerAction.timerCounter);
        sort.scheduleAtFixedRate(timerAction, 0, curSpeed);
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

