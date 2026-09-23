package com.example.smartpantrymanager;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;
import java.util.ArrayList;
import androidx.appcompat.app.AlertDialog;
import android.content.SharedPreferences;

public class MainActivity extends AppCompatActivity {
    private ListView listPantry;
    private TextView txtEmptyPantry;
    private PantryDataSource dataSource;
    private ArrayList<PantryItem> pantryItems;
    @Override

    protected void onResume() {
        super.onResume();
        loadPantryItems();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        listPantry = findViewById(R.id.listPantry);
        txtEmptyPantry = findViewById(R.id.txtEmptyPantry);

        dataSource = new PantryDataSource(this);
        RecipeDataSource recipeDataSource = new RecipeDataSource(this);
        recipeDataSource.open();
        recipeDataSource.preloadRecipes();
        recipeDataSource.close();

        Button btnAddIngredient = findViewById(R.id.btnAddIngredient);

        btnAddIngredient.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddEditIngredientActivity.class);
            startActivity(intent);
        });

        Button btnSuggestedRecipes = findViewById(R.id.btnSuggestedRecipes);
        btnSuggestedRecipes.setOnClickListener(v -> {

            SharedPreferences preferences =
                    getSharedPreferences("SmartPantrySettings", MODE_PRIVATE);

            boolean recipeSuggestions =
                    preferences.getBoolean("recipe_suggestions", true);

            if (recipeSuggestions) {

                Intent intent = new Intent(
                        MainActivity.this,
                        SuggestedRecipesActivity.class
                );

                startActivity(intent);

            } else {

                Toast.makeText(
                        MainActivity.this,
                        "Recipe suggestions are disabled in Settings",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        Button btnSettings = findViewById(R.id.btnSettings);

        btnSettings.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    SettingsActivity.class
            );
            startActivity(intent);
        });

        listPantry.setOnItemLongClickListener((parent, view, position, id) -> {

            PantryItem selectedPantryItem = pantryItems.get(position);

            String selectedItem = selectedPantryItem.getName() + " - " +
                    selectedPantryItem.getQuantity() + " " +
                    selectedPantryItem.getUnit();
            String[] options = {"Edit", "Delete", "Cancel"};

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

            builder.setTitle(selectedItem);

            builder.setItems(options, (dialog, which) -> {

                if (which == 0) {

                    Intent intent = new Intent(
                            MainActivity.this,
                            AddEditIngredientActivity.class
                    );

                    intent.putExtra("ITEM_ID", selectedPantryItem.getId());
                    intent.putExtra("ITEM_NAME", selectedPantryItem.getName());
                    intent.putExtra("ITEM_QUANTITY", selectedPantryItem.getQuantity());
                    intent.putExtra("ITEM_UNIT", selectedPantryItem.getUnit());

                    startActivity(intent);

                } else if (which == 1) {

                    dataSource.open();

                    int deletedRows = dataSource.deletePantryItem(
                            selectedPantryItem.getId()
                    );

                    dataSource.close();

                    if (deletedRows > 0) {
                        Toast.makeText(
                                MainActivity.this,
                                "Ingredient deleted",
                                Toast.LENGTH_SHORT
                        ).show();

                        loadPantryItems();

                    } else {
                        Toast.makeText(
                                MainActivity.this,
                                "Error deleting ingredient",
                                Toast.LENGTH_SHORT
                        ).show();
                    }

                } else {
                    dialog.dismiss();
                }
            });

            builder.show();

            return true;
        });
    }

    private void loadPantryItems() {

        dataSource.open();
        pantryItems = dataSource.getAllPantryItems();
        dataSource.close();


        SharedPreferences preferences =
                getSharedPreferences("SmartPantrySettings", MODE_PRIVATE);

        boolean showQuantities =
                preferences.getBoolean("show_quantities", true);

        PantryAdapter adapter = new PantryAdapter(
                this,
                pantryItems,
                showQuantities
        );

        listPantry.setAdapter(adapter);

        if (pantryItems.isEmpty()) {
            txtEmptyPantry.setVisibility(View.VISIBLE);
            listPantry.setVisibility(View.GONE);
        } else {
            txtEmptyPantry.setVisibility(View.GONE);
            listPantry.setVisibility(View.VISIBLE);
        }
    }




}