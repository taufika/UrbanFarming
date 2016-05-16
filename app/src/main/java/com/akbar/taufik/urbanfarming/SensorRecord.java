package com.akbar.taufik.urbanfarming;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Asus on 07/05/2016.
 */
public class SensorRecord extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "readingRecord";
    private static final String RECORD_TABLE = "recordTanaman";
    private static final String ID_RECORD = "id";
    private static final String ID_TANAMAN = "id_tanaman";
    private static final String TANGGAL_TANAMAN = "datetime";
    private static final String READING = "hasilBaca";

    public SensorRecord(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " +
                RECORD_TABLE + " (" +
                ID_RECORD + " INTEGER PRIMARY KEY, " +
                ID_TANAMAN + " INTEGER, " +
                TANGGAL_TANAMAN + " DATETIME, " +
                READING + " TEXT )";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + RECORD_TABLE);
        onCreate(db);
    }

    public void addRecord(int id_tanaman, float airTemp, float airHum, float soilTemp, float soilHum){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ID_TANAMAN, id_tanaman);
        values.put(TANGGAL_TANAMAN, "DATETIME('now')");
        values.put(READING, airTemp + ";" + airHum + ";" + soilTemp + ";" + soilHum);

        db.insert(RECORD_TABLE, null, values);
        db.close();
    }

    public String getReading(int id_tanaman) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        String reading = "";

        cursor = db.rawQuery("SELECT " + READING + " FROM " + RECORD_TABLE + " WHERE " + ID_TANAMAN + "=?", new String[] {id_tanaman + ""});

        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            reading = cursor.getString(cursor.getColumnIndex(READING));
        } else {
            reading = "0;0;0;0";
        }
        cursor.close();
        return reading;
    }
}
