package com.daily_dash.foodieepoodie;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView myTextView;
    private ImageView myImageView;
    private boolean isImageChanged = false;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        myImageView = findViewById(R.id.myImageView);
        myTextView = findViewById(R.id.myTextView);
        Button myButton = findViewById(R.id.myButton);
        Button orderButton = findViewById(R.id.orderButton);


        myButton.setOnClickListener(v -> {

            Toast.makeText(MainActivity.this, "Your image is ready!", Toast.LENGTH_SHORT).show();


            if (!isImageChanged) {
                myImageView.setImageResource(R.drawable.image1); // Ensure 'image1' exists in your drawable folder
                myTextView.setText("The image is displayed!");
                isImageChanged = true;
            }
        });


        orderButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, OrderActivity.class);
            startActivity(intent);
        });
    }
}