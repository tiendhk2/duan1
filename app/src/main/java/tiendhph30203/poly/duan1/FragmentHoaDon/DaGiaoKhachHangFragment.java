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

import tiendhph30203.poly.duan1.DonMua.Adapter_DaGiaoCuaKhachHang;
import tiendhph30203.poly.duan1.DonMua.HoaDon;
import tiendhph30203.poly.duan1.DonMua.HoaDonDAO;
import tiendhph30203.poly.projectdatdoan.R;

public class DaGiaoKhachHangFragment extends Fragment {

    private RecyclerView recyclerViewDonMua2;
    private FloatingActionButton btnThemDonMua;
    HoaDonDAO qlhd;
    ArrayList<HoaDon> list = new ArrayList<>();

    public DaGiaoKhachHangFragment() {

    }


    public static DaGiaoKhachHangFragment newInstance() {
        DaGiaoKhachHangFragment fragment = new DaGiaoKhachHangFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dagiaocuakhachhang, container, false);
        recyclerViewDonMua2 = view.findViewById(R.id.rcDonMua2);


        loadData();
        return view;
    }


    public void loadData() {
        qlhd = new HoaDonDAO(getContext());
        list = (ArrayList<HoaDon>) qlhd.getTrangThai3();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewDonMua2.setLayoutManager(linearLayoutManager);
        Adapter_DaGiaoCuaKhachHang adapterDonMua = new Adapter_DaGiaoCuaKhachHang(list, getContext(),qlhd);
        recyclerViewDonMua2.setAdapter(adapterDonMua);
    }
}
