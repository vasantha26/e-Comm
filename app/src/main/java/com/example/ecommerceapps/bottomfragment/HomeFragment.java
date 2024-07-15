package com.example.ecommerceapps.bottomfragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerceapps.model.Account;
import com.example.ecommerceapps.view.ShowItemActivty;
import com.example.ecommerceapps.viewmodel.ViewModal;
import com.example.ecommerceapps.adpater.PopularAdapter;
import com.example.ecommerceapps.databinding.ActivityHomeBinding;
import com.example.ecommerceapps.interfaces.FragmentBottomNavigation;
import com.example.ecommerceapps.interfaces.FragmentNavigation;
import com.example.ecommerceapps.model.Product;
import com.example.ecommerceapps.adpater.ProductAdapter;
import com.example.ecommerceapps.adpater.ProductCategoryAdapter;
import com.example.ecommerceapps.retrofit.ApiService;
import com.example.ecommerceapps.retrofit.RetrofitClient;
import com.example.ecommerceapps.loginfragment.LoginFragment;
import com.example.ecommerceapps.view.PriceActivty;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements ProductAdapter.itemClickListener, ProductCategoryAdapter.itemClickListener {

    ProductAdapter adapter;
    PopularAdapter popularAdapter;
    ProductCategoryAdapter productCategoryAdapter;

    ActivityHomeBinding binding;

    FragmentBottomNavigation communicator;

    ViewModal viewModal;

    List<Product> product = new ArrayList<>();
    List<Account> accounts = new ArrayList<>();


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            communicator = (FragmentBottomNavigation) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement FragmentToActivityCommunicator");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = ActivityHomeBinding.inflate(inflater, container, false);

        communicator.navigateBottomFrag(3, true);

        getProducts();

        fetchCategories();

        viewModal = new ViewModelProvider(this).get(ViewModal.class);

        viewModal.getAllProducts().observe(getViewLifecycleOwner(), products -> {
            setProductRecyclerView(products);
            showProductRecyclerView(products);
        });

        viewModal.getAllAccount().observe(getViewLifecycleOwner(), account -> this.accounts = account);

        viewModal.getAllFlowersItems().observe(getViewLifecycleOwner(), products -> this.product = products);

//        binding.logout.setOnClickListener(v -> {
//
//            FragmentNavigation navRegister = (FragmentNavigation) getActivity();
//            if (navRegister != null) {
//                navRegister.navigateFrag(new LoginFragment(), false);
//            }
//
//            for (Account account: accounts) {
//                viewModal.delete(account);
//            }
//        });

        binding.seeAll.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ShowItemActivty.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("productList", (ArrayList<Product>) product);
            intent.putExtras(bundle);
            startActivity(intent);
        });


        return binding.getRoot();


    }


    private void getProducts() {

        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<List<Product>> call = apiService.getProducts();

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(@NonNull Call<List<Product>> call, @NonNull Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    for (Product prodcut : response.body()) {
                        viewModal.insert(prodcut);
                    }
                } else {
                    Log.e("getProducts", "Failed to fetch data");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Product>> call, @NonNull Throwable t) {
                Log.e("getProducts", "Error occurred while fetching data", t.getCause());
            }
        });

    }

    private void fetchCategories() {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        Call<List<String>> call = apiService.getCategories();
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(@NonNull Call<List<String>> call, @NonNull Response<List<String>> response) {
                if (response.isSuccessful()) {
                    List<String> categories = response.body();
                    if (categories != null) {
                        setProductRecycler(categories);
                    }
                } else {
                    Toast.makeText(getContext(), "Failed to retrieve categories", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<String>> call, Throwable t) {
                Log.e("getProducts", "t.getMessage().toString() , " + t);

            }
        });

    }

    private void setProductRecyclerView(List<Product> products) {
        binding.productRecyclerView2.setHasFixedSize(true);
        binding.productRecyclerView2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new ProductAdapter(products, getContext(), this);
        binding.productRecyclerView2.setAdapter(adapter);
    }

    private void showProductRecyclerView(List<Product> products) {
        binding.showRecycler.setHasFixedSize(true);
        binding.showRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        popularAdapter = new PopularAdapter(products, getContext());
        binding.showRecycler.setAdapter(popularAdapter);
    }

    private void setProductRecycler(List<String> productCategoryList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        binding.catRecycler.setLayoutManager(layoutManager);
        productCategoryAdapter = new ProductCategoryAdapter(getActivity(), productCategoryList, this);
        binding.catRecycler.setAdapter(productCategoryAdapter);

    }

    @Override
    public void getItemList(List<Product> product, Product product1, int position) {

        Intent intent = new Intent(getActivity(), PriceActivty.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("productList", (ArrayList<Product>) product);
        intent.putExtra("product1", new Gson().toJson(product1));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void getItemList(String item) {
        getSpecificCategor(item);
    }

    private void getSpecificCategor(String item) {

        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<List<Product>> call = null;
        switch (item) {
            case "electronics":
                call = apiService.getElectronicsCategory();
                break;
            case "jewelery":
                call = apiService.getJeweleryCategory();
                break;
            case "men's clothing":
                call = apiService.getMenSpecificCategory();
                break;
            case "women's clothing":
                call = apiService.getWomenSpecificCategory();
                break;
        }

        if (call != null) {
            call.enqueue(new Callback<List<Product>>() {
                @Override
                public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Intent intent = new Intent(getActivity(), ShowItemActivty.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("productList", (ArrayList<Product>) response.body());
                        intent.putExtras(bundle);
                        startActivity(intent);

                    } else {
                        Log.e("getProducts", "Failed to fetch data");
                    }
                }

                @Override
                public void onFailure(Call<List<Product>> call, Throwable t) {
                    Log.e("getProducts", "Error occurred while fetching data", t.getCause());
                }
            });

        }
    }

}
