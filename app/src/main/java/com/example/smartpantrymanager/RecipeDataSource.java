package com.example.smartpantrymanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.database.Cursor;
import java.util.ArrayList;
public class RecipeDataSource {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public RecipeDataSource(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long addRecipe(Recipe recipe) {

        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.COLUMN_RECIPE_NAME, recipe.getName());
        values.put(DatabaseHelper.COLUMN_INSTRUCTIONS, recipe.getInstructions());

        return database.insert(
                DatabaseHelper.TABLE_RECIPES,
                null,
                values
        );
    }

    public long addRecipeIngredient(RecipeIngredient ingredient) {

        ContentValues values = new ContentValues();

        values.put(
                DatabaseHelper.COLUMN_RECIPE_ID,
                ingredient.getRecipeId()
        );

        values.put(
                DatabaseHelper.COLUMN_INGREDIENT_NAME,
                ingredient.getIngredientName()
        );

        values.put(
                DatabaseHelper.COLUMN_REQUIRED_QUANTITY,
                ingredient.getRequiredQuantity()
        );

        values.put(
                DatabaseHelper.COLUMN_UNIT,
                ingredient.getUnit()
        );

        return database.insert(
                DatabaseHelper.TABLE_RECIPE_INGREDIENTS,
                null,
                values
        );
    }

    public boolean recipesExist() {

        Cursor cursor = database.rawQuery(
                "SELECT COUNT(*) FROM " + DatabaseHelper.TABLE_RECIPES,
                null
        );

        boolean exists = false;

        if (cursor.moveToFirst()) {
            exists = cursor.getInt(0) > 0;
        }

        cursor.close();

        return exists;
    }

