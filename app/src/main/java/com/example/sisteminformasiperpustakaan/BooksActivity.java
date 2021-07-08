package com.example.sisteminformasiperpustakaan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class BooksActivity extends AppCompatActivity {
    String [] daftar;
    ListView ListView01;
    Menu menu;
    protected Cursor cursor;
    DataHelper dbcenter;
    Button btnInput;
    public static BooksActivity ba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        btnInput = (Button) findViewById(R.id.button2);

        btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BooksActivity.this,ActivityInput.class);
                startActivity(i);
            }
        });
        ba = this;
        dbcenter = new DataHelper(this);
        RefreshList();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(BooksActivity.this,HomeActivity.class);
        startActivity(i);
    }

    public void RefreshList(){
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM tb_buku",null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for(int cc = 0; cc<cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(0).toString()+"-"+cursor.getString(1);
        }
        ListView01 = (ListView) findViewById(R.id.listView1);
        ListView01.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1,daftar));
        ListView01.setSelected(true);
        ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String selection = daftar[position];
                final CharSequence[] dialogItem = {"Lihat Data", "Update Data","Hapus Data"};
                AlertDialog.Builder builder = new AlertDialog.Builder(BooksActivity.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item){
                            case 0:
                                // Detail Data Buku
                                Intent i = new Intent(getApplicationContext(), DetailBukuActivity.class);
                                i.putExtra("idBuku",selection);
                                startActivity(i);
                                break;
                            case 1:
                                //Update Data Buku
                                Intent in = new Intent(getApplicationContext(), ActivityUpdateBuku.class);
                                in.putExtra("id", selection);
                                startActivity(in);
                                break;
                            case 2:
                                //Hapus Data Buku
                                SQLiteDatabase db = dbcenter.getWritableDatabase();
                                String getNim = selection;
                                String[] dissolve = getNim.split("-");
                                db.execSQL("DELETE FROM tb_buku WHERE id_buku = '" + dissolve[0] + "'");
                                RefreshList();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
    }
}