package net.fpl.androidduanmau.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import net.fpl.androidduanmau.DTO.PhieuMuon;
import net.fpl.androidduanmau.DTO.Sach;
import net.fpl.androidduanmau.DTO.TopSach;
import net.fpl.androidduanmau.database.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class ThongKeDAO {
    SQLiteDatabase db;
    DbHelper dbHelper;
    Context context;

    public ThongKeDAO(Context context) {
        this.context = context;
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    //thong ke top 10
    public List<TopSach> getTop() {
        List<TopSach> list = new ArrayList<>();
        SachDAO sachDAO = new SachDAO(context);
        String sqlTop = "SELECT maSach, count(maSach) as soLuong FROM PhieuMuon Group by maSach ORDER BY soLuong DESC LIMIT 10";
        Cursor cursor = db.rawQuery(sqlTop, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            //lay ra ten sach theo maSach
            String maSach = cursor.getString(cursor.getColumnIndex(PhieuMuon.COL_NAME_MASACH_FK));
            Sach sach = sachDAO.getID(maSach);
            TopSach topSach = new TopSach();
            topSach.setTenSach(sach.getTenSach());
            String soLuong = cursor.getString(cursor.getColumnIndex("soLuong"));
            topSach.setSoLuong(Integer.parseInt(soLuong));
            list.add(topSach);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    //thong ke doanh thu
//    public int getDanhThu(String tuNgay,String denNgay){
//        String sqlDanhThu = "SELECT SUM(tienThue) as danhThu FROM PhieuMuon WHERE ngay BETWEEN ? AND ?";
//        List<Integer> list = new ArrayList<>();
//        Cursor cursor =db.rawQuery(sqlDanhThu,new String[]{tuNgay,denNgay});
//        while (cursor.moveToNext()){
//            try {
//                String danhthu = cursor.getString(cursor.getColumnIndex("danhThu"));
//                list.add(Integer.parseInt(danhthu));
//            }catch (Exception e){
//                list.add(0);
//            }
//        }
//        cursor.close();
//        return list.get(0);
//    }

}
