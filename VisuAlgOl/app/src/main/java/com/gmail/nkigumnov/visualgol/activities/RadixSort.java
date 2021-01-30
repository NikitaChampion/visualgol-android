package com.gmail.nkigumnov.visualgol.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.EditText;

import com.gmail.nkigumnov.visualgol.R;
import com.gmail.nkigumnov.visualgol.algorithms.sorting.Radix;
import com.gmail.nkigumnov.visualgol.util.Constants;
import com.gmail.nkigumnov.visualgol.util.Util;

import java.util.Random;

public class RadixSort extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    private EditText editText;
    private TextView tvDelay;
    private int childPosition = 0, groupPosition = 0;
    private int curSpeed = Constants.SPEED;
    private final int[] array = new int[7];
    private final TextView[] txtNum = new TextView[7];
    private final TextView[] number = new TextView[10];
    private final TextView[] amount = new TextView[10];
    private Radix radixSort;
    private final Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radix_sort);

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

        number[0] = findViewById(R.id.number1);
        number[1] = findViewById(R.id.number2);
        number[2] = findViewById(R.id.number3);
        number[3] = findViewById(R.id.number4);
        number[4] = findViewById(R.id.number5);
        number[5] = findViewById(R.id.number6);
        number[6] = findViewById(R.id.number7);
        number[7] = findViewById(R.id.number8);
        number[8] = findViewById(R.id.number9);
        number[9] = findViewById(R.id.number10);

        amount[0] = findViewById(R.id.amount1);
        amount[1] = findViewById(R.id.amount2);
        amount[2] = findViewById(R.id.amount3);
        amount[3] = findViewById(R.id.amount4);
        amount[4] = findViewById(R.id.amount5);
        amount[5] = findViewById(R.id.amount6);
        amount[6] = findViewById(R.id.amount7);
        amount[7] = findViewById(R.id.amount8);
        amount[8] = findViewById(R.id.amount9);
        amount[9] = findViewById(R.id.amount10);

        tvDelay = findViewById(R.id.tv_delay);
        tvDelay.setText(R.string.sec);

        ((SeekBar) findViewById(R.id.sb_delay)).setOnSeekBarChangeListener(this);
        findViewById(R.id.sort).setOnClickListener(this);
        findViewById(R.id.generate).setOnClickListener(this);
        findViewById(R.id.btn_save).setOnClickListener(this);

        editText = findViewById(R.id.edit_text);

        generate();
        radixSort = new Radix(this, array, curSpeed);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.sort) {
            radixSort.interrupt();
            update();
            radixSort = new Radix(this, array, curSpeed);
            radixSort.start();
        } else if (id == R.id.generate) {
            radixSort.interrupt();
            update();
            generate();
        } else if (id == R.id.btn_save) {
            Util.saveText(this, ((editText.getText().toString().equals("10")) ? '1' : '0'),
                    groupPosition + childPosition);
        }
    }

    private void generate() {
        for (int i = 0; i < array.length; ++i) {
            array[i] = Math.abs(random.nextInt()) % 1000;
        }
        update();
    }

    private void update() {
        runOnUiThread(() -> {
            for (int i = 0; i < array.length; ++i) {
                txtNum[i].setText(String.valueOf(array[i]));
                txtNum[i].setBackgroundResource(R.drawable.rectangle_search_1);
            }
            for (int i = 0; i < number.length; ++i) {
                number[i].setText(String.valueOf(i));
                number[i].setBackgroundResource(R.drawable.rectangle_2_gray);
                amount[i].setText("0");
                amount[i].setBackgroundResource(R.drawable.rectangle_white);
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

    public void setNumberColor(final int[] indices, final int color) {
        runOnUiThread(() -> {
            for (int index : indices) {
                number[index].setBackgroundResource(color);
                number[index].invalidate();
                number[index].requestLayout();
                number[index].refreshDrawableState();
                number[index].forceLayout();
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

    public void setAmountText(final int[] indices, final String[] text) {
        runOnUiThread(() -> {
            for (int i = 0; i < indices.length; ++i) {
                amount[indices[i]].setText(text[i]);
                amount[indices[i]].invalidate();
                amount[indices[i]].requestLayout();
                amount[indices[i]].refreshDrawableState();
                amount[indices[i]].forceLayout();
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
