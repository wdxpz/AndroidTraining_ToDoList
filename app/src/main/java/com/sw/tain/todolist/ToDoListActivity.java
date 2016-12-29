package com.sw.tain.todolist;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;


import com.sw.tain.todolist.DB.ToDoContentProvider;
import com.sw.tain.todolist.DB.ToDoItemDB;
import com.sw.tain.todolist.Model.ToDoItem;
import com.sw.tain.todolist.Model.ToDoListItemAdapter;

import java.util.ArrayList;
import java.util.Date;

public class ToDoListActivity extends FragmentActivity implements NewItemFragment.onNewItemCreateLisnter, LoaderManager.LoaderCallbacks<Cursor>{

    private ArrayList<ToDoItem> mArrayItems;
    private ToDoListItemAdapter mAdapter;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransction;
    private ItemListFragment mItemListFragment;
    private NewItemFragment mNewItemFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);


        mArrayItems = new ArrayList<>();
        mAdapter = new ToDoListItemAdapter(this, R.layout.list_item_to_do_list_item, mArrayItems);

//        mItemListFragment = new ItemListFragment();
//        mItemListFragment.setListAdapter(mAdapter);
//        mNewItemFragment = new NewItemFragment();

//        mFragmentManager = getSupportFragmentManager();
//        mFragmentTransction = mFragmentManager.beginTransaction();
//        mFragmentTransction.add(R.id.fragment_container_item_list, mItemListFragment);
//        mFragmentTransction.add(R.id.fragment_container_new_item, mNewItemFragment);
//        mFragmentTransction.commit();

        mItemListFragment = (ItemListFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_container_item_list);
        if(mItemListFragment!=null)
            mItemListFragment.setListAdapter(mAdapter);

        getSupportLoaderManager().initLoader(0, null, this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        getSupportLoaderManager().restartLoader(0, null, this);
    }

    @Override
    public void onNewItemCreate(String item) {
//        ToDoItem todoitem = new ToDoItem(item);
//        mArrayItems.add(todoitem);
//        mAdapter.notifyDataSetChanged();

        ContentValues values = new ContentValues();
        values.put(ToDoItemDB.ToDoItemTable.Col.TITLE, item);
        values.put(ToDoItemDB.ToDoItemTable.Col.DATE, System.currentTimeMillis());

        getContentResolver().insert(ToDoContentProvider.CONTENT_URI, values);

        getSupportLoaderManager().restartLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        CursorLoader loader = new CursorLoader(this,
                ToDoContentProvider.CONTENT_URI, null, null, null, null);

        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mArrayItems.clear();
        while(data.moveToNext()){
            long time = data.getLong(data.getColumnIndexOrThrow(ToDoItemDB.ToDoItemTable.Col.DATE));
            Date date = new Date(time);
            String title = data.getString(data.getColumnIndexOrThrow(ToDoItemDB.ToDoItemTable.Col.TITLE));
            ToDoItem item = new ToDoItem(date, title);
            mArrayItems.add(item);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
