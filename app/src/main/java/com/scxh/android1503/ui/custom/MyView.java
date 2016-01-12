package com.scxh.android1503.ui.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.scxh.android1503.R;
import com.scxh.android1503.util.Logs;

/**
 * 创建自定义View的两种方式
 * 方式一:直接继承View
 * 方式二:继承既有的一个子类(例如Button)
 * <p/>
 * 第一种方式:直接继承View
 * 实现步骤：
 * 1.创建一个view
 * 2.为你的view在资源标签下定义自设的属性
 * 3.在你的XML layout中指定属性值
 * 4.在运行时获取属性值
 * 5.把获取到的属性值应用在你的view上
 */
public class MyView extends View {
    private String content;//显示内容
    private boolean isShow;//是否显示
    private int mTextColor;//文本颜色
    private int backgrounddrawable;//背景颜色
    private int backgroundcolor;//背景颜色
    private int myTextSize;//文本字体大小
    private int mTextHeight = 20;//文本高度
    private Paint mTextPaint, mPiePaint, mShadowPaint;

    public MyView(Context context) {
        super(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);//初始化view
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);//初始化view
    }

    /**
     * 初始化
     *
     * @param context
     * @param attrs
     */
    private void init(Context context, AttributeSet attrs) {
        TypedArray typeArrray = null;
        try {
            /**
             * 通过context得到属性队列
             *  R.styleable.MyView是自定义的属性名字
             */
            typeArrray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyView, 0, 0);
            //通过TypedArray得到各个属性
            content = typeArrray.getString(R.styleable.MyView_content);
            isShow = typeArrray.getBoolean(R.styleable.MyView_isshow, false);
            mTextColor = typeArrray.getColor(R.styleable.MyView_textcolor, 0);
            backgroundcolor = typeArrray.getColor(R.styleable.MyView_mybackgroundcolor, 0);
            backgrounddrawable = typeArrray.getResourceId(R.styleable.MyView_mybackgrounddrawable, 0);
            Logs.w("backgroundres>>>>>>>" + backgrounddrawable + "");//getResourceId---2130837589
            //getColor-----17613

            myTextSize = typeArrray.getDimensionPixelSize(R.styleable.MyView_textsize, R.dimen.activity_horizontal_margin);
        } finally {
            //最后回收TypedArray
            typeArrray.recycle();
        }

/**
 * 得到三只画笔,颜色以及文本高度是通过属性队列里面得到的（用户设定 ）
 */
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextSize(mTextHeight);

        mPiePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPiePaint.setStyle(Paint.Style.FILL);
        mPiePaint.setTextSize(mTextHeight);

        mShadowPaint = new Paint(0);
        mShadowPaint.setColor(0xff101010);
        mShadowPaint.setMaskFilter(new BlurMaskFilter(8, BlurMaskFilter.Blur.NORMAL));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint mPaint = new Paint();
        mPaint.setColor(mTextColor);
        mPaint.setTextSize(myTextSize);

        Rect srcRt = new Rect();
        Rect endRt = new Rect();
        srcRt.set(40, 50, 400, 300);
        endRt.set(40, 50, 400, 300);
        if(backgrounddrawable!=0){
            canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), backgrounddrawable), srcRt, endRt, new Paint());
        }
        if(backgroundcolor!=0){
            canvas.drawColor(backgroundcolor);
        }
//        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), backgroundcolor), srcRt, endRt, new Paint());

        canvas.drawText(content, 50, 100, mPaint);
    }

    /**
     * onSizeChanged()，当你的view第一次被赋予一个大小时，或者你的view大小被更改时会被执行
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    /**
     * 精确的控制你的view的大小
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Logs.v("onTouchEvent >>>>>>>");
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Logs.v("按下事件");
                break;
            case MotionEvent.ACTION_UP:
                Logs.v("弹起事件");
                break;
            case MotionEvent.ACTION_MOVE:
                Logs.v("移动事件");
                break;
        }
        return true;

    }
}
