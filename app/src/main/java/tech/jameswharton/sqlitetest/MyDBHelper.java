package tech.jameswharton.sqlitetest;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDBHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "FilmLibrary.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "constellations";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "constelation_name";
    private static final String COLUMN_BRIGHTEST = "constelation_brighteststar";
    private static final String COLUMN_MAGNITUDE = "constelation_starmagnitude";
    private static final String COLUMN_DOMAIN = "constelation_domain";

    public MyDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_BRIGHTEST + " TEXT, " +
                COLUMN_MAGNITUDE + " TEXT, " +
                COLUMN_DOMAIN + " TEXT);";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addConstellation(String name, String domain, String starMagnitude, String brightestStar) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_BRIGHTEST, brightestStar);
        cv.put(COLUMN_MAGNITUDE, starMagnitude);
        cv.put(COLUMN_DOMAIN, domain);

        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            // Insertion failed
            Toast.makeText(context, "INSERT FAILURE", Toast.LENGTH_LONG).show();
        }
        else {
            // Insertion success
            Toast.makeText(context, "INSERT SUCCESS", Toast.LENGTH_LONG).show();
        }
    }

    public void updateData(String row_id, String name, String brightestStar, String starMagnitude, String domain) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_BRIGHTEST, brightestStar);
        cv.put(COLUMN_MAGNITUDE, starMagnitude);
        cv.put(COLUMN_DOMAIN, domain);


        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "UPDATE FAILURE", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(context, "UPDATE SUCCESS", Toast.LENGTH_LONG).show();
        }
    }

    public void deleteOneRecord(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if (result == -1 ) {
            Toast.makeText(context, "DELETE FAILURE", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(context,"DELETE SUCCESS", Toast.LENGTH_LONG).show();
        }
    }

    public void deleteAllRecords() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM `sqlite_sequence` WHERE `name` = '" + TABLE_NAME + "';");
        db.execSQL("DELETE FROM " + TABLE_NAME);

    }

    Cursor readData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}
