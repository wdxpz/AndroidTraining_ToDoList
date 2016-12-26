package com.sw.tain.todolist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by home on 2016/12/22.
 */

public class NewItemFragment extends Fragment {


    private EditText mEditNewItem;
    private onNewItemCreateLisnter mNewItemCreateLisnter;

    public interface onNewItemCreateLisnter{
        public void onNewItemCreate(String item);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            mNewItemCreateLisnter = (onNewItemCreateLisnter)context;
        }catch(ClassCastException e){
            e.printStackTrace();
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_new_item, container, false);

        mEditNewItem = (EditText)v.findViewById(R.id.edit_todo_item);
        mEditNewItem.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(keyEvent.getAction()==KeyEvent.ACTION_DOWN && keyEvent.getKeyCode()==KeyEvent.KEYCODE_ENTER){
                    mNewItemCreateLisnter.onNewItemCreate(mEditNewItem.getText().toString());
                    mEditNewItem.setText("");
                    return true;
                }
                return false;
            }
        });

        return v;
    }
}
