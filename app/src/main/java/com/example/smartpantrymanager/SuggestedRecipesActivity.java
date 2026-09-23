package com.example.smartpantrymanager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SuggestedRecipesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_suggested_recipes);

        ListView listRecipes = findViewById(R.id.listRecipes);
        TextView txtNoRecipes = findViewById(R.id.txtNoRecipes);
        Button btnBack = findViewById(R.id.btnBack);

        PantryDataSource pantryDataSource =
                new PantryDataSource(this);

        pantryDataSource.open();
        ArrayList<PantryItem> pantryItems =
                pantryDataSource.getAllPantryItems();
        pantryDataSource.close();

        RecipeDataSource recipeDataSource =
                new RecipeDataSource(this);

        recipeDataSource.open();

        ArrayList<Recipe> matchingRecipes =
                recipeDataSource.getMatchingRecipes(pantryItems);

        recipeDataSource.close();

        RecipeAdapter adapter = new RecipeAdapter(
                this,
                matchingRecipes
        );

        listRecipes.setAdapter(adapter);

        listRecipes.setOnItemClickListener((parent, view, position, id) -> {

            Recipe selectedRecipe = matchingRecipes.get(position);

            android.content.Intent intent = new android.content.Intent(
                    SuggestedRecipesActivity.this,
                    RecipeDetailActivity.class
            );

            intent.putExtra("RECIPE_ID", selectedRecipe.getId());
            intent.putExtra("RECIPE_NAME", selectedRecipe.getName());
            intent.putExtra("RECIPE_INSTRUCTIONS", selectedRecipe.getInstructions());

            startActivity(intent);
        });

        if (matchingRecipes.isEmpty()) {
            txtNoRecipes.setVisibility(View.VISIBLE);
            listRecipes.setVisibility(View.GONE);
        } else {
            txtNoRecipes.setVisibility(View.GONE);
            listRecipes.setVisibility(View.VISIBLE);
        }

        btnBack.setOnClickListener(v -> finish());
    }
}