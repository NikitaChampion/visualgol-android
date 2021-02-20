package com.gmail.nkigumnov.visualgol.algorithms.sorting;

import android.app.Activity;
import android.util.Pair;

import com.gmail.nkigumnov.visualgol.R;
import com.gmail.nkigumnov.visualgol.activities.StupidSort;

import java.util.TimerTask;

public class Stupid extends TimerTask {
    private final Activity activity;
    private final int[] mainArray;
    public int timerCounter;

    public Stupid(Activity activity, int[] array, int timerCounter) {
        this.activity = activity;
        mainArray = array.clone();
        this.timerCounter = timerCounter;
    }

    @Override
    public void run() {
        Pair<int[], int[]> p = sort();
        ((StupidSort) activity).setColor(p.first);
        ((StupidSort) activity).setText(p.second);
        ++timerCounter;
    }

    private Pair<int[], int[]> sort() {
        int[] colors = new int[mainArray.length];
        int[] array = mainArray.clone();
        int currentTime = -1;
        for (int i = 0; i < array.length; ++i) {
            colors[i] = R.drawable.rectangle_gray;
        }
        if (currentTime++ == timerCounter) {
            return new Pair<>(colors, array);
        }
        for (int i = 1; i < array.length; ++i) {
            colors[i - 1] = colors[i] = R.drawable.rectangle_orange;
            if (currentTime++ == timerCounter) {
                return new Pair<>(colors, array);
            }

            if (array[i - 1] > array[i]) {
                colors[i - 1] = colors[i] = R.drawable.rectangle_red;
                if (currentTime++ == timerCounter) {
                    return new Pair<>(colors, array);
                }

                int temp = array[i - 1];
                array[i - 1] = array[i];
                array[i] = temp;

                colors[i - 1] = colors[i] = R.drawable.rectangle_gray;
                if (currentTime++ == timerCounter) {
                    return new Pair<>(colors, array);
                }
                i = 0;
            } else {
                colors[i - 1] = colors[i] = R.drawable.rectangle_gray;
                if (currentTime++ == timerCounter) {
                    return new Pair<>(colors, array);
                }
            }
        }
        for (int i = 0; i < array.length; ++i) {
            colors[i] = R.drawable.rectangle_dark;
        }
        if (currentTime == timerCounter) {
            return new Pair<>(colors, array);
        }
        --timerCounter;
        return new Pair<>(colors, array);
    }
}
