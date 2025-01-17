package com.sunil_poudel.accessibilityapplication;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private TextView youtubeScrollTime;
    private TextView instagramScrollTime;
    private Button enableAccessibilityServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        youtubeScrollTime = findViewById(R.id.youtube_scroll_time);
        instagramScrollTime = findViewById(R.id.instagram_scroll_time);
        enableAccessibilityServices = findViewById(R.id.enable_accessibility_button);

        enableAccessibilityServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                startActivity(intent);
            }
        });


    }
}