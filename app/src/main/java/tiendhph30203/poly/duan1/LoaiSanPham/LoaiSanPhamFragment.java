package tiendhph30203.poly.duan1.LoaiSanPham;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import tiendhph30203.poly.projectdatdoan.R;


public class LoaiSanPhamFragment extends Fragment {
    private RecyclerView recyclerViewLoaiSanPham;
    private FloatingActionButton btnThemLoaiSanPham;
    LoaiSanPhamDAO loaiSanPhamDAO;
    ArrayList<LoaiSanPham> list = new ArrayList<>();

    public LoaiSanPhamFragment() {

    }

    public static LoaiSanPhamFragment newInstance() {
        LoaiSanPhamFragment fragment = new LoaiSanPhamFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loai_san_pham, container, false);
        recyclerViewLoaiSanPham = view.findViewById(R.id.recycleViewLoaiSanPham);
        btnThemLoaiSanPham = view.findViewById(R.id.btnThemLoaiSanPham);
        reload();
        btnThemLoaiSanPham.setOnClickListener(new View.OnClickListener() {
            EditText edtThemLoaiSanPham;
            Button btnDialogThemLoaiSachSanPham, btnDialogHuyThemLoaiSanPham;

            @Override
            public void onClick(View view1) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater1 = ((Activity) getContext()).getLayoutInflater();
                view1 = inflater1.inflate(R.layout.item_themloaisanpham, null);
                builder.setView(view1);
                Dialog dialog = builder.create();
                edtThemLoaiSanPham = view1.findViewById(R.id.edtThemLoaiSanPham);
                btnDialogThemLoaiSachSanPham = view1.findViewById(R.id.btnDialogThemLoaiSachSanPham);
                btnDialogHuyThemLoaiSanPham = view1.findViewById(R.id.btnDialogHuyThemLoaiSanPham);
                btnDialogHuyThemLoaiSanPham.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btnDialogThemLoaiSachSanPham.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LoaiSanPham loaiSanPham = new LoaiSanPham();
                        loaiSanPham.setTenLoaiSanPham(edtThemLoaiSanPham.getText().toString());


                        if (edtThemLoaiSanPham.getText().toString().isEmpty()) {
                            Toast.makeText(getContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
                        } else {
                            if (loaiSanPhamDAO.insert(loaiSanPham) > 0) {
                                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                            }
                            dialog.dismiss();
                            reload();
                        }
                    }
                });
                dialog.show();
            }
        });


        return view;
    }


    public void reload() {
        loaiSanPhamDAO = new LoaiSanPhamDAO(getContext());
        list = (ArrayList<LoaiSanPham>) loaiSanPhamDAO.getAll();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewLoaiSanPham.setLayoutManager(linearLayoutManager);
        Adapter_LoaiSanPham adapter_loaiSanPham = new Adapter_LoaiSanPham(list, getContext(), loaiSanPhamDAO);
        recyclerViewLoaiSanPham.setAdapter(adapter_loaiSanPham);
    }
}