package com.example.smartpantrymanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PantryAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<PantryItem> pantryItems;
    private boolean showQuantities;

    public PantryAdapter(Context context,
                         ArrayList<PantryItem> pantryItems,
                         boolean showQuantities) {

        this.context = context;
        this.pantryItems = pantryItems;
        this.showQuantities = showQuantities;
    }

    @Override
    public int getCount() {
        return pantryItems.size();
    }

    @Override
    public Object getItem(int position) {
        return pantryItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return pantryItems.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_pantry,
                    parent,
                    false
            );
        }

        TextView txtName =
                convertView.findViewById(R.id.txtPantryItemName);

        TextView txtQuantity =
                convertView.findViewById(R.id.txtPantryItemQuantity);

        PantryItem item = pantryItems.get(position);

        txtName.setText(item.getName());

        if (showQuantities) {

            txtQuantity.setText(
                    item.getQuantity() + " " + item.getUnit()
            );

            txtQuantity.setVisibility(View.VISIBLE);

        } else {

            txtQuantity.setVisibility(View.GONE);
        }

        return convertView;
    }
}