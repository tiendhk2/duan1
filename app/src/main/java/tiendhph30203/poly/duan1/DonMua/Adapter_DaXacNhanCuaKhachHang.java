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

public class Adapter_DaXacNhanCuaKhachHang extends RecyclerView.Adapter<Adapter_DaXacNhanCuaKhachHang.ViewHolder> {

    ArrayList<HoaDon> list;
    Context context;
    private HoaDonDAO hoaDonDAO;

    public Adapter_DaXacNhanCuaKhachHang(ArrayList<HoaDon> list, Context context, HoaDonDAO hoaDonDAO) {
        this.list = list;
        this.context = context;
        this.hoaDonDAO = this.hoaDonDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycledaxacnhancuakhachhang, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtMaHoaDon.setText("" + list.get(position).getMahoadon());
        holder.txtMaNguoiDung.setText("" + list.get(position).getManguoidung());
        holder.txtTenNguoiDung.setText("" + list.get(position).getHoten());
        holder.txtNgayMua.setText("" + list.get(position).getNgaymua());
        holder.txtTongTien.setText("" + list.get(position).getTongtien());
        holder.txtTrangThai.setText("" + list.get(position).getTrangthai());
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
            holder.txtTrangThai.setText("Trạng thái: " + "Đã xác nhận");
            holder.txtTrangThai.setTextColor(Color.YELLOW);
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
            txtMaHoaDon = itemView.findViewById(R.id.txtMaHoaDon2);
            txtMaNguoiDung = itemView.findViewById(R.id.txtMaNguoiDung2);
            txtTenNguoiDung = itemView.findViewById(R.id.txtTenNguoiDung2);
            txtNgayMua = itemView.findViewById(R.id.txtNgayMua2);
            txtTongTien = itemView.findViewById(R.id.txtTongTien2);
            txtTrangThai = itemView.findViewById(R.id.txtTrangThai2);
        }
    }
}
