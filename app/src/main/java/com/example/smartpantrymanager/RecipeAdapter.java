package com.example.smartpantrymanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RecipeAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Recipe> recipes;

    public RecipeAdapter(Context context, ArrayList<Recipe> recipes) {
        this.context = context;
        this.recipes = recipes;
    }

    @Override
    public int getCount() {
        return recipes.size();
    }

    @Override
    public Object getItem(int position) {
        return recipes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return recipes.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_recipe,
                    parent,
                    false
            );
        }

        TextView txtRecipeName =
                convertView.findViewById(R.id.txtRecipeItemName);

        TextView txtRecipeMessage =
                convertView.findViewById(R.id.txtRecipeItemMessage);

        Recipe recipe = recipes.get(position);

        txtRecipeName.setText(recipe.getName());

        txtRecipeMessage.setText(
                "You have all the required ingredients"
        );

        return convertView;
    }
}