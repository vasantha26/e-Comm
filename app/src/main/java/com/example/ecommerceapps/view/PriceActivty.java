package com.example.ecommerceapps.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.ecommerceapps.R;
import com.example.ecommerceapps.adpater.PriceAdapter;
import com.example.ecommerceapps.databinding.PriceItemViewBinding;
import com.example.ecommerceapps.interfaces.FragmentBottomNavigation;
import com.example.ecommerceapps.model.Product;
import com.example.ecommerceapps.viewmodel.ViewModal;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

public class PriceActivty extends AppCompatActivity {

    PriceAdapter adapter;
    PriceItemViewBinding binding;
    FragmentBottomNavigation communicator;
    List<Product> productList;
    Product product;
    List<String> itemList = new ArrayList<>();
    List<Product> productArrayList = new ArrayList<>();
    ViewModal viewModal;
    boolean isFav = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = PriceItemViewBinding.inflate(getLayoutInflater());
        setContentView( binding.getRoot());

        viewModal = new ViewModelProvider(this).get(ViewModal.class);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {productList = (List<Product>) bundle.getSerializable("productList");}

        String flowerJson = getIntent().getStringExtra("product1");
        if (flowerJson != null) {product = new Gson().fromJson(flowerJson, Product.class);}



        binding.textViewProductName.setText(product.getTitle());

        binding.textViewProductDescription.setText(product.getDescription());

        binding.textViewProductPrice.setText(String.valueOf(product.getPrice()));

        if (product.getFavorite()){
            binding.checkBoxFavorite.setImageResource(R.drawable.ic_favorite_inlined);
        }else {
            binding.checkBoxFavorite.setImageResource(R.drawable.ic_favorite_outslined);
        }

        binding.ratingBar.setRating(Float.parseFloat(String.valueOf(product.getRating().getRate())));
        String rating=String.valueOf(binding.ratingBar.getRating());
        binding.textViewProductFeatures.setText(rating);

        if (itemList  != null){
            itemList.add(product.getImage());

        }

        binding.checkBoxFavorite.setOnClickListener(v -> {
            isFav = !isFav;
            if (isFav){
                binding.checkBoxFavorite.setImageResource(R.drawable.ic_favorite_inlined);
                viewModal.updateFavorite(product.getId() ,true);
            }else {
                binding.checkBoxFavorite.setImageResource(R.drawable.ic_favorite_outslined);
                viewModal.updateFavorite(product.getId() ,false);
            }
        });

        binding.buttonAddToShopping.setOnClickListener(v -> {
            viewModal.updateCart(product.getId() ,true);
        });



        binding.checkBoxCart.setOnClickListener(v -> {
            Uri imgBitmapUri = Uri.parse(product.getImage());

            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            shareIntent.putExtra(Intent.EXTRA_STREAM, imgBitmapUri);
            shareIntent.setType("image/jpg");
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            shareIntent.putExtra(Intent.EXTRA_TEXT, product.getTitle());
            startActivity(Intent.createChooser(shareIntent, "Share with"));
        });

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
