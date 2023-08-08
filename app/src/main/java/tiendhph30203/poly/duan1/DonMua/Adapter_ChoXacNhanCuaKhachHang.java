package tiendhph30203.poly.duan1.DonMua;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tiendhph30203.poly.projectdatdoan.FragmentHoaDon.HoaDonChiTietActivity;
import tiendhph30203.poly.projectdatdoan.R;

public class Adapter_ChoXacNhanCuaKhachHang extends RecyclerView.Adapter<Adapter_ChoXacNhanCuaKhachHang.ViewHolder> {

    ArrayList<HoaDon> list;
    Context context;
    private HoaDonDAO hoaDonDAO;

    public Adapter_ChoXacNhanCuaKhachHang(ArrayList<HoaDon> list, Context context, HoaDonDAO hoaDonDAO) {
        this.list = list;
        this.context = context;
        this.hoaDonDAO = hoaDonDAO;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recyclechoxacnhancuakhachhang, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        HoaDon hoaDon = list.get(position);
        holder.txtMaHoaDon.setText("" + hoaDon.getMahoadon());
        holder.txtMaNguoiDung.setText("" +hoaDon.getManguoidung());
        holder.txtTenNguoiDung.setText("" + hoaDon.getHoten());
        holder.txtNgayMua.setText("" + hoaDon.getNgaymua());
        holder.txtTongTien.setText("" + hoaDon.getTongtien());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HoaDonChiTietActivity.class);
                intent.putExtra("mahoadon", hoaDon.getMahoadon());
                context.startActivity(intent);

            }
        });

        holder.huydonhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HoaDonDAO hoaDonDAO = new HoaDonDAO(context);
                int check = hoaDonDAO.delete(list.get(position).getMahoadon());
                if (check > 0) {
                    list.clear();
                    list = (ArrayList<HoaDon>) hoaDonDAO.getTrangThai0();
                    notifyDataSetChanged();
                    Toast.makeText(context, "Đã hủy đơn hàng thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Đã có sản phẩm, không thể xóa", Toast.LENGTH_SHORT).show();
                }
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
        Button huydonhang;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaHoaDon = itemView.findViewById(R.id.txtMaHoaDon2);
            txtMaNguoiDung = itemView.findViewById(R.id.txtMaNguoiDung2);
            txtTenNguoiDung = itemView.findViewById(R.id.txtTenNguoiDung2);
            txtNgayMua = itemView.findViewById(R.id.txtNgayMua2);
            txtTongTien = itemView.findViewById(R.id.txtTongTien2);
            txtTrangThai = itemView.findViewById(R.id.txtTrangThai2);
            huydonhang = itemView.findViewById(R.id.huydonhang);
        }
    }
}
