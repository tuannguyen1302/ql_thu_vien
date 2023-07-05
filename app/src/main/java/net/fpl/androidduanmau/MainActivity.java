package net.fpl.androidduanmau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import net.fpl.androidduanmau.Fragment.Fragment_DoanhThu;
import net.fpl.androidduanmau.Fragment.Fragment_DoiMK;
import net.fpl.androidduanmau.Fragment.Fragment_Loaisach;
import net.fpl.androidduanmau.Fragment.Fragment_Phieumuon;
import net.fpl.androidduanmau.Fragment.Fragment_QuanlyTV;
import net.fpl.androidduanmau.Fragment.Fragment_Sach;
import net.fpl.androidduanmau.Fragment.Fragment_ThemThuThu;
import net.fpl.androidduanmau.Fragment.Fragment_Top;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawLayout;
    private Toolbar toolbar;
    private NavigationView nvView;
    private View mHeaderView;
    private TextView tvUser;
    ActionBarDrawerToggle toggle;

    //khai bao bien check fragment hien tai
    public static final int FRAGMENT_PHIEUMUON = 0;
    public static final int FRAGMENT_LOAISACH = 1;
    public static final int FRAGMENT_SACH = 2;
    public static final int FRAGMENT_THANHVIEN = 3;
    public static final int FRAGMENT_TOP10 = 4;
    public static final int FRAGMENT_DOANHTHU = 5;
    public static final int FRAGMENT_THEMTHUTHU = 6;
    public static final int FRAGMENT_DOIMK = 7;

    //TAO BIEN DE CHECK FRAGMENT;
    int current = FRAGMENT_PHIEUMUON;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawLayout = (DrawerLayout) findViewById(R.id.dr_layout);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        nvView = (NavigationView) findViewById(R.id.nvView);
        //set toolbar
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        //Tao icon menu ba gach
        toggle = new ActionBarDrawerToggle(MainActivity.this, drawLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawLayout.addDrawerListener(toggle);//dua togle vao drawerlayout
        toggle.syncState();//dong bo hoa

        //navigation lang nghe su kien
        nvView.setNavigationItemSelectedListener(MainActivity.this);
        //set Fragment_PhieuMuon lam home
        replaceFragment(new Fragment_Phieumuon());
        //lay ra header cua naviagtion view de set text ten
        mHeaderView = nvView.getHeaderView(0);
        tvUser = mHeaderView.findViewById(R.id.tv_user);
        Intent intent = getIntent();
        String user = intent.getStringExtra("user");
        tvUser.setText(user);

//        admin co quyen add user
        if (user.equals("admin")) {
            nvView.getMenu().findItem(R.id.sub_AddUser).setVisible(true);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_PhieuMuon:
                setTitle("Quan Ly Phieu Muon");
                if (current != FRAGMENT_PHIEUMUON) {
                    replaceFragment(new Fragment_Phieumuon());
                    current = FRAGMENT_PHIEUMUON;
                }
                break;
            case R.id.nav_LoaiSach:
                setTitle("Quan Ly Loai Sach");
                if (current != FRAGMENT_LOAISACH) {
                    replaceFragment(new Fragment_Loaisach());
                    current = FRAGMENT_LOAISACH;
                }
                break;
            case R.id.nav_Sach:
                setTitle("Quan Ly Sach");
                if (current != FRAGMENT_SACH) {
                    replaceFragment(new Fragment_Sach());
                    current = FRAGMENT_SACH;
                }
                break;
            case R.id.nav_ThanhVien:
                setTitle("Quan Ly Thanh Vien");
                if (current != FRAGMENT_THANHVIEN) {
                    replaceFragment(new Fragment_QuanlyTV());
                    current = FRAGMENT_THANHVIEN;
                }
                break;
            case R.id.sub_Top:
                setTitle("Top Sach Ban Chay");
                if (current != FRAGMENT_TOP10) {
                    replaceFragment(new Fragment_Top());
                    current = FRAGMENT_TOP10;
                }
                break;
            case R.id.sub_DoanhThu:
                setTitle("Doanh Thu ");
                if (current != FRAGMENT_DOANHTHU) {
                    replaceFragment(new Fragment_DoanhThu());
                    current = FRAGMENT_DOANHTHU;
                }
                break;
            case R.id.sub_AddUser:
                setTitle("Them Nguoi Dung");
                if (current != FRAGMENT_THEMTHUTHU) {
                    replaceFragment(new Fragment_ThemThuThu());
                    current = FRAGMENT_THEMTHUTHU;
                }
                break;
            case R.id.sub_Pass:
                setTitle("Doi Mat Khau");
                if (current != FRAGMENT_DOIMK) {
                    replaceFragment(new Fragment_DoiMK());
                    current = FRAGMENT_DOIMK;
                }
                break;
            case R.id.sub_Logout:
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        //dong drawble
        drawLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.flcontent, fragment).commit();
    }

    //nut back tren dien thoai
    @Override
    public void onBackPressed() {
        if (drawLayout.isDrawerOpen(GravityCompat.START)) {
            drawLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}