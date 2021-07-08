package com.example.sisteminformasiperpustakaan;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "buku.db";
    private static final int DATABASE_VERSION = 1;


    public DataHelper(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
//        context.deleteDatabase(DATABASE_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE tb_buku (id_buku text primary key, judul_buku text null, penulis text null,tahun_terbit text null);";
        Log.d("Data","onCreate: " + sql);
        db.execSQL(sql);


        sql = "INSERT INTO tb_buku (id_buku, judul_buku, penulis,tahun_terbit) VALUES ('9789793062792', 'Laskar Pelangi','Andrea Hirata','2008');";
        db.execSQL(sql);

        sql = "INSERT INTO tb_buku (id_buku, judul_buku, penulis,tahun_terbit) VALUES ('9793062924', 'Sang Pemimpi','Andrea Hirata','2006');";
        db.execSQL(sql);


        sql = "CREATE TABLE tb_user" +
                " (username text null, " +
                "password text null,"+
                "nama text null);";

        Log.d("Data","onCreate: " + sql);
        db.execSQL(sql);

        sql = "INSERT INTO tb_user " + "(username, password,nama) VALUES ( 'rf123','1234qwer','Rafli');";
        db.execSQL(sql);

        sql = "CREATE TABLE tb_pinjaman (Id_Pinjam text primary key , nama_peminjam text null, judul_buku text null,tanggal_pinjam text null,tanggal_kembali text null,status text null);";
        Log.d("Data","onCreate: " + sql);
        db.execSQL(sql);


        sql = "INSERT INTO tb_pinjaman (Id_Pinjam, nama_peminjam, judul_buku,tanggal_pinjam,tanggal_kembali,status) VALUES ('P0001', 'Pli','Laskar Pelangi','15-06-2021','17-06-2021','Selesai');";
        db.execSQL(sql);

        sql = "INSERT INTO tb_pinjaman (Id_Pinjam, nama_peminjam, judul_buku,tanggal_pinjam,tanggal_kembali,status) VALUES ('P0002', 'Jack','Sang Pemimpi','17-06-2021','','Belum Selesai');";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

