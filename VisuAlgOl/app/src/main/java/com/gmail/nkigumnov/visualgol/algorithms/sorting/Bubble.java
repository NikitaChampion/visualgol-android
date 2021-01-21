package com.gmail.nkigumnov.visualgol.algorithms.sorting;

import android.app.Activity;

import com.gmail.nkigumnov.visualgol.R;
import com.gmail.nkigumnov.visualgol.activities.BubbleSort;

public class Bubble extends Thread {
    private final Activity activity;
    private final int[] array;
    private final int speed;

    public Bubble(Activity activity, int[] array, int speed) {
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
            for (int j = 1; j < array.length - i; ++j) {
                Thread.sleep(speed);
                ((BubbleSort) activity).setColor(new int[]{j - 1, j}, R.drawable.rectangle_orange);

                if (array[j - 1] > array[j]) {
                    int temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;

                    Thread.sleep(speed);
                    ((BubbleSort) activity).setColor(new int[]{j - 1, j}, R.drawable.rectangle_red);

                    Thread.sleep(speed);
                    ((BubbleSort) activity).setText(new int[]{j - 1, j},
                            new String[]{Integer.toString(array[j - 1]), Integer.toString(array[j])});
                }
                Thread.sleep(speed);
                ((BubbleSort) activity).setColor(new int[]{j - 1, j}, R.drawable.rectangle_gray);
                if (j == array.length - i - 1) {
                    ((BubbleSort) activity).setColor(new int[]{j}, R.drawable.rectangle_dark);
                }
            }
            if (i == array.length - 1) {
                ((BubbleSort) activity).setColor(new int[]{0}, R.drawable.rectangle_dark);
            }
        }
    }
}
