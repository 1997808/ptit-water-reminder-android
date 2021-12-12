package com.example.ptit_water_reminder.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.ptit_water_reminder.models.Cup;
import com.example.ptit_water_reminder.models.Notification;
import com.example.ptit_water_reminder.models.User;
import com.example.ptit_water_reminder.models.WaterLog;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "SQLite";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "waterReminder";

    // Table name
    private static final String TABLE_USER = "user";
    private static final String TABLE_NOTIFICATION = "notification";
    private static final String TABLE_CUP = "cup";
    private static final String TABLE_WATER_LOG = "waterLog";

    // Common column names
    private static final String KEY_ID ="id";
    private static final String KEY_USER_ID ="user_id";

    // USER Table
    private static final String KEY_USER_NAME ="name";
    private static final String KEY_USER_EMAIL = "email";
    private static final String KEY_USER_PASSWORD = "password";
    private static final String KEY_USER_WATER_TARGET = "waterTarget";

    // CUP Table
    private static final String KEY_CUP_AMOUNT = "amount";

    // WATER_LOG Table
    private static final String KEY_WATER_LOG_AMOUNT ="amount";
    private static final String KEY_WATER_LOG_CREATE_AT = "createAt";

    // NOTIFICATION Table
    private static final String KEY_NOTIFICATION_NOTE ="note";
    private static final String KEY_NOTIFICATION_TIME = "time";

    // Table Create Statements
    private static final String CREATE_TABLE_USER = "CREATE TABLE "
            + TABLE_USER + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_USER_NAME
            + " TEXT," + KEY_USER_EMAIL + " TEXT," + KEY_USER_PASSWORD
            + " TEXT," + KEY_USER_WATER_TARGET + " INTEGER" + ")";

//    private static final String CREATE_TABLE_CUP = "CREATE TABLE " + TABLE_CUP
//            + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_USER_ID + " INTEGER,"
//            + KEY_CUP_AMOUNT + " INTEGER" + ")";

    private static final String CREATE_TABLE_CUP = "CREATE TABLE " + TABLE_CUP
            + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_CUP_AMOUNT + " INTEGER" + ")";

    private static final String CREATE_TABLE_WATER_LOG = "CREATE TABLE "
            + TABLE_WATER_LOG + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_WATER_LOG_AMOUNT + " INTEGER," + KEY_WATER_LOG_CREATE_AT + " DATETIME" + ")";

    private static final String CREATE_TABLE_NOTIFICATION = "CREATE TABLE "
            + TABLE_NOTIFICATION + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_NOTIFICATION_NOTE + " TEXT," + KEY_NOTIFICATION_TIME + " DATETIME" + ")";

    public MyDatabaseHelper(Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_CUP);
        db.execSQL(CREATE_TABLE_WATER_LOG);
        db.execSQL(CREATE_TABLE_NOTIFICATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTIFICATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUP);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WATER_LOG);

        // Create tables again
        onCreate(db);
    }


    // If Cup table has no data
    // default, Insert 2 records.
    public void createDefaultCupsIfNeed()  {
        int count = this.getCupsCount();
        if(count ==0 ) {
            Cup cup1 = new Cup(300);
            Cup cup2 = new Cup(500);
            this.addCup(cup1);
            this.addCup(cup2);
        }
    }


    public void addUser(User user) {
        Log.i(TAG, "MyDatabaseHelper.addNote ... " + user.getName());
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_USER_NAME, user.getName());
        values.put(KEY_USER_WATER_TARGET, user.getWaterTarget());

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public void addCup(Cup cup) {
        Log.i(TAG, "MyDatabaseHelper.addNote ... " + cup.getCupAmount());
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CUP_AMOUNT, cup.getCupAmount());

        // Inserting Row
        db.insert(TABLE_CUP, null, values);
        db.close();
    }

    public void addWaterLog(WaterLog waterLog) {
        Log.i(TAG, "MyDatabaseHelper.addNote ... " + waterLog.getAmount());
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_WATER_LOG_AMOUNT, waterLog.getAmount());
        values.put(KEY_WATER_LOG_CREATE_AT, getDateTime());

        // Inserting Row
        db.insert(TABLE_WATER_LOG, null, values);
        db.close();
    }

//    public void addNotification(Notification notification) {
//        Log.i(TAG, "MyDatabaseHelper.addNote ... " + notification.getNote());
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(KEY_NOTIFICATION_NOTE, notification.getNote());
//        values.put(KEY_NOTIFICATION_TIME, notification.getTime());
//
//        // Inserting Row
//        db.insert(TABLE_WATER_LOG, null, values);
//        db.close();
//    }


//    public Note getNote(int id) {
//        Log.i(TAG, "MyDatabaseHelper.getNote ... " + id);
//
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.query(TABLE_NOTIFICATION, new String[] { KEY_ID,
//                        COLUMN_NOTE_TITLE, COLUMN_NOTE_CONTENT }, KEY_ID + "=?",
//                new String[] { String.valueOf(id) }, null, null, null, null);
//        if (cursor != null)
//            cursor.moveToFirst();
//
//        Note note = new Note(Integer.parseInt(cursor.getString(0)),
//                cursor.getString(1), cursor.getString(2));
//        // return note
//        return note;
//    }


//    public List<Note> getAllNotes() {
//        Log.i(TAG, "MyDatabaseHelper.getAllNotes ... " );
//
//        List<Note> noteList = new ArrayList<Note>();
//        // Select All Query
//        String selectQuery = "SELECT  * FROM " + TABLE_NOTIFICATION;
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        // looping through all rows and adding to list
//        if (cursor.moveToFirst()) {
//            do {
//                Note note = new Note();
//                note.setNoteId(Integer.parseInt(cursor.getString(0)));
//                note.setNoteTitle(cursor.getString(1));
////                note.setNoteContent(cursor.getString(2));
//                // Adding note to list
//                noteList.add(note);
//            } while (cursor.moveToNext());
//        }
//
//        // return note list
//        return noteList;
//    }

    public int getCupsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CUP;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }


//    public int updateNote(Note note) {
//        Log.i(TAG, "MyDatabaseHelper.updateNote ... "  + note.getNoteTitle());
//
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_NOTE_TITLE, note.getNoteTitle());
//        values.put(COLUMN_NOTE_CONTENT, note.getNoteContent());
//
//        // updating row
//        return db.update(TABLE_NOTIFICATION, values, KEY_ID + " = ?",
//                new String[]{String.valueOf(note.getNoteId())});
//    }

//    public void deleteNote(Note note) {
//        Log.i(TAG, "MyDatabaseHelper.updateNote ... " + note.getNoteTitle() );
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_NOTIFICATION, KEY_ID + " = ?",
//                new String[] { String.valueOf(note.getNoteId()) });
//        db.close();
//    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
