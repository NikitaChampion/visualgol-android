package com.gmail.nkigumnov.visualgol.algorithms.sorting;

import android.app.Activity;

import com.gmail.nkigumnov.visualgol.R;
import com.gmail.nkigumnov.visualgol.activities.QuickSort;
import com.gmail.nkigumnov.visualgol.util.AlgoCompletionListener;

import java.util.TimerTask;

public class Quick extends TimerTask {
    private final Activity activity;
    private final int[] mainArray;
    private int[] array;
    private int pivot;
    private int[] colors;
    private int time;
    public int timerCounter;
    public AlgoCompletionListener completionListener;

    public Quick(Activity activity, int[] array, int timerCounter) {
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
        colors = new int[mainArray.length + 1];
        colors[mainArray.length] = R.drawable.rectangle_white;
        for (int i = 0; i < array.length; ++i) {
            colors[i] = R.drawable.rectangle_gray;
        }

        sort();

        String[] text = new String[mainArray.length + 1];
        for (int i = 0; i < array.length; ++i) {
            text[i] = String.valueOf(array[i]);
        }
        text[mainArray.length] = String.valueOf(pivot);

        ((QuickSort) activity).setColor(colors);
        ((QuickSort) activity).setText(text);
        ++timerCounter;
    }

    private int partition(int l, int r) {
        colors[(l + r) / 2] = R.drawable.rectangle_red;
        if (time++ == timerCounter) {
            return r;
        }

        int pivot = array[(l + r) / 2];
        this.pivot = pivot;
        if (time++ == timerCounter) {
            return r;
        }

        colors[(l + r) / 2] = R.drawable.rectangle_gray;
        if (time++ == timerCounter) {
            return r;
        }

        while (l <= r) {
            colors[l] = R.drawable.rectangle_orange;
            colors[mainArray.length] = R.drawable.rectangle_orange;
            if (time++ == timerCounter) {
                return r;
            }
            while (array[l] < pivot) {
                colors[l] = R.drawable.rectangle_gray;
                colors[mainArray.length] = R.drawable.rectangle_white;
                if (time++ == timerCounter) {
                    return r;
                }
                ++l;
                colors[l] = R.drawable.rectangle_orange;
                colors[mainArray.length] = R.drawable.rectangle_orange;
                if (time++ == timerCounter) {
                    return r;
                }
            }
            colors[mainArray.length] = R.drawable.rectangle_white;
            if (time++ == timerCounter) {
                return r;
            }

            colors[r] = R.drawable.rectangle_orange;
            colors[mainArray.length] = R.drawable.rectangle_orange;
            if (time++ == timerCounter) {
                return r;
            }
            while (array[r] > pivot) {
                colors[r] = R.drawable.rectangle_gray;
                colors[mainArray.length] = R.drawable.rectangle_white;
                if (time++ == timerCounter) {
                    return r;
                }
                --r;
                colors[r] = R.drawable.rectangle_orange;
                colors[mainArray.length] = R.drawable.rectangle_orange;
                if (time++ == timerCounter) {
                    return r;
                }
            }
            colors[mainArray.length] = R.drawable.rectangle_white;
            if (time++ == timerCounter) {
                return r;
            }
            if (l >= r) {
                colors[l] = colors[r] = R.drawable.rectangle_gray;
                if (time++ == timerCounter) {
                    return r;
                }
                return r;
            }
            int temp = array[l];
            array[l] = array[r];
            array[r] = temp;

            colors[l] = colors[r] = R.drawable.rectangle_red;
            if (time++ == timerCounter) {
                return r;
            }

            colors[l] = colors[r] = R.drawable.rectangle_gray;
            if (time++ == timerCounter) {
                return r;
            }
            ++l;
            --r;
        }
        return r;
    }

    private void sort(int left, int right) {
        if (left < right) {
            int q = partition(left, right);
            if (time == timerCounter + 1) {
                return;
            }

            for (int i = q + 1; i <= right; ++i) {
                colors[i] = R.drawable.rectangle_2_gray;
            }
            if (time++ == timerCounter) {
                return;
            }
            sort(left, q);
            if (time == timerCounter + 1) {
                return;
            }

            for (int i = q + 1; i <= right; ++i) {
                colors[i] = R.drawable.rectangle_gray;
            }
            if (time++ == timerCounter) {
                return;
            }
            sort(q + 1, right);
        } else if (left == right) {
            colors[left] = R.drawable.rectangle_dark;
            ++time;
        }
    }

    private void sort() {
        if (time++ == timerCounter) {
            return;
        }
        sort(0, array.length - 1);
        if (time == timerCounter + 1) {
            return;
        }
        --timerCounter;
        completionListener.onAlgoCompleted();
    }
}
