package com.sw.tain.todolist.DB;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

/**
 * Created by home on 2016/12/29.
 */

public class ToDoContentProvider extends ContentProvider {
    public static final Uri CONTENT_URI = Uri.parse("content://com.sw.tain.todolist.ToDoContentProvider/todoitems");

    public static final int SINGLE_ROW = 1;
    public static final int ALL_ROWS = 2;
    public static final UriMatcher mUriMatcher;

    static{
        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mUriMatcher.addURI("com.sw.tain.todolist.ToDoContentProvider", "todoitems/#", SINGLE_ROW );
        mUriMatcher.addURI("com.sw.tain.todolist.ToDoContentProvider", "todoitems", ALL_ROWS );
    }

    private ToDoItemDBOpenHelper mDBOpenHelper;
    private SQLiteDatabase mDatabase;

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch(mUriMatcher.match(uri)){
            case SINGLE_ROW:
                return "vnd.android.cursor.item/vnd.com.sw.tain.todolist";
            case ALL_ROWS:
                return "vnd.android.cursor.dir/vnd.com.sw.tain.todolist";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Override
    public boolean onCreate() {
        mDBOpenHelper = new ToDoItemDBOpenHelper(getContext());
        mDatabase = mDBOpenHelper.getWritableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(ToDoItemDB.ToDoItemTable.NAME);

        if(mUriMatcher.match(uri)==SINGLE_ROW){
            String id = uri.getPathSegments().get(1);
            queryBuilder.appendWhere(ToDoItemDB.ToDoItemTable.Col.KEY_ID + "=" + id);
        }
        Cursor cursor = queryBuilder.query(mDatabase, projection, selection, selectionArgs, null, null, sortOrder);

        return cursor;
    }



    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
       long id = mDatabase.insert(ToDoItemDB.ToDoItemTable.NAME, null, values);

        if(id>-1){
            Uri newUri = ContentUris.withAppendedId(CONTENT_URI, id);

            getContext().getContentResolver().notifyChange(uri, null);

            return newUri;
        }

        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        if(mUriMatcher.match(uri)==SINGLE_ROW){
            String id = uri.getPathSegments().get(1);
            selection = ToDoItemDB.ToDoItemTable.Col.KEY_ID + "=" + id
                    + (!TextUtils.isEmpty(selection)?
                    "AND (" + selection + ")" : "");
        }

        //要想返回删除的数量，必须指定一条where子句， 要删除所有的行并返回一个值，则传入”1“；
        if(selection==null) selection = "1";

        int num = mDatabase.delete(ToDoItemDB.ToDoItemTable.NAME, selection, selectionArgs);

        getContext().getContentResolver().notifyChange(uri, null);

        return num;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        if(mUriMatcher.match(uri)==SINGLE_ROW){
            String id = uri.getPathSegments().get(1);
            selection = ToDoItemDB.ToDoItemTable.Col.KEY_ID + "=" + id
                    + (!TextUtils.isEmpty(selection)?
                    "AND (" + selection + ")" : "");
        }

        int num = mDatabase.update(ToDoItemDB.ToDoItemTable.NAME, values, selection, selectionArgs);

        getContext().getContentResolver().notifyChange(uri, null);

        return num;
    }
}
