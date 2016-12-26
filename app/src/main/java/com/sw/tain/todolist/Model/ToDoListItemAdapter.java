package com.sw.tain.todolist.Model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sw.tain.todolist.R;
import com.sw.tain.todolist.UIWidget.ToDoListItemView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by home on 2016/12/26.
 */

public class ToDoListItemAdapter extends ArrayAdapter<ToDoItem> {

    private final int mResource;
    private TextView mDateTextView;
    private ToDoListItemView mItemTextView;

    public ToDoListItemAdapter(Context context, int resource, List<ToDoItem> objects) {
        super(context, resource, objects);

        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout todoview;
        if(convertView==null){
//            todoview = LayoutInflater.from(getContext()).inflate(R.layout.list_item_to_do_list_item, parent, false);
            todoview = new LinearLayout(getContext());
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(mResource, todoview, true);
        }else
            todoview = (LinearLayout)convertView;

        mDateTextView = (TextView)todoview.findViewById(R.id.item_date);
        mItemTextView = (ToDoListItemView)todoview.findViewById(R.id.id_todolistitem);

        ToDoItem item = getItem(position);
        mDateTextView.setText(item.getDate());
        mItemTextView.setText(item.getTask());

        return todoview;
    }
}
