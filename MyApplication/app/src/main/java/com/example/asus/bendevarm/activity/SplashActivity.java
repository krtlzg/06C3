package com.example.asus.bendevarm.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.asus.bendevarm.R;

public class SplashActivity extends AppCompatActivity {


    ImageView imgYuzuk,imgMelek,imgBaslik;
    Boolean melekAnimBitti = false, yuzukAnimBitti = false, baslikAnimBitti = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imgMelek = (ImageView) findViewById(R.id.imgMelek);
        imgYuzuk = (ImageView) findViewById(R.id.imgYuzuk);
        imgBaslik = (ImageView) findViewById(R.id.imgBaslik);

        Animation animMelek = AnimationUtils.loadAnimation(this,R.anim.anim_melek);
        Animation animYuzuk = AnimationUtils.loadAnimation(this,R.anim.anim_yuzuk);
        Animation animBaslik = AnimationUtils.loadAnimation(this,R.anim.anim_baslik);
        imgMelek.startAnimation(animMelek);
        imgYuzuk.startAnimation(animYuzuk);
        imgBaslik.startAnimation(animBaslik);


        animMelek.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animKontrol(R.anim.anim_melek);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animYuzuk.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animKontrol(R.anim.anim_yuzuk);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animBaslik.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animKontrol(R.anim.anim_baslik);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }

    private void animKontrol(int animId)
    {
        switch (animId)
        {
            case R.anim.anim_melek:
                melekAnimBitti = true;
                break;
            case R.anim.anim_yuzuk:
                yuzukAnimBitti = true;
                break;
            case R.anim.anim_baslik:
                baslikAnimBitti = true;
                break;
        }

        if (melekAnimBitti && yuzukAnimBitti && baslikAnimBitti)
        {
            Intent i = new Intent(this,AcilisActivity.class);
            startActivity(i);
            finish();
        }
    }


    }

