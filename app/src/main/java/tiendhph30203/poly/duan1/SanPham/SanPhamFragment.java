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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tiendhph30203.poly.projectdatdoan.LoaiSanPham.LoaiSanPham;
import tiendhph30203.poly.projectdatdoan.LoaiSanPham.LoaiSanPhamDAO;
import tiendhph30203.poly.projectdatdoan.R;

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


                        String tenSanPham = edtThemTenSanPham.getText().toString().trim();
                        String anhSanPham = edtThemAnhSanPham.getText().toString().trim();
                        String giaSanPham = edtThemGiaSanPham.getText().toString().trim();
                        String soLuongTrongKho = edtThemSoLuongTrongKho.getText().toString().trim();
                        String ngaySanXuat = edtThemNgaySanXuat.getText().toString().trim();
                        String hanSuDung = edtThemHanSuDung.getText().toString().trim();
                        String linkAnhSanPham = edtThemLinkAnhSanPham.getText().toString().trim();
                        String giamgia = edtThemGiamGia.getText().toString().trim();

                        // Kiểm tra tên sản phẩm
                        if (tenSanPham.isEmpty()) {
                            edtThemTenSanPham.setError("Tên sản phẩm không được để trống");
                            return;
                        } else if (!tenSanPham.equals(tenSanPham.toUpperCase())) {
                            edtThemTenSanPham.setError("Tên sản phẩm phải viết hoa");
                            return;
                        }

                        // Kiểm tra  ảnh sản phẩm
                        if (anhSanPham.isEmpty()) {
                            edtThemAnhSanPham.setError("Ảnh sản phẩm không được để trống");

                            return;
                        }
                        // Kiểm tra link ảnh sản phẩm
                        if (linkAnhSanPham.isEmpty()) {
                            edtThemLinkAnhSanPham.setError("Link ảnh sản phẩm không được để trống");
                            return;
                        }


                        // Kiểm tra giá sản phẩm
                        if (giaSanPham.isEmpty()) {
                            edtThemGiaSanPham.setError("Giá sản phẩm không được để trống");
                            return;
                        } else {
                            try {
                                double gia = Double.parseDouble(giaSanPham);
                                if (gia <= 0) {
                                    edtThemGiaSanPham.setError("Giá sản phẩm phải lớn hơn 0");
                                    return;
                                }
                            } catch (NumberFormatException e) {
                                edtThemGiaSanPham.setError("Giá sản phẩm phải là số");
                                return;
                            }
                        }

                        // Kiểm tra giảm giá
                        if (giamgia.isEmpty()) {
                            edtThemGiamGia.setError("Giảm giá sản phẩm không được để trống");
                            return;
                        } else {
                            try {
                                double gia = Double.parseDouble(giamgia);
                                if (gia < 0) {
                                    edtThemGiamGia.setError("Giảm giá sản phẩm không được âm");
                                    return;
                                }
                            } catch (NumberFormatException e) {
                                edtThemGiamGia.setError("Giảm giá sản phẩm phải là số");
                                return;
                            }
                        }

                        // Kiểm tra số lượng trong kho
                        if (soLuongTrongKho.isEmpty()) {
                            edtThemSoLuongTrongKho.setError("Số lượng trong kho không được để trống");
                            return;
                        } else {
                            try {
                                int soLuong = Integer.parseInt(soLuongTrongKho);
                                if (soLuong <= 0) {
                                    edtThemSoLuongTrongKho.setError("Số lượng trong kho không được âm");
                                    return;
                                }
                            } catch (NumberFormatException e) {
                                edtThemSoLuongTrongKho.setError("Số lượng trong kho phải là số");
                                return;
                            }
                        }

                        // Kiểm tra ngày sản xuất
                        if (ngaySanXuat.isEmpty()) {
                            edtThemNgaySanXuat.setError("Ngày sản xuất không được để trống");
                            return;
                        } else {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                            sdf.setLenient(false);
                            try {
                                sdf.parse(ngaySanXuat);
                            } catch (ParseException e) {

                                edtThemNgaySanXuat.setError("Định dạng ngày tháng không hợp lệ (dd/MM/yyyy)");
                                return;
                            }
                        }

                        // Kiểm tra hạn sử dụng
                        if (hanSuDung.isEmpty()) {
                            edtThemHanSuDung.setError("Hạn sử dụng không được để trống");

                            return;
                        } else {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                            sdf.setLenient(false);
                            try {
                                Date ngaySanXuatDate = sdf.parse(ngaySanXuat);
                                Date hanSuDungDate = sdf.parse(hanSuDung);
                                if (hanSuDungDate.before(ngaySanXuatDate)) {

                                    edtThemHanSuDung.setError("Hạn sử dụng phải sau ngày sản xuất");
                                    return;
                                }
                            } catch (ParseException e) {
                                edtThemHanSuDung.setError("Định dạng ngày tháng không hợp lệ (dd/MM/yyyy)");
                                return;
                            }
                        }


                        SanPham sanPham = new SanPham();
                        sanPham.setAnhsanpham(anhSanPham);
                        sanPham.setTensanpham(tenSanPham);
                        sanPham.setAnhsanpham(linkAnhSanPham);
                        sanPham.setLinkanhsanpham(linkAnhSanPham);
                        sanPham.setGiasanpham(giaSanPham);
                        sanPham.setGiamgia(giamgia);
                        sanPham.setSoluongtrongkho(Integer.parseInt(soLuongTrongKho));
                        sanPham.setNgaysanxuat(ngaySanXuat);
                        sanPham.setHansudung(hanSuDung);
                        for (LoaiSanPham loaiSanPham : list) {
                            if (loaiSanPham.getTenLoaiSanPham().equals(spChonLoaiSanPham.getSelectedItem().toString())) {
                                sanPham.setMaloai(loaiSanPham.getMaLoaiSanPham());
                            }
                        }
                        if (sanPhamDAO.insert(sanPham) > 0) {
                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        } else {
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

    private void spChonLoai() {
        LoaiSanPhamDAO loaiSanPhamDAO1 = new LoaiSanPhamDAO(getContext());
        List<String> lst = new ArrayList<>();
        List<LoaiSanPham> list = (ArrayList<LoaiSanPham>) loaiSanPhamDAO1.getAll();
        for (LoaiSanPham loaiSanPham : list) {
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