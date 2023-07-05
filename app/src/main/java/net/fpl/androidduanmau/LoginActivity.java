package net.fpl.androidduanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import net.fpl.androidduanmau.DAO.ThuThuDAO;

public class LoginActivity extends AppCompatActivity {
    ImageView iv1, iv2;
    EditText ed_user, ed_pass;
    Button dangnhap, huy;
    ThuThuDAO thuThuDAO;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        iv1 = findViewById(R.id.iv1);
        iv2 = findViewById(R.id.iv2);
        ed_user = findViewById(R.id.ed_name);
        ed_pass = findViewById(R.id.ed_pass);
        dangnhap = findViewById(R.id.btn_dangnhap);
        huy = findViewById(R.id.btn_huy);
        checkBox = findViewById(R.id.chk_remember);
        Animation animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.anim_login);
        iv1.setAnimation(animation);
        Animation animation1 = AnimationUtils.loadAnimation(getBaseContext(), R.anim.anim_login);
        iv2.setAnimation(animation1);
        Animation animation2 = AnimationUtils.loadAnimation(getBaseContext(), R.anim.anim_scr1);
        ed_user.setAnimation(animation2);
        ed_pass.setAnimation(animation2);
        //khai bao doi tuong
        thuThuDAO = new ThuThuDAO(LoginActivity.this);


        //lay du lieu tu SharedPreferences
        //tao tep
        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        ed_user.setText(sharedPreferences.getString("USERNAME", ""));
        ed_pass.setText(sharedPreferences.getString("PASS", ""));
        checkBox.setChecked(sharedPreferences.getBoolean("REMEMBER", false));


        //cac event
        dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });
        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed_pass.setText("");
                ed_user.setText("");
            }
        });

    }


    private void checkLogin() {
        String user = ed_user.getText().toString();
        String pass = ed_pass.getText().toString();
        if (user.isEmpty() || pass.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Tên đăng nhập và mật khẩu không được để trống", Toast.LENGTH_LONG).show();
        } else {
            if (thuThuDAO.checkLogin(user, pass) > 0 || (user.equals("admin") && pass.equals("admin"))) {
                Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                rememberUser(user, pass, checkBox.isChecked());
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();


            } else {
                Toast.makeText(getApplicationContext(), "Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            }

        }

    }

    private void rememberUser(String user, String pass, boolean checked) {

        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (!checked) {
            editor.clear();
        } else {
            editor.putString("USERNAME", user);
            editor.putString("PASS", pass);
            editor.putBoolean("REMEMBER", checked);
        }
        //commit
        editor.commit();

    }
}