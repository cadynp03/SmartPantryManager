package com.example.smartpantrymanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class PantryDataSource {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public PantryDataSource(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }
    public long addPantryItem(PantryItem item) {

        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.COLUMN_NAME, item.getName());
        values.put(DatabaseHelper.COLUMN_QUANTITY, item.getQuantity());
        values.put(DatabaseHelper.COLUMN_UNIT, item.getUnit());

        return database.insert(
                DatabaseHelper.TABLE_PANTRY,
                null,
                values
        );
    }

    public ArrayList<PantryItem> getAllPantryItems() {

        ArrayList<PantryItem> pantryItems = new ArrayList<>();

        Cursor cursor = database.query(
                DatabaseHelper.TABLE_PANTRY,
                null,
                null,
                null,
                null,
                null,
                DatabaseHelper.COLUMN_NAME + " ASC"
        );

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(
                        cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID)
                );

                String name = cursor.getString(
                        cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME)
                );

                double quantity = cursor.getDouble(
                        cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_QUANTITY)
                );

                String unit = cursor.getString(
                        cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_UNIT)
                );

                PantryItem item = new PantryItem(id, name, quantity, unit);
                pantryItems.add(item);

            } while (cursor.moveToNext());
        }

        cursor.close();

        return pantryItems;
    }
    public int deletePantryItem(long id) {
        return database.delete(
                DatabaseHelper.TABLE_PANTRY,
                DatabaseHelper.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)}
        );
    }

    public int updatePantryItem(long id, String name, double quantity, String unit) {

        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.COLUMN_NAME, name);
        values.put(DatabaseHelper.COLUMN_QUANTITY, quantity);
        values.put(DatabaseHelper.COLUMN_UNIT, unit);

        return database.update(
                DatabaseHelper.TABLE_PANTRY,
                values,
                DatabaseHelper.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)}
        );
    }

    public boolean pantryItemExists(String name, String unit) {

        Cursor cursor = database.query(
                DatabaseHelper.TABLE_PANTRY,
                new String[]{DatabaseHelper.COLUMN_ID},
                "LOWER(" + DatabaseHelper.COLUMN_NAME + ") = LOWER(?) AND " +
                        "LOWER(" + DatabaseHelper.COLUMN_UNIT + ") = LOWER(?)",
                new String[]{name, unit},
                null,
                null,
                null
        );

        boolean exists = cursor.getCount() > 0;

        cursor.close();

        return exists;
    }
}