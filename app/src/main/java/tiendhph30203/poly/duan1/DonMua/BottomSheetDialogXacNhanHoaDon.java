package tiendhph30203.poly.duan1.DonMua;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import tiendhph30203.poly.projectdatdoan.MainActivity;
import tiendhph30203.poly.projectdatdoan.QuanLyKhachHang.KhachHang;
import tiendhph30203.poly.projectdatdoan.QuanLyKhachHang.KhachHangDAO;
import tiendhph30203.poly.projectdatdoan.R;
import tiendhph30203.poly.projectdatdoan.SanPham.SanPham;
import tiendhph30203.poly.projectdatdoan.SanPham.SanPhamDAO;

public class BottomSheetDialogXacNhanHoaDon extends BottomSheetDialogFragment {

    private static final String KEY_PUT = "order";
    private static final int MAX = 99999;
    private static final int MIN = 10000;
    private Order mOrder;
    private HoaDonDAO hoaDonDAO;
    private TextView tvTienDonHang, tvNameandSodienThoai, tvDiaChi, tvSanPham, tvPhuongThucThanhToan, tvThanhToanTaiQuay;
    private Button btnXacNhanDonHang, btnHuyNhanDonHang;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private GioHangDAO2 gioHangDAO2;
    private ArrayList<GioHang> listGioHang;
    private ArrayList<HoaDon> listHoaDon;
    private KhachHangDAO khachHangDAO;
    private KhachHang khachHang;
    private LinearLayout linerBottomSheet;
    private Dialog dialog;
    private LinearLayout dialogPros;
    private SanPhamDAO sanPhamDAO;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    private SanPham objSanPham;