    public void preloadRecipes() {

        if (recipesExist()) {
            return;
        }

        // Recipe 1: Hot Milk
        Recipe recipe1 = new Recipe(
                "Hot Milk",
                "",
                "Pour the milk into a saucepan. Heat gently until warm, then serve."
        );

        long recipe1Id = addRecipe(recipe1);

        addRecipeIngredient(
                new RecipeIngredient(
                        (int) recipe1Id,
                        "Milk",
                        250,
                        "ml"
                )
        );


        // Recipe 2: Scrambled Eggs
        Recipe recipe2 = new Recipe(
                "Scrambled Eggs",
                "",
                "Crack the eggs into a bowl and whisk them. Add the milk and mix well. " +
                        "Cook in a pan over medium heat, stirring gently until the eggs are cooked."
        );

        long recipe2Id = addRecipe(recipe2);

        addRecipeIngredient(
                new RecipeIngredient(
                        (int) recipe2Id,
                        "Eggs",
                        2,
                        "pcs"
                )
        );

        addRecipeIngredient(
                new RecipeIngredient(
                        (int) recipe2Id,
                        "Milk",
                        50,
                        "ml"
                )
        );

        // Recipe 3: French Toast
        Recipe recipe3 = new Recipe(
                "French Toast",
                "",
                "Crack the eggs into a bowl and whisk with the milk. Dip each slice of bread into the mixture. Cook the bread in a pan over medium heat until golden brown on both sides."
        );

        long recipe3Id = addRecipe(recipe3);

        addRecipeIngredient(
                new RecipeIngredient(
                        (int) recipe3Id,
                        "Bread",
                        2,
                        "slices"
                )
        );

        addRecipeIngredient(
                new RecipeIngredient(
                        (int) recipe3Id,
                        "Eggs",
                        2,
                        "pcs"
                )
        );

        addRecipeIngredient(
                new RecipeIngredient(
                        (int) recipe3Id,
                        "Milk",
                        100,
                        "ml"
                )
        );

        // Recipe 4: Cheese Sandwich
        Recipe recipe4 = new Recipe(
                "Cheese Sandwich",
                "",
                "Place the cheese between two slices of bread. Serve as is, or toast the sandwich until the bread is golden and the cheese has melted."
        );

        long recipe4Id = addRecipe(recipe4);

        addRecipeIngredient(
                new RecipeIngredient(
                        (int) recipe4Id,
                        "Bread",
                        2,
                        "slices"
                )
        );

        addRecipeIngredient(
                new RecipeIngredient(
                        (int) recipe4Id,
                        "Cheese",
                        2,
                        "slices"
                )
        );

        // Recipe 5: Peanut Butter Toast
        Recipe recipe5 = new Recipe(
                "Peanut Butter Toast",
                "",
                "Toast the bread until golden brown. Spread the peanut butter evenly over the toasted bread and serve."
        );

        long recipe5Id = addRecipe(recipe5);

        addRecipeIngredient(
                new RecipeIngredient(
                        (int) recipe5Id,
                        "Bread",
                        2,
                        "slices"
                )
        );

        addRecipeIngredient(
                new RecipeIngredient(
                        (int) recipe5Id,
                        "Peanut Butter",
                        30,
                        "g"
                )
        );

        // Recipe 6: Banana Smoothie
        Recipe recipe6 = new Recipe(
                "Banana Smoothie",
                "",
                "Peel and slice the banana. Add the banana and milk to a blender. Blend until smooth and serve."
        );

        long recipe6Id = addRecipe(recipe6);

        addRecipeIngredient(
                new RecipeIngredient(
                        (int) recipe6Id,
                        "Banana",
                        1,
                        "pcs"
                )
        );

        addRecipeIngredient(
                new RecipeIngredient(
                        (int) recipe6Id,
                        "Milk",
                        250,
                        "ml"
                )
        );

        // Recipe 7: Egg Sandwich
        Recipe recipe7 = new Recipe(
                "Egg Sandwich",
                "",
                "Cook the eggs in a pan until fully cooked. Place the cooked eggs between two slices of bread and serve."
        );

        long recipe7Id = addRecipe(recipe7);

        addRecipeIngredient(
                new RecipeIngredient(
                        (int) recipe7Id,
                        "Bread",
                        2,
                        "slices"
                )
        );

        addRecipeIngredient(
                new RecipeIngredient(
                        (int) recipe7Id,
                        "Eggs",
                        2,
                        "pcs"
                )
        );

        // Recipe 8: Cheese Omelette
        Recipe recipe8 = new Recipe(
                "Cheese Omelette",
                "",
                "Crack the eggs into a bowl and whisk them. Pour into a heated pan and cook gently. Add the cheese, fold the omelette in half, and cook until the cheese has melted."
        );

        long recipe8Id = addRecipe(recipe8);

        addRecipeIngredient(
                new RecipeIngredient(
                        (int) recipe8Id,
                        "Eggs",
                        2,
                        "pcs"
                )
        );

        addRecipeIngredient(
                new RecipeIngredient(
                        (int) recipe8Id,
                        "Cheese",
                        2,
                        "slices"
                )
        );

        // Recipe 9: Banana Toast
        Recipe recipe9 = new Recipe(
                "Banana Toast",
                "",
                "Toast the bread until golden brown. Peel and slice the banana, place the slices on top of the toast, and serve."
        );

        long recipe9Id = addRecipe(recipe9);

        addRecipeIngredient(
                new RecipeIngredient(
                        (int) recipe9Id,
                        "Bread",
                        2,
                        "slices"
                )
        );

        addRecipeIngredient(
                new RecipeIngredient(
                        (int) recipe9Id,
                        "Banana",
                        1,
                        "pcs"
                )
        );

        // Recipe 10: Peanut Butter Banana Toast
        Recipe recipe10 = new Recipe(
                "Peanut Butter Banana Toast",
                "",
                "Toast the bread until golden brown. Spread the peanut butter over the toast. Peel and slice the banana, place the slices on top, and serve."
        );

        long recipe10Id = addRecipe(recipe10);

        addRecipeIngredient(
                new RecipeIngredient(
                        (int) recipe10Id,
                        "Bread",
                        2,
                        "slices"
                )
        );

        addRecipeIngredient(
                new RecipeIngredient(
                        (int) recipe10Id,
                        "Peanut Butter",
                        30,
                        "g"
                )
        );

        addRecipeIngredient(
                new RecipeIngredient(
                        (int) recipe10Id,
                        "Banana",
                        1,
                        "pcs"
                )
        );

        // Recipe 11: Boiled Eggs
        Recipe recipe11 = new Recipe(
                "Boiled Eggs",
                "",
                "Place the eggs in a saucepan and cover with water. Bring the water to a boil and cook until the eggs are done. Allow to cool slightly, peel, and serve."
        );

        long recipe11Id = addRecipe(recipe11);

        addRecipeIngredient(
                new RecipeIngredient(
                        (int) recipe11Id,
                        "Eggs",
                        2,
                        "pcs"
                )
        );


// Recipe 12: Cheese Toast
        Recipe recipe12 = new Recipe(
                "Cheese Toast",
                "",
                "Place the cheese on the bread. Toast until the bread is golden and the cheese has melted. Serve warm."
        );

        long recipe12Id = addRecipe(recipe12);

        addRecipeIngredient(
                new RecipeIngredient(
                        (int) recipe12Id,
                        "Bread",
                        2,
                        "slices"
                )
        );

        addRecipeIngredient(
                new RecipeIngredient(
                        (int) recipe12Id,
                        "Cheese",
                        2,
                        "slices"
                )
        );


// Recipe 13: Banana Milkshake
        Recipe recipe13 = new Recipe(
                "Banana Milkshake",
                "",
                "Peel and slice the banana. Place the banana and milk into a blender. Blend until smooth and serve immediately."
        );

        long recipe13Id = addRecipe(recipe13);

        addRecipeIngredient(
                new RecipeIngredient(
                        (int) recipe13Id,
                        "Banana",
                        1,
                        "pcs"
                )
        );

        addRecipeIngredient(
                new RecipeIngredient(
                        (int) recipe13Id,
                        "Milk",
                        300,
                        "ml"
                )
        );


// Recipe 14: Peanut Butter Banana Sandwich
        Recipe recipe14 = new Recipe(
                "Peanut Butter Banana Sandwich",
                "",
                "Spread peanut butter over the bread. Peel and slice the banana and place the slices on the bread. Close the sandwich and serve."
        );

        long recipe14Id = addRecipe(recipe14);

        addRecipeIngredient(
                new RecipeIngredient(
                        (int) recipe14Id,
                        "Bread",
                        2,
                        "slices"
                )
        );

        addRecipeIngredient(
                new RecipeIngredient(
                        (int) recipe14Id,
                        "Peanut Butter",
                        30,
                        "g"
                )
        );

        addRecipeIngredient(
                new RecipeIngredient(
                        (int) recipe14Id,
                        "Banana",
                        1,
                        "pcs"
                )
        );


// Recipe 15: Egg and Cheese Sandwich
        Recipe recipe15 = new Recipe(
                "Egg and Cheese Sandwich",
                "",
                "Cook the eggs in a pan. Place the cooked eggs and cheese between two slices of bread. Serve warm."
        );

        long recipe15Id = addRecipe(recipe15);

        addRecipeIngredient(
                new RecipeIngredient(
                        (int) recipe15Id,
                        "Bread",
                        2,
                        "slices"
                )
        );

        addRecipeIngredient(
                new RecipeIngredient(
                        (int) recipe15Id,
                        "Eggs",
                        2,
                        "pcs"
                )
        );

        addRecipeIngredient(
                new RecipeIngredient(
                        (int) recipe15Id,
                        "Cheese",
                        2,
                        "slices"
                )
        );


// Recipe 16: Tomato Sandwich
        Recipe recipe16 = new Recipe(
                "Tomato Sandwich",
                "",
                "Wash and slice the tomato. Place the tomato slices between two slices of bread and serve."
        );

        long recipe16Id = addRecipe(recipe16);

        addRecipeIngredient(
                new RecipeIngredient(
                        (int) recipe16Id,
                        "Bread",
                        2,
                        "slices"
                )
        );

        addRecipeIngredient(
                new RecipeIngredient(
                        (int) recipe16Id,
                        "Tomato",
                        1,
                        "pcs"
                )
        );


// Recipe 17: Cheese and Tomato Sandwich
        Recipe recipe17 = new Recipe(
                "Cheese and Tomato Sandwich",
                "",
                "Wash and slice the tomato. Place the cheese and tomato between two slices of bread. Serve as is or toast until the cheese melts."
        );

        long recipe17Id = addRecipe(recipe17);

        addRecipeIngredient(
                new RecipeIngredient(
                        (int) recipe17Id,
                        "Bread",
                        2,
                        "slices"
                )
        );

        addRecipeIngredient(
                new RecipeIngredient(
                        (int) recipe17Id,
                        "Cheese",
                        2,
                        "slices"
                )
        );

        addRecipeIngredient(
                new RecipeIngredient(
                        (int) recipe17Id,
                        "Tomato",
                        1,
                        "pcs"
                )
        );


// Recipe 18: Tomato Omelette
        Recipe recipe18 = new Recipe(
                "Tomato Omelette",
                "",
                "Crack the eggs into a bowl and whisk them. Chop the tomato into small pieces. Pour the eggs into a heated pan, add the tomato, and cook until the eggs are fully cooked."
        );

        long recipe18Id = addRecipe(recipe18);

        addRecipeIngredient(
                new RecipeIngredient(
                        (int) recipe18Id,
                        "Eggs",
                        2,
                        "pcs"
                )
        );

        addRecipeIngredient(
                new RecipeIngredient(
                        (int) recipe18Id,
                        "Tomato",
                        1,
                        "pcs"
                )
        );


// Recipe 19: Cheese and Tomato Omelette
        Recipe recipe19 = new Recipe(
                "Cheese and Tomato Omelette",
                "",
                "Whisk the eggs in a bowl. Chop the tomato and add it to the eggs. Pour the mixture into a heated pan, add the cheese, and cook until the eggs are done and the cheese has melted."
        );

        long recipe19Id = addRecipe(recipe19);

        addRecipeIngredient(
                new RecipeIngredient(
                        (int) recipe19Id,
                        "Eggs",
                        2,
                        "pcs"
                )
        );

        addRecipeIngredient(
                new RecipeIngredient(
                        (int) recipe19Id,
                        "Tomato",
                        1,
                        "pcs"
                )
        );

        addRecipeIngredient(
                new RecipeIngredient(
                        (int) recipe19Id,
                        "Cheese",
                        2,
                        "slices"
                )
        );


// Recipe 20: Peanut Butter Banana Smoothie
        Recipe recipe20 = new Recipe(
                "Peanut Butter Banana Smoothie",
                "",
                "Peel and slice the banana. Add the banana, peanut butter, and milk to a blender. Blend until smooth and serve."
        );

        long recipe20Id = addRecipe(recipe20);

        addRecipeIngredient(
                new RecipeIngredient(
                        (int) recipe20Id,
                        "Banana",
                        1,
                        "pcs"
                )
        );

        addRecipeIngredient(
                new RecipeIngredient(
                        (int) recipe20Id,
                        "Peanut Butter",
                        30,
                        "g"
                )
        );

        addRecipeIngredient(
                new RecipeIngredient(
                        (int) recipe20Id,
                        "Milk",
                        250,
                        "ml"
                )
        );
    }

