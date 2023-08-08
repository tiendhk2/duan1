package tiendhph30203.poly.duan1.DonMua;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tiendhph30203.poly.projectdatdoan.FragmentHoaDon.HoaDonChiTietActivity;
import tiendhph30203.poly.projectdatdoan.R;

public class Adapter_DonMua2 extends RecyclerView.Adapter<Adapter_DonMua2.ViewHolder> {

    ArrayList<HoaDon> list;
    Context context;
    private HoaDonDAO hoaDonDAO;

    public Adapter_DonMua2(ArrayList<HoaDon> list, Context context, HoaDonDAO hoaDonDAO) {
        this.list = list;
        this.context = context;
        this.hoaDonDAO = hoaDonDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycledonmua2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtMaHoaDon.setText("" + list.get(position).getMahoadon());
        holder.txtMaNguoiDung.setText("" + list.get(position).getManguoidung());
        holder.txtTenNguoiDung.setText("" + list.get(position).getHoten());
        holder.txtNgayMua.setText("" + list.get(position).getNgaymua());
        holder.txtTongTien.setText("" + list.get(position).getTongtien());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HoaDonChiTietActivity.class);
                intent.putExtra("mahoadon", list.get(position).getMahoadon());
                context.startActivity(intent);

            }
        });
        int tinhTrang = list.get(position).getTrangthai();
        if (tinhTrang == 0) {
            holder.txtTrangThai.setText("Trạng thái: " + "Chờ xác nhận");
            holder.txtTrangThai.setTextColor(Color.RED);
        } else if (tinhTrang == 1) {
            holder.txtTrangThai.setText("Trạng thái: " + "Đã giao");
            holder.txtTrangThai.setTextColor(Color.GREEN);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMaHoaDon, txtMaNguoiDung, txtMaSanPham, txtTenNguoiDung, txtNgayMua, txtTongTien, txtSoLuongDaMua, txtTrangThai;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaHoaDon = itemView.findViewById(R.id.txtMaHoaDon3);
            txtMaNguoiDung = itemView.findViewById(R.id.txtMaNguoiDung3);
            txtTenNguoiDung = itemView.findViewById(R.id.txtTenNguoiDung3);
            txtNgayMua = itemView.findViewById(R.id.txtNgayMua3);
            txtTongTien = itemView.findViewById(R.id.txtTongTien3);
            txtTrangThai = itemView.findViewById(R.id.txtTrangThai3);


        }
    }
}
