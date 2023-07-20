package tiendhph30203.poly.duan1.SanPham;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class SanPhamFragment extends Fragment {
    private RecyclerView recycleViewSanPham;
    private FloatingActionButton btnThemSanPham;
    private Spinner spChonLoaiSanPham;
    SanPhamDAO sanPhamDAO;
    ArrayList<SanPham> list = new ArrayList<>();


    public SanPhamFragment() {

    }

    public static SanPhamFragment newInstance() {
        SanPhamFragment fragment = new SanPhamFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_san_pham, container, false);

        recycleViewSanPham = view.findViewById(R.id.recycleViewSanPham);
        btnThemSanPham = view.findViewById(R.id.btnThemSanPham);
        btnThemSanPham.setOnClickListener(new View.OnClickListener() {
            EditText edtThemTenSanPham, edtThemAnhSanPham, edtThemLinkAnhSanPham,
                    edtThemGiaSanPham, edtThemGiamGia, edtThemSoLuongTrongKho, edtThemNgaySanXuat, edtThemHanSuDung;
            Button btnDialogHuyThemSanPham, btnDialogThemSanPham;
            private SanPhamDAO sanPhamDAO = new SanPhamDAO(getContext());
            private LoaiSanPhamDAO loaiSanPhamDAO = new LoaiSanPhamDAO(getContext());
            private List<LoaiSanPham> list = (ArrayList<LoaiSanPham>) loaiSanPhamDAO.getAll();

            @Override
            public void onClick(View view1) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater1 = ((Activity) getContext()).getLayoutInflater();
                view1 = inflater1.inflate(R.layout.item_themsanpham, null);
                builder.setView(view1);
                Dialog dialog = builder.create();
                edtThemTenSanPham = view1.findViewById(R.id.edtThemTenSanPham);
                edtThemAnhSanPham = view1.findViewById(R.id.edtThemAnhSanPham);
                edtThemLinkAnhSanPham = view1.findViewById(R.id.edtThemLinkAnhSanPham);
                edtThemGiaSanPham = view1.findViewById(R.id.edtThemGiaSanPham);
                edtThemGiamGia = view1.findViewById(R.id.edtThemGiamGia);
                edtThemSoLuongTrongKho = view1.findViewById(R.id.edtThemSoLuongTrongKho);
                edtThemNgaySanXuat = view1.findViewById(R.id.edtThemNgaySanXuat);
                edtThemHanSuDung = view1.findViewById(R.id.edtThemHanSuDung);
                btnDialogThemSanPham = view1.findViewById(R.id.btnDialogThemSanPham);
                btnDialogHuyThemSanPham = view1.findViewById(R.id.btnDialogHuyThemSanPham);
                spChonLoaiSanPham = view1.findViewById(R.id.spChonLoaiSanPham);
                spChonLoai();
                btnDialogHuyThemSanPham.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btnDialogThemSanPham.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SanPham sanPham = new SanPham();
                        sanPham.setTensanpham(edtThemTenSanPham.getText().toString());
                        sanPham.setAnhsanpham(edtThemAnhSanPham.getText().toString());
                        sanPham.setLinkanhsanpham(edtThemLinkAnhSanPham.getText().toString());
                        sanPham.setGiasanpham(edtThemGiaSanPham.getText().toString());
                        sanPham.setGiamgia(edtThemGiamGia.getText().toString());
                        sanPham.setSoluongtrongkho(Integer.parseInt(edtThemSoLuongTrongKho.getText().toString()));
                        sanPham.setNgaysanxuat(edtThemNgaySanXuat.getText().toString());
                        sanPham.setHansudung(edtThemHanSuDung.getText().toString());
                        for (LoaiSanPham loaiSanPham: list) {
                            if(loaiSanPham.getTenLoaiSanPham().equals(spChonLoaiSanPham.getSelectedItem().toString())){
                                sanPham.setMaloai(loaiSanPham.getMaLoaiSanPham());
                            }
                        }
                        if(sanPhamDAO.insert(sanPham) > 0){
                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                        reload();
//                        if (edtThemLoaiSanPham.getText().toString().isEmpty()) {
//                            Toast.makeText(getContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
//                        } else {
//                            if (loaiSanPhamDAO.insert(loaiSanPham) > 0) {
//                                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
//                            } else {
//                                Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
//                            }
                            dialog.dismiss();

                    }
                });
                dialog.show();
            }
        });
        reload();
        return view;
    }

    private void spChonLoai(){
        LoaiSanPhamDAO loaiSanPhamDAO1 = new LoaiSanPhamDAO(getContext());
        List<String> lst = new ArrayList<>();
        List<LoaiSanPham> list = (ArrayList<LoaiSanPham>) loaiSanPhamDAO1.getAll();
        for (LoaiSanPham loaiSanPham: list) {
            lst.add(loaiSanPham.getTenLoaiSanPham());
        }
        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, lst);
        spChonLoaiSanPham.setAdapter(adapter);
    }


    public void reload() {
        sanPhamDAO = new SanPhamDAO(getContext());
        list = (ArrayList<SanPham>) sanPhamDAO.getAll();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recycleViewSanPham.setLayoutManager(linearLayoutManager);
        Adapter_SanPham sanPhamAdapter = new Adapter_SanPham(getContext());
        sanPhamAdapter.setData(list);
        recycleViewSanPham.setAdapter(sanPhamAdapter);
    }
}