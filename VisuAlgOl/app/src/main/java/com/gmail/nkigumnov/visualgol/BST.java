package com.gmail.nkigumnov.visualgol;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class BST extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    TextView Bst;
    ImageView imageView;
    Button bst;
    Button bst2;
    SeekBar SbDelay;
    TextView TvDelay;
    private int curSpeed = 1250;
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bst);

        //Toast.makeText(this, ""+cur, Toast.LENGTH_SHORT).show();

        imageView = findViewById(R.id.imageView);

        TvDelay = findViewById(R.id.TvDelay);
        TvDelay.setText(R.string.sec);

        SbDelay = findViewById(R.id.SbDelay);
        SbDelay.setOnSeekBarChangeListener(this);

        Bst = findViewById(R.id.Bst);

        bst = findViewById(R.id.bst);
        bst.setOnClickListener(this);

        bst2 = findViewById(R.id.bst2);
        bst2.setOnClickListener(this);

        ContestSet();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bst:
                handler.removeCallbacksAndMessages(null);
                ContestSet();
                bst_ins();
                break;
            case R.id.bst2:
                handler.removeCallbacksAndMessages(null);
                ContestSet();
                bst_del();
                break;
            default:
                break;
        }
    }

    public void ContestSet() {
        //DFS == 0, BFS == 1
        imageView.setBackgroundResource(R.drawable.bst);
    }

    int[] bst_ins = {R.drawable.bstins1, R.drawable.bstins2, R.drawable.bstins3, R.drawable.bstins4, R.drawable.bstins5};
    int[] bst_del = {R.drawable.bstdel1, R.drawable.bstdel2, R.drawable.bstdel3, R.drawable.bstdel4, R.drawable.bstdel5};

    public void bst_ins() {
        animation_bst_ins();
    }

    public void animation_bst_ins() {
        for (int i = 0; i < bst_ins.length; ++i) {
            final int j = i;
            handler.postDelayed(() -> imageView.setBackgroundResource(bst_ins[j]), curSpeed * (i + 1));
        }
    }

    public void bst_del() {
        animation_bst_del();
    }

    public void animation_bst_del() {
        for (int i = 0; i < bst_del.length; ++i) {
            final int j = i;
            handler.postDelayed(() -> imageView.setBackgroundResource(bst_del[j]), curSpeed * i);
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
