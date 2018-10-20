package com.olympiad_algorithms.visualgol_android;

import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class Graph extends AppCompatActivity {

    int cur = 0;
    ImageView imageView;
    AnimationDrawable mAnimationDrawable;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        Bundle arguments = getIntent().getExtras();
        if (arguments != null)
            cur = arguments.getInt("num", 1);

        imageView = findViewById(R.id.imageView);

        Toast.makeText(this, ""+cur, Toast.LENGTH_SHORT).show();

        ContestSet();
    }

    public void ContestSet() {
        //DFS == 0, BFS == 1
        if (cur == 0)
            imageView.setBackgroundResource(R.drawable.dfs);
        else
            imageView.setBackgroundResource(R.drawable.bfs);

        mAnimationDrawable = (AnimationDrawable) imageView.getBackground();
        mAnimationDrawable.start();

        handler.postDelayed(new Runnable() {
            public void run() {
                mAnimationDrawable.stop();
                ContestSet();
            }
        }, 12000);
    }

}
