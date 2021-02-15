package com.gmail.nkigumnov.visualgol.algorithms.sorting;

import android.app.Activity;

import com.gmail.nkigumnov.visualgol.R;
import com.gmail.nkigumnov.visualgol.activities.MergeSort;

public class Merge extends Thread {
    private final Activity activity;
    private final int[] array;
    private final int speed;

    public Merge(Activity activity, int[] array, int speed) {
        this.activity = activity;
        this.array = array.clone();
        this.speed = speed;
    }

    @Override
    public void run() {
        try {
            sort();
        } catch (InterruptedException ignored) {
        }
    }

    private void merge(int l, int mid, int r) throws InterruptedException {
        Thread.sleep(speed);
        for (int i = 0; i < array.length; ++i) {
            if (i >= l && i < r) {
                ((MergeSort) activity).setColor(new int[]{i}, R.drawable.rectangle_gray);
            } else {
                ((MergeSort) activity).setColor(new int[]{i}, R.drawable.rectangle_2_gray);
            }
        }
        int i1 = 0, i2 = 0;
        int[] result = new int[r - l];
        while (l + i1 < mid && mid + i2 < r) {
            Thread.sleep(speed);
            ((MergeSort) activity).setColor(new int[]{l + i1}, R.drawable.rectangle_orange);
            ((MergeSort) activity).setColor(new int[]{mid + i2}, R.drawable.rectangle_orange);
            if (array[l + i1] < array[mid + i2]) {
                result[i1 + i2] = array[l + i1];
                Thread.sleep(speed);
                ((MergeSort) activity).setText(new int[]{l + i1},
                        new String[]{""});
                ((MergeSort) activity).setMergeText(new int[]{l + i1 + i2},
                        new String[]{Integer.toString(array[l + i1])});
                ((MergeSort) activity).setColor(new int[]{l + i1}, R.drawable.rectangle_gray);
                ++i1;
            } else {
                result[i1 + i2] = array[mid + i2];
                Thread.sleep(speed);
                ((MergeSort) activity).setText(new int[]{mid + i2},
                        new String[]{""});
                ((MergeSort) activity).setMergeText(new int[]{l + i1 + i2},
                        new String[]{Integer.toString(array[mid + i2])});
                ((MergeSort) activity).setColor(new int[]{mid + i2}, R.drawable.rectangle_gray);
                ++i2;
            }
        }
        while (l + i1 < mid) {
            Thread.sleep(speed);
            ((MergeSort) activity).setColor(new int[]{l + i1}, R.drawable.rectangle_orange);
            result[i1 + i2] = array[l + i1];
            Thread.sleep(speed);
            ((MergeSort) activity).setText(new int[]{l + i1},
                    new String[]{""});
            ((MergeSort) activity).setMergeText(new int[]{l + i1 + i2},
                    new String[]{Integer.toString(array[l + i1])});
            ((MergeSort) activity).setColor(new int[]{l + i1}, R.drawable.rectangle_gray);
            ++i1;
        }
        while (mid + i2 < r) {
            Thread.sleep(speed);
            ((MergeSort) activity).setColor(new int[]{mid + i2}, R.drawable.rectangle_orange);
            result[i1 + i2] = array[mid + i2];
            Thread.sleep(speed);
            ((MergeSort) activity).setText(new int[]{mid + i2},
                    new String[]{""});
            ((MergeSort) activity).setMergeText(new int[]{l + i1 + i2},
                    new String[]{Integer.toString(array[mid + i2])});
            ((MergeSort) activity).setColor(new int[]{mid + i2}, R.drawable.rectangle_gray);
            ++i2;
        }
        for (int i = 0; i < i1 + i2; ++i) {
            array[l + i] = result[i];
            Thread.sleep(speed);
            ((MergeSort) activity).setMergeColor(new int[]{l + i}, R.drawable.rectangle_orange);

            Thread.sleep(speed);
            ((MergeSort) activity).setMergeText(new int[]{l + i}, new String[]{" "});
            ((MergeSort) activity).setText(new int[]{l + i},
                    new String[]{Integer.toString(array[l + i])});
            ((MergeSort) activity).setMergeColor(new int[]{l + i}, R.drawable.rectangle_white);
        }
    }

    private void sort() throws InterruptedException {
        for (int i = 1; i < array.length; i *= 2) {
            for (int j = 0; j < array.length - i; j += 2 * i) {
                merge(j, j + i, Math.min(j + 2 * i, array.length));
            }
        }
        Thread.sleep(speed);
        for (int i = 0; i < array.length; ++i) {
            ((MergeSort) activity).setColor(new int[]{i}, R.drawable.rectangle_dark);
        }
    }
}
