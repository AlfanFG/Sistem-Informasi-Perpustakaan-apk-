package com.example.sisteminformasiperpustakaan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ActivityInputPinjaman extends AppCompatActivity {
    EditText et1,et2,et3,et4,et5;
    DataHelper dbHelper;
    Spinner status;
    String edit,sql;
    Button btnSimpan,btnTanggal1,btnTanggal2;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_pinjaman);

        dbHelper = new DataHelper(this);

        //Assign edit text component to java
        et1 = (EditText) findViewById(R.id.etPinjaman1);
        et2 = (EditText) findViewById(R.id.etPinjaman2);
        et3 = (EditText) findViewById(R.id.etPinjaman3);
        et4 = (EditText) findViewById(R.id.etPinjaman4);
        et4.setFocusable(false);
        et5 = (EditText) findViewById(R.id.etPinjaman5);
        et5.setFocusable(false);

        //Assign spinner component to java
        status = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.status, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        status.setAdapter(adapter);

        //Assign Button component to java
        btnTanggal1 = (Button) findViewById(R.id.btnTanggal1);
        btnTanggal2 = (Button) findViewById(R.id.btnTanggal2);
        btnSimpan = (Button) findViewById(R.id.btnSimpanPinjaman);

        //Create Date Formatter
        dateFormatter = new SimpleDateFormat("dd-MM-YYYY", Locale.US);

        // Open Date Dialog for Renting books
        btnTanggal1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id;
                id = R.id.btnTanggal1;
                showDateDialog(id);
            }
        });

        // Open Date Dialog for Returning books
        btnTanggal2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = R.id.btnTanggal2;
                showDateDialog(id);
            }
        });

        //Save Renting Data
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                edit = et1.getText().toString();
                edit = et2.getText().toString();
                edit = et3.getText().toString();
                edit = et4.getText().toString();
                edit = et5.getText().toString();
                edit = status.getSelectedItem().toString();

                if(edit.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Kolom todak boleh kosong...", Toast.LENGTH_SHORT).show();
                }else{
                    sql = "INSERT INTO tb_pinjaman (Id_Pinjam, nama_peminjam, judul_buku,tanggal_pinjam,tanggal_kembali,status) VALUES ('"+et1.getText().toString()+"', '"+et2.getText().toString()+"','"+et3.getText().toString()+"','"+et4.getText().toString()+"','"+et5.getText().toString()+"','"+status.getSelectedItem().toString()+"')";
                    db.execSQL(sql);
                    Toast.makeText(getApplicationContext(), "Data Tersimpan...", Toast.LENGTH_SHORT).show();
                    finish();
                }
                PinjamActivity.da.RefreshList();
            }
        });

    }
    public void showDateDialog(int id){
        Calendar newCalendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year,month,dayOfMonth);
                if(id == R.id.btnTanggal1){
                    et4.setText(dateFormatter.format(newDate.getTime()));
                }else{
                    et5.setText(dateFormatter.format(newDate.getTime()));
                }

            }
        },newCalendar.get(Calendar.YEAR),newCalendar.get(Calendar.MONTH),newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
//    public void generateID(){
//        String query;
//
//        query =
//    }
}