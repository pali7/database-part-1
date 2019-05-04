package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

public class DbHelper extends SQLiteOpenHelper {
    public static final String TAG = MainActivity.class.getSimpleName();
    private static final String DATABASE_NAME = "myapp";
    private static final int DATABASE_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Notes.CREATE_TABLE);
        onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Notes.TABLE_NAME);
    }


    public List<Notes> getAllNotes() {

        List<Notes> noteList = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        String selectQuery = "SELECT * FROM " + Notes.TABLE_NAME;

        Cursor cursor = database.rawQuery(selectQuery, null);

        Log.d(TAG, String.valueOf(cursor.getCount()));
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Notes notes = new Notes();
                    notes.setId(cursor.getInt(cursor.getColumnIndex(Notes.COLUMN_ID)));
                    notes.setTitle(cursor.getString(cursor.getColumnIndex(Notes.COLUMN_TITLE)));
                    notes.setDescription(cursor.getString(cursor.getColumnIndex(Notes.COLUMN_DESCRIPTION)));
                    noteList.add(notes);
                }
                while (cursor.moveToNext());

            }
        }
        database.close();
        return noteList;

    }

    public Notes getNote (long id){
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.query(Notes.TABLE_NAME, new String[] {
                Notes.COLUMN_ID,Notes.COLUMN_TITLE, Notes.COLUMN_DESCRIPTION
        },Notes.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null,null,null,null);

        if (cursor != null)
            cursor.moveToFirst();
        Notes note=new Notes(
                cursor.getInt(cursor.getColumnIndex(Notes.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Notes.COLUMN_TITLE)),
                cursor.getString(cursor.getColumnIndex(Notes.COLUMN_DESCRIPTION))
        );

        cursor.close();
        return note;

    }

    public void insertNote(Notes notes) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Notes.COLUMN_TITLE, notes.getTitle());
        values.put(Notes.COLUMN_DESCRIPTION, notes.getDescription());

        db.insert(Notes.TABLE_NAME, null, values);
        db.close();
    }
}
