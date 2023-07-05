package net.fpl.androidduanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import net.fpl.androidduanmau.DTO.LoaiSach;
import net.fpl.androidduanmau.database.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class LoaiSachDAO {
    SQLiteDatabase db;
    DbHelper dbHelper;

    public LoaiSachDAO(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    //them
    public long insert(LoaiSach loaiSach) {
        ContentValues values = new ContentValues();
        values.put(LoaiSach.TEN_LOAI, loaiSach.getTenLoai());
        long result = db.insert(LoaiSach.TB_NAME, null, values);
        return result;
    }

    //sua
    public int update(LoaiSach loaiSach) {
        ContentValues values = new ContentValues();
        values.put(LoaiSach.TEN_LOAI, loaiSach.getTenLoai());
        //new string chuyển đối tượng thành chuỗi
        int result = db.update(LoaiSach.TB_NAME, values, "maLoai=?", new String[]{String.valueOf(loaiSach.getMaLoai())});
        return result;
    }

    //xoa
    public int delete(String id) {
        int result = db.delete(LoaiSach.TB_NAME, "maLoai=?", new String[]{id});
        return result;
    }

    //get data nhieu tham so
    private List<LoaiSach> getData(String sql, String... paramater) {
        List<LoaiSach> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, paramater);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int ma = cursor.getInt(cursor.getColumnIndex(LoaiSach.MA_LOAI_PK));
            String tenLoai = cursor.getString(cursor.getColumnIndex(LoaiSach.TEN_LOAI));
            LoaiSach loaiSach = new LoaiSach(ma, tenLoai);
            list.add(loaiSach);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }


    //get tat ca data
    public List<LoaiSach> getAll() {
        String sql = "SELECT * FROM " + LoaiSach.TB_NAME;
        return getData(sql);
    }

    //get data theo id
    public LoaiSach getID(String id) {
        String sql = "SELECT * FROM LoaiSach WHERE maLoai=?";
        List<LoaiSach> list = getData(sql, id);
        return list.get(0);
    }

    public List<LoaiSach> checkGetIDLoaiSach(int id) {
        String sql = "SELECT * FROM LoaiSach as ls INNER JOIN Sach as s ON ls.maLoai = s.maLoai WHERE ls.maLoai=?";
        List<LoaiSach> list = getData(sql, String.valueOf(id));
        return list;
    }
}
