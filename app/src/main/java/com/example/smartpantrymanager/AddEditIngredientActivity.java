package com.example.smartpantrymanager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class AddEditIngredientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_edit_ingredient);
        EditText edtIngredientName = findViewById(R.id.edtIngredientName);
        EditText edtQuantity = findViewById(R.id.edtQuantity);
        EditText edtUnit = findViewById(R.id.edtUnit);
        int itemId = getIntent().getIntExtra("ITEM_ID", -1);

        if (itemId != -1) {
            String itemName = getIntent().getStringExtra("ITEM_NAME");
            double itemQuantity = getIntent().getDoubleExtra("ITEM_QUANTITY", 0);
            String itemUnit = getIntent().getStringExtra("ITEM_UNIT");

            edtIngredientName.setText(itemName);
            edtQuantity.setText(String.valueOf(itemQuantity));
            edtUnit.setText(itemUnit);
        }

        Button btnSaveIngredient = findViewById(R.id.btnSaveIngredient);
        btnSaveIngredient.setOnClickListener(v -> {

            String name = edtIngredientName.getText().toString().trim();
            String quantityText = edtQuantity.getText().toString().trim();
            String unit = edtUnit.getText().toString().trim();

            if (name.isEmpty() || quantityText.isEmpty() || unit.isEmpty()) {
                Toast.makeText(
                        AddEditIngredientActivity.this,
                        "Please fill in all fields",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            double quantity;

            try {
                quantity = Double.parseDouble(quantityText);
            } catch (NumberFormatException e) {
                Toast.makeText(
                        AddEditIngredientActivity.this,
                        "Please enter a valid quantity",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            if (quantity <= 0) {
                Toast.makeText(
                        AddEditIngredientActivity.this,
                        "Quantity must be greater than 0",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            PantryDataSource dataSource =
                    new PantryDataSource(AddEditIngredientActivity.this);

            if (itemId == -1) {

                dataSource.open();

                if (dataSource.pantryItemExists(name, unit)) {

                    dataSource.close();

                    Toast.makeText(
                            AddEditIngredientActivity.this,
                            "This ingredient already exists in your pantry",
                            Toast.LENGTH_SHORT
                    ).show();

                    return;
                }

                PantryItem item = new PantryItem(name, quantity, unit);
                long result = dataSource.addPantryItem(item);

                dataSource.close();

                if (result != -1) {
                    Toast.makeText(
                            AddEditIngredientActivity.this,
                            "Ingredient saved successfully",
                            Toast.LENGTH_SHORT
                    ).show();

                    finish();
                } else {
                    Toast.makeText(
                            AddEditIngredientActivity.this,
                            "Error saving ingredient",
                            Toast.LENGTH_SHORT
                    ).show();
                }

            } else {
                dataSource.open();

                int updatedRows = dataSource.updatePantryItem(
                        itemId,
                        name,
                        quantity,
                        unit
                );

                dataSource.close();

                if (updatedRows > 0) {
                    Toast.makeText(
                            AddEditIngredientActivity.this,
                            "Ingredient updated successfully",
                            Toast.LENGTH_SHORT
                    ).show();

                    finish();
                } else {
                    Toast.makeText(
                            AddEditIngredientActivity.this,
                            "Error updating ingredient",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }
        });

        Button btnCancel = findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(v -> finish());
    }
}