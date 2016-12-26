package com.sw.tain.todolist;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;


import java.util.ArrayList;

public class ToDoListActivity extends FragmentActivity implements NewItemFragment.onNewItemCreateLisnter{

    private ArrayList<String> mArrayItems;
    private ArrayAdapter<String> mAdapter;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransction;
    private ItemListFragment mItemListFragment;
    private NewItemFragment mNewItemFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);


        mArrayItems = new ArrayList<>();
        mAdapter = new ArrayAdapter<>(this, R.layout.list_item_to_do_list_item, mArrayItems);

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

    }

    @Override
    public void onNewItemCreate(String item) {
        mArrayItems.add(item);
        mAdapter.notifyDataSetChanged();
    }
}
