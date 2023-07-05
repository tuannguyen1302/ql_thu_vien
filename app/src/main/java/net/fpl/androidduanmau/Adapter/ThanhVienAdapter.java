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

import net.fpl.androidduanmau.DTO.ThanhVien;
import net.fpl.androidduanmau.Fragment.Fragment_QuanlyTV;
import net.fpl.androidduanmau.R;

import java.util.ArrayList;

public class ThanhVienAdapter extends BaseAdapter {
    Context context;
    Fragment_QuanlyTV fragment;
    ArrayList<ThanhVien> lists;

    public ThanhVienAdapter(Context context, Fragment_QuanlyTV fragment, ArrayList<ThanhVien> lists) {
        this.context = context;
        this.fragment = fragment;
        this.lists = lists;
    }

    @Override
    public int getCount() {
        if (lists != null) {
            return lists.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lists.get(position).getMaTV();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolDer viewHolDer;
        if (convertView == null) {
            viewHolDer = new ViewHolDer();
            convertView = LayoutInflater.from(context).inflate(R.layout.thanhvien_item, null);
            viewHolDer.tvMaTV = convertView.findViewById(R.id.tv_MaTV);
            viewHolDer.tvTen = convertView.findViewById(R.id.tv_TenTV);
            viewHolDer.tvNamSinh = convertView.findViewById(R.id.tv_NamSinh);
            viewHolDer.imgDel = convertView.findViewById(R.id.img_DeleteTV);
            convertView.setTag(viewHolDer);
        } else {
            viewHolDer = (ViewHolDer) convertView.getTag();
        }
        ThanhVien item = lists.get(position);
        viewHolDer.tvMaTV.setText("Mã TV: " + item.getMaTV());
        viewHolDer.tvTen.setText("Tên TV: " + item.getHoTen());
        viewHolDer.tvNamSinh.setText("Năm Sinh: " + item.getNamSinh());

        //xoa
        viewHolDer.imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //gọi method xoa  trong ThanhVien Fragment
                fragment.xoa(String.valueOf(item.getMaTV()));

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


    public class ViewHolDer {
        TextView tvMaTV;
        TextView tvTen;
        TextView tvNamSinh;
        ImageView imgDel;

    }
}
