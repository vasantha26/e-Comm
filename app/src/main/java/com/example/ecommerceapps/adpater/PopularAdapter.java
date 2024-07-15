package com.example.ecommerceapps.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerceapps.R;
import com.example.ecommerceapps.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ProductViewHolder> {

    Context context;
    List<Product>  products;

    public PopularAdapter(List<Product>  product , Context context ) {
        this.context = context;
        this.products = product;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.popular_row_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        Product product = products.get(position);

        Picasso.get().load(product.getImage()).into(holder.viewEachImage);






    }

    @Override
    public int getItemCount() {
        return products.size();
    }

//    @Override
////    public int getItemCount() {
////        return products.size();
////    }


    public static final class ProductViewHolder extends RecyclerView.ViewHolder{


//        TextView catagoryName;
        ImageView viewEachImage;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            viewEachImage = itemView.findViewById(R.id.banner_imageview);
//            catagoryImg = itemView.findViewById(R.id.product_image);

        }
    }

}
