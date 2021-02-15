package com.gmail.nkigumnov.visualgol.algorithms.sorting;

import android.app.Activity;

import com.gmail.nkigumnov.visualgol.R;
import com.gmail.nkigumnov.visualgol.activities.RadixSort;

public class Radix extends Thread {
    private final Activity activity;
    private int[] array;
    private final int[] amount = new int[10];
    private final int speed;

    public Radix(Activity activity, int[] array, int speed) {
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
        for (int i = 1; i < 1000; i *= 10) {
            Thread.sleep(speed);
            int[] answer = new int[array.length];
            for (int j = 0; j < amount.length; ++j) {
                amount[j] = 0;
                ((RadixSort) activity).setAmountText(new int[]{j}, new String[]{String.valueOf(amount[j])});
            }
            for (int j = 0; j < array.length; ++j) {
                Thread.sleep(speed);
                int digit = (array[j] / i) % 10;
                ++amount[digit];
                ((RadixSort) activity).setColor(new int[]{j}, R.drawable.rectangle_search_3);
                ((RadixSort) activity).setNumberColor(new int[]{digit}, R.drawable.rectangle_search_3);
                ((RadixSort) activity).setAmountText(new int[]{digit}, new String[]{String.valueOf(amount[digit])});

                Thread.sleep(speed);
                ((RadixSort) activity).setColor(new int[]{j}, R.drawable.rectangle_search_1);
                ((RadixSort) activity).setNumberColor(new int[]{digit}, R.drawable.rectangle_2_gray);
            }
            for (int j = 1; j < amount.length; ++j) {
                Thread.sleep(speed);
                amount[j] += amount[j - 1];
                ((RadixSort) activity).setNumberColor(new int[]{j}, R.drawable.rectangle_search_3);
                ((RadixSort) activity).setAmountText(new int[]{j}, new String[]{String.valueOf(amount[j])});

                Thread.sleep(speed);
                ((RadixSort) activity).setNumberColor(new int[]{j}, R.drawable.rectangle_2_gray);
            }
            for (int j = array.length - 1; j >= 0; --j) {
                int digit = (array[j] / i) % 10;
                answer[amount[digit] - 1] = array[j];
                --amount[digit];
            }
            array = answer.clone();
            Thread.sleep(speed);
            for (int j = 0; j < array.length; ++j) {
                ((RadixSort) activity).setText(new int[]{j}, new String[]{String.valueOf(array[j])});
            }
        }
        for (int i = 0; i < array.length; ++i) {
            ((RadixSort) activity).setText(new int[]{i}, new String[]{String.valueOf(array[i])});
        }
    }
}
