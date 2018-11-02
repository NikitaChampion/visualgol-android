package com.olympiad_algorithms.visualgol_android;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Graph extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    TextView title;
    TextView Graph;
    TextView task;
    ImageView imageView;
    Button graphs;
    Button btnSave;
    EditText edit_text;
    SeekBar SbDelay;
    TextView TvDelay;
    private int childPosition = 0, groupPosition = 0;
    private int curSpeed = 1250;
    private Handler handler = new Handler();

    private final static String FILE_NAME = "qwerty.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            childPosition = arguments.getInt("num", 1);
            groupPosition = arguments.getInt("num_2", 0);
        }

        //Toast.makeText(this, ""+cur, Toast.LENGTH_SHORT).show();

        title = findViewById(R.id.title);

        imageView = findViewById(R.id.imageView);

        TvDelay = findViewById(R.id.TvDelay);
        TvDelay.setText(R.string.sec);

        SbDelay = findViewById(R.id.SbDelay);
        SbDelay.setOnSeekBarChangeListener(this);

        Graph = findViewById(R.id.Graph);

        task = findViewById(R.id.task);

        graphs = findViewById(R.id.graphs);
        graphs.setOnClickListener(this);

        edit_text = findViewById(R.id.edit_text);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

        ContestSet();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.graphs:
                handler.removeCallbacksAndMessages(null);
                ContestSet();
                if (childPosition == 0)
                    dfs();
                else
                    bfs();
                break;
            case R.id.btnSave:
                if (childPosition == 0 && edit_text.getText().toString().equals("4"))
                    saveText('1');
                else if (childPosition == 1 && edit_text.getText().toString().equals("9"))
                    saveText('1');
                else
                    saveText('0');
                break;
            default:
                break;
        }
    }

    public void ContestSet() {
        //DFS == 0, BFS == 1
        if (childPosition == 0) {
            imageView.setBackgroundResource(R.drawable.d1);
            graphs.setText(R.string.dfs);
            Graph.setText(R.string.dfs_);
            title.setText(R.string.dfs);
            task.setText(R.string.task10);
        }
        else {
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
        for (int i = 0; i < d.length; ++i)
        {
            final int j = i;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    imageView.setBackgroundResource(d[j]);
                }
            }, curSpeed*(i+1));
        }
    }

    public void bfs() {
        animation_bfs();
    }

    public void animation_bfs() {
        for (int i = 0; i < b.length; ++i)
        {
            final int j = i;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    imageView.setBackgroundResource(b[j]);
                }
            }, curSpeed*(i+1));
        }
    }

    static String convertStreamToString(FileInputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    public String loadText() {
        try {
            FileInputStream fin = openFileInput(FILE_NAME);
            String str = convertStreamToString(fin);
            fin.close();
            return str;
        } catch(IOException ex) {
            //Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            StringBuilder curBuilder = new StringBuilder();
            for (int i = 0; i < 100; ++i)
                curBuilder.append('0');
            return curBuilder.toString();
        }
    }

    public void saveText(char ch) {
        char[] c = loadText().toCharArray();
        c[groupPosition+childPosition] = ch;
        String str = new String(c);
        try {
            FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(str.getBytes());
            fos.close();
        } catch(IOException ex) {
            //Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        if (ch == '1') Toast.makeText(this, "Right answer, text saved", Toast.LENGTH_SHORT).show();
        else Toast.makeText(this, "Wrong answer, try again", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        curSpeed = progress;
        String s = String.valueOf(progress/1000.)+" sec";
        TvDelay.setText(s);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
