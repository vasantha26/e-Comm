package com.example.ecommerceapps.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ecommerceapps.adpater.PriceAdapter;
import com.example.ecommerceapps.databinding.PriceItemViewBinding;
import com.example.ecommerceapps.interfaces.FragmentBottomNavigation;
import com.example.ecommerceapps.model.Product;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PriceActivty extends AppCompatActivity {

    PriceAdapter adapter;
    PriceItemViewBinding binding;
    FragmentBottomNavigation communicator;
    List<Product> productList;
    Product product;
    List<String> itemList = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = PriceItemViewBinding.inflate(getLayoutInflater());
        setContentView( binding.getRoot());

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {productList = (List<Product>) bundle.getSerializable("productList");}

        String flowerJson = getIntent().getStringExtra("product1");
        if (flowerJson != null) {product = new Gson().fromJson(flowerJson, Product.class);}




        binding.textDescription.setText(product.getDescription());

        binding.ratingBar.setRating(Float.parseFloat(String.valueOf(product.getRating().getRate())));
//        String rating=String.valueOf(binding.ratingBar.getRating());
//        holder.tvAct.setText(rating);
//        holder.productPrice.setText( String.valueOf(product.getPrice()));

        if (itemList  != null){
            itemList.add(product.getImage());

        }

        binding.backButton.setOnClickListener(v -> finish());

        getPriceItems(itemList);



    }

    private void  getPriceItems(List<String> product){

        binding.recyclerViewAllImages.setHasFixedSize(true);
        binding.recyclerViewAllImages.setLayoutManager(new LinearLayoutManager(getApplicationContext(),  LinearLayoutManager.HORIZONTAL, false));
        adapter = new PriceAdapter(product ,getApplicationContext());
        binding.recyclerViewAllImages.setAdapter(adapter);
    }
}
