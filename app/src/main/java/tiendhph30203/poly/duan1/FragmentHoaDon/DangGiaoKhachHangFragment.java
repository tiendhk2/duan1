package tiendhph30203.poly.duan1.FragmentHoaDon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import tiendhph30203.poly.projectdatdoan.DonMua.Adapter_DangGiaoCuaKhachHang;
import tiendhph30203.poly.projectdatdoan.DonMua.HoaDon;
import tiendhph30203.poly.projectdatdoan.DonMua.HoaDonDAO;
import tiendhph30203.poly.projectdatdoan.R;

public class DangGiaoKhachHangFragment extends Fragment {
    private RecyclerView recyclerViewDonMua;
    private FloatingActionButton btnThemDonMua;
    HoaDonDAO qlhd;
    HoaDonDAO hoaDonDAO;
    ArrayList<HoaDon> list;

    public DangGiaoKhachHangFragment() {

    }


    public static ChoXacNhanKhachHangFragment newInstance() {
        ChoXacNhanKhachHangFragment fragment = new ChoXacNhanKhachHangFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.danggiaokhachhangfragment, container, false);
        recyclerViewDonMua = view.findViewById(R.id.rcDangGiaoKhachHang);
        loadData();
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    public void loadData() {
        qlhd = new HoaDonDAO(getContext());
        list = (ArrayList<HoaDon>) qlhd.getTrangThai2();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewDonMua.setLayoutManager(linearLayoutManager);
        Adapter_DangGiaoCuaKhachHang adapterDonMua = new Adapter_DangGiaoCuaKhachHang(list, getContext(),qlhd);
        recyclerViewDonMua.setAdapter(adapterDonMua);
    }
}
