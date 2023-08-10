package tiendhph30203.poly.duan1.FragmentHoaDon;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import tiendhph30203.poly.duan1.DonMua.HoaDon;
import tiendhph30203.poly.duan1.DonMua.HoaDonChiTiet;
import tiendhph30203.poly.duan1.DonMua.HoaDonChiTietDAO;
import tiendhph30203.poly.duan1.DonMua.HoaDonDAO;
import tiendhph30203.poly.duan1.QuanLyKhachHang.KhachHang;
import tiendhph30203.poly.duan1.QuanLyKhachHang.KhachHangDAO;
import tiendhph30203.poly.projectdatdoan.R;

public class HoaDonChiTietActivity extends AppCompatActivity {
    private RecyclerView rcyHoaDonChiTiet;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    private Toolbar idToolBarLienKetTaiKhoan;
    private KhachHangDAO khachHangDAO;
    private KhachHang khachHang;
    private HoaDonDAO hoaDonDAO;
    private HoaDon hoaDon;
    private TextView tvKhachHang, tvSoDienThoai, tvDiaChi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don_chi_tiet);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);

        } else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        rcyHoaDonChiTiet = findViewById(R.id.recyclerViewHoaDonChiTiet);
        tvKhachHang = findViewById(R.id.tvKhachHangChiTiet);
        tvSoDienThoai = findViewById(R.id.tvSoDienThoaiChiTiet);
        tvDiaChi = findViewById(R.id.tvDiaChiChiTiet);
        hoaDonChiTietDAO = new HoaDonChiTietDAO(getApplicationContext());
        hoaDonDAO = new HoaDonDAO(this);
        khachHangDAO = new KhachHangDAO(this);
        idToolBarLienKetTaiKhoan = findViewById(R.id.idToolBarLK);
        setSupportActionBar(idToolBarLienKetTaiKhoan);
        getSupportActionBar().setTitle("Chi Tiết Hoá Đơn");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        idToolBarLienKetTaiKhoan.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Bundle bundle = getIntent().getExtras();
        int maHoaDon = 0;
        if (bundle != null) {
            maHoaDon = bundle.getInt("mahoadon");
            hoaDon = hoaDonDAO.getID(maHoaDon + "");
            if (hoaDon.getManguoidung() == 0) {
                tvDiaChi.setText("ĐKhông có");
                tvSoDienThoai.setText("Không có");
                tvKhachHang.setText("khách tại quầy");
            } else {
                khachHang = khachHangDAO.getID(hoaDon.getManguoidung() + "");
                tvDiaChi.setText(khachHang.getDiachi());
                tvSoDienThoai.setText(khachHang.getSodienthoai());
                tvKhachHang.setText(khachHang.getHoten());

            }
        }
        List<HoaDonChiTiet> list = hoaDonChiTietDAO.getAll();
        List<HoaDonChiTiet> listXuatHD = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMaHoaDon() == maHoaDon) {
                listXuatHD.add(list.get(i));
            }
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rcyHoaDonChiTiet.setLayoutManager(linearLayoutManager);
        HoaDonChiTietAdapter hoaDonChiTietAdapter = new HoaDonChiTietAdapter(getApplicationContext(), listXuatHD);
        rcyHoaDonChiTiet.setAdapter(hoaDonChiTietAdapter);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
