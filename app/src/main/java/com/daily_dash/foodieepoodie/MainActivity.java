package com.daily_dash.foodieepoodie;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView myTextView;
    private Button myButton;
    private ImageView myImageView;
    private boolean isImageChanged = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myImageView = findViewById(R.id.myImageView);
        myTextView = findViewById(R.id.myTextView);
        myButton = findViewById(R.id.myButton);
        myButton.setOnClickListener(v -> {

            Toast.makeText(MainActivity.this, "Your image is ready!", Toast.LENGTH_SHORT).show();

            if (!isImageChanged) {
                myImageView.setImageResource(R.drawable.image1);

                myTextView.setText("The image is displayed!");

                isImageChanged = true;
            }
        });
    }
}
