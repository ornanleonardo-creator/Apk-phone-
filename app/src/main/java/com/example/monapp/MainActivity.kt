package com.example.monapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Lier l'écran du jeu
        setContentView(R.layout.activity_main);

        TextView scoreText = findViewById(R.id.scoreText);
        Button tapButton = findViewById(R.id.tapButton);

        tapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score++;
                scoreText.setText("Score : " + score);
            }
        });
    }
}
