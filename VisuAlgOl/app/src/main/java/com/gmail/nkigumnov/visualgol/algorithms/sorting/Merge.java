package com.gmail.nkigumnov.visualgol.algorithms.sorting;

import android.app.Activity;
import android.util.Pair;

import com.gmail.nkigumnov.visualgol.R;
import com.gmail.nkigumnov.visualgol.activities.MergeSort;

import java.util.TimerTask;

public class Merge extends TimerTask {
    private final Activity activity;
    private final int[] mainArray;
    public int timerCounter;

    public Merge(Activity activity, int[] array, int timerCounter) {
        this.activity = activity;
        mainArray = array.clone();
        this.timerCounter = timerCounter;
    }

    @Override
    public void run() {
        Pair<Pair<int[], int[]>, Pair<String[], String[]>> p = sort();
        ((MergeSort) activity).setColor(p.first.first, p.first.second);
        ((MergeSort) activity).setText(p.second.first, p.second.second);
        ++timerCounter;
    }

    private int merge(int l, int mid, int r, int[] colors, int[] colorsMerge, int[] array,
                      String[] textArray, String[] textArrayMerge, int currentTime) {
        for (int i = 0; i < array.length; ++i) {
            if (i >= l && i < r) {
                colors[i] = R.drawable.rectangle_gray;
            } else {
                colors[i] = R.drawable.rectangle_2_gray;
            }
        }
        if (currentTime++ == timerCounter) {
            return currentTime;
        }
        int i1 = 0, i2 = 0;
        int[] result = new int[r - l];
        while (l + i1 < mid && mid + i2 < r) {
            colors[l + i1] = R.drawable.rectangle_orange;
            colors[mid + i2] = R.drawable.rectangle_orange;
            if (currentTime++ == timerCounter) {
                return currentTime;
            }
            if (array[l + i1] < array[mid + i2]) {
                result[i1 + i2] = array[l + i1];

                textArray[l + i1] = "";
                textArrayMerge[l + i1 + i2] = Integer.toString(array[l + i1]);
                colors[l + i1] = R.drawable.rectangle_gray;
                if (currentTime++ == timerCounter) {
                    return currentTime;
                }
                ++i1;
            } else {
                result[i1 + i2] = array[mid + i2];

                textArray[mid + i2] = "";
                textArrayMerge[l + i1 + i2] = Integer.toString(array[mid + i2]);
                colors[mid + i2] = R.drawable.rectangle_gray;
                if (currentTime++ == timerCounter) {
                    return currentTime;
                }
                ++i2;
            }
        }
        while (l + i1 < mid) {
            colors[l + i1] = R.drawable.rectangle_orange;
            if (currentTime++ == timerCounter) {
                return currentTime;
            }
            result[i1 + i2] = array[l + i1];

            textArray[l + i1] = "";
            textArrayMerge[l + i1 + i2] = Integer.toString(array[l + i1]);
            colors[l + i1] = R.drawable.rectangle_gray;
            if (currentTime++ == timerCounter) {
                return currentTime;
            }
            ++i1;
        }
        while (mid + i2 < r) {
            colors[mid + i2] = R.drawable.rectangle_orange;
            if (currentTime++ == timerCounter) {
                return currentTime;
            }
            result[i1 + i2] = array[mid + i2];

            textArray[mid + i2] = "";
            textArrayMerge[l + i1 + i2] = Integer.toString(array[mid + i2]);
            colors[mid + i2] = R.drawable.rectangle_gray;
            if (currentTime++ == timerCounter) {
                return currentTime;
            }
            ++i2;
        }
        for (int i = 0; i < i1 + i2; ++i) {
            array[l + i] = result[i];
            colorsMerge[l + i] = R.drawable.rectangle_orange;
            if (currentTime++ == timerCounter) {
                return currentTime;
            }

            textArray[l + i] = Integer.toString(array[l + i]);
            textArrayMerge[l + i] = "";
            textArrayMerge[l + i] = "";
            colorsMerge[l + i] = R.drawable.rectangle_white;
            if (currentTime++ == timerCounter) {
                return currentTime;
            }
        }
        return currentTime;
    }

    private Pair<Pair<int[], int[]>, Pair<String[], String[]>> sort() {
        int[] colors = new int[mainArray.length];
        int[] colorsMerge = new int[mainArray.length];
        int[] array = mainArray.clone();
        String[] textArray = new String[mainArray.length];
        String[] textArrayMerge = new String[mainArray.length];
        int currentTime = -1;
        for (int i = 0; i < array.length; ++i) {
            colors[i] = R.drawable.rectangle_gray;
            colorsMerge[i] = R.drawable.rectangle_white;
            textArray[i] = String.valueOf(array[i]);
            textArrayMerge[i] = "";
        }
        if (currentTime++ == timerCounter) {
            return new Pair<>(new Pair<>(colors, colorsMerge), new Pair<>(textArray, textArrayMerge));
        }
        for (int i = 1; i < array.length; i *= 2) {
            for (int j = 0; j < array.length - i; j += 2 * i) {
                currentTime = merge(j, j + i, Math.min(j + 2 * i, array.length), colors, colorsMerge, array,
                        textArray, textArrayMerge, currentTime);
                if (currentTime == timerCounter + 1) {
                    return new Pair<>(new Pair<>(colors, colorsMerge), new Pair<>(textArray, textArrayMerge));
                }
            }
        }
        for (int i = 0; i < array.length; ++i) {
            colors[i] = R.drawable.rectangle_dark;
        }
        if (currentTime == timerCounter) {
            return new Pair<>(new Pair<>(colors, colorsMerge), new Pair<>(textArray, textArrayMerge));
        }
        --timerCounter;
        return new Pair<>(new Pair<>(colors, colorsMerge), new Pair<>(textArray, textArrayMerge));
    }
}
