package com.gmail.nkigumnov.visualgol;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class SegmentTree extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    TextView St;
    ImageView imageView;
    Button st;
    Button st2;
    SeekBar SbDelay;
    TextView TvDelay;
    private int curSpeed = 1250;
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segment_tree);

        //Toast.makeText(this, ""+cur, Toast.LENGTH_SHORT).show();

        imageView = findViewById(R.id.imageView);

        TvDelay = findViewById(R.id.TvDelay);
        TvDelay.setText(R.string.sec);

        SbDelay = findViewById(R.id.SbDelay);
        SbDelay.setOnSeekBarChangeListener(this);

        St = findViewById(R.id.St);

        st = findViewById(R.id.st);
        st.setOnClickListener(this);

        st2 = findViewById(R.id.st2);
        st2.setOnClickListener(this);

        ContestSet();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.st:
                handler.removeCallbacksAndMessages(null);
                ContestSet();
                seg_tree();
                break;
            case R.id.st2:
                handler.removeCallbacksAndMessages(null);
                ContestSet();
                seg_tree_sum();
                break;
            default:
                break;
        }
    }

    public void ContestSet() {
        //DFS == 0, BFS == 1
        imageView.setBackgroundResource(R.drawable.st1);
    }

    int[] st_ = {R.drawable.st2, R.drawable.st3, R.drawable.st4, R.drawable.st5};
    int[] st_sum = {R.drawable.stsum1, R.drawable.stsum2, R.drawable.stsum3, R.drawable.stsum4, R.drawable.stsum5};

    public void seg_tree() {
        animation_st();
    }

    public void animation_st() {
        for (int i = 0; i < st_.length; ++i) {
            final int j = i;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    imageView.setBackgroundResource(st_[j]);
                }
            }, curSpeed * (i + 1));
        }
    }

    public void seg_tree_sum() {
        animation_st_sum();
    }

    public void animation_st_sum() {
        for (int i = 0; i < st_sum.length; ++i) {
            final int j = i;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    imageView.setBackgroundResource(st_sum[j]);
                }
            }, curSpeed * i);
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
