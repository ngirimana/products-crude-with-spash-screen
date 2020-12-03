package com.safari1006.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UpdateProduct extends AppCompatActivity {
    private EditText prodName,prodDesc,prodPrice,imageLink;
    private Button btnUpdateProd;
    private String id,name,price,desc,image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_product);
        prodName=findViewById(R.id.productName);
        prodDesc=findViewById(R.id.productDesc);
        prodPrice=findViewById(R.id.productPrice);
        btnUpdateProd= findViewById(R.id.btnUpdateProd);
        imageLink=findViewById(R.id.productImageLink);

      Intent intent=getIntent();
        id=intent.getStringExtra("ID");
       name= intent.getStringExtra("NAME");
       price= intent.getStringExtra("PRICE");
        desc=intent.getStringExtra("DESCRIPTION");
        image=intent.getStringExtra("IMAGE");

        prodName.setText(name);
        prodDesc.setText(desc);
        prodPrice.setText(price);
        imageLink.setText(image);

        btnUpdateProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            updateProduct(
                    ""+id,
                    ""+prodName.getText().toString(),
                    ""+prodPrice.getText().toString(),
                    ""+prodDesc.getText().toString(),
                    ""+imageLink.getText().toString()
            );
            }
        });

    }

    private void updateProduct(String id, String name, String price, String description, String imageLink) {
        String url="https://product-crud-op.herokuapp.com/api/v1/products/all/"+id;

        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", name);
            jsonObject.put("price",price);
            jsonObject.put("description", description);
            jsonObject.put("image",imageLink);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        //build response
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PATCH, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                        System.out.println(response);
                    Toast.makeText(UpdateProduct.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(UpdateProduct.this,AllProduct.class);
                    startActivity(intent);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(jsonObject);
                Toast.makeText(UpdateProduct.this, (CharSequence) error, Toast.LENGTH_SHORT).show();
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
        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }


    }

