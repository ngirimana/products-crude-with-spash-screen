package com.safari1006.assignment;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductHolder extends RecyclerView.ViewHolder {
    ProductModel productModel;
        TextView name,desc,price,expiryDate;
    public ProductHolder(@NonNull View itemView) {
        super(itemView);
        name=(TextView)itemView.findViewById(R.id.rProdName);
        desc=(TextView)itemView.findViewById(R.id.rPprodDescription);
        price=(TextView)itemView.findViewById(R.id.rProdPrice);
        expiryDate=(TextView)itemView.findViewById(R.id.RexpirationDate);
    }
    public void bind(  ProductModel productModel){
        name.setText(productModel.getName());
        desc.setText(productModel.getDescription());
        price.setText(productModel.getPrice());
        expiryDate.setText(productModel.getExpirationDate());
    }
}
