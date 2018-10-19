package com.olympiad_algorithms.visualgol_android;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
//import android.widget.Toast;

public class Algorithms extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    String setter;
    int sizer = 0;

    private final static String FILE_NAME = "qwerty.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algorithms);

        // get the listView
        expListView = findViewById(R.id.expListView);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild, setter);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // ListView child click listener
        expListView.setOnChildClickListener(new OnChildClickListener() {
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                if (groupPosition == 0)
                {
                    Intent intent;
                    switch (childPosition) {
                        case 0:
                            intent = new Intent(Algorithms.this, StupidSort.class);
                            break;
                        case 1:
                            intent = new Intent(Algorithms.this, BubbleSort.class);
                            break;
                        /*case 2:
                            intent = new Intent(Algorithms.this, SelectionSort.class);
                            break;
                        case 3:
                            intent = new Intent(Algorithms.this, MergeSort.class);
                            break;
                        case 4:
                            intent = new Intent(Algorithms.this, QuickSort.class);
                            break;*/
                        default:
                            intent = new Intent(Algorithms.this, StupidSort.class);
                            break;
                    }
                    startActivity(intent);
                }
                else if (groupPosition == 4)
                {
                    Intent intent;
                    switch (childPosition) {
                        case 0:
                            intent = new Intent(Algorithms.this, Prefix_fun.class);
                            break;
                        case 1:
                            intent = new Intent(Algorithms.this, Z_fun.class);
                            break;
                        default:
                            intent = new Intent(Algorithms.this, Prefix_fun.class);
                            break;
                    }
                    startActivity(intent);
                }
                return false;
            }
        });
    }

    /*
    ===========================================================================
        Preparing the list data
    ===========================================================================
    */
    private void prepareListData() {
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();

        listDataHeader.add(getString(R.string.sorting));
        listDataHeader.add(getString(R.string.search));
        listDataHeader.add(getString(R.string.recursion));
        listDataHeader.add(getString(R.string.graphs));
        listDataHeader.add(getString(R.string.strings));

        // Adding child data
        List<String> SortArray = new ArrayList<>();
        SortArray.add(getString(R.string.what));
        SortArray.add(getString(R.string.bubble_sort));
        SortArray.add(getString(R.string.selection_sort));
        SortArray.add(getString(R.string.merge_sort));
        SortArray.add(getString(R.string.quick_sort));

        List<String> SearchArray = new ArrayList<>();
        SearchArray.add(getString(R.string.lin_search));
        SearchArray.add(getString(R.string.bin_search));
        SearchArray.add(getString(R.string.ter_search));

        List<String> RecursionArray = new ArrayList<>();
        RecursionArray.add(getString(R.string.what));

        List<String> GraphsArray = new ArrayList<>();
        GraphsArray.add(getString(R.string.dfs));
        GraphsArray.add(getString(R.string.bfs));

        List<String> StringArray = new ArrayList<>();
        StringArray.add(getString(R.string.prefix));
        StringArray.add(getString(R.string.z_fun));

        listDataChild.put(listDataHeader.get(0), SortArray);
        listDataChild.put(listDataHeader.get(1), SearchArray);
        listDataChild.put(listDataHeader.get(2), RecursionArray);
        listDataChild.put(listDataHeader.get(3), GraphsArray);
        listDataChild.put(listDataHeader.get(4), StringArray);

        sizer = SortArray.size()+SearchArray.size()+RecursionArray.size()+GraphsArray.size()+StringArray.size();

        setter = loadText();

        //Toast.makeText(this, setter, Toast.LENGTH_SHORT).show();
    }

    static String convertStreamToString(FileInputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    public String loadText() {
        try {
            FileInputStream fin = openFileInput(FILE_NAME);
            String str =  convertStreamToString(fin);
            fin.close();
            return str;
        } catch (IOException ex) {
            //Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            StringBuilder curBuilder = new StringBuilder();
            for (int i = 0; i < sizer; ++i)
                curBuilder.append('0');
            saveText(curBuilder.toString());
            return curBuilder.toString();
        }
    }
    public void saveText(String s) {
        try {
            FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(s.getBytes());
            fos.close();
        } catch(IOException ex) {
            //Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}