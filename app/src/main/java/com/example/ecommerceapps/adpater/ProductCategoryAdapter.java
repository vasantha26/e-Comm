package com.example.ecommerceapps.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerceapps.R;

import java.util.List;

public class ProductCategoryAdapter extends RecyclerView.Adapter<ProductCategoryAdapter.ProductViewHolder> {

    Context context;
    List<String> productCategoryList;
    itemClickListener itemClickListener;


    public ProductCategoryAdapter(Context context, List<String> productCategoryList,itemClickListener clickListener) {
        this.context = context;
        this.productCategoryList = productCategoryList;
        this.itemClickListener = clickListener;

    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.category_row_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        holder.catagoryName.setText(productCategoryList.get(position));

        holder.itemView.setOnClickListener(v -> {

            itemClickListener.getItemList(productCategoryList.get(position));
        });



    }

    @Override
    public int getItemCount() {
        return productCategoryList.size();
    }


    public static final class ProductViewHolder extends RecyclerView.ViewHolder{


        TextView catagoryName;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            catagoryName = itemView.findViewById(R.id.cat_name);

        }
    }

    public interface itemClickListener {

        void getItemList(String item);

    }

}
