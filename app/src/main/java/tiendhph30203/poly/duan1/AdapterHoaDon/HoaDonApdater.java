package tiendhph30203.poly.duan1.AdapterHoaDon;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import tiendhph30203.poly.projectdatdoan.FragmentHoaDon.DaGiaoFragment;
import tiendhph30203.poly.projectdatdoan.FragmentHoaDon.DangGiaoFragment;

public class HoaDonApdater extends FragmentStateAdapter {

    public HoaDonApdater(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new DangGiaoFragment();
        }
        return new DaGiaoFragment();
    }


    @Override
    public int getItemCount() {
        return 2;
    }
}
