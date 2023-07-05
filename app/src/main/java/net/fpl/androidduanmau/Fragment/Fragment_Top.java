package net.fpl.androidduanmau.Fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import net.fpl.androidduanmau.Adapter.TopAdapter;
import net.fpl.androidduanmau.DAO.ThongKeDAO;
import net.fpl.androidduanmau.DTO.TopSach;
import net.fpl.androidduanmau.R;

import java.util.ArrayList;

public class Fragment_Top extends Fragment {
    private ListView lv;
    TopAdapter adapter;
    ArrayList<TopSach> list;
    ThongKeDAO dao;


    public Fragment_Top() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_fragment_top, container, false);
        lv = (ListView) view.findViewById(R.id.lvTop);
        dao = new ThongKeDAO(getActivity());
        list = (ArrayList<TopSach>) dao.getTop();
        adapter = new TopAdapter(getActivity(), list);
        adapter.notifyDataSetChanged();
        lv.setAdapter(adapter);
        return view;
    }
}