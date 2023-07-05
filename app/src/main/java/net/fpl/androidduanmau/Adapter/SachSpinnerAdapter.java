package net.fpl.androidduanmau.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.fpl.androidduanmau.DTO.Sach;
import net.fpl.androidduanmau.R;

import java.util.ArrayList;

public class SachSpinnerAdapter extends BaseAdapter {
    Context context;
    ArrayList<Sach> list;

    public SachSpinnerAdapter(Context context, ArrayList<Sach> list) {
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
        return list.get(position).getMaSach();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.sach_item_spinner, null);
        } else {
            view = convertView;
        }
        //anh xa
        TextView tvMaSach = view.findViewById(R.id.tv_MaSach_sp);
        TextView tvTenSach = view.findViewById(R.id.tv_TenSach_sp);
        //gan data
        Sach item = list.get(position);
        tvMaSach.setText(item.getMaSach() + ".");
        tvTenSach.setText(item.getTenSach());
        return view;
    }

}
