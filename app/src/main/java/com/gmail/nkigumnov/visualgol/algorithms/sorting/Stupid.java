package com.gmail.nkigumnov.visualgol.algorithms.sorting;

import android.app.Activity;

import com.gmail.nkigumnov.visualgol.R;
import com.gmail.nkigumnov.visualgol.activities.StupidSort;
import com.gmail.nkigumnov.visualgol.util.AlgoCompletionListener;

import java.util.TimerTask;

public class Stupid extends TimerTask {
    private final Activity activity;
    private final int[] mainArray;
    private int[] array;
    private int[] colors;
    private int time;
    public int timerCounter;
    public AlgoCompletionListener completionListener;

    public Stupid(Activity activity, int[] array, int timerCounter) {
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

        ((StupidSort) activity).setColor(colors);
        ((StupidSort) activity).setText(array);
        ++timerCounter;
    }

    private void sort() {
        if (time++ == timerCounter) {
            return;
        }
        for (int i = 1; i < array.length; ++i) {
            colors[i - 1] = colors[i] = R.drawable.rectangle_orange;
            if (time++ == timerCounter) {
                return;
            }

            if (array[i - 1] > array[i]) {
                colors[i - 1] = colors[i] = R.drawable.rectangle_red;
                if (time++ == timerCounter) {
                    return;
                }

                int temp = array[i - 1];
                array[i - 1] = array[i];
                array[i] = temp;

                colors[i - 1] = colors[i] = R.drawable.rectangle_gray;
                if (time++ == timerCounter) {
                    return;
                }
                i = 0;
            } else {
                colors[i - 1] = colors[i] = R.drawable.rectangle_gray;
                if (time++ == timerCounter) {
                    return;
                }
            }
        }
        for (int i = 0; i < array.length; ++i) {
            colors[i] = R.drawable.rectangle_dark;
        }
        completionListener.onAlgoCompleted();
        if (time == timerCounter) {
            return;
        }
        --timerCounter;
    }
}
