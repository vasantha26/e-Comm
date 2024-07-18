package com.example.ecommerceapps.adpater;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.ecommerceapps.R;
import com.example.ecommerceapps.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    List<Product> productList;
    Context context;
    itemClickListener itemClickListener;

    public ProductAdapter(List<Product> productList, Context context ,itemClickListener clickListener) {
        this.productList = productList;
        this.context = context;
        this.itemClickListener = clickListener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_card_view, parent, false);
        return new ProductViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);


        Glide.with(context).load(product.getImage()).into(holder.productImage);


        holder.productTitle.setText(product.getTitle());

        holder.ratingbar.setRating(Float.parseFloat(String.valueOf(product.getRating().getRate())));
        String rating=String.valueOf(holder.ratingbar.getRating());
        holder.tvAct.setText(rating);
        holder.productPrice.setText( String.valueOf(product.getPrice()));


        holder.itemView.setOnClickListener(v -> itemClickListener.getItemList(productList,product,position));




    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public interface itemClickListener {

        void getItemList(List<Product> product, Product product1, int position);

    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productTitle,tvAct;
        TextView productPrice ,productSize;
        CardView cardView;
        RatingBar ratingbar;

        public ProductViewHolder(@NonNull View view) {
            super(view);
            cardView = view.findViewById(R.id.cardview);
            productImage = view.findViewById(R.id.productImageView);
            productTitle = view.findViewById(R.id.productNameTextView);
            productPrice = view.findViewById(R.id.productPriceTextView);
            ratingbar= view.findViewById(R.id.ratingBar1);
            tvAct=view.findViewById(R.id.activites);

        }
    }


}
