package com.example.sisteminformasiperpustakaan;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityInput extends AppCompatActivity {
    EditText et1,et2,et3,et4;
    DataHelper dbHelper;
    String edit,sql;
    Button btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        dbHelper = new DataHelper(this);

        et1 = (EditText) findViewById(R.id.editText1);
        et2 = (EditText) findViewById(R.id.editText2);
        et3 = (EditText) findViewById(R.id.editText3);
        et4 = (EditText) findViewById(R.id.editText4);

        btnSimpan = (Button) findViewById(R.id.btnSimpanBuku);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                edit = et1.getText().toString();
                edit = et2.getText().toString();
                edit = et3.getText().toString();
                edit = et4.getText().toString();

                if(edit.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Kolom todak boleh kosong...", Toast.LENGTH_SHORT).show();

                }else{
                    sql = "INSERT INTO tb_buku (id_buku, judul_buku, penulis,tahun_terbit) VALUES ('"+et1.getText().toString()+"', '"+et2.getText().toString()+"','"+et3.getText().toString()+"','"+et4.getText().toString()+"')";
                    db.execSQL(sql);
                    Toast.makeText(getApplicationContext(), "Data Tersimpan...", Toast.LENGTH_SHORT).show();
                    finish();
                }
                BooksActivity.ba.RefreshList();
            }
        });
    }
}