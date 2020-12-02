package com.safari1006.assignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public  class ProductAdapter extends RecyclerView.Adapter<ProductHolder>{
    List<ProductModel> productList;
    ProductHolder productHolder;

    public ProductAdapter(List<ProductModel> products) {
        this.productList = products;

    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_procuct, parent, false);
        return new ProductHolder(view);

    }

    @Override
    public void onBindViewHolder(ProductHolder holder, int position) {
        ProductModel product=productList.get(position);
        holder.bind(product);
        holder=productHolder;

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}