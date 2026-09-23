package com.example.smartpantrymanager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class RecipeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recipe_detail);

        TextView txtRecipeName =
                findViewById(R.id.txtDetailRecipeName);

        TextView txtIngredients =
                findViewById(R.id.txtDetailIngredients);

        TextView txtInstructions =
                findViewById(R.id.txtDetailInstructions);

        Button btnBack =
                findViewById(R.id.btnBackToRecipes);

        int recipeId =
                getIntent().getIntExtra("RECIPE_ID", -1);

        String recipeName =
                getIntent().getStringExtra("RECIPE_NAME");

        String instructions =
                getIntent().getStringExtra("RECIPE_INSTRUCTIONS");

        txtRecipeName.setText(recipeName);
        txtInstructions.setText(instructions);

        RecipeDataSource recipeDataSource =
                new RecipeDataSource(this);

        recipeDataSource.open();

        ArrayList<RecipeIngredient> ingredients =
                recipeDataSource.getIngredientsForRecipe(recipeId);

        recipeDataSource.close();

        StringBuilder ingredientText = new StringBuilder();

        for (RecipeIngredient ingredient : ingredients) {

            ingredientText.append("• ")
                    .append(ingredient.getIngredientName())
                    .append(" - ")
                    .append(ingredient.getRequiredQuantity())
                    .append(" ")
                    .append(ingredient.getUnit())
                    .append("\n");
        }

        txtIngredients.setText(ingredientText.toString());

        btnBack.setOnClickListener(v -> finish());
    }
}