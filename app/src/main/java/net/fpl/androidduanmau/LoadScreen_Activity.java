package net.fpl.androidduanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class LoadScreen_Activity extends AppCompatActivity {
    TextView tv1;
    ImageView iv1;

    int delay = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_screen);
        tv1 = findViewById(R.id.tv_tittle);
        iv1 = findViewById(R.id.iv_may);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_scr1);
        tv1.setAnimation(animation);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_scr1);
        iv1.setAnimation(animation1);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LoadScreen_Activity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, delay);
    }
}