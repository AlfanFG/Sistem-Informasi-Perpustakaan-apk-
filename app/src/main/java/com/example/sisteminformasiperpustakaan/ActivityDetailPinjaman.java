package com.example.sisteminformasiperpustakaan;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityDetailPinjaman extends AppCompatActivity {
    TextView txt1,txt2,txt3,txt4,txt5,txt6;
    DataHelper dbHelper;
    String getExtra;
    String[] dissolve;
    protected Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pinjaman);

        //Instantiate DataHelper Class
        dbHelper = new DataHelper(this);
        //Instantiate SQLiteDatabase
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        //Assign .xml Components
        txt1 = (TextView) findViewById(R.id.etPinjaman1);
        txt2 = (TextView) findViewById(R.id.etPinjaman2);
        txt3 = (TextView) findViewById(R.id.etPinjaman3);
        txt4 = (TextView) findViewById(R.id.etPinjaman4);
        txt5 = (TextView) findViewById(R.id.etPinjaman5);
        txt6 = (TextView) findViewById(R.id.etPinjaman6);

        //get Id pinjaman
        getExtra = getIntent().getStringExtra("id");
        dissolve = getExtra.split("-");

        cursor = db.rawQuery("SELECT * FROM tb_pinjaman WHERE Id_Pinjam = '" +dissolve[0] + "'", null);
        cursor.moveToFirst();
        if(cursor.getCount()>0){
            cursor.moveToPosition(0);
            txt1.setText(cursor.getString(0));
            txt2.setText(cursor.getString(1));
            txt3.setText(cursor.getString(2));
            txt4.setText(cursor.getString(3));
            txt5.setText(cursor.getString(4));
            txt6.setText(cursor.getString(5));
        }
    }
}