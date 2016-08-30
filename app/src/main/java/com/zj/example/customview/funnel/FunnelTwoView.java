package com.zj.example.customview.funnel;


import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhengjiong on 16/1/20.
 */
@SuppressLint("NewApi")
public class FunnelTwoView extends View implements ValueAnimator.AnimatorUpdateListener {

    public static  float ANGLE_SCALE =1.85f;
    private int maxMoney;
    private float phaseX = 1f;
    private int textAlpha = 255;
    private float mLastX;
    private float mLastY;
    private float mLastWidth;
    private int maxHight;
    private Path mPath;
    private Paint mPaintLine;
    private Paint mPaintText;
    private ObjectAnimator xAnimator;
    private ObjectAnimator alphaAnimator;
    private List<Integer> mMoneys = new ArrayList<>();
    private ArrayList<Paint> mPaints = new ArrayList<>();
    private ArrayList<String> colors = new ArrayList<>();

    private ArrayList<Float> mPathHeights = new ArrayList<>();
    private ArrayList<Float> mPathAngleWidths = new ArrayList<>();

    private float mTotalHeight =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, getResources().getDisplayMetrics());
    private float maxWidth =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 330, getResources().getDisplayMetrics());
    private float maxLineH =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, getResources().getDisplayMetrics());
    private float minLineH =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0, getResources().getDisplayMetrics());

    private float startOffsetX =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
    private float startOffsetY =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());

    private float lineStartOffsetX =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
    private float textStartOffsetX =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 7, getResources().getDisplayMetrics());

    public FunnelTwoView(Context context) {
        this(context, null);
    }

    public FunnelTwoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FunnelTwoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public  void setData(List<Integer> moneys, int maxMoney,ArrayList<String> colors) {
        this.mMoneys = moneys;
        this.maxMoney = maxMoney;
        this.colors=colors;
        stopAnimator();
        init();
        calculate();
        getMaxHight();
        requestLayout();
    }

    private  void   getMaxHight() {
        float tempf=0.0f;
        for (int i=0;i<mMoneys.size();i++){
            if (i==0) {
                //高度进1法，而不是四舍五入法
                tempf = (startOffsetY + mPathHeights.get(i)) ;
            }else{
               tempf =  (tempf + mPathHeights.get(i)) ;
            }
        }
        maxHight=Math.round(tempf)+(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
       // maxHight=maxHight+ (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
                
    }

    private  void  calculate() {
        mPathHeights.clear();
        mPathAngleWidths.clear();
        for (int i = 0; i < mMoneys.size(); i++) {
            int money = mMoneys.get(i);
            float scale = (float) money / maxMoney;
            Float mPathHeight = mTotalHeight * scale * phaseX;
            if (mPathHeight < minLineH * phaseX) {
                mPathHeight = minLineH * phaseX;
            } else if (mPathHeight > maxLineH * phaseX) {
                mPathHeight = maxLineH * phaseX;
            }
            mPathHeights.add(i, mPathHeight);
            Float mPathAngleWidth = mPathHeight / ANGLE_SCALE;
            mPathAngleWidths.add(i, mPathAngleWidth);
        }
    }

    private void init() {
        mPaints.clear();
        for (int i = 0; i < mMoneys.size(); i++) {
            Paint mPaint = new Paint();
            mPaint.setColor(Color.parseColor(colors.get(i)));
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setDither(true);
            mPaint.setAntiAlias(true);
            mPaints.add(mPaint);
        }
        mPaintLine = new Paint();
        mPaintText = new Paint();
        mPaintLine.setColor(Color.parseColor("#A8ADB2"));
        mPaintLine.setStyle(Paint.Style.FILL);
        mPaintLine.setStrokeWidth(2);

        mPaintText.setColor(Color.parseColor("#A8ADB2"));
        mPaintText.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14, getResources().getDisplayMetrics()));
        mPaintText.setAntiAlias(true);
        mPaintText.setTextAlign(Paint.Align.LEFT);
    }



    private void draw2(Canvas canvas, Paint mPaint, Float mPathAngleWidth, Float mPathHeight,int i) {
        if (i==0){
            mLastX = startOffsetX;
            mLastY = startOffsetY;
            mLastWidth = maxWidth - startOffsetX;
        }
        mPath = new Path();
        mPath.moveTo(mLastX, mLastY);
        mPath.lineTo(mLastX + mLastWidth, mLastY);
        mPath.lineTo(mLastX + mLastWidth - mPathAngleWidth, mLastY + mPathHeight);
        mPath.lineTo(mLastX + mPathAngleWidth, mLastY + mPathHeight);
        mPath.close();
        canvas.drawPath(mPath, mPaint);
        mLastWidth = mLastWidth - 2 * mPathAngleWidth;
        mLastX = mLastX + mPathAngleWidth;
        mLastY = mLastY + mPathHeight;
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mMoneys.size(); i++) {
            draw2(canvas, mPaints.get(i), mPathAngleWidths.get(i), mPathHeights.get(i),i);
        }
    }

    public void animateY() {
        xAnimator = ObjectAnimator.ofFloat(this, "phaseX", 0, 1);
        xAnimator.setDuration(2500);
        xAnimator.addUpdateListener(this);
        xAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        xAnimator.start();

        alphaAnimator = ObjectAnimator.ofInt(this, "textAlpha", 0, 255);
        alphaAnimator.setDuration(2000);
        alphaAnimator.addUpdateListener(this);
        alphaAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        //alphaAnimator.start();
    }

    public void stopAnimator(){
        if (xAnimator!=null&&alphaAnimator!=null)
            if (xAnimator.isRunning()) {
                xAnimator.end();
                alphaAnimator.end();
            }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredLength(widthMeasureSpec, true),
                getMeasuredLength(heightMeasureSpec, false));
    }

    private int getMeasuredLength(int length, boolean isWidth) {
        int specMode = MeasureSpec.getMode(length);
        int specSize = MeasureSpec.getSize(length);
        int size;
        if (specMode == MeasureSpec.EXACTLY) {
            size = specSize;
        } else {
            size = maxHight;
                    //+(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
            Log.i("FunnelMain", "onMeasure:"+ size);
        }
        return size;
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
        Log.i("Animation", "onAnimationUpdate:动画更新...." );
        calculate();
        postInvalidate();
    }
    @Override
    protected void onAnimationEnd() {
        super.onAnimationEnd();
        Log.i("Animation", "onAnimationUpdate:动画结束....");
        maxHight=0;
    }

    public static float getAngleScale() {
        return ANGLE_SCALE;
    }

    public static void setAngleScale(float angleScale) {
        ANGLE_SCALE = angleScale;
    }
}