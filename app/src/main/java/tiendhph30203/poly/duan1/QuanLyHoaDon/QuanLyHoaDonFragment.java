package tiendhph30203.poly.duan1.QuanLyHoaDon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import tiendhph30203.poly.projectdatdoan.R;

public class QuanLyHoaDonFragment extends Fragment {


    public QuanLyHoaDonFragment() {

    }


    public static QuanLyHoaDonFragment newInstance() {
        QuanLyHoaDonFragment fragment = new QuanLyHoaDonFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quan_ly_hoa_don, container, false);
        return view;
    }
}