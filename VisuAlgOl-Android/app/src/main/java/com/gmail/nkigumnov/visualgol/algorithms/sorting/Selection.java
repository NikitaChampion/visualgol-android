package com.gmail.nkigumnov.visualgol.algorithms.sorting;

import android.app.Activity;

import com.gmail.nkigumnov.visualgol.R;
import com.gmail.nkigumnov.visualgol.activities.SelectionSort;

import java.util.TimerTask;

public class Selection extends TimerTask {
    private final Activity activity;
    private final int[] mainArray;
    private int[] array;
    private int[] colors;
    private int time;
    public int timerCounter;

    public Selection(Activity activity, int[] array, int timerCounter) {
        this.activity = activity;
        mainArray = array.clone();
        this.timerCounter = timerCounter;
    }

    @Override
    public void run() {
        time = -1;
        array = mainArray.clone();
        colors = new int[array.length];
        for (int i = 0; i < array.length; ++i) {
            colors[i] = R.drawable.rectangle_gray;
        }

        sort();

        ((SelectionSort) activity).setColor(colors);
        ((SelectionSort) activity).setText(array);
        ++timerCounter;
    }

    private void sort() {
        if (time++ == timerCounter) {
            return;
        }
        for (int i = 0; i < array.length; ++i) {
            colors[i] = R.drawable.rectangle_red;
            if (time++ == timerCounter) {
                return;
            }
            int pos = i;
            for (int j = i + 1; j < array.length; ++j) {
                colors[j] = R.drawable.rectangle_orange;
                if (time++ == timerCounter) {
                    return;
                }

                if (array[pos] > array[j]) {
                    if (pos == i) {
                        colors[pos] = R.drawable.rectangle_purple;
                    } else {
                        colors[pos] = R.drawable.rectangle_gray;
                    }
                    pos = j;

                    colors[j] = R.drawable.rectangle_red;
                } else {
                    colors[j] = R.drawable.rectangle_gray;
                }
                if (time++ == timerCounter) {
                    return;
                }
            }

            int temp = array[i];
            array[i] = array[pos];
            array[pos] = temp;

            colors[pos] = R.drawable.rectangle_gray;
            colors[i] = R.drawable.rectangle_dark;

            if (time++ == timerCounter) {
                return;
            }
        }
        --timerCounter;
    }
}
// slider с 0,... (на 0 не успевает) ([0,01, 3]) (++)
// TODO: Thread.sleep(time) -> sleep() + обработать interrupt между sleep (++)
// TODO: interrupt потока вынести в отдельный метод (++)
// TODO: переход на таймер вместо отдельного потока (+++)
// TODO: Binary insertion (++)
// TODO: Heap sort (++++)
// Merge (++)
// Quick (++)
// TODO: Search (++)
// TODO: Другие алгоритмы (++++)
// TODO: поменять layout-ы (++)
// TODO: Добавить нормальное описание всех алгоритмов (+++++)
// TODO: показать в Counting, что сортировка устойчивая
// TODO: дизайн
