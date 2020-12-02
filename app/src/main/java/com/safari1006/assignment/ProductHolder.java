package com.safari1006.assignment;

import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class ProductHolder extends RecyclerView.ViewHolder {
    ProductModel productModel;
        TextView name,desc,price,expiryDate;
        ImageView imageView;
    public ProductHolder(@NonNull View itemView) {
        super(itemView);
        name=(TextView)itemView.findViewById(R.id.rProdName);
        desc=(TextView)itemView.findViewById(R.id.rPprodDescription);
        price=(TextView)itemView.findViewById(R.id.rProdPrice);
        imageView= (itemView).findViewById(R.id.prodImage);
    }
    public void bind(  ProductModel productModel){
        name.setText(productModel.getName());
        desc.setText(productModel.getDescription());
        price.setText(productModel.getPrice()+"RWF");
        Glide.with(imageView).load(productModel.getImage()).into(imageView);
    }
}
