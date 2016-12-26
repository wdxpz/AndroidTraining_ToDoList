package com.sw.tain.todolist.Model;

import android.icu.text.SimpleDateFormat;

import java.util.Date;

/**
 * Created by home on 2016/12/26.
 */

public class ToDoItem {
    String mTask;
    Date mDate;

    public ToDoItem(Date date, String task) {
        mDate = date;
        mTask = task;
    }

    public ToDoItem(String task) {
        mTask = task;
        mDate = new Date(System.currentTimeMillis());
    }


    public String getDate(){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
        return format.format(mDate);
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public String getTask() {
        return mTask;
    }

    public void setTask(String task) {
        mTask = task;
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
        String dataString = format.format(mDate);
        return "("+dataString+") " + mTask;
    }
}
