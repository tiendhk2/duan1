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

import tiendhph30203.poly.projectdatdoan.DonMua.Adapter_DaXacNhanCuaKhachHang;
import tiendhph30203.poly.projectdatdoan.DonMua.HoaDon;
import tiendhph30203.poly.projectdatdoan.DonMua.HoaDonDAO;
import tiendhph30203.poly.projectdatdoan.R;

public class DaXacNhanKhachHangFragment extends Fragment {
    private RecyclerView recyclerViewDonMua;
    private FloatingActionButton btnThemDonMua;
    HoaDonDAO qlhd;
    ArrayList<HoaDon> list = new ArrayList<>();

    public DaXacNhanKhachHangFragment() {

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
        View view = inflater.inflate(R.layout.activity_da_xac_nhan_khach_hang_fragment, container, false);
        recyclerViewDonMua = view.findViewById(R.id.rcDaXacNhan);
        loadData();
        return view;
    }


    public void loadData() {
        qlhd = new HoaDonDAO(getContext());
        list = (ArrayList<HoaDon>) qlhd.getTrangThai1();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewDonMua.setLayoutManager(linearLayoutManager);
        Adapter_DaXacNhanCuaKhachHang adapterDonMua = new Adapter_DaXacNhanCuaKhachHang(list, getContext(),qlhd);
        recyclerViewDonMua.setAdapter(adapterDonMua);
    }


}