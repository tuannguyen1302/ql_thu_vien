package net.fpl.androidduanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import net.fpl.androidduanmau.DTO.Sach;
import net.fpl.androidduanmau.database.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class SachDAO {
    SQLiteDatabase db;
    DbHelper dbHelper;

    public SachDAO(Context context) {
        //Context là thành phần trong ứng dụng android
        // cung cấp quyền truy cập thông tin về các trạng thái của ứng dụng đó
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    //them
    public long insert(Sach sach) {
        ContentValues values = new ContentValues();
        values.put(Sach.COL_NAME_TENSACH, sach.getTenSach());
        values.put(Sach.COL_NAME_GIATHUE, sach.getGiaThue());
        values.put(Sach.COL_NAME_MALOAI_FK, sach.getMaLoai());
        long result = db.insert(Sach.TB_NAME, null, values);
        return result;
    }

    //sua
    public int update(Sach sach) {
        ContentValues values = new ContentValues();
        values.put(Sach.COL_NAME_TENSACH, sach.getTenSach());
        values.put(Sach.COL_NAME_GIATHUE, sach.getGiaThue());
        values.put(Sach.COL_NAME_MALOAI_FK, sach.getMaLoai());
        int result = db.update(Sach.TB_NAME, values, "maSach=?", new String[]{String.valueOf(sach.getMaSach())});
        return result;
    }

    //xoa
    public int delete(String id) {
        int result = db.delete(Sach.TB_NAME, "maSach=?", new String[]{id});
        return result;
    }

    //get data nhieu tham so
    private List<Sach> getData(String sql, String... paramater) {
        List<Sach> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, paramater);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int ma = cursor.getInt(cursor.getColumnIndex(Sach.COL_NAME_MASACH_PK));
            String ten = cursor.getString(cursor.getColumnIndex(Sach.COL_NAME_TENSACH));
            int giaThue = cursor.getInt(cursor.getColumnIndex(Sach.COL_NAME_GIATHUE));
            int maLoai = cursor.getInt(cursor.getColumnIndex(Sach.COL_NAME_MALOAI_FK));
            Sach sach = new Sach(ma, ten, giaThue, maLoai);
            list.add(sach);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }


    //get tat ca data
    public List<Sach> getAll() {
        String sql = "SELECT * FROM " + Sach.TB_NAME;
        return getData(sql);
    }

    //get data theo id
    public Sach getID(String id) {
        String sql = "SELECT * FROM Sach WHERE maSach=?";
        List<Sach> list = getData(sql, id);
        return list.get(0);
    }

    public List<Sach> checkGetIDSach(int id) {
        String sql = "SELECT * FROM Sach as s INNER JOIN PhieuMuon as pm ON s.maSach = pm.maSach WHERE s.maSach=?";
        List<Sach> list = getData(sql, String.valueOf(id));
        return list;
    }
}
