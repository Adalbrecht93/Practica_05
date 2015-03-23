package com.example.alberto.practica_05;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Alberto on 23/03/2015.
 */
public class baseDeDatos extends SQLiteOpenHelper {

    public static final  int DB_V = 1;
    public static final String DB_N = "Notas.db";
    public static final String DB_TN = "Notas";
    public static final String DB_ID = BaseColumns._ID;
    public static final String DB_TITLE = "Titulo";
    public static final String DB_NOTE = "Nota";

    public static final String DB_R_ID = "List_ID";
    public static final String DB_R_TITLE = "List_Title";
    public static final String DB_R_NOTE = "List_Nota";

    public baseDeDatos(Context context) {
        super(context, DB_N, null, DB_V);
    }

    public baseDeDatos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ DB_TN + "("
                + DB_ID + " INTEGER PRIMARY KEY, "
                + DB_TITLE + " TEXT, "
                + DB_NOTE + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void noteAdd(String nota, String texto) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.putNull(DB_ID);
        values.put(DB_TITLE, nota);
        values.put(DB_NOTE, texto);

        db.insert(DB_TN, null, values);
    }

    public void noteUpdate(long id, String title, String body){
        SQLiteDatabase db = getWritableDatabase();
        // New value for one column
        ContentValues values = new ContentValues();
        values.put(DB_TITLE, title);
        values.put(DB_NOTE, body);
        // Which row to update, based on the ID
        String selection = DB_ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(id) };
        int count = db.update(
                DB_TN,
                values,
                selection,
                selectionArgs);
    }

    public Cursor noteRead(){
        SQLiteDatabase db = getReadableDatabase();

        String[] columns = {DB_ID, DB_TITLE, DB_NOTE};

        return db.query(DB_TN, columns, null, null, null, null, null);
    }


}
