package net.fpl.androidduanmau.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.fpl.androidduanmau.DTO.LoaiSach;
import net.fpl.androidduanmau.R;

import java.util.ArrayList;

public class LoaiSachSpinnerAdapter extends BaseAdapter {
    Context context;
    ArrayList<LoaiSach> list;

    public LoaiSachSpinnerAdapter(Context context, ArrayList<LoaiSach> list) {
        this.context = context;
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
        return list.get(position).getMaLoai();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.loaisach_spinner, null);
        } else {
            view = convertView;
        }
        //anh xa
        TextView tvMaLoaiSach = view.findViewById(R.id.tv_MaLoaiSach_sp);
        TextView tvTenLoaiSach = view.findViewById(R.id.tv_TenLoaiSach_sp);
        //gan data
        LoaiSach item = list.get(position);
        tvMaLoaiSach.setText(item.getMaLoai() + ".");
        tvTenLoaiSach.setText(item.getTenLoai());
        return view;
    }
}
