package com.example.phonecontact_testhipe;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "contacts.db";
    private static final int DATABASE_VERSION = 1;
    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS contact" +
                "(id_contact INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name TEXT, " +
                "phone TEXT, " +
                "email TEXT);";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql); sql = "INSERT INTO contact (name, phone, email) "
                + "VALUES ('Aldi', '089523178818', 'mrgad98@gmail.com');";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
    }
}