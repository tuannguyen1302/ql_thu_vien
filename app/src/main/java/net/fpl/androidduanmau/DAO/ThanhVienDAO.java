package net.fpl.androidduanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import net.fpl.androidduanmau.DTO.ThanhVien;
import net.fpl.androidduanmau.database.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class ThanhVienDAO {
    SQLiteDatabase db;
    DbHelper dbHelper;

    public ThanhVienDAO(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    //them
    public long insert(ThanhVien thanhVien) {
        ContentValues values = new ContentValues();
        values.put(ThanhVien.COL_NAME_HOTEN, thanhVien.getHoTen());
        values.put(ThanhVien.COL_NAME_NAMSINH, thanhVien.getNamSinh());
        long result = db.insert(ThanhVien.TB_NAME, null, values);
        return result;
    }

    //sua
    public int update(ThanhVien thanhVien) {
        ContentValues values = new ContentValues();
        values.put(ThanhVien.COL_NAME_HOTEN, thanhVien.getHoTen());
        values.put(ThanhVien.COL_NAME_NAMSINH, thanhVien.getNamSinh());
        int result = db.update(ThanhVien.TB_NAME, values, "maTV=?", new String[]{String.valueOf(thanhVien.getMaTV())});
        return result;
    }


    //xoa
    public int delete(String id) {
        int result = db.delete(ThanhVien.TB_NAME, "maTV=?", new String[]{id});
        return result;
    }

    //get data nhieu tham so
    private List<ThanhVien> getData(String sql, String... paramater) {
        List<ThanhVien> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, paramater);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int ma = cursor.getInt(cursor.getColumnIndex(ThanhVien.COL_NAME_MATV_PK));
            String ten = cursor.getString(cursor.getColumnIndex(ThanhVien.COL_NAME_HOTEN));
            String namSinh = cursor.getString(cursor.getColumnIndex(ThanhVien.COL_NAME_NAMSINH));
            ThanhVien thanhVien = new ThanhVien(ma, ten, namSinh);
            list.add(thanhVien);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }


    //get tat ca data
    public List<ThanhVien> getAll() {
        String sql = "SELECT * FROM " + ThanhVien.TB_NAME;
        return getData(sql);
    }

    //get data theo id
    public ThanhVien getID(String id) {
        String sql = "SELECT * FROM ThanhVien WHERE maTV=?";
        List<ThanhVien> list = getData(sql, id);
        return list.get(0);
    }


    public List<ThanhVien> checkGetIDThanhVien(int id) {
        String sql = "SELECT * FROM ThanhVien as tv INNER JOIN PhieuMuon as pm ON tv.maTV = pm.maTV WHERE tv.maTV=?";
        List<ThanhVien> list = getData(sql, String.valueOf(id));
        return list;
    }
}
