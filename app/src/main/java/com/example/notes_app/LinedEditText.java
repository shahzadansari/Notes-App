package com.example.notes_app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class LinedEditText extends androidx.appcompat.widget.AppCompatEditText {

    private Rect mRect;
    private Paint mPaint;

    // we need this constructor for LayoutInflater
    public LinedEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        mRect = new Rect();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(1);
        mPaint.setColor(getResources().getColor(R.color.colorGray)); // Color of the lines on paper

    }

    @Override
    protected void onDraw(Canvas canvas) {

        // get the height of the view
        int height = ((View) this.getParent()).getHeight();

        int lineHeight = getLineHeight();
        int numberOfLines = height / lineHeight;

        Rect r = mRect;
        Paint paint = mPaint;

        int baseline = getLineBounds(0, r);

        for (int i = 0; i < numberOfLines; i++) {

            canvas.drawLine(r.left, baseline, r.right, baseline, paint);

            baseline += lineHeight;
        }

        super.onDraw(canvas);
    }
}
