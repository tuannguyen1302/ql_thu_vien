package net.fpl.androidduanmau.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.fpl.androidduanmau.DTO.ThanhVien;
import net.fpl.androidduanmau.R;

import java.util.ArrayList;

public class ThanhVienSpinnerAdapter extends BaseAdapter {
    Context context;
    ArrayList<ThanhVien> list;

    public ThanhVienSpinnerAdapter(Context context, ArrayList<ThanhVien> list) {
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
        return list.get(position).getMaTV();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.thanhvien_item_spinner, null);
        } else {
            view = convertView;
        }
        //anh xa
        TextView tvMaThanhVien = view.findViewById(R.id.tv_MaThanhVien_sp);
        TextView tvTenThanhVien = view.findViewById(R.id.tv_TenThanhVien_sp);
        //gan data
        ThanhVien item = list.get(position);
        tvMaThanhVien.setText(item.getMaTV() + ".");
        tvTenThanhVien.setText(item.getHoTen());
        return view;
    }

}
