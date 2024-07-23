package com.example.ecommerceapps.bottomfragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ecommerceapps.R;
import com.example.ecommerceapps.adpater.PopularAdapter;
import com.example.ecommerceapps.adpater.ShowAllProductAdapter;
import com.example.ecommerceapps.databinding.FragmentCartBinding;
import com.example.ecommerceapps.model.Product;
import com.example.ecommerceapps.view.ShowItemActivty;
import com.example.ecommerceapps.viewmodel.ViewModal;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment implements ShowAllProductAdapter.itemClickListener {

    ShowAllProductAdapter adapter;


    FragmentCartBinding binding;

    ViewModal viewModal;

    List<Product> product = new ArrayList<>();




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(inflater ,container,false);


        binding.emtpy.setVisibility(View.VISIBLE);


        viewModal = new ViewModelProvider(this).get(ViewModal.class);


        binding.back.setOnClickListener(v -> {

        });


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


        viewModal.getAllCart().observe(getViewLifecycleOwner(), products ->{
                    this.product = products;
                    binding.emtpy.setVisibility(View.GONE);
                    binding.recyclerView.setVisibility(View.VISIBLE);
                    showProductRecyclerView(products);
                });


        return binding.getRoot();

    }

    private void showProductRecyclerView(List<Product> products) {

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new ShowAllProductAdapter(products ,getContext() ,this);
        binding.recyclerView.setAdapter(adapter);
    }



    @Override
    public void getItemList(List<Product> product, Product product1, int position) {

    }

    private void filter(String text) {
        ArrayList<Product> filteredlist = new ArrayList<Product>();

        for (Product item : product) {
            if (item.getTitle().toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            Toast.makeText(getContext(), "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            adapter.filterList(filteredlist);
        }
    }
}
