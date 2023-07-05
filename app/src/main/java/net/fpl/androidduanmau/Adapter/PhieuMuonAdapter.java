package net.fpl.androidduanmau.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.fpl.androidduanmau.DAO.SachDAO;
import net.fpl.androidduanmau.DAO.ThanhVienDAO;
import net.fpl.androidduanmau.DTO.PhieuMuon;
import net.fpl.androidduanmau.DTO.Sach;
import net.fpl.androidduanmau.DTO.ThanhVien;
import net.fpl.androidduanmau.Fragment.Fragment_Phieumuon;
import net.fpl.androidduanmau.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PhieuMuonAdapter extends BaseAdapter {
    Context context;
    Fragment_Phieumuon fragment;
    ArrayList<PhieuMuon> list;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public PhieuMuonAdapter(Context context, Fragment_Phieumuon fragment, ArrayList<PhieuMuon> list) {
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getMaSach();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolDer;
        if (convertView == null) {
            viewHolDer = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.phieumuon_item, null);
            viewHolDer.tvMaPM = convertView.findViewById(R.id.tv_MaPM);
            viewHolDer.tvTenTV = convertView.findViewById(R.id.tv_TenTV_phieumuon);
            viewHolDer.tvTenSach = convertView.findViewById(R.id.tv_TenSach_phieumuon);
            viewHolDer.tvTienThue = convertView.findViewById(R.id.tv_TienThue_phieumuon);
            viewHolDer.tvTraSach = convertView.findViewById(R.id.tv_TraSach);
            viewHolDer.tvNgay = convertView.findViewById(R.id.tv_NgayThue);
            viewHolDer.imgDel = convertView.findViewById(R.id.img_DeletePM);
            convertView.setTag(viewHolDer);
        } else {
            //xem bộ nhớ đệm
            viewHolDer = (ViewHolder) convertView.getTag();
        }
        PhieuMuon item = list.get(position);
        //maPM
        viewHolDer.tvMaPM.setText("Mã PM: " + item.getMaPM());
        //tenSach
        SachDAO sachDAO = new SachDAO(context);
        Sach sach = sachDAO.getID(item.getMaSach() + "");
        viewHolDer.tvTenSach.setText("Tên Sách: " + sach.getTenSach());
        //ten TV
        ThanhVienDAO thanhVienDAO = new ThanhVienDAO(context);
        ThanhVien thanhVien = thanhVienDAO.getID(item.getMaTV() + "");
        viewHolDer.tvTenTV.setText("Tên TV: " + thanhVien.getHoTen());
        //Tien Thue
        viewHolDer.tvTienThue.setText("Tiền Thuê: " + item.getTienThue() + " VNĐ");
        //Ngay Thuê
        viewHolDer.tvNgay.setText("Ngày Thuê: " + simpleDateFormat.format(item.getNgay()));
        //tra sách
        if (item.getTraSach() == 1) {
            viewHolDer.tvTraSach.setTextColor(Color.BLUE);
            viewHolDer.tvTraSach.setText("Đã Trả Sách");
        } else {
            viewHolDer.tvTraSach.setTextColor(Color.RED);
            viewHolDer.tvTraSach.setText("Chưa Trả Sách");
        }
        //xoa
        viewHolDer.imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //gọi method xoa  trong Phieu Muon Fragment
                fragment.xoa(String.valueOf(item.getMaPM()));

            }
        });
        if (position % 2 == 0) {
            convertView.setBackgroundColor(Color.parseColor("#03A9F4"));
        } else {
            convertView.setBackgroundColor(Color.parseColor("#FFEB3B"));
        }


        //animation lv
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.anim_login);
        convertView.startAnimation(animation);
        return convertView;
    }

    private class ViewHolder {
        TextView tvMaPM;
        TextView tvTenTV;
        TextView tvTenSach;
        TextView tvTraSach;
        TextView tvTienThue;
        TextView tvNgay;
        ImageView imgDel;
    }
}