    public static BottomSheetDialogXacNhanHoaDon newInstance(Order order) {
        BottomSheetDialogFragment bottomSheetDialogFragment = new BottomSheetDialogXacNhanHoaDon();
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_PUT, order);
        bottomSheetDialogFragment.setArguments(bundle);
        return (BottomSheetDialogXacNhanHoaDon) bottomSheetDialogFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mOrder = (Order) bundle.get(KEY_PUT);

        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.activity_bottom_sheet_dialog_xac_nhan_hoa_don, null);
        bottomSheetDialog.setContentView(view);
        initView(view);
        setDataOrder();

        hoaDonDAO = new HoaDonDAO(getContext());
        btnHuyNhanDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });
        khachHangDAO = new KhachHangDAO(getContext());

        btnXacNhanDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuatHoaDon();



            }
        });
        return bottomSheetDialog;
    }

    private void xuatHoaDon() {
        String date = sdf.format(new Date());
        if (mOrder.getMaKH() != 0 && mOrder.getPhuongThucThanhToan() == 1) {
            khachHang = khachHangDAO.getID(mOrder.getMaKH() + "");
            int kq = khachHangDAO.update(khachHang);
            if (kq > 0) {
                Log.d("zzzzzz", "Thanh toán thành công ");
            } else {
                Log.d("zzzzzz", "Thanh toán thất bại");
            }

        }
        HoaDon hoaDon = new HoaDon();

        Random random = new Random();
        int numberRandom;
        do{
            numberRandom = random.nextInt(MAX - MIN + 1) + MIN;
        }while (!(hoaDonDAO.checkMaHoaDon(numberRandom+"") < 0));
        hoaDon.setMahoadon(numberRandom);

        hoaDon.setHoten("Dương Tiến");
        hoaDon.setTongtien((int) mOrder.getTongTien());
        hoaDon.setTrangthai(0);
        if(mOrder.getMaKH() != 0){
            hoaDon.setManguoidung(mOrder.getMaKH());
        }
        hoaDon.setMasanpham(1);

        hoaDon.setNgaymua(date);
        if (hoaDonDAO.insert(hoaDon) > 0) {
            Toast.makeText(getContext(), "Thanh toán thành công vui lòng chờ !", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getContext(), MainActivity.class));
            insertHoaDonChiTiet(numberRandom);
        } else {
            Log.d("zzzzzz", "Thanh toán thất bại ");
        }


    }


    @Override
    public void onDetach() {
        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
        super.onDetach();
    }


    private void insertHoaDonChiTiet(int maHoaDon) {
        gioHangDAO2 = new GioHangDAO2(getContext());
        sanPhamDAO = new SanPhamDAO(getContext());
        hoaDonChiTietDAO = new HoaDonChiTietDAO(getContext());
        listGioHang = gioHangDAO2.getListCart(mOrder.getMaOrder() + "", mOrder.getMaQuyen());
        for (GioHang obj : listGioHang) {
            HoaDonChiTiet objHoaDonChiTiet = new HoaDonChiTiet();
            objHoaDonChiTiet.setMaHoaDon(maHoaDon);
            objHoaDonChiTiet.setMaSP(obj.getMasanpham());
            objHoaDonChiTiet.setDonGia(obj.getGiasanpham());
            objHoaDonChiTiet.setSoLuong(obj.getSoluong());
            objSanPham = sanPhamDAO.getID(objHoaDonChiTiet.getMaSP() + "");
            if (objSanPham != null) {
                objSanPham.setSoluongtrongkho(objSanPham.getSoluongtrongkho() - objHoaDonChiTiet.getSoLuong());
                int kq = sanPhamDAO.update(objSanPham);
                if (kq > 0) {
                    Log.d("zzzzzz", "Sửa số lượng thành công ");
                } else {
                    Log.d("zzzzzz", "Sửa số lượng thất bại ");
                }
            }
            long kq = hoaDonChiTietDAO.insert(objHoaDonChiTiet);
            if (kq > 0) {
                Log.d("zzzzzz", "thêm vào hóa đơn chi tiết thành công: ");
                gioHangDAO2.deleteGioHang(mOrder.getMaOrder() + "", mOrder.getMaQuyen());

            } else {
                Log.d("zzzzzz", "thêm vào hóa đơn chi tiết thất bại ");
            }
        }
        Toast.makeText(getContext(), "Thanh toán thành công vui lòng chờ !", Toast.LENGTH_SHORT).show();

    }


    private void setDataOrder() {
        if (mOrder == null) {
            return;
        }
         String data; // Biến để lưu dữ liệu được truyền vào từ Bundl
        // Lấy dữ liệu từ Bundle (nếu có)
        if (getArguments() != null) {
            data = getArguments().getString("hoten"); // "key_data" là key được sử dụng để truyền dữ liệu
        }

        // Xử lý dữ liệu tại đây (ví dụ: hiển thị dữ liệu lên giao diện)
        SharedPreferences preferences = getActivity().getSharedPreferences("thongtin", Context.MODE_PRIVATE);
        String quyen = (preferences.getString("quyen", ""));
        tvTienDonHang.setText(mOrder.getTongTien() + " VND");
        tvSanPham.setText(mOrder.getListProduct());
        tvDiaChi.setText(mOrder.getDiaChi() == null ? "Địa chỉ: không có" : "Địa chỉ : " + mOrder.getDiaChi());
        if (quyen.equals("nguoidung")) {
            tvThanhToanTaiQuay.setVisibility(View.VISIBLE);
        } else {
            tvThanhToanTaiQuay.setVisibility(View.INVISIBLE);
        }
        if (mOrder.getTenKhachHang() == null && mOrder.getSoDienThoai() == null && mOrder.getDiaChi() == null) {
            tvNameandSodienThoai.setText("Khách tại của hàng");
        } else {
            tvNameandSodienThoai.setText(mOrder.getTenKhachHang() + " - 0" + mOrder.getSoDienThoai());
        }
        if (mOrder.getPhuongThucThanhToan() == 1) {
            tvPhuongThucThanhToan.setText("Thanh toán khi nhận hàng");
        } else if (mOrder.getPhuongThucThanhToan() == 2) {
            tvPhuongThucThanhToan.setText("Thanh toán khi nhận hàng");
        }

    }

    private void initView(View view) {
        tvTienDonHang = view.findViewById(R.id.tvTienDonHang);
        tvSanPham = view.findViewById(R.id.tvSanPham);
        tvPhuongThucThanhToan = view.findViewById(R.id.tvPhuongThucThanhToan);
        tvNameandSodienThoai = view.findViewById(R.id.tvNameandSodienThoai);
        tvDiaChi = view.findViewById(R.id.tvDiaChi);
        btnXacNhanDonHang = view.findViewById(R.id.btnXacNhanDonHangOk);
        btnHuyNhanDonHang = view.findViewById(R.id.btnHuyNhanDonHang);
        tvThanhToanTaiQuay = view.findViewById(R.id.tvThanhToanTaiQuay);
        linerBottomSheet = view.findViewById(R.id.linerBottomSheet);
    }
}