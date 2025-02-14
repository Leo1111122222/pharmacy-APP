package com.example.pgarmacy;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.widget.Toast;


public class SqlDB extends SQLiteOpenHelper {
    //private static final string dataBaseName = "pharmacy.db";

    public SqlDB(Context context) {
        super(context, "pharmacy.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String usersQuerySql = "CREATE TABLE users (\n" +
                "    id       INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    name     TEXT    NOT NULL,\n" +
                "    email    TEXT    NOT NULL\n" +
                "                     UNIQUE,\n" +
                "    password TEXT    NOT NULL\n" +
                ");";
        String medicineQuerySql = "CREATE TABLE medicine (\n" +
                "    med_id     INTEGER            PRIMARY KEY ASC AUTOINCREMENT\n" +
                "                                  NOT NULL,\n" +
                "    name       TEXT (100, 100)    NOT NULL,\n" +
                "    company    TEXT (100, 100)    NOT NULL,\n" +
                "    price      INTEGER (100, 100) NOT NULL,\n" +
                "    roof_num   INTEGER (100, 100) NOT NULL,\n" +
                "    cabine_num INTEGER (100, 100) NOT NULL\n" +
                ");";

        db.execSQL(usersQuerySql);
        ///  db.execSQL(medicineQuerySql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        /// db.execSQL("DROP TABLE IF EXISTS medicine");
        onCreate(db);
    }

    public void addUser(String name, String email, String password, Context context) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("name", name);
        contentValue.put("email", email);
        contentValue.put("password", password);
        long result = db.insert("users", null, contentValue);
        if (result == -1) {
            Toast.makeText(context, "Failed with inserting data", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Data insert done", Toast.LENGTH_SHORT).show();
        }

    }

    public boolean userLogin(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT COUNT(*) FROM users WHERE email = ? AND password = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{email, password});

        boolean exists = false;
        if (cursor.moveToFirst()) {
            int count = cursor.getInt(0);
            exists = count > 0;
        }
        cursor.close();
        return exists;
    }

    public boolean checkEmailUserExists(String email) {
        String emailQuery = "SELECT COUNT(*) FROM users WHERE email = ?\n";
        SQLiteDatabase db = this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(emailQuery, new String[]{email});
        boolean exists = false;
        if (cursor.moveToFirst()) {
            int count = cursor.getInt(0);
            exists = count > 0;

        }
        return exists;
    }
}
