package com.example.sisteminformasiperpustakaan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn1,btn2;
    EditText et1, et2;
    DataHelper dbHelper;
    String edit, username, password;
    String[] user;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DataHelper(this);

        et1 = (EditText) findViewById(R.id.text1);
        et2 = (EditText) findViewById(R.id.text2);

        btn1 = (Button) findViewById(R.id.btnlogin);
        btn2 = (Button) findViewById(R.id.btnSignUp);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] data = new String[2];
                edit = et1.getText().toString();
                edit = et2.getText().toString();

                if (edit.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Kolom tidak boleh kosong...", Toast.LENGTH_SHORT).show();
                } else {
                    data = login(et1.getText().toString(), et2.getText().toString());
                    if (data[0].equals("0")) {
                        Toast.makeText(getApplicationContext(), "Password Salah", Toast.LENGTH_SHORT).show();
                    } else if (data[0].equals("-1")) {
                        Toast.makeText(getApplicationContext(), "Username Salah", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);

                    }
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(i);
            }
        });


    }

    public String[] login(String Username, String Password) {

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        edit = Username;
        edit = Password;

        cursor = db.rawQuery("SELECT * FROM tb_user WHERE username = '" + Username + "'", null);
        user = new String[3];
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                if (Password.equals(cursor.getString(1))) {
                    user[0] = cursor.getString(0).toString();
                    user[1] = cursor.getString(2).toString();
                    return user;
                }
                cursor.moveToNext();
            }
            user[0] = "0";
            user[1] = "";
            return user;
        }
        user[0] = "-1";
        user[1] = "";
        return user;
    }
}