    public ArrayList<RecipeIngredient> getIngredientsForRecipe(int recipeId) {

        ArrayList<RecipeIngredient> ingredients = new ArrayList<>();

        Cursor cursor = database.query(
                DatabaseHelper.TABLE_RECIPE_INGREDIENTS,
                null,
                DatabaseHelper.COLUMN_RECIPE_ID + " = ?",
                new String[]{String.valueOf(recipeId)},
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(
                        cursor.getColumnIndexOrThrow(
                                DatabaseHelper.COLUMN_RECIPE_INGREDIENT_ID
                        )
                );

                String name = cursor.getString(
                        cursor.getColumnIndexOrThrow(
                                DatabaseHelper.COLUMN_INGREDIENT_NAME
                        )
                );

                double quantity = cursor.getDouble(
                        cursor.getColumnIndexOrThrow(
                                DatabaseHelper.COLUMN_REQUIRED_QUANTITY
                        )
                );

                String unit = cursor.getString(
                        cursor.getColumnIndexOrThrow(
                                DatabaseHelper.COLUMN_UNIT
                        )
                );

                RecipeIngredient ingredient = new RecipeIngredient(
                        id,
                        recipeId,
                        name,
                        quantity,
                        unit
                );

                ingredients.add(ingredient);

            } while (cursor.moveToNext());
        }

