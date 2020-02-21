package com.rafi.training.aplikasijson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Description extends AppCompatActivity {
    public TextView tvNamadesc, tvMerchantDesc, tvJumlahDesc, tvMiniprodukDesc,tvMiniSlugmerchant,tvMiniCategoryDesc,tvMiniNameCategoryDesc,tvMiniRamCategorydesc;
    public ImageView lvProductDescku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        Data productku = getIntent().getParcelableExtra("produk");

        String namaProduk = productku.productname;
        int jumlahProduk = productku.productQty;
        String slugproduk = productku.productSlug;
        String merchantProduk = productku.getMerchant().getMerchantname();
        String kategoriProduk = productku.getCategory().getCategoryname();

        tvNamadesc=findViewById(R.id.tv_name_desc);
        tvNamadesc.setText(namaProduk);
        tvMerchantDesc=findViewById(R.id.tv_name_desc);
        tvMerchantDesc.setText(merchantProduk);
        tvJumlahDesc=findViewById(R.id.tv_desc_id);
        tvJumlahDesc.setText(jumlahProduk);
        tvMiniprodukDesc=findViewById(R.id.tv_desc_produk);
        tvMiniprodukDesc.setText(namaProduk);
        tvMiniSlugmerchant=findViewById(R.id.tv_desc_slog);
        tvMiniSlugmerchant.setText(slugproduk);
        tvMiniCategoryDesc=findViewById(R.id.tv_desc_kategori);
        tvMiniCategoryDesc.setText(kategoriProduk);
        tvMiniNameCategoryDesc=findViewById(R.id.tv_desc_hp);
        tvMiniNameCategoryDesc.setText(namaProduk);
        tvMiniRamCategorydesc=findViewById(R.id.tv_id);
        tvMiniRamCategorydesc.setText(jumlahProduk);

        String baseUrl = "http://192.168.6.221:81/storage";
        String url = baseUrl+productku.getProductimage();
        Glide.with(this).load(url).into(lvProductDescku);

    }
}
