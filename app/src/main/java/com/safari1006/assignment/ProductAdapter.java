package com.safari1006.assignment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public  class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.Holder>{

    List<ProductModel> productList;
    Context context;

    public ProductAdapter(List<ProductModel> products,Context context) {
        this.context = context;
        this.productList = products;


    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_procuct, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        ProductModel product=productList.get(position);
        String name= product.getName();
        String desc=product.getDescription();
        String price=product.getPrice();
        String id=product.getId();
        String image=product.getImage();

        // setting
        holder.name.setText(name);
        holder.price.setText(price+"RWF");
        holder.desc.setText(desc);
        Glide.with(holder.imageView).load(image).into(holder.imageView);
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Intent intent= new Intent(context,UpdateProduct.class);
                intent.putExtra("ID", id);
                intent.putExtra("NAME", name);
                intent.putExtra("PRICE", price);
                intent.putExtra("DESCRIPTION", desc);
                intent.putExtra("IMAGE", image);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
               context.startActivity(intent);
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteProductInfo(""+id);
                Intent intent= new Intent(context,AllProduct.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


    }

    private void deleteProductInfo(String id) {
        String url="https://product-crud-op.herokuapp.com/api/v1/products/all/delete/"+id;

        final JSONObject jsonObject = new JSONObject();

        //build response
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DELETE, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    Toast.makeText(context, "product deleted successfully", Toast.LENGTH_SHORT).show();


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
            }
        }) {
            @NonNull
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");

                return headers;
            }
        };
        Volley.newRequestQueue(context).add(jsonObjectRequest);
    }



    @Override
    public int getItemCount() {
         return productList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView name,desc,price;
        ImageView imageView;
        ImageButton btnEdit,btnDelete;

        public Holder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.rProdName);
            desc=(TextView)itemView.findViewById(R.id.rPprodDescription);
            price=(TextView)itemView.findViewById(R.id.rProdPrice);
            imageView= (itemView).findViewById(R.id.prodImage);
            btnEdit= (itemView).findViewById(R.id.editBtn);
            btnDelete=(itemView).findViewById(R.id.deleteBtn);
        }
    }

}
