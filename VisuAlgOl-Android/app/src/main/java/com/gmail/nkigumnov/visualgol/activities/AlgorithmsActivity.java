package com.gmail.nkigumnov.visualgol.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.widget.ExpandableListView;

import com.gmail.nkigumnov.visualgol.R;

import com.gmail.nkigumnov.visualgol.BST;
import com.gmail.nkigumnov.visualgol.Graph;
import com.gmail.nkigumnov.visualgol.Search;
import com.gmail.nkigumnov.visualgol.SegmentTree;
import com.gmail.nkigumnov.visualgol.Strings;

import com.gmail.nkigumnov.visualgol.util.Util;
import com.gmail.nkigumnov.visualgol.util.ExpandableListAdapter;

public class AlgorithmsActivity extends AppCompatActivity {
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;
    private String setter;
    private final Class<?>[][] intents = {
            {StupidSort.class, BubbleSort.class, SelectionSort.class, InsertionSort.class,
                    MergeSort.class, QuickSort.class, CountingSort.class, RadixSort.class},
            {Search.class, Search.class},
            {Graph.class, Graph.class},
            {Strings.class, Strings.class},
            {BST.class, SegmentTree.class}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        downloadContent();
    }

    @Override
    protected void onRestart() {
        super.onRestart();  // Always call the superclass method first
        downloadContent();
    }

    public void downloadContent() {
        setContentView(R.layout.activity_algorithms);

        // get the listView
        ExpandableListView expListView = findViewById(R.id.expListView);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild, setter);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // ListView child click listener
        expListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            Intent intent = new Intent(this, intents[groupPosition][childPosition]);
            intent.putExtra("num", childPosition);
            intent.putExtra("num_2", listAdapter.getPozAll(groupPosition));
            startActivity(intent);
            return false;
        });
    }

    /**
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();

        listDataHeader.add(getString(R.string.sorting));
        listDataHeader.add(getString(R.string.search));
        listDataHeader.add(getString(R.string.graphs));
        listDataHeader.add(getString(R.string.strings));
        listDataHeader.add(getString(R.string.data_structures));

        // Adding child data
        List<String> SortArray = new ArrayList<>();
        SortArray.add(getString(R.string.what));
        SortArray.add(getString(R.string.bubble_sort));
        SortArray.add(getString(R.string.selection_sort));
        SortArray.add(getString(R.string.insertion_sort));
        SortArray.add(getString(R.string.merge_sort));
        SortArray.add(getString(R.string.quick_sort));
        SortArray.add(getString(R.string.counting_sort));
        SortArray.add(getString(R.string.radix_sort));

        List<String> SearchArray = new ArrayList<>();
        SearchArray.add(getString(R.string.lin_search));
        SearchArray.add(getString(R.string.bin_search));

        List<String> GraphsArray = new ArrayList<>();
        GraphsArray.add(getString(R.string.dfs));
        GraphsArray.add(getString(R.string.bfs));

        List<String> StringArray = new ArrayList<>();
        StringArray.add(getString(R.string.prefix));
        StringArray.add(getString(R.string.z));

        List<String> DSArray = new ArrayList<>();
        DSArray.add(getString(R.string.st));
        DSArray.add(getString(R.string.bst));

        listDataChild.put(listDataHeader.get(0), SortArray);
        listDataChild.put(listDataHeader.get(1), SearchArray);
        listDataChild.put(listDataHeader.get(2), GraphsArray);
        listDataChild.put(listDataHeader.get(3), StringArray);
        listDataChild.put(listDataHeader.get(4), DSArray);

        setter = Util.loadText(this);

        //Toast.makeText(this, setter, Toast.LENGTH_SHORT).show();
    }
}
