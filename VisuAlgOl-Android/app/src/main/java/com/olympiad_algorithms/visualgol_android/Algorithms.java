package com.olympiad_algorithms.visualgol_android;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Algorithms extends AppCompatActivity {

    String[] mGroupsArray;
    String[] SortArray;
    String[] SearchArray;
    String[] RecursionArray;
    String[] GraphsArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algorithms);

        String locale = java.util.Locale.getDefault().getDisplayName(); // получение текущего языка системы

        if (locale.charAt(0) == 'р' && locale.charAt(1) == 'у')
        {
            mGroupsArray = new String[] { "Сортировки", "Поиск", "Рекурсия", "Графы" };
            SortArray = new String[] { "Что это и для чего нужно?", "Сортировка пузырьком", "Сортировка Шелла", "Сортировка слиянием", "Пирамидальная сортировка", "Быстрая сортировка" };
            SearchArray = new String[] { "Линейный поиск", "Бинарный поиск", "Тернарный поиск" };
            RecursionArray = new String[] { "Что это и для чего нужно?" };
            GraphsArray = new String[] { "Поиск в глубину", "Поиск в ширину" };
        }
        else
        {
            mGroupsArray = new String[] { "Sorting algorithms", "Search algorithms", "Recursion", "Graphs" };
            SortArray = new String[] { "What is it and what is it for?", "Bubble", "Shell sort", "Merge sort", "Heap sort", "Quick sort" };
            SearchArray = new String[] { "Linear search", "Binary search", "Ternary search" };
            RecursionArray = new String[] { "What is it and what is it for?" };
            GraphsArray = new String[] { "DFS", "BFS" };
        }

        Map<String, String> map; // коллекция для групп
        ArrayList<Map<String, String>> groupDataList = new ArrayList<>(); // заполняем коллекцию групп из массива с названиями групп

        for (String group : mGroupsArray) { // заполняем список атрибутов для каждой группы
            map = new HashMap<>();
            map.put("groupName", group); // Темы
            groupDataList.add(map);
        }

        String groupFrom[] = new String[]{"groupName"}; // список атрибутов групп для чтения
        int groupTo[] = new int[]{android.R.id.text1}; // список ID view-элементов, в которые будет помещены атрибуты групп

        ArrayList<ArrayList<Map<String, String> > > childDataList = new ArrayList<>(); // создаём общую коллекцию для коллекций элементов

        ArrayList<Map<String, String> > childDataItemList = new ArrayList<>(); // создаём коллекцию элементов для первой группы

        for (String algorithm : SortArray) {
            map = new HashMap<>();
            map.put("algorithmName", algorithm); // "Конкретные" алгоритмы
            childDataItemList.add(map);
        }

        childDataList.add(childDataItemList); // добавляем в коллекцию коллекций

        childDataItemList = new ArrayList<>(); // создаём коллекцию элементов для второй группы
        for (String algorithm : SearchArray) {
            map = new HashMap<>();
            map.put("algorithmName", algorithm);
            childDataItemList.add(map);
        }
        childDataList.add(childDataItemList);

        childDataItemList = new ArrayList<>(); // создаём коллекцию элементов для третьей группы
        for (String algorithm : RecursionArray) {
            map = new HashMap<>();
            map.put("algorithmName", algorithm);
            childDataItemList.add(map);
        }
        childDataList.add(childDataItemList);

        childDataItemList = new ArrayList<>(); // создаём коллекцию элементов для четвёртой группы
        for (String algorithm : GraphsArray) {
            map = new HashMap<>();
            map.put("algorithmName", algorithm);
            childDataItemList.add(map);
        }
        childDataList.add(childDataItemList);

        String childFrom[] = new String[]{"algorithmName"}; // список атрибутов элементов для чтения

        int childTo[] = new int[]{android.R.id.text1}; // список ID view-элементов, в которые будет помещены атрибуты элементов

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                this, groupDataList,
                android.R.layout.simple_expandable_list_item_1, groupFrom,
                groupTo, childDataList, android.R.layout.simple_list_item_1,
                childFrom, childTo);

        ExpandableListView expandableListView = findViewById(R.id.expListView);
        expandableListView.setAdapter(adapter);


        // нажатие на элемент
        expandableListView.setOnChildClickListener(new OnChildClickListener() {
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                if (groupPosition == 0)
                {
                    if (childPosition == 0) {
                        Intent intent = new Intent(Algorithms.this, StupidSort.class);
                        startActivity(intent);
                    }
                    else if (childPosition == 1) {
                        Intent intent = new Intent(Algorithms.this, BubbleSort.class);
                        startActivity(intent);
                    }
                    else if (childPosition == 2) {
                        Intent intent = new Intent(Algorithms.this, ShellSort.class);
                        startActivity(intent);
                    }
                    else if (childPosition == 3) {
                        Intent intent = new Intent(Algorithms.this, MergeSort.class);
                        startActivity(intent);
                    }
                    else if (childPosition == 4) {
                        Intent intent = new Intent(Algorithms.this, HeapSort.class);
                        startActivity(intent);
                    }
                    else if (childPosition == 5) {
                        Intent intent = new Intent(Algorithms.this, QuickSort.class);
                        startActivity(intent);
                    }
                }
                return false;
            }
        });
    }
}
