package com.akbar.taufik.urbanfarming;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 21/04/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "tanamanManager";

    private static final String TABLE_TANAMAN = "tanaman";

    private static final String ID_TANAMAN = "id";
    private static final String NAMA_TANAMAN = "nama";
    private static final String TANGGAL_TANAMAN = "tanggal";

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_TANAMAN + " (" +
                ID_TANAMAN + " INTEGER PRIMARY KEY, " +
                NAMA_TANAMAN + " TEXT, " +
                TANGGAL_TANAMAN + " TEXT )";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_TANAMAN);
        onCreate(db);
    }

    public void addTanaman(Tanaman tanaman){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAMA_TANAMAN, tanaman.namaTanaman);
        values.put(TANGGAL_TANAMAN, tanaman.tanggalTanaman);

        db.insert(TABLE_TANAMAN, null, values);
        db.close();
    }

    public Tanaman[] readAllTanaman(){
        List<Tanaman> listTanaman = new ArrayList<Tanaman>();

        String selectQuery = "SELECT * FROM " + TABLE_TANAMAN;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        ListTanaman.arrayTanaman.clear();

        if(cursor.moveToFirst()){
            do {
                Tanaman tanaman = new Tanaman(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
                listTanaman.add(tanaman);
                ListTanaman.arrayTanaman.add(tanaman);
            } while (cursor.moveToNext());
        }
        db.close();
        return listTanaman.toArray(new Tanaman[listTanaman.size()]);
    }

    public void deleteTanaman(Tanaman tanaman){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TANAMAN, ID_TANAMAN + " = ?", new String[]{ String.valueOf(tanaman.id) });
        db.close();
    }
}
