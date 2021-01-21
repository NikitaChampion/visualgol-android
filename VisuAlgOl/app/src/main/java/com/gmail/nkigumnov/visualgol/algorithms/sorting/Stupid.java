package com.gmail.nkigumnov.visualgol.algorithms.sorting;

import android.app.Activity;

import com.gmail.nkigumnov.visualgol.R;
import com.gmail.nkigumnov.visualgol.activities.StupidSort;

public class Stupid extends Thread {
    private final Activity activity;
    private final int[] array;
    private final int speed;

    public Stupid(Activity activity, int[] array, int speed) {
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
        for (int i = 1; i < array.length; ++i) {
            Thread.sleep(speed);
            ((StupidSort) activity).setColor(new int[]{i - 1, i}, R.drawable.rectangle_orange);

            if (array[i - 1] > array[i]) {
                int temp = array[i - 1];
                array[i - 1] = array[i];
                array[i] = temp;

                Thread.sleep(speed);
                ((StupidSort) activity).setColor(new int[]{i - 1, i}, R.drawable.rectangle_red);

                Thread.sleep(speed);
                ((StupidSort) activity).setText(new int[]{i - 1, i},
                        new String[]{Integer.toString(array[i - 1]), Integer.toString(array[i])});

                Thread.sleep(speed);
                ((StupidSort) activity).setColor(new int[]{i - 1, i}, R.drawable.rectangle_gray);
                i = 0;
            } else {
                Thread.sleep(speed);
                ((StupidSort) activity).setColor(new int[]{i - 1, i}, R.drawable.rectangle_gray);
            }
        }
        Thread.sleep(speed);
        for (int i = 0; i < array.length; ++i) {
            ((StupidSort) activity).setColor(new int[]{i}, R.drawable.rectangle_dark);
        }
    }
}
