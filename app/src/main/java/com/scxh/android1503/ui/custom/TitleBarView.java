package com.scxh.android1503.ui.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scxh.android1503.R;

public class TitleBarView extends RelativeLayout implements View.OnClickListener {
    private String mTitle;
    private String mLeft;
    private String mRight;
    private LayoutInflater mInfalter;
    //利用接口回调的方式进行事件响应
    private OnTitleBarClickListener mOnClickListener; //声明接口

    /**
     * 定义接口
     */
    public interface OnTitleBarClickListener {
        public void onTitleBarClick(View v);
    }

    /**
     * 注册接口
     *
     * @param l
     */
    public void registerOnClickListener(OnTitleBarClickListener l) {
        mOnClickListener = l;
    }

    public TitleBarView(Context context) {
        super(context);
    }

    public TitleBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TitleBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attrs) {
        TypedArray a = null;
        try {
            a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TitleBarView, 0, 0);
            mTitle = a.getString(R.styleable.TitleBarView_title_text);

            mLeft = a.getString(R.styleable.TitleBarView_left_text);
            mRight = a.getString(R.styleable.TitleBarView_right_text);
        } finally {
            a.recycle();
        }

        mInfalter = LayoutInflater.from(context);
        initView();
    }

    public void initView() {
        View v = mInfalter.inflate(R.layout.custom_title_layout, this, true);

        if (mLeft != null) {
            TextView leftTxt = (TextView) v.findViewById(R.id.title_left_txt);
            leftTxt.setText(mLeft);
            leftTxt.setOnClickListener(this);
        }

        if (mRight != null) {
            TextView rightTxt = (TextView) v.findViewById(R.id.title_right_txt);
            rightTxt.setText(mRight);
            rightTxt.setOnClickListener(this);
        }

        if (mTitle != null) {
            TextView titleTxt = (TextView) v.findViewById(R.id.title_title_txt);
            titleTxt.setText(mTitle);
        }
    }

    @Override
    public void onClick(View v) {
        if (mOnClickListener != null) {
            mOnClickListener.onTitleBarClick(v);
        }
    }
}
