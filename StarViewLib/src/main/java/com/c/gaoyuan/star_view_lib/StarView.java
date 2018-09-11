package com.c.gaoyuan.star_view_lib;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with Android Studio.
 * Email: fargao0932@gmail.com
 * User: GaoYuan
 * Data: 2018/7/30
 * Time: 15:34
 * Desc: StarView
 */
public class StarView extends View {

    private int mStarCount;

    private int mCheckStarCount;

    private int mStarDrawable;

    private int mCheckStarDrawable;

    private float mStarHeight;

    private float mStarWidth;

    private float mStarHorizontalSpace;

    private List<StarViewBean> mStarViewBeans;

    private StoreItemOnClickListener mStoreItemOnClickListener;

    public StarView(Context context) {
        this(context, null);
    }

    public StarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        @SuppressLint("Recycle")
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.StarView, defStyleAttr, 0);
        mStarCount = typedArray.getInt(R.styleable.StarView_starCount, 5);
        mCheckStarCount = typedArray.getInt(R.styleable.StarView_checkStarCount, 0);
        mCheckStarDrawable = typedArray.getResourceId(R.styleable.StarView_checkStarCount, R.drawable.search_result_brands_list_icon_star_y);
        mStarDrawable = typedArray.getResourceId(R.styleable.StarView_checkStarCount, R.drawable.search_result_brands_list_icon_star_g);
        mStarWidth = typedArray.getDimensionPixelSize(R.styleable.StarView_starWidth, 20);
        mStarHeight = typedArray.getDimensionPixelSize(R.styleable.StarView_starHeight, 20);
        mStarHorizontalSpace = typedArray.getDimensionPixelSize(R.styleable.StarView_starHorizontalSpace, 20);
        typedArray.recycle();

        mStarViewBeans = new ArrayList<>();
    }

    public void createView(Canvas canvas) {

        if (mStarCount <= 0) return;

        float space = 0f;

        for (int i = 0; i < mStarCount; i++) {

            @SuppressLint("DrawAllocation")
            Bitmap bitmap;

            if (i < mCheckStarCount) {
                bitmap = BitmapFactory.decodeResource(getResources(), mCheckStarDrawable);
            } else {
                bitmap = BitmapFactory.decodeResource(getResources(), mStarDrawable);
            }

            bitmap = Bitmap.createScaledBitmap(bitmap, (int) mStarWidth, (int) mStarHeight, true);

            StarViewBean starViewBean = new StarViewBean();
            starViewBean.setWidth((int) space);
            starViewBean.setHeight(getHeight());
            mStarViewBeans.add(starViewBean);

            canvas.drawBitmap(bitmap, space, 0, null);
            space = space + mStarHorizontalSpace + mStarWidth;
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (mStoreItemOnClickListener == null) return super.onTouchEvent(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                float x = event.getX();

                for (int i = 0; i < mStarViewBeans.size(); i++) {

                    float sx = mStarViewBeans.get(i).getWidth();

                    if (x >= sx && x <= sx + mStarWidth) {
                        mStoreItemOnClickListener.onClick(i);
                        setCheckStarCount(i + 1);
                        refreshView();
                        return true;
                    }
                }
                break;
        }

        return true;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            float viewWidth = mStarWidth * mStarCount + (mStarHorizontalSpace * (mStarCount - 1));
            width = (int) (getPaddingLeft() + viewWidth + getPaddingRight());
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            float viewHeight = mStarHeight;
            height = (int) (getPaddingTop() + viewHeight + getPaddingBottom());
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        createView(canvas);
    }

    public void setStarCount(int starCount) {
        this.mStarCount = starCount;
    }

    public void setStarHeight(float starHeight) {
        this.mStarHeight = starHeight;
    }

    public void setStarWidth(float starWidth) {
        this.mStarWidth = starWidth;
    }

    public void setStarHorizontalSpace(float starHorizontalSpace) {
        this.mStarHorizontalSpace = starHorizontalSpace;
    }

    public void setCheckStarCount(int checkStarCount) {
        this.mCheckStarCount = checkStarCount;
    }

    public void setStarDrawable(@DrawableRes int starDrawable) {
        this.mStarDrawable = starDrawable;
    }

    public void setCheckStarDrawable(@DrawableRes int checkStarDrawable) {
        this.mCheckStarDrawable = checkStarDrawable;
    }

    public void setStoreItemOnClickListener(StoreItemOnClickListener storeItemOnClickListener) {
        this.mStoreItemOnClickListener = storeItemOnClickListener;
    }

    public int getCheckStarCount() {
        return this.mCheckStarCount;
    }

    public void refreshView() {
        invalidate();
    }
}
