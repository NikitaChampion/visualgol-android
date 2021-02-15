package com.gmail.nkigumnov.visualgol.algorithms.sorting;

import android.app.Activity;

import com.gmail.nkigumnov.visualgol.R;
import com.gmail.nkigumnov.visualgol.activities.QuickSort;

public class Quick extends Thread {
    private final Activity activity;
    private final int[] array;
    private final int speed;

    public Quick(Activity activity, int[] array, int speed) {
        this.activity = activity;
        this.array = array.clone();
        this.speed = speed;
    }

    @Override
    public void run() {
        try {
            sort(0, array.length - 1);
        } catch (InterruptedException ignored) {
        }
    }

    private int partition(int l, int r) throws InterruptedException {
        Thread.sleep(speed);
        ((QuickSort) activity).setColor(new int[]{(l + r) / 2}, R.drawable.rectangle_red);

        int pivot = array[(l + r) / 2];
        Thread.sleep(speed);
        ((QuickSort) activity).setPivotText(Integer.toString(pivot));

        Thread.sleep(speed);
        ((QuickSort) activity).setColor(new int[]{(l + r) / 2}, R.drawable.rectangle_gray);

        while (l <= r) {
            Thread.sleep(speed);
            ((QuickSort) activity).setColor(new int[]{l}, R.drawable.rectangle_orange);
            ((QuickSort) activity).setPivotColor(R.drawable.rectangle_orange);
            while (array[l] < pivot) {
                Thread.sleep(speed);
                ((QuickSort) activity).setColor(new int[]{l}, R.drawable.rectangle_gray);
                ((QuickSort) activity).setPivotColor(R.drawable.rectangle_white);
                ++l;
                Thread.sleep(speed);
                ((QuickSort) activity).setColor(new int[]{l}, R.drawable.rectangle_orange);
                ((QuickSort) activity).setPivotColor(R.drawable.rectangle_orange);
            }
            Thread.sleep(speed);
            ((QuickSort) activity).setPivotColor(R.drawable.rectangle_white);

            Thread.sleep(speed);
            ((QuickSort) activity).setColor(new int[]{r}, R.drawable.rectangle_orange);
            ((QuickSort) activity).setPivotColor(R.drawable.rectangle_orange);
            while (array[r] > pivot) {
                Thread.sleep(speed);
                ((QuickSort) activity).setColor(new int[]{r}, R.drawable.rectangle_gray);
                ((QuickSort) activity).setPivotColor(R.drawable.rectangle_white);
                --r;
                Thread.sleep(speed);
                ((QuickSort) activity).setColor(new int[]{r}, R.drawable.rectangle_orange);
                ((QuickSort) activity).setPivotColor(R.drawable.rectangle_orange);
            }
            Thread.sleep(speed);
            ((QuickSort) activity).setPivotColor(R.drawable.rectangle_white);
            if (l >= r) {
                Thread.sleep(speed);
                ((QuickSort) activity).setColor(new int[]{l, r}, R.drawable.rectangle_gray);
                return r;
            }
            int temp = array[l];
            array[l] = array[r];
            array[r] = temp;

            Thread.sleep(speed);
            ((QuickSort) activity).setColor(new int[]{l, r}, R.drawable.rectangle_red);

            Thread.sleep(speed);
            ((QuickSort) activity).setText(new int[]{l, r},
                    new String[]{Integer.toString(array[l]), Integer.toString(array[r])});

            Thread.sleep(speed);
            ((QuickSort) activity).setColor(new int[]{l, r}, R.drawable.rectangle_gray);
            ++l;
            --r;
        }
        return r;
    }

    private void sort(int left, int right) throws InterruptedException {
        if (left < right) {
            int q = partition(left, right);

            Thread.sleep(speed);
            for (int i = q + 1; i <= right; ++i) {
                ((QuickSort) activity).setColor(new int[]{i}, R.drawable.rectangle_2_gray);
            }
            sort(left, q);

            Thread.sleep(speed);
            for (int i = q + 1; i <= right; ++i) {
                ((QuickSort) activity).setColor(new int[]{i}, R.drawable.rectangle_gray);
            }
            sort(q + 1, right);
        } else if (left == right) {
            ((QuickSort) activity).setColor(new int[]{left}, R.drawable.rectangle_dark);
        }
    }
}
