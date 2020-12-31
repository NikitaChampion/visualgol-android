package com.gmail.nkigumnov.visualgol;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class SegmentTree extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    private ImageView imageView;
    private TextView TvDelay;
    private int curSpeed = Constants.SPEED;
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segment_tree);

        //Toast.makeText(this, ""+cur, Toast.LENGTH_SHORT).show();

        imageView = findViewById(R.id.imageView);

        TvDelay = findViewById(R.id.TvDelay);
        TvDelay.setText(R.string.sec);

        ((SeekBar) findViewById(R.id.SbDelay)).setOnSeekBarChangeListener(this);
        findViewById(R.id.st).setOnClickListener(this);
        findViewById(R.id.st2).setOnClickListener(this);

        contestSet();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.st) {
            handler.removeCallbacksAndMessages(null);
            contestSet();
            segmentTree();
        } else if (id == R.id.st2) {
            handler.removeCallbacksAndMessages(null);
            contestSet();
            segmentTreeSum();
        }
    }

    public void contestSet() {
        //DFS == 0, BFS == 1
        imageView.setBackgroundResource(R.drawable.st1);
    }

    int[] st_ = {R.drawable.st2, R.drawable.st3, R.drawable.st4, R.drawable.st5};
    int[] st_sum = {R.drawable.stsum1, R.drawable.stsum2, R.drawable.stsum3, R.drawable.stsum4, R.drawable.stsum5};

    public void segmentTree() {
        animationST();
    }

    public void animationST() {
        for (int i = 0; i < st_.length; ++i) {
            final int j = i;
            handler.postDelayed(() -> imageView.setBackgroundResource(st_[j]), curSpeed * (i + 1));
        }
    }

    public void segmentTreeSum() {
        animationStSum();
    }

    public void animationStSum() {
        for (int i = 0; i < st_sum.length; ++i) {
            final int j = i;
            handler.postDelayed(() -> imageView.setBackgroundResource(st_sum[j]), curSpeed * i);
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