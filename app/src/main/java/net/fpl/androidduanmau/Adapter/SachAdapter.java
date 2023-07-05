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

import net.fpl.androidduanmau.DAO.LoaiSachDAO;
import net.fpl.androidduanmau.DTO.LoaiSach;
import net.fpl.androidduanmau.DTO.Sach;
import net.fpl.androidduanmau.Fragment.Fragment_Sach;
import net.fpl.androidduanmau.R;

import java.util.ArrayList;

public class SachAdapter extends BaseAdapter {
    Context context;
    Fragment_Sach fragment;
    ArrayList<Sach> list;

    public SachAdapter(Context context, Fragment_Sach fragment, ArrayList<Sach> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.sach_item, null);
            viewHolDer.tvMaSach = convertView.findViewById(R.id.tv_MaSach);
            viewHolDer.tvTenSach = convertView.findViewById(R.id.tv_TenSach);
            viewHolDer.tvGiaThue = convertView.findViewById(R.id.tv_GiaThue);
            viewHolDer.tvLoai = convertView.findViewById(R.id.tv_LoaiSach);
            viewHolDer.imgDel = convertView.findViewById(R.id.img_DeleteSach);
            convertView.setTag(viewHolDer);
        } else {
            viewHolDer = (ViewHolder) convertView.getTag();
        }
        Sach item = list.get(position);
        LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);
        LoaiSach loaiSach = loaiSachDAO.getID(item.getMaLoai() + "");
        viewHolDer.tvMaSach.setText("Mã Sách: " + item.getMaSach());
        viewHolDer.tvTenSach.setText("Tên Sách: " + item.getTenSach());
        viewHolDer.tvGiaThue.setText("Giá Thuê: " + item.getGiaThue() + " VNĐ");
        viewHolDer.tvLoai.setText("Loại Sách: " + loaiSach.getTenLoai());

        //xoa
        viewHolDer.imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //gọi method xoa  trong Sach Fragment
                fragment.xoa(String.valueOf(item.getMaSach()));

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
        TextView tvMaSach;
        TextView tvTenSach;
        TextView tvLoai;
        TextView tvGiaThue;
        ImageView imgDel;
    }
}
