package tiendhph30203.poly.duan1.GioHang;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tiendhph30203.poly.duan1.DonMua.BottomSheetDialogXacNhanHoaDon;
import tiendhph30203.poly.duan1.DonMua.GioHang;
import tiendhph30203.poly.duan1.DonMua.GioHangDAO2;
import tiendhph30203.poly.duan1.DonMua.HoaDonChiTietDAO;
import tiendhph30203.poly.duan1.DonMua.HoaDonDAO;
import tiendhph30203.poly.duan1.DonMua.Order;
import tiendhph30203.poly.duan1.QuanLyKhachHang.KhachHang;
import tiendhph30203.poly.duan1.QuanLyKhachHang.KhachHangDAO;
import tiendhph30203.poly.projectdatdoan.R;

public class XacNhanThongTinGioHang extends AppCompatActivity {
    private TextView tvKhachHang, tvSoDienThoai, tvDiaChi, tvDiemThuongHoaDon;
    private RecyclerView recyclerViewChiTietHoaDon;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    private Button btnXacNhan, btnXacNhanDiemThuong;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private ArrayList<GioHang> list = new ArrayList<>();
    private CartListComfirmAdapter cartListAdapter;
    private HoaDonDAO hoaDonDAO;
    private GioHangDAO2 gioHangDAO2;
    private Toolbar toolbar;
    private static final int MAX = 99999;
    private static final int MIN = 10000;
    private double tax;
    private static final String KEY_PUT = "order";
    private ArrayList<GioHang> listGioHang;
    private KhachHangDAO khachHangDAO;
    private KhachHang khachHang;
    private ArrayList<KhachHang> listKhachHang;
    private List<PhuongThucThanhToan> listThanhToan;
    private Spinner spinnerThanhToan;
    private EditText edDiemThuongHoaDon;
    private String tenKh, quyen, diachi;
    private int maGioHang, maNV, maKH;
    private Order mOrder;
    private Long soDienThoai;
    private String titlePhuongThucThanhToan, userNameKhachHang = null;
    private LinearLayout linearLayoutManHinhXacNhan;
    private int maQuyen;
    private LinearLayout linearLayoutDiaChi, linerDialog;
    private double diemThuongAll = 0;
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private int diemThuongSend;
    private int maPhuongThucThanhToan;
    private Boolean checkDiaChi = true;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xac_nhan_thong_tin_gio_hang);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);

        } else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        tvKhachHang = findViewById(R.id.tvKhachHang);
        tvSoDienThoai = findViewById(R.id.tvSoDienThoai);
        tvDiaChi = findViewById(R.id.tvDiaChi);


        tvKhachHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String currentText = tvKhachHang.getText().toString().trim();
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(XacNhanThongTinGioHang.this);
//                LayoutInflater inflater = getLayoutInflater();
//                View dialogView = inflater.inflate(R.layout.dialog_edit_tenkhachhang, null);
//                builder.setView(dialogView);
//                EditText editText = dialogView.findViewById(R.id.editText);
//                editText.setText(currentText);
//                builder.setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        String newText = editText.getText().toString().trim();
//                        khachHang.setHoten(newText);
//                        if (khachHangDAO.update(khachHang) > 0) {
//                            dialog.dismiss();
//                            tvKhachHang.setText(newText);
//                            Toast.makeText(XacNhanThongTinGioHang.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(XacNhanThongTinGioHang.this, "Sửa thất bại", Toast.LENGTH_SHORT).show();
//                        }
//
//
//                    }
//                });
//
//                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//
//                // Hiển thị dialog
//                AlertDialog dialog = builder.create();
//                dialog.show();
            }
        });

        tvSoDienThoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String currentText = tvSoDienThoai.getText().toString();
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(XacNhanThongTinGioHang.this);
//                LayoutInflater inflater = getLayoutInflater();
//                View dialogView = inflater.inflate(R.layout.dialog_edit_sodienthoai, null);
//                builder.setView(dialogView);
//                EditText editText = dialogView.findViewById(R.id.editTextsdt);
//                editText.setText(currentText);
//                builder.setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        String newText = editText.getText().toString().trim();
//                        khachHang.setSodienthoai(newText);
//                        if (khachHangDAO.update(khachHang) > 0) {
//                            dialog.dismiss();
//                            tvSoDienThoai.setText(newText);
//                            Toast.makeText(XacNhanThongTinGioHang.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(XacNhanThongTinGioHang.this, "Sửa thất bại", Toast.LENGTH_SHORT).show();
//                        }
//
//
//                    }
//                });
//
//                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//
//                // Hiển thị dialog
//                AlertDialog dialog = builder.create();
//                dialog.show();
            }
        });
        tvDiaChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        recyclerViewChiTietHoaDon = findViewById(R.id.recyclerViewChiTietHoaDon);
        btnXacNhan = findViewById(R.id.btnXacNhan);
        toolbar = findViewById(R.id.idToolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Xác Nhận Thanh Toán");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(XacNhanThongTinGioHang.this, GioHangActivity.class));
            }
        });
        khachHangDAO = new KhachHangDAO(this);
        linearLayoutManHinhXacNhan = findViewById(R.id.linearLayoutManHinhXacNhan);
        linearLayoutDiaChi = findViewById(R.id.linearLayoutDiaChi);


        thongTinHoaDon();
        thongTinNhanHang();
        gioHangDAO2 = new GioHangDAO2(this);
        hoaDonDAO = new HoaDonDAO(this);
        list = (ArrayList<GioHang>) gioHangDAO2.getListCart(maGioHang + "", maQuyen);
        initList();
        tinhTien();
        Log.d("zzzz", "Tong tien" + tinhTien());

        String ten = tvKhachHang.getText().toString();
        String date = sdf.format(new Date());
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openBottomSheetFragment();

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private Double tongTienSauKhiGiam() {

        Double finalDiemThuong = Double.valueOf(0);
        finalDiemThuong = tinhTien();
        btnXacNhan.setText(finalDiemThuong + " VND");
        return finalDiemThuong;
    }

    private void openBottomSheetFragment() {
        Order order = new Order(list, tenKh, soDienThoai, diachi, tinhTien(), maPhuongThucThanhToan, maKH, maNV, maGioHang, maQuyen);
        BottomSheetDialogXacNhanHoaDon bottomSheetDialogXacNhanHoaDon = BottomSheetDialogXacNhanHoaDon.newInstance(order);
        bottomSheetDialogXacNhanHoaDon.show(getSupportFragmentManager(), bottomSheetDialogXacNhanHoaDon.getTag());
        bottomSheetDialogXacNhanHoaDon.setCancelable(false);
    }


