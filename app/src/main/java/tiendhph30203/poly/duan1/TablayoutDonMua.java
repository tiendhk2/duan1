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

import tiendhph30203.poly.projectdatdoan.AdapterHoaDon.DonMuaAdapter;

public class TablayoutDonMua extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_tablayoutdonmua, container, false);
        TabLayout tabLayout2 = view.findViewById(R.id.tabLayout2);
        ViewPager2 viewPager2 = view.findViewById(R.id.viewPager2);
        DonMuaAdapter adapter = new DonMuaAdapter(getActivity());
        viewPager2.setAdapter(adapter);
        new TabLayoutMediator(tabLayout2, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

                switch (position) {
                    case 0:
                        tab.setText("Chờ xác nhận");
                        break;
                    case 1:
                        tab.setText("Đã xác nhận");
                        break;
                    case 2:
                        tab.setText("Đang giao");
                        break;
                    case 3:
                        tab.setText("Đã giao");
                        break;

                }


            }
        }).attach();
        return view;
    }
}
