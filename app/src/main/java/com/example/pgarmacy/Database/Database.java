package com.example.pgarmacy.Database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.pgarmacy.Medicine.Medicine;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteAssetHelper {
    private static final String DB_NAME = "pharmacy1.db";

    public Database(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @SuppressLint("Range")
    public List<Medicine> getMedicines() {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        String[] sqlSelect = {"med_id", "name", "company", "price", "roof_num", "cabin_num"};
        String tableName = "medicine";
        queryBuilder.setTables(tableName);
        Cursor cursor = queryBuilder.query(readableDatabase, sqlSelect, null, null,
                null, null, null, null, null);
        List<Medicine> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Medicine medicine;
                medicine = new Medicine();
                medicine.setId(cursor.getInt(cursor.getColumnIndex("med_id")));
                medicine.setName(cursor.getString(cursor.getColumnIndex("name")));
                medicine.setCompany(cursor.getString(cursor.getColumnIndex("company")));
                medicine.setPrice(cursor.getInt(cursor.getColumnIndex("price")));
                medicine.setRoof(cursor.getInt(cursor.getColumnIndex("roof_num")));
                medicine.setCabin(cursor.getInt(cursor.getColumnIndex("cabin_num")));
            } while (cursor.moveToNext());
        }
        return result;
    }

    @SuppressLint("Range")
    public List<String> getMedicineName() {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        String[] sqlSelect = {"name"};
        String tableName = "medicine";
        queryBuilder.setTables(tableName);
        Cursor cursor = queryBuilder.query(readableDatabase, sqlSelect, null, null,
                null, null, null, null, null);
        List<String> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                result.add(cursor.getString(cursor.getColumnIndex("name")));
            } while (cursor.moveToNext());
        }
        return result;
    }

    @SuppressLint("Range")
    public List<Medicine> getMedicineByName(String name) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        String[] sqlSelect = {"med_id", "name", "company", "price", "roof_num", "cabin_num"};
        String tableName = "medicine";
        queryBuilder.setTables(tableName);
        Cursor cursor = queryBuilder.query(readableDatabase, sqlSelect, "name LIKE ?", new String[]{"%" + name + "%"},
                null, null, null, null, null);
        List<Medicine> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Medicine medicine = new Medicine();
                medicine.setId(cursor.getInt(cursor.getColumnIndex("med_id")));
                medicine.setName(cursor.getString(cursor.getColumnIndex("name")));
                medicine.setCompany(cursor.getString(cursor.getColumnIndex("company")));
                medicine.setPrice(cursor.getInt(cursor.getColumnIndex("price")));
                medicine.setRoof(cursor.getInt(cursor.getColumnIndex("roof_num")));
                medicine.setCabin(cursor.getInt(cursor.getColumnIndex("cabin_num")));
                result.add(medicine);
            } while (cursor.moveToNext());
        }
        return result;
    }
}
