package com.gmail.nkigumnov.visualgol.algorithms.sorting;

import android.app.Activity;
import android.util.Pair;

import com.gmail.nkigumnov.visualgol.R;
import com.gmail.nkigumnov.visualgol.activities.SelectionSort;

import java.util.TimerTask;

public class Selection extends TimerTask {
    private final Activity activity;
    private final int[] mainArray;
    public int timerCounter;

    public Selection(Activity activity, int[] array, int timerCounter) {
        this.activity = activity;
        mainArray = array.clone();
        this.timerCounter = timerCounter;
    }

    @Override
    public void run() {
        Pair<int[], int[]> p = sort();
        ((SelectionSort) activity).setColor(p.first);
        ((SelectionSort) activity).setText(p.second);
        ++timerCounter;
    }

    private Pair<int[], int[]> sort() {
        int[] colors = new int[mainArray.length];
        int[] array = mainArray.clone();
        int currentTime = -1;
        for (int i = 0; i < array.length; ++i) {
            colors[i] = R.drawable.rectangle_gray;
        }
        if (currentTime++ == timerCounter) {
            return new Pair<>(colors, array);
        }
        for (int i = 0; i < array.length; ++i) {
            colors[i] = R.drawable.rectangle_red;
            if (currentTime++ == timerCounter) {
                return new Pair<>(colors, array);
            }
            int pos = i;
            for (int j = i + 1; j < array.length; ++j) {
                colors[j] = R.drawable.rectangle_orange;
                if (currentTime++ == timerCounter) {
                    return new Pair<>(colors, array);
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
                if (currentTime++ == timerCounter) {
                    return new Pair<>(colors, array);
                }
            }

            int temp = array[i];
            array[i] = array[pos];
            array[pos] = temp;

            colors[pos] = R.drawable.rectangle_gray;
            colors[i] = R.drawable.rectangle_dark;

            if (currentTime++ == timerCounter) {
                return new Pair<>(colors, array);
            }
        }
        --timerCounter;
        return new Pair<>(colors, array);
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
