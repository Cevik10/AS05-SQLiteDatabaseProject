package com.hakancevik.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.hakancevik.sqlitedatabase.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        try {

            // create database
            SQLiteDatabase database = this.openOrCreateDatabase("Musicians", MODE_PRIVATE, null);
            database.execSQL("CREATE TABLE IF NOT EXISTS musicians (id INTEGER PRIMARY KEY,name VARCHAR, age INTEGER)");


            // add data
            database.execSQL("INSERT INTO musicians (name,age) VALUES ('Ray',67)");
            database.execSQL("INSERT INTO musicians (name,age) VALUES ('Hakan Cevik',21)");
            database.execSQL("INSERT INTO musicians (name,age) VALUES ('Frank Sinatra',82)");


            // update data
            database.execSQL("UPDATE musicians SET age = 80 WHERE name = 'Frank Sinatra'");
            database.execSQL("UPDATE musicians SET name = 'Ray Liotta' WHERE id = 1");


            // delete data
            database.execSQL("DELETE FROM musicians WHERE id=3");


            // read data
            Cursor cursor = database.rawQuery("SELECT * FROM musicians", null);

            // filter data
            //Cursor cursor = database.rawQuery("SELECT * FROM musicians WHERE id = 2",null);
            //Cursor cursor = database.rawQuery("SELECT * FROM musicians WHERE age > 55",null);
            //Cursor cursor = database.rawQuery("SELECT * FROM musicians WHERE name = 'Ray'",null);
            //Cursor cursor = database.rawQuery("SELECT * FROM musicians WHERE name LIKE '%a'",null);
            //Cursor cursor = database.rawQuery("SELECT * FROM musicians WHERE name LIKE 'H%'",null);


            int nameIndex = cursor.getColumnIndex("name");
            int ageIndex = cursor.getColumnIndex("age");
            int idIndex = cursor.getColumnIndex("id");

            while (cursor.moveToNext()) {

                System.out.println("Id: " + cursor.getString(idIndex));
                System.out.println("Name: " + cursor.getString(nameIndex));
                System.out.println("Age: " + cursor.getString(ageIndex));

            }
            cursor.close();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}