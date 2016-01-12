package com.scxh.android1503.animator;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.scxh.android1503.R;

public class AnimatorOneActivity extends Activity {
    private TextView mAnimatorTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator_one_layout);
        mAnimatorTxt = (TextView) findViewById(R.id.animator_textivew);
    }

    public void onAlphaClickListener(View v) {
//        ObjectAnimator animator = ObjectAnimator.ofFloat(mAnimatorTxt, "alpha", 1f, 0f, 1f);
//        animator.setDuration(5000);
//        animator.start();

        mAnimatorTxt.animate().alpha(0f).setDuration(3000).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mAnimatorTxt.animate().alpha(1f).setDuration(3000);
            }
        });

    }

    public void onRotationClickListener(View v) {
//        ObjectAnimator animator = ObjectAnimator.ofFloat(mAnimatorTxt, "rotation", 0f, 360f);
//        animator.setDuration(3000);
//        animator.start();


        mAnimatorTxt.animate().rotation(360f).setDuration(4000).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mAnimatorTxt.animate().rotation(0f);
            }
        });
    }

    public void onTranslationClickListener(View v) {
        float curTranslationX = mAnimatorTxt.getTranslationX();
        ObjectAnimator animator = ObjectAnimator.ofFloat(mAnimatorTxt, "translationX", curTranslationX, 500f, curTranslationX);
        animator.setDuration(5000);
        animator.start();
    }

    public void onScaleYClickListener(View v) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mAnimatorTxt, "scaleY", 1f, 3f, 1f);
        animator.setDuration(5000);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Toast.makeText(AnimatorOneActivity.this, "动画结束", Toast.LENGTH_SHORT).show();
            }
        });
        animator.start();
    }

    public void onCompaneClickListener(View v) {
        ObjectAnimator moveIn = ObjectAnimator.ofFloat(mAnimatorTxt, "translationX", -500f, 0f);
        ObjectAnimator rotate = ObjectAnimator.ofFloat(mAnimatorTxt, "rotation", 0f, 360f);
        ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(mAnimatorTxt, "alpha", 1f, 0f, 1f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(rotate).with(fadeInOut).before(moveIn);
        animSet.setDuration(5000);
        animSet.start();
    }

    public void onXMLCompaneClickListener(View v) {
        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.animator_alpha);
        animator.setTarget(mAnimatorTxt);
        animator.start();
    }
}
