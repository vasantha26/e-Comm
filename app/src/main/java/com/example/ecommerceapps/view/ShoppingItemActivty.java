package com.example.ecommerceapps.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ecommerceapps.adpater.ShowAllProductAdapter;
import com.example.ecommerceapps.databinding.ShoppItemAcitivtyBinding;
import com.example.ecommerceapps.databinding.ShowItemActivityBinding;
import com.example.ecommerceapps.interfaces.FragmentBottomNavigation;
import com.example.ecommerceapps.model.Product;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ShoppingItemActivty extends AppCompatActivity  {

    ShoppItemAcitivtyBinding binding;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ShoppItemAcitivtyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String priceJson = getIntent().getStringExtra("price");

        binding.money.setText(priceJson);

        binding.paynow.setOnClickListener(v -> {
            Toast.makeText(this ,"Coming Soon", Toast.LENGTH_SHORT).show();
        });

        binding.upi.setOnClickListener(v -> {
            Toast.makeText(this ,"Coming Soon", Toast.LENGTH_SHORT).show();
        });

           binding.Card.setOnClickListener(v -> {
            Toast.makeText(this ,"Coming Soon", Toast.LENGTH_SHORT).show();
        });

           binding.back.setOnClickListener(v -> {
               Intent intent = new Intent(this ,ShowItemActivty.class);
               startActivity(intent);
        });




        binding.back.setOnClickListener(v -> finish());





    }

}
