package com.rafi.training.aplikasijson;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Data> dataList = new ArrayList<>();
    Merchant merchant;

    public ProductAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.producthp,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.productname.setText(dataList.get(position).getProductname());
        holder.merchant.setText(dataList.get(position).getMerchant().getMerchantname());
        holder.category.setText( dataList.get(position).getCategory().getCategoryname());
        String baseUrl = "http://192.168.6.221:81/storage/";
        String url = baseUrl+dataList.get(position).getProductimage();
        Glide.with(context)
                .load(url)
                .into(holder.imageView);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent descriptionhandphone = new Intent(context,Description.class);
                descriptionhandphone.putExtra("produk",dataList.get(position));
                context.startActivity(descriptionhandphone);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void addData(ArrayList<Data> dataList) {
        this.dataList = dataList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView productid,productname,merchant,category;
        private ImageView imageView;
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productid=itemView.findViewById(R.id.tv_id);
            productname=itemView.findViewById(R.id.tv_name);
            merchant=itemView.findViewById(R.id.tv_merchant);
            category=itemView.findViewById(R.id.tv_category);
            imageView=itemView.findViewById(R.id.img_hp);
            linearLayout=itemView.findViewById(R.id.ly_product);
        }
    }

}