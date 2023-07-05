package net.fpl.androidduanmau.Fragment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import net.fpl.androidduanmau.Adapter.LoaiSachSpinnerAdapter;
import net.fpl.androidduanmau.Adapter.SachAdapter;
import net.fpl.androidduanmau.DAO.LoaiSachDAO;
import net.fpl.androidduanmau.DAO.SachDAO;
import net.fpl.androidduanmau.DTO.LoaiSach;
import net.fpl.androidduanmau.DTO.Sach;
import net.fpl.androidduanmau.R;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Sach extends Fragment {
    private ListView lv;
    private FloatingActionButton fab;
    ArrayList<Sach> list;
    SachAdapter adapter;
    Dialog dialog;
    TextView tvTitleDialog;
    EditText edTenSach;
    Spinner spinnerLoai;
    EditText edGiaThue;
    Button btnSave;
    Button btnCancel;
    Sach item;
    int maLoaiSach, positon;
    LoaiSachSpinnerAdapter spinnerAdapter;
    LoaiSachDAO loaiSachDAO;
    ArrayList<LoaiSach> listLoaiSach;
    SachDAO dao;

    public Fragment_Sach() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_fragment_sach, container, false);
        lv = (ListView) view.findViewById(R.id.lvSach);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        //khoi tao
        dao = new SachDAO(getContext());

        //cap nhat listview
        capNhatLv();


        //event fab thêm Sach
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getContext(), 0);
            }
        });

        //update du lieu
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getContext(), 1);
                return false;
            }
        });
        return view;
    }

    private void capNhatLv() {
        list = (ArrayList<Sach>) dao.getAll();
        adapter = new SachAdapter(getActivity(), this, list);
        adapter.notifyDataSetChanged();
        lv.setAdapter(adapter);
    }


    private void openDialog(Context context, int type) {
        //custom dialog
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.sach_dialog);
        //ẩn background dialog
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //anh xa
        tvTitleDialog = dialog.findViewById(R.id.tv_Sach_title_dialog);
        edTenSach = dialog.findViewById(R.id.ed_TenSach);
        edGiaThue = dialog.findViewById(R.id.ed_GiaThue);
        spinnerLoai = dialog.findViewById(R.id.spinner_loai);
        btnSave = dialog.findViewById(R.id.btn_SaveSach);
        btnCancel = dialog.findViewById(R.id.btn_CancelSach);
        //set Text Title
        tvTitleDialog.setText("Thêm Sách");

        //xu ly get data cho spinner
        listLoaiSach = new ArrayList<>();
        loaiSachDAO = new LoaiSachDAO(context);
        listLoaiSach = (ArrayList<LoaiSach>) loaiSachDAO.getAll();
        spinnerAdapter = new LoaiSachSpinnerAdapter(context, listLoaiSach);
        spinnerLoai.setAdapter(spinnerAdapter);
        //lay ma loai sach khi chon
        spinnerLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maLoaiSach = listLoaiSach.get(position).getMaLoai();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //kiem tra gia tri cua type 0 = insert và 1 = update
        if (type != 0) {
            tvTitleDialog.setText("Sửa Sách");
            edTenSach.setText(item.getTenSach());
            edGiaThue.setText(item.getGiaThue() + "");
            for (int i = 0; i < listLoaiSach.size(); i++) {
                if (item.getMaLoai() == listLoaiSach.get(i).getMaLoai()) {
                    positon = i;
                    break;
                }
            }
            spinnerLoai.setSelection(positon);
        }

        //đóng dialog
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sach item_insert = new Sach();
                item_insert.setTenSach(edTenSach.getText().toString());
                item_insert.setGiaThue(Integer.parseInt(edGiaThue.getText().toString()));
                item_insert.setMaLoai(maLoaiSach);
                if (validate() > 0) {
                    if (type == 0) {
                        // type=0 : insert
                        long result = dao.insert(item_insert);
                        if (result > 0) {
                            Toast.makeText(context, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        //type=1 : update
                        item_insert.setMaSach(item.getMaSach());
                        int result = dao.update(item_insert);
                        if (result > 0) {
                            Toast.makeText(context, "Sửa Thành Công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Sửa Thất Bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhatLv();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    private int validate() {
        int check = 1;
        if (edTenSach.getText().toString().isEmpty() || edGiaThue.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Không được để trống Thông Tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        if (listLoaiSach.size() <= 0) {
            Toast.makeText(getContext(), "Loại Sách Chưa Có Dữ Liệu!!!", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }

    public void xoa(String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_delete, null);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();
        Button btn_delete = view.findViewById(R.id.btn_ok_delete);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Sach> list_check = dao.checkGetIDSach(Integer.parseInt(id));
                if (list_check.size() > 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_e, null);
                    builder.setView(view);
                    AlertDialog alertDialog2 = builder.create();
                    alertDialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    alertDialog2.show();
                    Button btn_e = view.findViewById(R.id.btn_e);
                    btn_e.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog2.dismiss();
                            alertDialog.dismiss();
                        }
                    });
                    return;
                }


                int result = dao.delete(id);
                if (result > 0) {
                    Toast.makeText(getContext(), "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                    capNhatLv();
                    alertDialog.dismiss();
                } else {
                    Toast.makeText(getContext(), "Xóa Thất Bại", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                }
            }
        });
    }

}