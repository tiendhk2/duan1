package tiendhph30203.poly.duan1.FragmentHoaDon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tiendhph30203.poly.projectdatdoan.DonMua.Adapter_DaGiaoCuaKhachHang;
import tiendhph30203.poly.projectdatdoan.DonMua.Adapter_DangGiao;
import tiendhph30203.poly.projectdatdoan.DonMua.HoaDon;
import tiendhph30203.poly.projectdatdoan.DonMua.HoaDonDAO;
import tiendhph30203.poly.projectdatdoan.R;

public class DaGiaoFragment extends Fragment {
    RecyclerView recycleViewDaGiao;
    private HoaDonDAO hoaDonDAO;
    Adapter_DangGiao adapter_donMua;

    RecyclerView recycleViewDangGiao;
    private RecyclerView recyclerViewDonMua;
    HoaDonDAO qlhd;
    ArrayList<HoaDon> list = new ArrayList<>();
    private int trangthai;


    public DaGiaoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        loadData1();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dagiao_fragment, container, false);
        recycleViewDaGiao = view.findViewById(R.id.recycleViewDaGiao);
        loadData1();
        return view;
    }

    public void loadData1() {
        ArrayList<HoaDon> list = new ArrayList<>();
        qlhd = new HoaDonDAO(getContext());
        list = (ArrayList<HoaDon>) qlhd.getTrangThai3();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recycleViewDaGiao.setLayoutManager(linearLayoutManager);
        Adapter_DaGiaoCuaKhachHang adapterDaGiao = new Adapter_DaGiaoCuaKhachHang(list, getContext(),qlhd);
        recycleViewDaGiao.setAdapter(adapterDaGiao);
    }

}