        cursor.close();

        return ingredients;
    }

    public ArrayList<Recipe> getAllRecipes() {

        ArrayList<Recipe> recipes = new ArrayList<>();

        Cursor cursor = database.query(
                DatabaseHelper.TABLE_RECIPES,
                null,
                null,
                null,
                null,
                null,
                DatabaseHelper.COLUMN_RECIPE_NAME + " ASC"
        );

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(
                        cursor.getColumnIndexOrThrow(
                                DatabaseHelper.COLUMN_RECIPE_ID
                        )
                );

                String name = cursor.getString(
                        cursor.getColumnIndexOrThrow(
                                DatabaseHelper.COLUMN_RECIPE_NAME
                        )
                );

                String instructions = cursor.getString(
                        cursor.getColumnIndexOrThrow(
                                DatabaseHelper.COLUMN_INSTRUCTIONS
                        )
                );

                Recipe recipe = new Recipe(
                        id,
                        name,
                        "",
                        instructions
                );

                recipes.add(recipe);

            } while (cursor.moveToNext());
        }

        cursor.close();

        return recipes;
    }

    public ArrayList<Recipe> getMatchingRecipes(
            ArrayList<PantryItem> pantryItems) {

        ArrayList<Recipe> matchingRecipes = new ArrayList<>();
        ArrayList<Recipe> allRecipes = getAllRecipes();

        for (Recipe recipe : allRecipes) {

            ArrayList<RecipeIngredient> requiredIngredients =
                    getIngredientsForRecipe(recipe.getId());

            boolean canMakeRecipe = true;

            for (RecipeIngredient required : requiredIngredients) {

                boolean ingredientFound = false;

                for (PantryItem pantryItem : pantryItems) {

                    if (pantryItem.getName().equalsIgnoreCase(
                            required.getIngredientName())
                            &&
                            pantryItem.getUnit().equalsIgnoreCase(
                                    required.getUnit())
                            &&
                            pantryItem.getQuantity() >=
                                    required.getRequiredQuantity()) {

                        ingredientFound = true;
                        break;
                    }
                }

                if (!ingredientFound) {
                    canMakeRecipe = false;
                    break;
                }
            }

            if (canMakeRecipe) {
                matchingRecipes.add(recipe);
            }
        }

        return matchingRecipes;
    }
}