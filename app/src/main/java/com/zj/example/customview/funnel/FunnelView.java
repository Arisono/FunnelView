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
    
    public static final float ANGLE_SCALE = 3.0f;
    private List<Integer> mMoneys = new ArrayList<>();
    private int maxMoney;

    private float phaseX = 1f;
    private int textAlpha = 255;

    private Paint mPaint1;
    private Paint mPaint2;
    private Paint mPaint3;
    private Paint mPaint4;
    private Paint mPaint5;
    private Paint mPaint6;
    private Paint mPaint7;
    private Paint mPaint8;
    private Paint mPaint9;
    private Paint mPaint10;

    private Paint mPaintLine;
    private Paint mPaintText;


    private Path mPath1;
    private Path mPath2;
    private Path mPath3;
    private Path mPath4;
    private Path mPath5;

    private Path mPath6;
    private Path mPath7;
    private Path mPath8;
    private Path mPath9;
    private Path mPath10;


    private float mTotalHeight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 240, getResources().getDisplayMetrics());

    private float mPath1Height;
    private float mPath2Height;
    private float mPath3Height;
    private float mPath4Height;
    private float mPath5Height;
    private float mPath6Height;
    private float mPath7Height;
    private float mPath8Height;
    private float mPath9Height;
    private float mPath10Height;

    private float mPath1AngleWidth;
    private float mPath2AngleWidth;
    private float mPath3AngleWidth;
    private float mPath4AngleWidth;
    private float mPath5AngleWidth;
    private float mPath6AngleWidth;
    private float mPath7AngleWidth;
    private float mPath8AngleWidth;
    private float mPath9AngleWidth;
    private float mPath10AngleWidth;




    private float mPath3LineStartX;
    private float mPath3LineStartY;

    private float mPath4LineStartX;
    private float mPath4LineStartY;

    private float mPath5LineStartX;
    private float mPath5LineStartY;

    private float maxWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, getResources().getDisplayMetrics());
    private float maxLineH = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 90, getResources().getDisplayMetrics());
    private float minLineH = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());

    private float startOffsetX = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics());
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
                    mPath1AngleWidth = mPath1Height / ANGLE_SCALE;
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
                case 5:
                    mPath6Height = mTotalHeight * scale * phaseX;
                    if (mPath6Height < minLineH * phaseX) {
                        mPath6Height = minLineH * phaseX;
                    }else if (mPath6Height > maxLineH * phaseX) {
                        mPath6Height = maxLineH * phaseX;
                    }

                    mPath6AngleWidth = mPath6Height / ANGLE_SCALE;
                    break;
                case 6:
                    mPath7Height = mTotalHeight * scale * phaseX;
                    if (mPath7Height < minLineH * phaseX) {
                        mPath7Height = minLineH * phaseX;
                    }else if (mPath7Height > maxLineH * phaseX) {
                        mPath7Height = maxLineH * phaseX;
                    }

                    mPath7AngleWidth = mPath7Height / ANGLE_SCALE;
                    break;
                case 7:
                    mPath8Height = mTotalHeight * scale * phaseX;
                    if (mPath8Height < minLineH * phaseX) {
                        mPath8Height = minLineH * phaseX;
                    }else if (mPath8Height > maxLineH * phaseX) {
                        mPath8Height = maxLineH * phaseX;
                    }

                    mPath8AngleWidth = mPath8Height / ANGLE_SCALE;
                    break;
                case 8:
                    mPath9Height = mTotalHeight * scale * phaseX;
                    if (mPath9Height < minLineH * phaseX) {
                        mPath9Height = minLineH * phaseX;
                    }else if (mPath9Height > maxLineH * phaseX) {
                        mPath9Height = maxLineH * phaseX;
                    }

                    mPath9AngleWidth = mPath9Height / ANGLE_SCALE;
                    break;
                case 9:
                    mPath10Height = mTotalHeight * scale * phaseX;
                    if (mPath10Height < minLineH * phaseX) {
                        mPath10Height = minLineH * phaseX;
                    }else if (mPath10Height > maxLineH * phaseX) {
                        mPath10Height = maxLineH * phaseX;
                    }

                    mPath10AngleWidth = mPath10Height / ANGLE_SCALE;
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
        mPaint6 = new Paint();
        mPaint7 = new Paint();
        mPaint8 = new Paint();
        mPaint9 = new Paint();
        mPaint10 = new Paint();
        mPaintLine = new Paint();
        mPaintText = new Paint();

        mPaint1.setColor(Color.parseColor("#F87A27"));
        mPaint1.setStyle(Paint.Style.FILL);
        mPaint1.setDither(true); //设定是否使用图像抖动处理，会使绘制出来的图片颜色更加平滑和饱满，图像更加清晰
        mPaint1.setAntiAlias(true);

        mPaint2.setColor(Color.parseColor("#FB9E36"));
        mPaint2.setStyle(Paint.Style.FILL);
        mPaint2.setDither(true);
        mPaint2.setAntiAlias(true);

        mPaint3.setColor(Color.parseColor("#FBC12E"));
        mPaint3.setStyle(Paint.Style.FILL);
        mPaint3.setDither(true);
        mPaint3.setAntiAlias(true);

        mPaint4.setColor(Color.parseColor("#A1D644"));
        mPaint4.setStyle(Paint.Style.FILL);
        mPaint4.setDither(true);
        mPaint4.setAntiAlias(true);

        mPaint5.setColor(Color.parseColor("#26BEF4"));
        mPaint5.setStyle(Paint.Style.FILL);
        mPaint5.setDither(true);
        mPaint5.setAntiAlias(true);


        mPaint6.setColor(Color.parseColor("#FF9900"));
        mPaint6.setStyle(Paint.Style.FILL);
        mPaint6.setDither(true);
        mPaint6.setAntiAlias(true);


        mPaint7.setColor(Color.parseColor("#26BEF4"));
        mPaint7.setStyle(Paint.Style.FILL);
        mPaint7.setDither(true);
        mPaint7.setAntiAlias(true);


        mPaint8.setColor(Color.parseColor("#993366"));
        mPaint8.setStyle(Paint.Style.FILL);
        mPaint8.setDither(true);
        mPaint8.setAntiAlias(true);


        mPaint9.setColor(Color.parseColor("#C0C0C0"));
        mPaint9.setStyle(Paint.Style.FILL);
        mPaint9.setDither(true);
        mPaint9.setAntiAlias(true);


        mPaint10.setColor(Color.parseColor("#FFCC99"));
        mPaint10.setStyle(Paint.Style.FILL);
        mPaint10.setDither(true);
        mPaint10.setAntiAlias(true);



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
        mLastY = startOffsetY ;
        mLastWidth=maxWidth-startOffsetX;

        mPath1 = new Path();
        mPath1.moveTo(mLastX, startOffsetY);
        mPath1.lineTo(mLastX+mLastWidth , startOffsetY);
        mPath1.lineTo(mLastX+mLastWidth-mPath1AngleWidth, mLastY+ mPath1Height);
        mPath1.lineTo(mLastX + mPath1AngleWidth, mLastY+ mPath1Height);

        mPath1.close();
        canvas.drawPath(mPath1, mPaint1);
        mLastWidth=mLastWidth-2*mPath1AngleWidth;
        mLastX=mLastX+mPath1AngleWidth;
        mLastY=mLastY+mPath1Height  ;

    }


    private void draw2(Canvas canvas) {

        mPath2 = new Path();

        mPath2.moveTo(mLastX,  mLastY);
        mPath2.lineTo(mLastX+mLastWidth, mLastY);
        mPath2.lineTo(mLastX +mLastWidth- mPath2AngleWidth, mLastY + mPath2Height);
        mPath2.lineTo(mLastX+mPath2AngleWidth, mLastY + mPath2Height);
        mPath2.close();
        canvas.drawPath(mPath2, mPaint2);

        mLastWidth = mLastWidth - mPath2AngleWidth  - mPath2AngleWidth;
        mLastX = mLastX + mPath2AngleWidth;
        mLastY = mLastY + mPath2Height;
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

        mLastWidth = mLastWidth - 2 * mPath4AngleWidth;//最新长度
        mLastX = mLastX + mPath4AngleWidth;//第四个点的x坐标
        mLastY = mLastY + mPath4Height;//第四个点的y坐标
        mPath4LineStartX = mLastX + mLastWidth +  mPath4AngleWidth / 2;
        mPath4LineStartY = mLastY - mPath4Height / 2;
    }

    private void draw5(Canvas canvas) {
        mPath5 = new Path();

        mPath5.moveTo(mLastX,  mLastY);
        mPath5.lineTo(mLastX + mLastWidth, mLastY);
        mPath5.lineTo(mLastX +mLastWidth-mPath5AngleWidth, mLastY + mPath5Height);
        mPath5.lineTo(mLastX+mPath5AngleWidth, mLastY + mPath5Height);
        mPath5.close();
        canvas.drawPath(mPath5, mPaint5);


        //mLastWidth = maxWidth - startOffsetX;
        //mLastX = mLastX;
//        mLastY = mLastY + mPath5Height;
//        mPath5LineStartX = mLastX + mLastWidth;
//        mPath5LineStartY = mLastY - mPath5Height / 2;

        mLastWidth = mLastWidth - 2 * mPath5AngleWidth;//最新长度
        mLastX = mLastX + mPath5AngleWidth;//第四个点的x坐标
        mLastY = mLastY + mPath5Height;//第四个点的y坐标
    }



    private void draw6(Canvas canvas) {
        mPath6 = new Path();
        mPath6.moveTo(mLastX,  mLastY);
        mPath6.lineTo(mLastX + mLastWidth, mLastY);
        mPath6.lineTo(mLastX +mLastWidth-mPath6AngleWidth, mLastY + mPath6Height);
        mPath6.lineTo(mLastX+mPath6AngleWidth, mLastY + mPath6Height);
        mPath6.close();
        canvas.drawPath(mPath6, mPaint6);
        mLastWidth = mLastWidth - 2 * mPath6AngleWidth;//最新长度
        mLastX = mLastX + mPath6AngleWidth;//第四个点的x坐标
        mLastY = mLastY + mPath6Height;//第四个点的y坐标
    }



    private void draw7(Canvas canvas) {
        mPath7 = new Path();
        mPath7.moveTo(mLastX,  mLastY);
        mPath7.lineTo(mLastX + mLastWidth, mLastY);
        mPath7.lineTo(mLastX +mLastWidth-mPath7AngleWidth, mLastY + mPath7Height);
        mPath7.lineTo(mLastX+mPath7AngleWidth, mLastY + mPath7Height);
        mPath7.close();
        canvas.drawPath(mPath7, mPaint7);
        mLastWidth = mLastWidth - 2 * mPath7AngleWidth;//最新长度
        mLastX = mLastX + mPath7AngleWidth;//第四个点的x坐标
        mLastY = mLastY + mPath7Height;//第四个点的y坐标
    }


    private void draw8(Canvas canvas) {
        mPath8 = new Path();
        mPath8.moveTo(mLastX,  mLastY);
        mPath8.lineTo(mLastX + mLastWidth, mLastY);
        mPath8.lineTo(mLastX +mLastWidth-mPath8AngleWidth, mLastY + mPath8Height);
        mPath8.lineTo(mLastX+mPath8AngleWidth, mLastY + mPath8Height);
        mPath8.close();
        canvas.drawPath(mPath8, mPaint8);
        mLastWidth = mLastWidth - 2 * mPath8AngleWidth;//最新长度
        mLastX = mLastX + mPath8AngleWidth;//第四个点的x坐标
        mLastY = mLastY + mPath8Height;//第四个点的y坐标
    }



    private void draw9(Canvas canvas) {
        mPath9 = new Path();
        mPath9.moveTo(mLastX,  mLastY);
        mPath9.lineTo(mLastX + mLastWidth, mLastY);
        mPath9.lineTo(mLastX +mLastWidth-mPath9AngleWidth, mLastY + mPath9Height);
        mPath9.lineTo(mLastX+mPath9AngleWidth, mLastY + mPath9Height);
        mPath9.close();
        canvas.drawPath(mPath9, mPaint9);
        mLastWidth = mLastWidth - 2 * mPath9AngleWidth;//最新长度
        mLastX = mLastX + mPath9AngleWidth;//第四个点的x坐标
        mLastY = mLastY + mPath9Height;//第四个点的y坐标
    }


    private void draw10(Canvas canvas) {
        mPath10 = new Path();
        mPath10.moveTo(mLastX,  mLastY);
        mPath10.lineTo(mLastX + mLastWidth, mLastY);
        mPath10.lineTo(mLastX +mLastWidth-mPath10AngleWidth, mLastY + mPath10Height);
        mPath10.lineTo(mLastX+mPath10AngleWidth, mLastY + mPath10Height);
        mPath10.close();
        canvas.drawPath(mPath10, mPaint10);
        mLastWidth = mLastWidth - 2 * mPath10AngleWidth;//最新长度
        mLastX = mLastX + mPath10AngleWidth;//第四个点的x坐标
        mLastY = mLastY + mPath10Height;//第四个点的y坐标
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


        draw6(canvas);
        draw7(canvas);
        draw8(canvas);
        draw9(canvas);
        draw10(canvas);

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


    private void drawText1(Canvas canvas) {
        Paint.FontMetrics fontMetrics = mPaintText.getFontMetrics();

        float fontTotalHeight = fontMetrics.bottom - fontMetrics.top;

        float offY = fontTotalHeight / 2 - fontMetrics.bottom;
        //System.out.println("offY=" +offY);
        //float newY = baseY + offY;

        mPaintText.setAlpha(textAlpha);
        canvas.drawText("初期沟通(10%)", maxWidth + textStartOffsetX, mLastY - mPath1Height / 2 + offY, mPaintText);
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

}
