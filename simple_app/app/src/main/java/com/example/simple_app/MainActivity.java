package com.example.simple_app;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    int counter = 0;
    TextView textViewCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button exitbutton = findViewById(R.id.button1);
        exitbutton.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Potwierdzenie");
            builder.setMessage("Czy na pewno chcesz zamknąć aplikację?");
            builder.setPositiveButton("Tak", (dialog, which) -> finish());
            builder.setNegativeButton("Nie", (dialog, which) -> dialog.dismiss());
            AlertDialog dialog = builder.create();
            dialog.show();
        });

        textViewCounter = findViewById(R.id.licznik);
        Button buttonIncrement = findViewById(R.id.button2);

        if (savedInstanceState != null) {
            counter = savedInstanceState.getInt("COUNTER_KEY", 0);
            textViewCounter.setText(String.valueOf(counter));
        }

        buttonIncrement.setOnClickListener(v -> {
            counter++;
            textViewCounter.setText(String.valueOf(counter));
        });

        Button reset = findViewById(R.id.button3);
        reset.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Potwierdzenie");
            builder.setMessage("Czy na pewno chcesz zresetować licznik?");
            builder.setPositiveButton("Tak", ((dialog, which) -> {
                counter = 0;
                textViewCounter.setText(String.valueOf(counter));
            }));
            builder.setNegativeButton("Nie",((dialog, which) -> dialog.dismiss()));
            AlertDialog dialog = builder.create();
            dialog.show();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("COUNTER_KEY", counter);
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Potwierdzenie");
        builder.setMessage("Czy na pewno chcesz wyjść?");
        builder.setPositiveButton("Tak", (dialog, which) -> MainActivity.super.onBackPressed());
        builder.setNegativeButton("Nie", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

}
