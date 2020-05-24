package com.example.proyecto3cm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class IntroActivity extends AppCompatActivity {

    LinearLayout lyText;
    LinearLayout lyBtn;
    Animation uptodown;
    Animation downtoup;
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        lyText = (LinearLayout) findViewById(R.id.text_intro);
        lyBtn = (LinearLayout) findViewById(R.id.btn_intro);

        uptodown = AnimationUtils.loadAnimation(this, R.anim.up_to_down);
        lyText.setAnimation(uptodown);

        downtoup = AnimationUtils.loadAnimation(this, R.anim.down_to_up);
        lyBtn.setAnimation(downtoup);

        btnStart = (Button) findViewById(R.id.button);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                startActivity(intent);
                Animatoo.animateFade(IntroActivity.this);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        uptodown = AnimationUtils.loadAnimation(this, R.anim.up_to_down);
        lyText.setAnimation(uptodown);

        downtoup = AnimationUtils.loadAnimation(this, R.anim.down_to_up);
        lyBtn.setAnimation(downtoup);
    }
}
