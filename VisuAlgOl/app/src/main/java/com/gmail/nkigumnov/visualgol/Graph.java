package com.gmail.nkigumnov.visualgol;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.gmail.nkigumnov.visualgol.util.Constants;
import com.gmail.nkigumnov.visualgol.util.Util;

public class Graph extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    private TextView title;
    private TextView Graph;
    private TextView task;
    private ImageView imageView;
    private Button graphs;
    private EditText editText;
    private TextView TvDelay;
    private int childPosition = 0, groupPosition = 0;
    private int curSpeed = Constants.SPEED;
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            childPosition = arguments.getInt("num", 0);
            groupPosition = arguments.getInt("num_2", 0);
        }

        //Toast.makeText(this, ""+cur, Toast.LENGTH_SHORT).show();

        title = findViewById(R.id.title);

        imageView = findViewById(R.id.imageView);

        TvDelay = findViewById(R.id.TvDelay);
        TvDelay.setText(R.string.sec);

        ((SeekBar) findViewById(R.id.SbDelay)).setOnSeekBarChangeListener(this);
        findViewById(R.id.btnSave).setOnClickListener(this);

        Graph = findViewById(R.id.Graph);

        task = findViewById(R.id.task);

        graphs = findViewById(R.id.graphs);
        graphs.setOnClickListener(this);

        editText = findViewById(R.id.edit_text);

        contestSet();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.graphs) {
            handler.removeCallbacksAndMessages(null);
            contestSet();
            if (childPosition == 0) {
                dfs();
            } else {
                bfs();
            }
        } else if (id == R.id.btnSave) {
            if (childPosition == 0 && editText.getText().toString().equals("4")) {
                Util.saveText(this, '1', groupPosition + childPosition);
            } else if (childPosition == 1 && editText.getText().toString().equals("9")) {
                Util.saveText(this, '1', groupPosition + childPosition);
            } else {
                Util.saveText(this, '0', groupPosition + childPosition);
            }
        }
    }

    public void contestSet() {
        //DFS == 0, BFS == 1
        if (childPosition == 0) {
            imageView.setBackgroundResource(R.drawable.d1);
            graphs.setText(R.string.dfs);
            Graph.setText(R.string.dfs_);
            title.setText(R.string.dfs);
            task.setText(R.string.task10);
        } else {
            imageView.setBackgroundResource(R.drawable.b1);
            graphs.setText(R.string.bfs);
            Graph.setText(R.string.bfs_);
            title.setText(R.string.bfs);
            task.setText(R.string.task11);
        }
    }

    int[] d = {R.drawable.d2, R.drawable.d3, R.drawable.d4, R.drawable.d5, R.drawable.d6,
            R.drawable.d7, R.drawable.d8, R.drawable.d9, R.drawable.d10, R.drawable.d11, R.drawable.d12};
    int[] b = {R.drawable.b2, R.drawable.b3, R.drawable.b4, R.drawable.b5, R.drawable.b6,
            R.drawable.b7, R.drawable.b8, R.drawable.b9, R.drawable.b10, R.drawable.b11, R.drawable.b12};

    public void dfs() {
        animation_dfs();
    }

    public void animation_dfs() {
        for (int i = 0; i < d.length; ++i) {
            final int j = i;
            handler.postDelayed(() -> imageView.setBackgroundResource(d[j]), curSpeed * (i + 1));
        }
    }

    public void bfs() {
        animation_bfs();
    }

    public void animation_bfs() {
        for (int i = 0; i < b.length; ++i) {
            final int j = i;
            handler.postDelayed(() -> imageView.setBackgroundResource(b[j]), curSpeed * (i + 1));
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        curSpeed = progress;
        String s = progress / 1000. + " sec";
        TvDelay.setText(s);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}