package com.auto.sequences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button eucBtn, colBtn, fibBtn, lucBtn, triBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide the status bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        //Set buttons
        eucBtn = findViewById(R.id.euc_btn);
        colBtn = findViewById(R.id.col_btn);
        fibBtn = findViewById(R.id.fib_btn);
        lucBtn = findViewById(R.id.luc_btn);
        triBtn = findViewById(R.id.tri_btn);

        fibBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SequenceActivity.class);
                intent.putExtra("sequenceName", getString(R.string.fib_name));
                intent.putExtra("sequenceDescription", getString(R.string.fib_desc));
                intent.putExtra("sequenceFind", getString(R.string.fib_find));
                intent.putExtra("sequenceForm", getString(R.string.fib_form));
                startActivity(intent);
            }
        });

        eucBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SequenceActivity.class);
                intent.putExtra("sequenceName", getString(R.string.euc_name));
                intent.putExtra("sequenceDescription", getString(R.string.euc_desc));
                intent.putExtra("sequenceFind", getString(R.string.euc_find));
                intent.putExtra("sequenceForm", getString(R.string.euc_form));
                intent.putExtra("input2Visibility", View.VISIBLE);
                startActivity(intent);
            }
        });

        colBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SequenceActivity.class);
                intent.putExtra("sequenceName", getString(R.string.col_name));
                intent.putExtra("sequenceDescription", getString(R.string.col_desc));
                intent.putExtra("sequenceFind", getString(R.string.col_find));
                intent.putExtra("sequenceForm", getString(R.string.col_form));
                startActivity(intent);
            }
        });

        lucBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SequenceActivity.class);
                intent.putExtra("sequenceName", getString(R.string.luc_name));
                intent.putExtra("sequenceDescription", getString(R.string.luc_desc));
                intent.putExtra("sequenceFind", getString(R.string.luc_find));
                intent.putExtra("sequenceForm", getString(R.string.luc_form));
                startActivity(intent);
            }
        });

        triBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SequenceActivity.class);
                intent.putExtra("sequenceName", getString(R.string.trib_name));
                intent.putExtra("sequenceDescription", getString(R.string.trib_desc));
                intent.putExtra("sequenceFind", getString(R.string.trib_find));
                intent.putExtra("sequenceForm", getString(R.string.trib_form));
                startActivity(intent);
            }
        });


    }
}