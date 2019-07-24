package com.example.phonecontact_testhipe;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import android.widget.Toast;

public class CreateActivity extends AppCompatActivity implements View.OnClickListener {
    DataHelper dbHelper;
    String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu); return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId(); if (id == R.id.action_read) {
            Intent iread = new Intent(this, MainActivity.class);
            startActivity(iread);
            return true; }
        else if (id == R.id.action_create) {
            Intent icreate = new Intent(this, CreateActivity.class);
            startActivity(icreate); return true; }
        return super.onOptionsItemSelected(item); }

    @Override
    public void onClick(View v) {
        dbHelper = new DataHelper(this);
        EditText etName = (EditText) findViewById(R.id.editText_name);
        EditText etPhone = (EditText) findViewById(R.id.editText_phone);
        EditText etEmail = (EditText) findViewById(R.id.editText_email);
        if ((etName != null) && (etPhone != null)) {

            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.execSQL("INSERT INTO contact(name, phone, email) VALUES('" +
                    etName.getText().toString() +"','"+
                    etPhone.getText().toString() +"','"+
                    etEmail + "')");
            Toast.makeText(getApplicationContext(),
                    "Success", Toast.LENGTH_LONG).show();
    }  MainActivity.ma.RefreshList(); finish(); }
}
