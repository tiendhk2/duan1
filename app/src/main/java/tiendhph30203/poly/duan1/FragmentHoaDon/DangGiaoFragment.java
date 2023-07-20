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

import tiendhph30203.poly.projectdatdoan.DonMua.Adapter_DangGiao;
import tiendhph30203.poly.projectdatdoan.DonMua.HoaDon;
import tiendhph30203.poly.projectdatdoan.DonMua.HoaDonDAO;
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
//        loadData(1 ,null);

        loadData();
        return view;
    }




//
//    private void loadData(int id, String maKH) {
//        ArrayList<HoaDon> listDXN = null;
//        if (id == 1) {
//            ArrayList<HoaDon> list = (ArrayList<HoaDon>) hoaDonDAO.getAll();
//            listDXN = new ArrayList<>();
//            for (int i = 0; i < list.size(); i++) {
//                if (list.get(i).getTrangthai() == 1) {
//                    listDXN.add(list.get(i));
//                }
//            }
//        } else if (id == 2) {
//            ArrayList<HoaDon> list = (ArrayList<HoaDon>) hoaDonDAO.getAllKH(maKH);
//            listDXN = new ArrayList<>();
//            for (int i = 0; i < list.size(); i++) {
//                if (list.get(i).getTrangthai() == 1) {
//                    listDXN.add(list.get(i));
//                }
//            }
//        }
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//        rcDonMua.setLayoutManager(linearLayoutManager);
//        Adapter_DangGiao adapter_dangGiao = new Adapter_DangGiao(list, getContext(),hoaDonDAO);
//        rcDonMua.setAdapter(adapter_dangGiao);
//    }

    public void loadData() {
        ArrayList<HoaDon> list = new ArrayList<>();
        qlhd = new HoaDonDAO(getContext());
        list = (ArrayList<HoaDon>) qlhd.getAll();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recycleViewDangGiao.setLayoutManager(linearLayoutManager);
        Adapter_DangGiao adapter_phieuMuon = new Adapter_DangGiao(list, getContext(),qlhd);
        recycleViewDangGiao.setAdapter(adapter_phieuMuon);
    }

}
