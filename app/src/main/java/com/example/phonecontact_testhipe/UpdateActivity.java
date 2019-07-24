package com.example.phonecontact_testhipe;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class UpdateActivity extends AppCompatActivity implements
        View.OnClickListener {
    DataHelper dbHelper;
    protected Cursor cursor;
    Button tonSave;
    EditText textName, textPhone,textEmail;
    private int idContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        dbHelper = new DataHelper(this);
        textName = (EditText) findViewById(R.id.editText_name);
        textPhone = (EditText) findViewById(R.id.editText_phone);
        textEmail = (EditText) findViewById(R.id.editText_email);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM contact WHERE id_contact = '" +
                getIntent().getStringExtra("id_contact") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0);
            idContact = Integer.parseInt(cursor.getString(0).toString());
            textName.setText(cursor.getString(1).toString());
            textPhone.setText(cursor.getString(2).toString());
            textEmail.setText(cursor.getString(3).toString());
        }
        tonSave = (Button) findViewById(R.id.button_main);
        tonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if ((textName != null) && (textPhone != null)) {
                    StringBuffer group = new StringBuffer();
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    db.execSQL("update contact set name='" +
                            textName.getText().toString() +"', phone='" +
                            textPhone.getText().toString()+"', email='"+
                            textEmail.getText().toString() +"' where id_contact='" +
                            idContact+"'");
                    Toast.makeText(getApplicationContext(), "Success",
                            Toast.LENGTH_LONG).show();
                }
                MainActivity.ma.RefreshList();
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_read) {
            Intent iread = new Intent(this, MainActivity.class);
            startActivity(iread);
            return true;
        } else if (id == R.id.action_create) {
            Intent icreate = new Intent(this, CreateActivity.class);
            startActivity(icreate);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

    }
}
