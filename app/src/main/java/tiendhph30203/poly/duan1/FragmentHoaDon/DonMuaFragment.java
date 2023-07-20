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

import tiendhph30203.poly.projectdatdoan.DonMua.Adapter_DonMua;
import tiendhph30203.poly.projectdatdoan.DonMua.HoaDon;
import tiendhph30203.poly.projectdatdoan.DonMua.HoaDonDAO;
import tiendhph30203.poly.projectdatdoan.R;

public class DonMuaFragment extends Fragment {
    private RecyclerView recyclerViewDonMua;
    private FloatingActionButton btnThemDonMua;
    HoaDonDAO qlhd;
    ArrayList<HoaDon> list = new ArrayList<>();

    public DonMuaFragment() {

    }


    public static DonMuaFragment newInstance() {
        DonMuaFragment fragment = new DonMuaFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_don_mua, container, false);
        recyclerViewDonMua = view.findViewById(R.id.rcDonMua);


        loadData();
        return view;
    }


    public void loadData() {
        qlhd = new HoaDonDAO(getContext());
        list = (ArrayList<HoaDon>) qlhd.getAll();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewDonMua.setLayoutManager(linearLayoutManager);
        Adapter_DonMua adapterDonMua = new Adapter_DonMua(list, getContext(),qlhd);
        recyclerViewDonMua.setAdapter(adapterDonMua);
    }
}