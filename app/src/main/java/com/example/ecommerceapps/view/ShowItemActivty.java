package com.example.ecommerceapps.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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

        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });


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

    private void filter(String text) {
        ArrayList<Product> filteredlist = new ArrayList<Product>();

        for (Product item : productList) {
            if (item.getTitle().toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            Toast.makeText(getApplicationContext(), "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            adapter.filterList(filteredlist);
        }
    }
}
