package com.example.sisteminformasiperpustakaan;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityUpdateBuku extends AppCompatActivity {
    protected Cursor cursor;
    EditText et1,et2,et3,et4;
    Button btnUpdate;
    String edit;
    DataHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_buku);

        dbHelper = new DataHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        et1 = (EditText) findViewById(R.id.editText1);
        et1.setEnabled(false);
        et2 = (EditText) findViewById(R.id.editText2);
        et3 = (EditText) findViewById(R.id.editText3);
        et4 = (EditText) findViewById(R.id.editText4);

        btnUpdate = (Button) findViewById(R.id.btnUpdateBuku);

        String getExtra = getIntent().getStringExtra("id");
        String[] dissolve = getExtra.split("-");
        cursor = db.rawQuery("SELECT * FROM tb_buku WHERE id_buku = '" +dissolve[0] + "'", null);
        cursor.moveToFirst();
        if(cursor.getCount()>0){
            cursor.moveToPosition(0);
            et1.setText(cursor.getString(0));
            et2.setText(cursor.getString(1));
            et3.setText(cursor.getString(2));
            et4.setText(cursor.getString(3));
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                edit = et1.getText().toString();
                edit = et2.getText().toString();
                edit = et3.getText().toString();
                edit = et4.getText().toString();
                if(edit.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Kolom tidak boleh kosong...",Toast.LENGTH_SHORT).show();
                }else{
                    db.execSQL("UPDATE tb_buku SET judul_buku='" + et2.getText().toString() + "', penulis='" + et3.getText().toString() + "', tahun_terbit='" + et4.getText().toString() + "' WHERE id_buku='" + et1.getText().toString() + "'");
                    Toast.makeText(getApplicationContext(),"Data telah diubah...",Toast.LENGTH_SHORT).show();
                    finish();
                }
                BooksActivity.ba.RefreshList();
            }
        });
    }
}