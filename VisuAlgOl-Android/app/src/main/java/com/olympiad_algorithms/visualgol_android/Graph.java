package com.olympiad_algorithms.visualgol_android;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Graph extends AppCompatActivity implements View.OnClickListener {

    TextView title;
    ImageView imageView;
    Button graphs;
    Button btnSave;
    EditText edit_text;
    int cur = 0;
    private long num_of_clicks = 0;
    private Handler handler = new Handler();

    private final static String FILE_NAME = "qwerty.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        Bundle arguments = getIntent().getExtras();
        if (arguments != null)
            cur = arguments.getInt("num", 1);

        //Toast.makeText(this, ""+cur, Toast.LENGTH_SHORT).show();

        num_of_clicks = 0;

        title = findViewById(R.id.title);

        imageView = findViewById(R.id.imageView);

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
                ++num_of_clicks;
                ContestSet();
                if (cur == 0)
                    handler.postDelayed(new Runnable() { public void run() { dfs(num_of_clicks); } }, 600);
                else
                    handler.postDelayed(new Runnable() { public void run() { bfs(num_of_clicks); } }, 600);
                break;
            case R.id.btnSave:
                if (edit_text.getText().toString().equals("....."))
                    saveText('1');
                else saveText('0');
            default:
                break;
        }
    }

    public void ContestSet() {
        //DFS == 0, BFS == 1
        if (cur == 0) {
            imageView.setBackgroundResource(R.drawable.d1);
            graphs.setText(R.string.dfs);
            title.setText(R.string.dfs);
        }
        else {
            imageView.setBackgroundResource(R.drawable.b1);
            graphs.setText(R.string.bfs);
            title.setText(R.string.bfs);
        }
    }

    int[] d = {R.drawable.d2, R.drawable.d3, R.drawable.d4, R.drawable.d5, R.drawable.d6,
               R.drawable.d7, R.drawable.d8, R.drawable.d9, R.drawable.d10, R.drawable.d11, R.drawable.d12};
    int[] b = {R.drawable.b2, R.drawable.b3, R.drawable.b4, R.drawable.b5, R.drawable.b6,
            R.drawable.b7, R.drawable.b8, R.drawable.b9, R.drawable.b10, R.drawable.b11, R.drawable.b12};

    public void dfs(long cur) {
        animation_dfs(cur);
    }

    public void animation_dfs(final long cur) {
        long current = 0;
        for (int i = 0; i < d.length; ++i)
        {
            if (cur != num_of_clicks) return;
            ++current;
            final int j = i;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (cur != num_of_clicks) return;
                    imageView.setBackgroundResource(d[j]);
                }
            }, 1250*current);
        }
    }

    public void bfs(long cur) {
        animation_bfs(cur);
    }

    public void animation_bfs(final long cur) {
        long current = 0;
        for (int i = 0; i < b.length; ++i)
        {
            if (cur != num_of_clicks) return;
            ++current;
            final int j = i;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (cur != num_of_clicks) return;
                    imageView.setBackgroundResource(b[j]);
                }
            }, 1250*current);
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
        c[8+cur] = ch;
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
}
