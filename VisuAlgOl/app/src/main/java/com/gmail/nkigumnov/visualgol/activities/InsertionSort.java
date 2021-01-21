package com.gmail.nkigumnov.visualgol.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.EditText;

import com.gmail.nkigumnov.visualgol.R;
import com.gmail.nkigumnov.visualgol.algorithms.sorting.Insertion;
import com.gmail.nkigumnov.visualgol.util.Constants;
import com.gmail.nkigumnov.visualgol.util.Util;

import java.util.Random;

public class InsertionSort extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    private EditText editText;
    private TextView tvDelay;
    private int childPosition = 0, groupPosition = 0;
    private int curSpeed = Constants.SPEED;
    private final int[] array = new int[8];
    private final TextView[] txtNum = new TextView[8];
    private Insertion insSort;
    private final Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertion_sort);

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

        tvDelay = findViewById(R.id.TvDelay);
        tvDelay.setText(R.string.sec);

        ((SeekBar) findViewById(R.id.SbDelay)).setOnSeekBarChangeListener(this);
        findViewById(R.id.ins_sort).setOnClickListener(this);
        findViewById(R.id.generate).setOnClickListener(this);
        findViewById(R.id.btnSave).setOnClickListener(this);

        editText = findViewById(R.id.edit_text);

        generate();
        insSort = new Insertion(this, array, curSpeed);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.ins_sort) {
            insSort.interrupt();
            update();
            insSort = new Insertion(this, array, curSpeed);
            insSort.start();
        } else if (id == R.id.generate) {
            insSort.interrupt();
            update();
            generate();
        } else if (id == R.id.btnSave) {
            Util.saveText(this, ((editText.getText().toString().equals("3 4 2 1")) ? '1' : '0'),
                    groupPosition + childPosition);
        }
    }

    private void generate() {
        for (int i = 0; i < array.length; ++i) {
            array[i] = random.nextInt() % 10;
        }
        update();
    }

    private void update() {
        runOnUiThread(() -> {
            for (int i = 0; i < array.length; ++i) {
                txtNum[i].setText(String.valueOf(array[i]));
                txtNum[i].setBackgroundResource(R.drawable.rectangle_gray);
            }
        });
    }

    public void setColor(final int[] indices, final int color) {
        runOnUiThread(() -> {
            for (int index : indices) {
                txtNum[index].setBackgroundResource(color);
                txtNum[index].invalidate();
                txtNum[index].requestLayout();
                txtNum[index].refreshDrawableState();
                txtNum[index].forceLayout();
            }
        });
    }

    public void setText(final int[] indices, final String[] text) {
        runOnUiThread(() -> {
            for (int i = 0; i < indices.length; ++i) {
                txtNum[indices[i]].setText(text[i]);
                txtNum[indices[i]].invalidate();
                txtNum[indices[i]].requestLayout();
                txtNum[indices[i]].refreshDrawableState();
                txtNum[indices[i]].forceLayout();
            }
        });
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