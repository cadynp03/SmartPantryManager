package com.example.smartpantrymanager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private Switch switchShowQuantities;
    private Switch switchRecipeNotifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);

        switchShowQuantities =
                findViewById(R.id.switchShowQuantities);

        switchRecipeNotifications =
                findViewById(R.id.switchRecipeNotifications);

        Button btnSaveSettings =
                findViewById(R.id.btnSaveSettings);

        Button btnBackSettings =
                findViewById(R.id.btnBackSettings);

        SharedPreferences preferences =
                getSharedPreferences("SmartPantrySettings", MODE_PRIVATE);

        boolean showQuantities =
                preferences.getBoolean("show_quantities", true);

        boolean recipeSuggestions =
                preferences.getBoolean("recipe_suggestions", true);

        switchShowQuantities.setChecked(showQuantities);
        switchRecipeNotifications.setChecked(recipeSuggestions);

        btnSaveSettings.setOnClickListener(v -> {

            SharedPreferences.Editor editor = preferences.edit();

            editor.putBoolean(
                    "show_quantities",
                    switchShowQuantities.isChecked()
            );

            editor.putBoolean(
                    "recipe_suggestions",
                    switchRecipeNotifications.isChecked()
            );

            editor.apply();

            Toast.makeText(
                    SettingsActivity.this,
                    "Settings saved successfully",
                    Toast.LENGTH_SHORT
            ).show();
        });

        btnBackSettings.setOnClickListener(v -> finish());
    }
}