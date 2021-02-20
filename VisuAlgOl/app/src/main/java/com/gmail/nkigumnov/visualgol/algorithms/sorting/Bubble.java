package com.gmail.nkigumnov.visualgol.algorithms.sorting;

import android.app.Activity;
import android.util.Pair;

import com.gmail.nkigumnov.visualgol.R;
import com.gmail.nkigumnov.visualgol.activities.BubbleSort;

import java.util.TimerTask;

public class Bubble extends TimerTask {
    private final Activity activity;
    private final int[] mainArray;
    public int timerCounter;

    public Bubble(Activity activity, int[] array, int timerCounter) {
        this.activity = activity;
        mainArray = array.clone();
        this.timerCounter = timerCounter;
    }

    @Override
    public void run() {
        Pair<int[], int[]> p = sort();
        ((BubbleSort) activity).setColor(p.first);
        ((BubbleSort) activity).setText(p.second);
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
        for (int i = 0; i < array.length; ++i) {
            for (int j = 1; j < array.length - i; ++j) {
                colors[j - 1] = colors[j] = R.drawable.rectangle_orange;
                if (currentTime++ == timerCounter) {
                    return new Pair<>(colors, array);
                }

                if (array[j - 1] > array[j]) {
                    colors[j - 1] = colors[j] = R.drawable.rectangle_red;
                    if (currentTime++ == timerCounter) {
                        return new Pair<>(colors, array);
                    }

                    int temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;

                    if (currentTime++ == timerCounter) {
                        return new Pair<>(colors, array);
                    }
                }
                colors[j - 1] = colors[j] = R.drawable.rectangle_gray;
                if (j == array.length - i - 1) {
                    colors[j] = R.drawable.rectangle_dark;
                }
                if (currentTime++ == timerCounter) {
                    return new Pair<>(colors, array);
                }
            }
            if (i == array.length - 1) {
                colors[0] = R.drawable.rectangle_dark;
                if (currentTime++ == timerCounter) {
                    return new Pair<>(colors, array);
                }
            }
        }
        --timerCounter;
        return new Pair<>(colors, array);
    }
}
