package com.sw.tain.todolist.UIWidget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.TextView;

import com.sw.tain.todolist.R;

/**
 * Created by home on 2016/12/26.
 */

public class ToDoListItemView extends TextView {
    private Paint linePaint;
    private Paint marginPaint;
    private float margin;
    private int paperColor;

    public ToDoListItemView(Context context) {
        super(context);
        init();
    }

    public ToDoListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ToDoListItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ToDoListItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        Resources re =  getResources();

        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(re.getColor(R.color.notepad_lines));

        marginPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        marginPaint.setColor(re.getColor(R.color.notepad_margin));

        paperColor = re.getColor(R.color.notepad_paper);

        margin = re.getDimension(R.dimen.notepad_margin);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(paperColor);

        canvas.drawLine(0,0,0, getMeasuredHeight(), linePaint);
        canvas.drawLine(0, getMeasuredHeight(), getMeasuredWidth(), getMeasuredHeight(), linePaint);

        canvas.drawLine(margin, 0, margin, getMeasuredHeight(), marginPaint);

        canvas.save();
        canvas.translate(margin, 0);


        super.onDraw(canvas);
        canvas.restore();
    }
}
