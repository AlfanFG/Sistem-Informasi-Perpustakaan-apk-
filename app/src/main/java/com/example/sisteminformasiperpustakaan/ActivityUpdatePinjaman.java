package com.example.sisteminformasiperpustakaan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ActivityUpdatePinjaman extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    EditText et1,et2,et3,et4,et5;
    Button btnUpdate,btnCalendar1, btnCalendar2;
    Spinner status;
    String getExtra,edit,myString;
    String[] dissolve;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pinjaman);

        dbHelper = new DataHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        et1 = (EditText) findViewById(R.id.etPinjaman1);
        et1.setEnabled(false);
        et2 = (EditText) findViewById(R.id.etPinjaman2);
        et3 = (EditText) findViewById(R.id.etPinjaman3);
        et4 = (EditText) findViewById(R.id.etPinjaman4);
        et5 = (EditText) findViewById(R.id.etPinjaman5);

        btnCalendar1 = (Button) findViewById(R.id.btnTanggal1);
        btnCalendar2 = (Button) findViewById(R.id.btnTanggal2);
        btnUpdate = (Button) findViewById(R.id.btnUpdatePinjaman);

        dateFormatter = new SimpleDateFormat("dd-MM-YYYY", Locale.US);


        //Set Data for Each Form Field Retrieved From The Corresponding Table
        getExtra = getIntent().getStringExtra("id");
        dissolve = getExtra.split("-");
        cursor = db.rawQuery("SELECT * FROM tb_pinjaman WHERE Id_Pinjam = '" +dissolve[0] + "'", null);
        cursor.moveToFirst();
        if(cursor.getCount()>0){
            cursor.moveToPosition(0);
            et1.setText(cursor.getString(0));
            et2.setText(cursor.getString(1));
            et3.setText(cursor.getString(2));
            et4.setText(cursor.getString(3));
            et5.setText(cursor.getString(4));
            myString = cursor.getString(5);

            status = (Spinner) findViewById(R.id.spinner1);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.status, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            status.setAdapter(adapter);
            for(int i = 0; i<adapter.getCount();i++){
                if(myString.trim().equals(adapter.getItem(i).toString())){
                    status.setSelection(i);
                    break;
                }
            }
        }

        btnCalendar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = R.id.btnTanggal1;
                showDateDialog(id);
            }
        });

        btnCalendar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = R.id.btnTanggal2;
                showDateDialog(id);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                edit = et1.getText().toString();
                edit = et2.getText().toString();
                edit = et3.getText().toString();
                edit = et4.getText().toString();
                edit = et5.getText().toString();


                if(edit.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Kolom tidak boleh kosong...",Toast.LENGTH_SHORT).show();
                }else{
                    db.execSQL("UPDATE tb_pinjaman SET nama_peminjam='" + et2.getText().toString() + "', judul_buku='" + et3.getText().toString() + "', tanggal_pinjam='" + et4.getText().toString() +"',tanggal_kembali='"+et5.getText().toString()+"',status='"+status.getSelectedItem().toString()+"' WHERE Id_Pinjam='" + et1.getText().toString() + "'");
                    Toast.makeText(getApplicationContext(),"Data telah diubah...",Toast.LENGTH_SHORT).show();
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
}