package net.fpl.androidduanmau.Fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import net.fpl.androidduanmau.DAO.PhieuMuonDAO;
import net.fpl.androidduanmau.DAO.ThongKeDAO;
import net.fpl.androidduanmau.DTO.PhieuMuon;
import net.fpl.androidduanmau.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Fragment_DoanhThu extends Fragment {
    private TextView tvTuNgay;
    private TextView tvDenNgay;
    private Button btnShow;
    private TextView tvTong;
    private TextView tvTongtientheokhoang;
    ThongKeDAO thongKeDAO;
    ArrayList<PhieuMuon> list;

    public Fragment_DoanhThu() {
    }

    PhieuMuonDAO phieuMuonDAO;
    int Tong;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_fragment_doanh_thu, container, false);
        tvTuNgay = (TextView) view.findViewById(R.id.tv_tu);
        tvDenNgay = (TextView) view.findViewById(R.id.tv_den);
        btnShow = (Button) view.findViewById(R.id.btn_show);
        tvTong = (TextView) view.findViewById(R.id.tv_tong);
        tvTongtientheokhoang = (TextView) view.findViewById(R.id.tv_tongtientheokhoang);
        thongKeDAO = new ThongKeDAO(getContext());
        phieuMuonDAO = new PhieuMuonDAO(getContext());
        //tong tien
        list = (ArrayList<PhieuMuon>) phieuMuonDAO.getAll();
        for (int i = 0; i < list.size(); i++) {
            Tong += list.get(i).getTienThue();
        }
        //bat dau
        tvTuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        tvTuNgay.setText(dayOfMonth + "-" + (month + 1) + "-" + year);

                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
        //ket thuc
        tvDenNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tvDenNgay.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });


        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tvTong.setText(Tong + " VNĐ");
                int Tongtientheokhoang = 0;
                String batDau = tvTuNgay.getText().toString();
                String ketThuc = tvDenNgay.getText().toString();
                for (int i = 0; i < list.size(); i++) {
                    try {
                        if (list.get(i).getNgay().compareTo(simpleDateFormat.parse(batDau)) >= 0 && list.get(i).getNgay().compareTo(simpleDateFormat.parse(ketThuc)) <= 0) {
                            Tongtientheokhoang += list.get(i).getTienThue();
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                tvTongtientheokhoang.setText(Tongtientheokhoang + " VNĐ");


            }
        });
        return view;
    }
}