package com.example.bookapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Testyyy extends AppCompatActivity {

    String selectedTopic = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testyyy);

        final TextView txtest1 = findViewById(R.id.txt1);
        final TextView txtest2 = findViewById(R.id.txt2);
        final TextView txtest3 = findViewById(R.id.txt3);
        final TextView txtest4 = findViewById(R.id.txt4);

        txtest1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTopic = "test1";
                Intent intent = new Intent(Testyyy.this, StartTesty.class);
                intent.putExtra("selectedTopic", selectedTopic);
                startActivity(intent);
            }
        });


        txtest2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTopic = "test2";
                Intent intent = new Intent(Testyyy.this, StartTesty.class);
                intent.putExtra("selectedTopic", selectedTopic);
                startActivity(intent);
            }
        });


        txtest3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTopic = "test3";
                Intent intent = new Intent(Testyyy.this, StartTesty.class);
                intent.putExtra("selectedTopic", selectedTopic);
                startActivity(intent);
            }
        });



        txtest4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTopic = "test4";
                Intent intent = new Intent(Testyyy.this, StartTesty.class);
                intent.putExtra("selectedTopic", selectedTopic);
                startActivity(intent);
            }
        });






    }
}