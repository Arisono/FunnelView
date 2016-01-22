package com.zj.example.customview.funnel;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhengjiong on 16/1/20.
 */
public class FunnelView extends View implements ValueAnimator.AnimatorUpdateListener{
    public static final float ANGLE_SCALE = 1.6f;
    private List<Integer> mMoneys = new ArrayList<>();
    private int maxMoney;

    private float phaseX = 1f;
    private int textAlpha = 255;

    private Paint mPaint1;
    private Paint mPaint2;
    private Paint mPaint3;
    private Paint mPaint4;
    private Paint mPaint5;
    private Paint mPaintLine;
    private Paint mPaintText;


    private Path mPath1;
    private Path mPath2;
    private Path mPath3;
    private Path mPath4;
    private Path mPath5;

    private float mTotalHeight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 180, getResources().getDisplayMetrics());

    private float mPath1Height;
    private float mPath2Height;
    private float mPath3Height;
    private float mPath4Height;
    private float mPath5Height;

    private float mPath2AngleWidth;
    private float mPath3AngleWidth;
    private float mPath4AngleWidth;
    private float mPath5AngleWidth;

    private float mPath3LineStartX;
    private float mPath3LineStartY;

    private float mPath4LineStartX;
    private float mPath4LineStartY;

    private float mPath5LineStartX;
    private float mPath5LineStartY;

    private float maxWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 230, getResources().getDisplayMetrics());
    private float maxLineH = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 42, getResources().getDisplayMetrics());
    private float minLineH = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());

    private float startOffsetX = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15, getResources().getDisplayMetrics());
    private float startOffsetY = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());

    private float lineStartOffsetX = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
    private float textStartOffsetX = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 7, getResources().getDisplayMetrics());

    private float mLastX;
    private float mLastY;
    private float mLastWidth;

    public FunnelView(Context context) {
        this(context, null);
    }

    public FunnelView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FunnelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    public void setData(List<Integer> moneys, int maxMoney){
        this.mMoneys = moneys;
        this.maxMoney = maxMoney;

        calculate();

        //int averageHeight = (int) (mTotalHeight / 5);

        invalidate();
    }

    private void calculate() {
        for (int i = 0; i < mMoneys.size(); i++) {
            int money = mMoneys.get(i);
            float scale = (float)money / maxMoney;
            switch (i) {
                case 0:
                    mPath1Height = mTotalHeight * scale * phaseX;
                    if (mPath1Height < minLineH * phaseX) {
                        mPath1Height = minLineH * phaseX;
                    }else if (mPath1Height > maxLineH * phaseX) {
                        mPath1Height = maxLineH * phaseX;
                    }

                    //System.out.println("mPath1Height=" + mPath1Height + " ,phaseX=" + phaseX);
                    break;
                case 1:
                    mPath2Height = mTotalHeight * scale * phaseX;
                    if (mPath2Height < minLineH * phaseX) {
                        mPath2Height = minLineH * phaseX;
                    } else if (mPath2Height > maxLineH * phaseX) {
                        mPath2Height = maxLineH * phaseX;
                    }

                    mPath2AngleWidth = mPath2Height / ANGLE_SCALE;

                    //System.out.println("mPath2Height=" + mPath2Height);
                    break;
                case 2:
                    mPath3Height = mTotalHeight * scale * phaseX;
                    if (mPath3Height < minLineH * phaseX) {
                        mPath3Height = minLineH * phaseX;
                    }else if (mPath3Height > maxLineH * phaseX) {
                        mPath3Height = maxLineH * phaseX;
                    }

                    mPath3AngleWidth = mPath3Height / ANGLE_SCALE;

                    //System.out.println("mPath3Height=" + mPath3Height);
                    break;
                case 3:
                    mPath4Height = mTotalHeight * scale * phaseX;
                    if (mPath4Height < minLineH * phaseX) {
                        mPath4Height = minLineH * phaseX;
                    }else if (mPath4Height > maxLineH * phaseX) {
                        mPath4Height = maxLineH * phaseX;
                    }

                    mPath4AngleWidth = mPath4Height / ANGLE_SCALE;

                    //System.out.println("mPath4Height=" + mPath4Height);
                    break;
                case 4:
                    mPath5Height = mTotalHeight * scale * phaseX;
                    if (mPath5Height < minLineH * phaseX) {
                        mPath5Height = minLineH * phaseX;
                    }else if (mPath5Height > maxLineH * phaseX) {
                        mPath5Height = maxLineH * phaseX;
                    }

                    mPath5AngleWidth = mPath5Height / ANGLE_SCALE;

                    //System.out.println("mPath5Height=" + mPath5Height);
                    break;
            }

        }
    }

    private void init() {
        mPaint1 = new Paint();
        mPaint2 = new Paint();
        mPaint3 = new Paint();
        mPaint4 = new Paint();
        mPaint5 = new Paint();
        mPaintLine = new Paint();
        mPaintText = new Paint();

        mPaint1.setColor(Color.parseColor("#F87A27"));
        mPaint1.setStyle(Paint.Style.FILL);

        mPaint2.setColor(Color.parseColor("#FB9E36"));
        mPaint2.setStyle(Paint.Style.FILL);

        mPaint3.setColor(Color.parseColor("#FBC12E"));
        mPaint3.setStyle(Paint.Style.FILL);

        mPaint4.setColor(Color.parseColor("#A1D644"));
        mPaint4.setStyle(Paint.Style.FILL);

        mPaint5.setColor(Color.parseColor("#26BEF4"));
        mPaint5.setStyle(Paint.Style.FILL);

        mPaintLine.setColor(Color.parseColor("#A8ADB2"));
//        mPaintLine.setColor(Color.parseColor("#A8ADB2"));
        mPaintLine.setStyle(Paint.Style.FILL);
        mPaintLine.setStrokeWidth(2);

        mPaintText.setColor(Color.parseColor("#A8ADB2"));
        mPaintText.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14, getResources().getDisplayMetrics()));
        mPaintText.setAntiAlias(true);
        mPaintText.setTextAlign(Paint.Align.LEFT);
    }

    private void draw1(Canvas canvas) {
        mLastX = startOffsetX;
        mLastY = startOffsetY + mPath1Height;

        mPath1 = new Path();

        mPath1.moveTo(startOffsetX, startOffsetY);
        mPath1.lineTo(maxWidth, startOffsetY);
        mPath1.lineTo(maxWidth, mLastY);
        mPath1.lineTo(startOffsetX, mLastY);

        mPath1.close();
        canvas.drawPath(mPath1, mPaint1);


    }

    private void drawText1(Canvas canvas) {
        Paint.FontMetrics fontMetrics = mPaintText.getFontMetrics();

        float fontTotalHeight = fontMetrics.bottom - fontMetrics.top;

        float offY = fontTotalHeight / 2 - fontMetrics.bottom;
        //System.out.println("offY=" +offY);
        //float newY = baseY + offY;

        mPaintText.setAlpha(textAlpha);
        System.out.println("textAlpha=" + textAlpha);
        canvas.drawText("初期沟通(10%)", maxWidth + textStartOffsetX, mLastY - mPath1Height / 2 + offY, mPaintText);
    }

    private void draw2(Canvas canvas) {

        mPath2 = new Path();

        mPath2.moveTo(mLastX,  mLastY);
        mPath2.lineTo(maxWidth, mLastY);
        mPath2.lineTo(maxWidth - mPath2AngleWidth, mLastY + mPath2Height);
        mPath2.lineTo(startOffsetX + mPath2AngleWidth, mLastY + mPath2Height);
        mPath2.close();
        canvas.drawPath(mPath2, mPaint2);

        mLastWidth = maxWidth - mPath2AngleWidth - startOffsetX - mPath2AngleWidth;
        mLastX = startOffsetX + mPath2AngleWidth;
        mLastY = mLastY + mPath2Height;
    }

    private void drawText2(Canvas canvas) {
        Paint.FontMetrics fontMetrics = mPaintText.getFontMetrics();

        float fontTotalHeight = fontMetrics.bottom - fontMetrics.top;

        float offY = fontTotalHeight / 2 - fontMetrics.bottom;
        //float newY = baseY + offY;

        canvas.drawText("立项跟踪(10%)", maxWidth + textStartOffsetX, mLastY - mPath2Height / 2 + offY, mPaintText);
    }

    private void drawLine3(Canvas canvas) {
        canvas.drawLine(mPath3LineStartX + lineStartOffsetX, mPath3LineStartY, maxWidth, mPath3LineStartY, mPaintLine);
    }

    private void drawText3(Canvas canvas) {
        Paint.FontMetrics fontMetrics = mPaintText.getFontMetrics();

        float fontTotalHeight = fontMetrics.bottom - fontMetrics.top;

        float offY = fontTotalHeight / 2 - fontMetrics.bottom;
        //float newY = baseY + offY;

        canvas.drawText("呈报方案(10%)", maxWidth + textStartOffsetX, mLastY - mPath3Height / 2 + offY, mPaintText);
    }

    private void drawLine4(Canvas canvas){
        canvas.drawLine(mPath4LineStartX + lineStartOffsetX, mPath4LineStartY, maxWidth, mPath4LineStartY, mPaintLine);
    }

    private void drawText4(Canvas canvas) {
        Paint.FontMetrics fontMetrics = mPaintText.getFontMetrics();

        float fontTotalHeight = fontMetrics.bottom - fontMetrics.top;

        float offY = fontTotalHeight / 2 - fontMetrics.bottom;
        //float newY = baseY + offY;

        canvas.drawText("商务谈判(10%)", maxWidth + textStartOffsetX, mLastY - mPath4Height / 2 + offY, mPaintText);
    }

    private void drawLine5(Canvas canvas){
        canvas.drawLine(mPath5LineStartX + lineStartOffsetX, mPath5LineStartY, maxWidth, mPath5LineStartY, mPaintLine);
    }

    private void drawText5(Canvas canvas) {
        Paint.FontMetrics fontMetrics = mPaintText.getFontMetrics();

        float fontTotalHeight = fontMetrics.bottom - fontMetrics.top;

        float offY = fontTotalHeight / 2 - fontMetrics.bottom;
        //float newY = baseY + offY;

        canvas.drawText("赢单(10%)", maxWidth + textStartOffsetX, mLastY - mPath5Height / 2 + offY, mPaintText);
    }

    private void draw3(Canvas canvas) {

        mPath3 = new Path();

        mPath3.moveTo(mLastX,  mLastY);
        mPath3.lineTo(mLastX + mLastWidth, mLastY);
        mPath3.lineTo(mLastX + mLastWidth - mPath3AngleWidth, mLastY + mPath3Height);
        mPath3.lineTo(mLastX + mPath3AngleWidth, mLastY + mPath3Height);
        mPath3.close();
        canvas.drawPath(mPath3, mPaint3);

        mLastWidth = mLastWidth - mPath3AngleWidth - mPath3AngleWidth;
        mLastX = mLastX + mPath3AngleWidth;
        mLastY = mLastY + mPath3Height;
        mPath3LineStartX = mLastX + mLastWidth +  mPath3AngleWidth / 2;
        mPath3LineStartY = mLastY - mPath3Height / 2;
    }

    private void draw4(Canvas canvas) {

        mPath4 = new Path();

        mPath4.moveTo(mLastX,  mLastY);
        mPath4.lineTo(mLastX + mLastWidth, mLastY);
        mPath4.lineTo(mLastX + mLastWidth - mPath4AngleWidth, mLastY + mPath4Height);
        mPath4.lineTo(mLastX + mPath4AngleWidth, mLastY + mPath4Height);
        mPath4.close();
        canvas.drawPath(mPath4, mPaint4);

        mLastWidth = mLastWidth - 2 * mPath4AngleWidth;
        mLastX = mLastX + mPath4AngleWidth;
        mLastY = mLastY + mPath4Height;
        mPath4LineStartX = mLastX + mLastWidth +  mPath4AngleWidth / 2;
        mPath4LineStartY = mLastY - mPath4Height / 2;
    }

    private void draw5(Canvas canvas) {
        mPath5 = new Path();

        mPath5.moveTo(mLastX,  mLastY);
        mPath5.lineTo(mLastX + mLastWidth, mLastY);
        mPath5.lineTo(mLastX + mLastWidth, mLastY + mPath5Height);
        mPath5.lineTo(mLastX, mLastY + mPath5Height);
        mPath5.close();
        canvas.drawPath(mPath5, mPaint5);

        //mLastWidth = maxWidth - startOffsetX;
        //mLastX = mLastX;
        mLastY = mLastY + mPath5Height;
        mPath5LineStartX = mLastX + mLastWidth;
        mPath5LineStartY = mLastY - mPath5Height / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        draw1(canvas);
        drawText1(canvas);


        draw2(canvas);
        drawText2(canvas);

        draw3(canvas);
        drawLine3(canvas);
        drawText3(canvas);

        draw4(canvas);
        drawLine4(canvas);
        drawText4(canvas);

        draw5(canvas);
        drawLine5(canvas);
        drawText5(canvas);

    }

    public void animateY(){
        ObjectAnimator xAnimator = ObjectAnimator.ofFloat(this, "phaseX", 0, 1);
        xAnimator.setDuration(2000);
        xAnimator.addUpdateListener(this);
        xAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        xAnimator.start();

        ObjectAnimator alphaAnimator = ObjectAnimator.ofInt(this, "textAlpha", 0, 255);
        alphaAnimator.setDuration(2000);
        //alphaAnimator.addUpdateListener(this);
        alphaAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        alphaAnimator.start();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /*int specMode = MeasureSpec.getMode(heightMeasureSpec);
        int specSize = MeasureSpec.getSize(heightMeasureSpec);

        int height = 0;
        if (specMode == MeasureSpec.EXACTLY) {
            height = specSize;
        } else {
            height = (int) mLastY;
        }
        System.out.println("onMeasure mLastY=" + mLastY);

        setMeasuredDimension(MeasureSpec.getMode(widthMeasureSpec), height);*/
    }

    public float getPhaseX() {
        return phaseX;
    }

    public void setPhaseX(float phaseX) {
        this.phaseX = phaseX;
    }

    public int getTextAlpha() {
        return textAlpha;
    }

    public void setTextAlpha(int textAlpha) {
        this.textAlpha = textAlpha;
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        calculate();
        postInvalidate();
    }
}
