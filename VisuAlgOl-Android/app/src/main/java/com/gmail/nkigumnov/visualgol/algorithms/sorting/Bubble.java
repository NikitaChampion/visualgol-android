package com.gmail.nkigumnov.visualgol.algorithms.sorting;

import android.app.Activity;

import com.gmail.nkigumnov.visualgol.R;
import com.gmail.nkigumnov.visualgol.activities.BubbleSort;
import com.gmail.nkigumnov.visualgol.util.AlgoCompletionListener;

import java.util.TimerTask;

public class Bubble extends TimerTask {
    private final Activity activity;
    private final int[] mainArray;
    private int[] array;
    private int[] colors;
    private int time;
    public int timerCounter;
    public AlgoCompletionListener completionListener;

    public Bubble(Activity activity, int[] array, int timerCounter) {
        this.activity = activity;
        mainArray = array.clone();
        this.timerCounter = timerCounter;
    }

    public void setCompletionListener(AlgoCompletionListener completionListener) {
        this.completionListener = completionListener;
    }

    @Override
    public void run() {
        time = -1;
        array = mainArray.clone();
        colors = new int[array.length];
        for (int i = 0; i < array.length; ++i) {
            colors[i] = R.drawable.rectangle_gray;
        }

        sort();

        ((BubbleSort) activity).setColor(colors);
        ((BubbleSort) activity).setText(array);
        ++timerCounter;
    }

    private void sort() {
        if (time++ == timerCounter) {
            return;
        }
        for (int i = 0; i < array.length; ++i) {
            for (int j = 1; j < array.length - i; ++j) {
                colors[j - 1] = colors[j] = R.drawable.rectangle_orange;
                if (time++ == timerCounter) {
                    return;
                }

                if (array[j - 1] > array[j]) {
                    colors[j - 1] = colors[j] = R.drawable.rectangle_red;
                    if (time++ == timerCounter) {
                        return;
                    }

                    int temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;

                    if (time++ == timerCounter) {
                        return;
                    }
                }
                colors[j - 1] = colors[j] = R.drawable.rectangle_gray;
                if (j == array.length - i - 1) {
                    colors[j] = R.drawable.rectangle_dark;
                }
                if (time++ == timerCounter) {
                    return;
                }
            }
            if (i == array.length - 1) {
                colors[0] = R.drawable.rectangle_dark;
                completionListener.onAlgoCompleted();
                if (time++ == timerCounter) {
                    return;
                }
            }
        }
        --timerCounter;
    }
}
