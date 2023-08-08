package tiendhph30203.poly.duan1.GioHang;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tiendhph30203.poly.projectdatdoan.DonMua.GioHang;
import tiendhph30203.poly.projectdatdoan.R;

public class CartListComfirmAdapter extends RecyclerView.Adapter<CartListComfirmAdapter.CartListViewHolder> {
    private ArrayList<GioHang> list;


    public CartListComfirmAdapter(ArrayList<GioHang> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public CartListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cart_list_comfirm_adapter, parent, false);
        return new CartListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartListViewHolder holder, int position) {
        final GioHang obj = list.get(position);
        if(obj == null){
            return;
        }
//        if(obj.getAnhsanpham() == null){
////            Picasso.get().load(obj.getLinkAnhSP()).into(holder.imgSanPham);
//        }else if(obj.getLinkanhsanpham() == null){
////            Bitmap bitmap = BitmapFactory.decodeByteArray(obj.getAnhSP(),0,obj.getAnhSP().length);
////            holder.imgSanPham.setImageBitmap(bitmap);
//        }
        holder.tvTenSP.setText("Sản phẩm: " +obj.getTensanpham());
        holder.tvSoLuong.setText("Số lượng: " +obj.getSoluong()+"");
        holder.tvThanhTien.setText("Thành tiền: " +(Math.round(obj.getSoluong()*obj.getGiasanpham()*100)/100) +" VND");

    }

    @Override
    public int getItemCount() {
        if(list != null){
            return list.size();
        }
        return 0;
    }

    public class CartListViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgSanPham;

        private TextView tvTenSP,tvSoLuong,tvThanhTien;
        public CartListViewHolder(@NonNull View itemView) {
            super(itemView);
            imgSanPham = itemView.findViewById(R.id.imgSanPham);
            tvTenSP = itemView.findViewById(R.id.tvTenSP);
            tvSoLuong = itemView.findViewById(R.id.tvSoLuong);
            tvThanhTien = itemView.findViewById(R.id.tvThanhTien);


        }
    }
}
