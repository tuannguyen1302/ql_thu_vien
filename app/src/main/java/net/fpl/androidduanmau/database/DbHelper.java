package net.fpl.androidduanmau.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "Qlthuvien.db";
    public static final int DB_VERSION = 1;
    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Tao bang Thu Thu
        String createTableThuThu ="create table ThuThu (maTT text primary key, hoTen text not null, matKhau text not null)";
        db.execSQL(createTableThuThu);

        //Tao bang Thanh Vien
        String createTableThanhVien ="create table ThanhVien (maTV integer primary key autoincrement, hoTen text not null, namSinh text not null)";
        db.execSQL(createTableThanhVien);

        //Tao bang Loai Sach
        String createTableLoaiSach ="create table LoaiSach (maLoai integer primary key autoincrement, tenLoai text not null)";
        db.execSQL(createTableLoaiSach);

        //Tao bang  Sach
        String createTableSach ="create table Sach (maSach integer primary key autoincrement, tenSach text not null, giaThue integer not null, maLoai integer references LoaiSach(maLoai))";
        db.execSQL(createTableSach);

        //Tao bang  Phieu Muon
        String createTablePhieuMuon ="create table PhieuMuon (maPM integer primary key autoincrement, maTT text references ThuThu(maTT), maTV integer references ThanhVien(maTV), maSach integer references Sach(maSach), tienThue integer not null, ngay date not null, traSach integer not null)";
        db.execSQL(createTablePhieuMuon);


        //them mot so du lieu de test

        createTableThanhVien = "INSERT INTO ThanhVien VALUES(NULL, 'Vu Manh Tu1','1123')";
        db.execSQL(createTableThanhVien);

        createTableLoaiSach = "INSERT INTO LoaiSach VALUES(NULL, 'Xa Hoi')";
        db.execSQL(createTableLoaiSach);

        createTableSach = "INSERT INTO Sach VALUES(NULL, 'Cong Nghe Thong Tin',2500,1)";
        db.execSQL(createTableSach);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //xoa table ThuThu
        String dropTableThuThu = "drop table if exists ThuThu";
        db.execSQL(dropTableThuThu);
        //xoa table ThanhVien
        String dropTableThanhVien = "drop table if exists ThanhVien";
        db.execSQL(dropTableThanhVien);
        //xoa table LoaiSach
        String dropTableLoaiSach = "drop table if exists LoaiSach";
        db.execSQL(dropTableLoaiSach);
        //xoa table Sach
        String dropTableSach = "drop table if exists Sach";
        db.execSQL(dropTableSach);
        //xoa table PhieuMuon
        String dropTablePhieuMuon = "drop table if exists PhieuMuon";
        db.execSQL(dropTablePhieuMuon);


        //tao lai cac bang khi version thay doi
        onCreate(db);
    }
}
