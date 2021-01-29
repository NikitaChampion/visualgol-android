package com.gmail.nkigumnov.visualgol.algorithms.sorting;

import android.app.Activity;

import com.gmail.nkigumnov.visualgol.R;
import com.gmail.nkigumnov.visualgol.activities.CountingSort;

public class Counting extends Thread {
    private final Activity activity;
    private final int[] array;
    private final int[] array2 = new int[7];
    private final int speed;

    public Counting(Activity activity, int[] array, int speed) {
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
            ((CountingSort) activity).setColor(new int[]{i}, R.drawable.rectangle_search_3);
            ((CountingSort) activity).setNumberColor(new int[]{array[i]}, R.drawable.rectangle_search_3);

            Thread.sleep(speed);
            ((CountingSort) activity).setColor(new int[]{i}, R.drawable.rectangle_search_1);
            ((CountingSort) activity).setNumberColor(new int[]{array[i]}, R.drawable.rectangle_2_gray);

            ((CountingSort) activity).setText(new int[]{i}, new String[]{""});
            ((CountingSort) activity).setAmountText(new int[]{array[i]}, new String[]{String.valueOf(++array2[array[i]])});
        }
        int index = 0;
        for (int i = 0; i < array2.length; ++i) {
            for (int j = array2[i] - 1; j >= 0; --j) {
                Thread.sleep(speed);
                ((CountingSort) activity).setColor(new int[]{index}, R.drawable.rectangle_search_3);
                ((CountingSort) activity).setNumberColor(new int[]{i}, R.drawable.rectangle_search_3);
                ((CountingSort) activity).setText(new int[]{index}, new String[]{String.valueOf(i)});
                ((CountingSort) activity).setAmountText(new int[]{i}, new String[]{String.valueOf(j)});

                Thread.sleep(speed);
                ((CountingSort) activity).setColor(new int[]{index++}, R.drawable.rectangle_search_1);
                ((CountingSort) activity).setNumberColor(new int[]{i}, R.drawable.rectangle_2_gray);
            }
        }
    }
}
