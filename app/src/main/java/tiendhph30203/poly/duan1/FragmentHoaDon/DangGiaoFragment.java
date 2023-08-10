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

import tiendhph30203.poly.duan1.DonMua.Adapter_DangGiao;
import tiendhph30203.poly.duan1.DonMua.HoaDon;
import tiendhph30203.poly.duan1.DonMua.HoaDonDAO;
import tiendhph30203.poly.projectdatdoan.R;

public class DangGiaoFragment extends Fragment {
    private RecyclerView recycleViewDangGiao;
    HoaDonDAO qlhd;
    HoaDonDAO hoaDonDAO;
    ArrayList<HoaDon> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.danggiao_fragment, container, false);
        recycleViewDangGiao = view.findViewById(R.id.recycleViewDangGiao);
        loadData();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    public void loadData() {
        ArrayList<HoaDon> list = new ArrayList<>();
        qlhd = new HoaDonDAO(getContext());
        list = (ArrayList<HoaDon>) qlhd.getTrangThai2();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recycleViewDangGiao.setLayoutManager(linearLayoutManager);
        Adapter_DangGiao adapter_daXacNhan = new Adapter_DangGiao(list, getContext(),qlhd);
        recycleViewDangGiao.setAdapter(adapter_daXacNhan);
    }

}
