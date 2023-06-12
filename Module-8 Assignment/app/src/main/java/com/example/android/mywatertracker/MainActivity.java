package com.example.android.mywatertracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.mywatertracker.WaterTrackingService;

public class MainActivity extends AppCompatActivity {
    private Button addWaterButton;
    private TextView waterLevelTextView;
    private int waterLevel = 2500; // Initial water level in milliliters

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        waterLevelTextView = findViewById(R.id.water_level_text_view);
        addWaterButton = findViewById(R.id.add_water_button);
        addWaterButton.setOnClickListener(v -> {
            addWater(250); // Increase the water level by 250 milliliters when the button is clicked
        });
    }

    private void addWater(int amount) {
        waterLevel += amount;
        updateWaterLevelTextView();
    }

    private void updateWaterLevelTextView() {
        waterLevelTextView.setText("Water Level: " + waterLevel + " ml");
    }

    private void startService() {
        Intent serviceIntent = new Intent(this, WaterTrackingService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(serviceIntent);
        } else {
            startService(serviceIntent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateWaterLevelTextView();
    }
}