//    private void dialogThemDiaChi(Context context, int gravity, KhachHang objKhachHang) {
//        Dialog dialog = new Dialog(context, R.style.PauseDialogAnimation);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.custom_dialog_them_dia_chi);
//
//        Window window = dialog.getWindow();
//        if (window == null) {
//            return;
//        }
//        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
//        window.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.shadowDialog)));
//        WindowManager.LayoutParams windowAttributes = window.getAttributes();
//        windowAttributes.gravity = gravity;
//        window.setAttributes(windowAttributes);
//        EditText edDiaChi = dialog.findViewById(R.id.edDiaChi);
//        Button btnXacNhan = dialog.findViewById(R.id.btnXacNhan);
//        Button btnHuy = dialog.findViewById(R.id.btnHuy);
//        if (Gravity.BOTTOM == gravity) {
//            dialog.setCancelable(true);
//        } else {
//            dialog.setCancelable(false);
//        }
//        KhachHangDAO khachHangDAO = new KhachHangDAO(context);
//        btnXacNhan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String diaChi = edDiaChi.getText().toString().trim();
//                if (diaChi.isEmpty()) {
//                    Toast.makeText(context, "Bạn chưa thay đổi gì cả", Toast.LENGTH_SHORT).show();
//                    dialog.dismiss();
//                    return;
//                }
//                objKhachHang.setDiachi(diaChi);
//                int kq = khachHangDAO.update(objKhachHang);
//                if (kq > 0) {
//                    Toast.makeText(context, "Cật nhật thành công", Toast.LENGTH_SHORT).show();
//                    diachi = diaChi;
//                    tvDiaChi.setText("Địa chỉ:" + diaChi);
//                } else {
//                    Toast.makeText(context, "Cật nhận địa chỉ thất bại", Toast.LENGTH_SHORT).show();
//                }
//                dialog.dismiss();
//            }
//
//        });
//        btnHuy.setOnClickListener(v -> dialog.dismiss());
//
//        dialog.show();
//    }

    private void thongTinNhanHang() {
        SharedPreferences preferences = getSharedPreferences("thongtin", MODE_PRIVATE);
        String userName = (preferences.getString("username", ""));
        String quyen = (preferences.getString("loaitaikhoan", ""));
        if (quyen.equals("nguoidung")) {
            ArrayList<KhachHang> list;
            maQuyen = 1;
//            khachHang = khachHangDAO.getUserName2(userName);
            if (khachHang != null) {
                tvKhachHang.setText(khachHang.getHoten());
                tvSoDienThoai.setText(khachHang.getSodienthoai() + "");
                tvDiaChi.setText(khachHang.getDiachi());
                maGioHang = khachHang.getManguoidung();
                diachi = khachHang.getDiachi();
                tenKh = khachHang.getHoten();
                soDienThoai = Long.valueOf(khachHang.getSodienthoai());
                userNameKhachHang = khachHang.getUsername();
            }
        }

    }

    private double tinhTien() {
        double TongTien;
        double thue = 0.02;
        double phidichvu = 2000;
        tax = Math.round((gioHangDAO2.getTolalFee(maGioHang + "", maQuyen) * thue) * 100) / 100;
        double total = Math.round(((gioHangDAO2.getTolalFee(maGioHang + "", maQuyen) + tax) * 100) / 100);
        btnXacNhan.setText(total + phidichvu + " VND");
        TongTien = total + phidichvu;

        return TongTien;

    }

    public void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewChiTietHoaDon.setLayoutManager(linearLayoutManager);
        cartListAdapter = new CartListComfirmAdapter(list);
        recyclerViewChiTietHoaDon.setAdapter(cartListAdapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerViewChiTietHoaDon.addItemDecoration(itemDecoration);

    }


    private void thongTinHoaDon() {
        SharedPreferences preferences = getSharedPreferences("thongtin", MODE_PRIVATE);
        String userName = (preferences.getString("username", ""));
        String quyen = (preferences.getString("loaitaikhoan", ""));
        if (quyen.equals("nguoidung")) {
            khachHang = khachHangDAO.getUserName2(userName);
            if (khachHang != null) {
                maKH = khachHang.getManguoidung();

            }
        }
    }
}