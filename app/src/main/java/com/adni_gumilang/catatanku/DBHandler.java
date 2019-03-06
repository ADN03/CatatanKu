package com.adni_gumilang.catatanku;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "db_notes";
    public static final String TABLE_NAME = "table_notes";

    public static final String ROW_ID = "_id";
    public static final String row_nama_notes = "Nama_notes";
    public static final String row_tanggal_notes = "Tanggal_notes";
    public static final String row_keterangan = "Keterangan";

    private SQLiteDatabase db;


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, 2);
        db = getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + "(" + ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + row_nama_notes + " TEXT," + row_tanggal_notes + " TEXT," + row_keterangan + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    //MENGAMBIL SEMUA DATA SQLITE
    public Cursor semuaData() {
        Cursor cur = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return cur;
    }

    //MENGAMBIL DATA BERDASARKAN ID
    public Cursor satuData(long id) {
        Cursor cur = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + ROW_ID + "=" + id, null);
        return cur;
    }

    //MENAMBAH DATA
    public void tambahData(ContentValues values) {
        db.insert(TABLE_NAME, null, values);

    }

    //MENGUBAH DATA BERDASARKAN ID
    public void editData(ContentValues values, long id) {
        db.update(TABLE_NAME, values, ROW_ID + "=" + id, null);
    }


    //HAPUS DATA BERDASARKAN ID
    public void hapusData(long id) {
        db.delete(TABLE_NAME, ROW_ID + "=" + id, null);
    }

}




