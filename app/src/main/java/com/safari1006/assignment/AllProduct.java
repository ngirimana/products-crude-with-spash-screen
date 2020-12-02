package com.safari1006.assignment;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AllProduct extends AppCompatActivity {
    final String GET_URL="https://product-crud-op.herokuapp.com/api/v1/products/all";
    ProductModel productModel;
    RecyclerView recyclerView;
   List<ProductModel> products;
   ActionBar actionBar;
    FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_product);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);

        getAllProducts();
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        fab =findViewById(R.id.addFabButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AllProduct.this,AddProduct.class);
                startActivity(intent);
            }
        });

    }
    public void getAllProducts(){
        ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading Data");
        progressDialog.show();
        products=new ArrayList<>();
          RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.GET,GET_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject firstObj = new JSONObject(response);
                    JSONArray secondArr = firstObj.getJSONArray("data");
                    for (int i=0;i<secondArr.length();i++) {
                        JSONObject productObject=secondArr.getJSONObject(i);
                      ProductModel productModel=new ProductModel(productObject.getString("_id"),productObject.getString("name"),
                              productObject.getString("price"),productObject.getString("description"),
                              productObject.getString("image"));
                        products.add(productModel);
                    }
                    System.out.println(secondArr);
                    ProductAdapter productAdapter=new ProductAdapter(products);
                    recyclerView.setAdapter(productAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
                Toast.makeText(AllProduct.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });
                requestQueue.add(stringRequest);

    }}