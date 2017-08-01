package yswl.com.testmvp.activity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;

import com.airbnb.lottie.LottieAnimationView;

import yswl.com.klibrary.base.MActivity;
import yswl.com.testmvp.MainActivity;
import yswl.com.testmvp.R;

public class WelcomeActivity extends MActivity {
    LottieAnimationView animationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Toolbar toolbar = findView(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("WELCOME");

        animationView = findView(R.id.logo);
        animationView.addAnimatorUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animationView.setAlpha((1f - (float) animation.getAnimatedValue()));
            }
        });

        animationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                startActivity(new Intent(WelcomeActivity.this, Main4Activity.class));
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        animationView.setProgress(0f);
        animationView.playAnimation();
    }

    @Override
    public void onStop() {
        super.onStop();
        animationView.cancelAnimation();
    }

}

