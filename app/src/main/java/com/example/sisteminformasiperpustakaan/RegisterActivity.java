package com.example.sisteminformasiperpustakaan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    DataHelper dbHelper;
    Button btnSignUp;
    EditText et1,et2,et3;
    String edit,sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbHelper = new DataHelper(this);

        et1 = (EditText) findViewById(R.id.text1);
        et2 = (EditText) findViewById(R.id.text2);
        et3 = (EditText) findViewById(R.id.text3);

        btnSignUp = (Button) findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                edit = et1.getText().toString();
                edit = et2.getText().toString();
                edit = et3.getText().toString();

                if(edit.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Kolom todak boleh kosong...", Toast.LENGTH_SHORT).show();

                }else{
                    sql = "INSERT INTO tb_user (username, password, nama) VALUES ('"+et1.getText().toString()+"', '"+et2.getText().toString()+"','"+et3.getText().toString()+"')";
                    db.execSQL(sql);
                    Toast.makeText(getApplicationContext(), "Data Tersimpan...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    finish();
                }
            }
        });
    }
}