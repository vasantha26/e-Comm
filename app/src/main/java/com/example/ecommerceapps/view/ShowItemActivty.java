package com.example.ecommerceapps.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ecommerceapps.adpater.PriceAdapter;
import com.example.ecommerceapps.adpater.ProductAdapter;
import com.example.ecommerceapps.adpater.ShowAllProductAdapter;
import com.example.ecommerceapps.databinding.PriceItemViewBinding;
import com.example.ecommerceapps.databinding.ShowItemActivityBinding;
import com.example.ecommerceapps.databinding.ShowProductViewBinding;
import com.example.ecommerceapps.interfaces.FragmentBottomNavigation;
import com.example.ecommerceapps.model.Product;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ShowItemActivty extends AppCompatActivity implements ShowAllProductAdapter.itemClickListener {

    ShowAllProductAdapter adapter;
    ShowItemActivityBinding binding;
    FragmentBottomNavigation communicator;
    List<Product> productList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ShowItemActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {productList = (List<Product>) bundle.getSerializable("productList");}


        binding.back.setOnClickListener(v -> finish());

        getPriceItems(productList);



    }

    private void  getPriceItems(List<Product> product){
        
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),  LinearLayoutManager.VERTICAL, false));
        adapter = new ShowAllProductAdapter(product ,getApplicationContext() ,this);
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    public void getItemList(List<Product> product, Product product1, int position) {
        Intent intent = new Intent(getApplicationContext(), PriceActivty.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("productList", (ArrayList<Product>) product);
        intent.putExtra("product1", new Gson().toJson(product1));
        intent.putExtras(bundle);
        startActivity(intent);

    }
}
