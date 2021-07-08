package com.example.sisteminformasiperpustakaan;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class DetailBukuActivity extends AppCompatActivity {
    TextView textView1,textView2,textView3,textView4;
    DataHelper dbHelper;
    String getExtra;
    String[] dissolve;
    protected Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_buku);

        dbHelper = new DataHelper(this);

        //Assign XML components to Java
        textView1 = (TextView) findViewById(R.id.txtView2);
        textView2 = (TextView) findViewById(R.id.txtView4);
        textView3 = (TextView) findViewById(R.id.txtView6);
        textView4 = (TextView) findViewById(R.id.txtView8);

        //Get Readable Database
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        getExtra = getIntent().getStringExtra("idBuku");
        dissolve = getExtra.split("-");

        cursor = db.rawQuery("SELECT * FROM tb_buku WHERE id_buku = '" +dissolve[0] + "'", null);
        cursor.moveToFirst();
        if(cursor.getCount()>0){
            cursor.moveToPosition(0);
            textView1.setText(cursor.getString(0));
            textView2.setText(cursor.getString(1));
            textView3.setText(cursor.getString(2));
            textView4.setText(cursor.getString(3));
        }
    }
}