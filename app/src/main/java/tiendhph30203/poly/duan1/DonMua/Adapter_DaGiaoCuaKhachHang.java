package tiendhph30203.poly.duan1.DonMua;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tiendhph30203.poly.projectdatdoan.FragmentHoaDon.HoaDonChiTietActivity;
import tiendhph30203.poly.projectdatdoan.R;
import tiendhph30203.poly.projectdatdoan.SanPham.DanhGiaSanPham;

public class Adapter_DaGiaoCuaKhachHang extends RecyclerView.Adapter<Adapter_DaGiaoCuaKhachHang.ViewHolder> {
    ArrayList<HoaDon> list;
    Context context;
    private HoaDonDAO hoaDonDAO;


    public Adapter_DaGiaoCuaKhachHang(ArrayList<HoaDon> list, Context context, HoaDonDAO hoaDonDAO) {
        this.list = list;
        this.context = context;
        this.hoaDonDAO = this.hoaDonDAO;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycledagiaocuakhachhang, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtMaHoaDon.setText("" + list.get(position).getMahoadon());
        holder.txtMaNguoiDung.setText("" + list.get(position).getManguoidung());
        holder.txtTenNguoiDung.setText("" + list.get(position).getHoten());
        holder.txtNgayMua.setText("" + list.get(position).getNgaymua());
        holder.txtTongTien.setText("" + list.get(position).getTongtien());
        holder.txtTrangThai.setText("" + list.get(position).getTrangthai());
        SharedPreferences preferences = context.getSharedPreferences("thongtin", Context.MODE_PRIVATE);
        String quyen = (preferences.getString("loaitaikhoan", ""));
        if ((quyen.equals("admin"))) {
            holder.btnDanhGia.setVisibility(View.INVISIBLE);
        }
        holder.btnDanhGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DanhGiaSanPham.class);
                context.startActivity(intent);
            }
        });
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
        } else if (tinhTrang == 2) {
            holder.txtTrangThai.setText("Trạng thái: " + "Chờ lấy hàng");
            holder.txtTrangThai.setTextColor(Color.YELLOW);
        } else if (tinhTrang == 3) {
            holder.txtTrangThai.setText("Trạng thái: " + "Đã giao hàng");
            holder.txtTrangThai.setTextColor(Color.GREEN);

        }


    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMaHoaDon, txtMaNguoiDung, txtMaSanPham, txtTenNguoiDung, txtNgayMua, txtTongTien, txtSoLuongDaMua, txtTrangThai;
        Button btnDanhGia;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnDanhGia = itemView.findViewById(R.id.btnDanhGia);
            txtMaHoaDon = itemView.findViewById(R.id.txtMaHoaDon1);
            txtMaNguoiDung = itemView.findViewById(R.id.txtMaNguoiDung1);
            txtTenNguoiDung = itemView.findViewById(R.id.txtTenNguoiDung1);
            txtNgayMua = itemView.findViewById(R.id.txtNgayMua1);
            txtTongTien = itemView.findViewById(R.id.txtTongTien1);
            txtTrangThai = itemView.findViewById(R.id.txtTrangThai1);
        }
    }

}
