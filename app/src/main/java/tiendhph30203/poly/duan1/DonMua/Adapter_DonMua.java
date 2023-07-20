package tiendhph30203.poly.duan1.DonMua;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tiendhph30203.poly.projectdatdoan.R;

public class Adapter_DonMua extends RecyclerView.Adapter<Adapter_DonMua.ViewHolder> {

    ArrayList<HoaDon> list;
    Context context;
    private HoaDonDAO hoaDonDAO;

    public Adapter_DonMua(ArrayList<HoaDon> list, Context context, HoaDonDAO hoaDonDAO) {
        this.list = list;
        this.context = context;
        this.hoaDonDAO = hoaDonDAO;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycledonmua, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtMaHoaDon.setText("Mã hóa đơn: " + list.get(position).getMahoadon());
        holder.txtMaNguoiDung.setText("Mã người dùng: " + list.get(position).getManguoidung());
        holder.txtMaSanPham.setText("Mã sản phẩm: " + list.get(position).getMasanpham());
        holder.txtTenNguoiDung.setText("Tên người dùng: " + list.get(position).getHoten());
        holder.txtNgayMua.setText("Ngày mua: " + list.get(position).getNgaymua());
        holder.txtTongTien.setText("Tổng tiền: " + list.get(position).getTongtien());

        holder.txtSoLuongDaMua.setText("Số lượng đã mua: " + list.get(position).getSoluongdamua());
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

    public  class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMaHoaDon, txtMaNguoiDung, txtMaSanPham, txtTenNguoiDung, txtNgayMua, txtTongTien, txtSoLuongDaMua, txtTrangThai;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaHoaDon = itemView.findViewById(R.id.txtMaHoaDon2);
            txtMaNguoiDung = itemView.findViewById(R.id.txtMaNguoiDung2);
            txtMaSanPham = itemView.findViewById(R.id.txtMaSanPham2);
            txtTenNguoiDung = itemView.findViewById(R.id.txtTenNguoiDung2);
            txtNgayMua = itemView.findViewById(R.id.txtNgayMua2);
            txtTongTien = itemView.findViewById(R.id.txtTongTien2);
            txtSoLuongDaMua = itemView.findViewById(R.id.txtSoLuongDaMua2);
            txtTrangThai = itemView.findViewById(R.id.txtTrangThai2);



        }
    }
}
