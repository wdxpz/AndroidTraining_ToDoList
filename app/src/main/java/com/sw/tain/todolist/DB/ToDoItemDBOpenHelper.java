package com.sw.tain.todolist.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by home on 2016/12/29.
 */

public class ToDoItemDBOpenHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "ToDoItemDB";
    public static final int VERSION = 1;


    public static final String CREATE_TABLE_SQL = "create table "
            + ToDoItemDB.ToDoItemTable.NAME + "("
            + ToDoItemDB.ToDoItemTable.Col.KEY_ID + " integer primary key autoincrement, "
            + ToDoItemDB.ToDoItemTable.Col.TITLE  + " text no null, "
            + ToDoItemDB.ToDoItemTable.Col.DATE + " long)";

    public ToDoItemDBOpenHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if it exist " + ToDoItemDB.ToDoItemTable.NAME);
        onCreate(db);
    }
}
