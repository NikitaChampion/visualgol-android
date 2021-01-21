package com.gmail.nkigumnov.visualgol.algorithms.sorting;

import android.app.Activity;

import com.gmail.nkigumnov.visualgol.R;
import com.gmail.nkigumnov.visualgol.activities.InsertionSort;

public class Insertion extends Thread {
    private final Activity activity;
    private final int[] array;
    private final int speed;

    public Insertion(Activity activity, int[] array, int speed) {
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
            for (int j = i; j >= 1; --j) {
                Thread.sleep(speed);
                ((InsertionSort) activity).setColor(new int[]{j - 1, j}, R.drawable.rectangle_orange);

                if (array[j - 1] > array[j]) {
                    int temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;

                    Thread.sleep(speed);
                    ((InsertionSort) activity).setColor(new int[]{j - 1, j}, R.drawable.rectangle_red);

                    Thread.sleep(speed);
                    ((InsertionSort) activity).setText(new int[]{j - 1, j},
                            new String[]{Integer.toString(array[j - 1]), Integer.toString(array[j])});

                    Thread.sleep(speed);
                    ((InsertionSort) activity).setColor(new int[]{j - 1, j}, R.drawable.rectangle_purple);
                } else {
                    Thread.sleep(speed);
                    ((InsertionSort) activity).setColor(new int[]{j - 1, j}, R.drawable.rectangle_purple);
                    break;
                }
            }
        }
        Thread.sleep(speed);
        for (int i = 0; i < array.length; ++i) {
            ((InsertionSort) activity).setColor(new int[]{i}, R.drawable.rectangle_dark);
        }
    }
}
