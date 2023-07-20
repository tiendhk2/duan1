package tiendhph30203.poly.duan1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import tiendhph30203.poly.projectdatdoan.AdapterHoaDon.HoaDonApdater;

public class Tablayoutactivity extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_tablayoutactivity, container, false);
        TabLayout tabLayoutChi = view.findViewById(R.id.tabLayoutChi);
        ViewPager2 viewPager2Chi = view.findViewById(R.id.viewPager2Chi);
        HoaDonApdater adapter = new HoaDonApdater(getActivity());
        viewPager2Chi.setAdapter(adapter);
        new TabLayoutMediator(tabLayoutChi, viewPager2Chi, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position == 0) {
                    tab.setText("Đang Giao");

                } else {
                    tab.setText("Đã Giao");
                }

            }
        }).attach();
        return view;
    }
}