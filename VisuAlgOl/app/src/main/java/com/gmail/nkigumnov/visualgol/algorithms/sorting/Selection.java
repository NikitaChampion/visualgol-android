package com.gmail.nkigumnov.visualgol.algorithms.sorting;

import android.app.Activity;

import com.gmail.nkigumnov.visualgol.R;
import com.gmail.nkigumnov.visualgol.activities.SelectionSort;

public class Selection extends Thread {
    private final Activity activity;
    private final int[] array;
    private final int speed;

    public Selection(Activity activity, int[] array, int speed) {
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

    private void sort() throws InterruptedException {
        for (int i = 0; i < array.length; ++i) {
            Thread.sleep(speed);
            ((SelectionSort) activity).setColor(new int[]{i}, R.drawable.rectangle_red);
            int pos = i;
            for (int j = i + 1; j < array.length; ++j) {
                Thread.sleep(speed);
                ((SelectionSort) activity).setColor(new int[]{j}, R.drawable.rectangle_orange);

                Thread.sleep(speed);
                if (array[pos] > array[j]) {
                    if (pos == i) {
                        ((SelectionSort) activity).setColor(new int[]{pos}, R.drawable.rectangle_purple);
                    } else {
                        ((SelectionSort) activity).setColor(new int[]{pos}, R.drawable.rectangle_gray);
                    }
                    pos = j;

                    ((SelectionSort) activity).setColor(new int[]{j}, R.drawable.rectangle_red);
                } else {
                    ((SelectionSort) activity).setColor(new int[]{j}, R.drawable.rectangle_gray);
                }
            }
            Thread.sleep(speed);
            int temp = array[i];
            array[i] = array[pos];
            array[pos] = temp;
            ((SelectionSort) activity).setText(new int[]{i, pos},
                    new String[]{Integer.toString(array[i]), Integer.toString(array[pos])});
            ((SelectionSort) activity).setColor(new int[]{pos}, R.drawable.rectangle_gray);
            ((SelectionSort) activity).setColor(new int[]{i}, R.drawable.rectangle_dark);
        }
    }
}
// TODO: slider с 0,... (на 0 не успевает) ([0,01, 3])