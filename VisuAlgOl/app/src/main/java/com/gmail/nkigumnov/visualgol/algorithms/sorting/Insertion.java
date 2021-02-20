package com.gmail.nkigumnov.visualgol.algorithms.sorting;

import android.app.Activity;
import android.util.Pair;

import com.gmail.nkigumnov.visualgol.R;
import com.gmail.nkigumnov.visualgol.activities.InsertionSort;

import java.util.TimerTask;

public class Insertion extends TimerTask {
    private final Activity activity;
    private final int[] mainArray;
    public int timerCounter;

    public Insertion(Activity activity, int[] array, int timerCounter) {
        this.activity = activity;
        mainArray = array.clone();
        this.timerCounter = timerCounter;
    }

    @Override
    public void run() {
        Pair<int[], int[]> p = sort();
        ((InsertionSort) activity).setColor(p.first);
        ((InsertionSort) activity).setText(p.second);
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
            for (int j = i; j >= 1; --j) {
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

                    colors[j - 1] = colors[j] = R.drawable.rectangle_purple;
                    if (currentTime++ == timerCounter) {
                        return new Pair<>(colors, array);
                    }
                } else {
                    colors[j - 1] = colors[j] = R.drawable.rectangle_purple;
                    if (currentTime++ == timerCounter) {
                        return new Pair<>(colors, array);
                    }
                    break;
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
