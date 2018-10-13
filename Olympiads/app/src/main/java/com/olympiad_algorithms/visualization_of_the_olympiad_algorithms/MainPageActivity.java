package com.olympiad_algorithms.visualization_of_the_olympiad_algorithms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainPageActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnActDynPr;
    Button btnActRek;
    Button btnActSort;
    Button btnActGeom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        btnActDynPr = (Button) findViewById(R.id.btnActDynPr);
        btnActDynPr.setOnClickListener(this);

        btnActRek = (Button) findViewById(R.id.btnActRek);
        btnActRek.setOnClickListener(this);

        btnActSort = (Button) findViewById(R.id.btnActSort);
        btnActSort.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnActDynPr:
                Intent intent = new Intent(this, ActivityDynamicPr.class);
                startActivity(intent);
                break;

            case R.id.btnActRek:
                //    Intent intent = new Intent(this, .class);
                //    startActivity(intent);
                break;

            case R.id.btnActSort:
                //    Intent intent = new Intent(this, .class);
                //    startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}