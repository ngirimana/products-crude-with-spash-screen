package com.safari1006.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class AddProduct extends AppCompatActivity {
    private EditText prodName,prodDesc,prodPrice,prodExpiry;
    private Button btnAddProd;
     String url="https://product-crud-op.herokuapp.com/api/v1/products/add-product";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        prodName=findViewById(R.id.productName);
        prodDesc=findViewById(R.id.productDesc);
        prodPrice=findViewById(R.id.productPrice);
        prodExpiry=findViewById(R.id.productExpiryDate);
        btnAddProd= findViewById(R.id.btnAddProd);

        String name =prodName.getText().toString();
        String description =""+prodDesc.getText().toString();
        String price =""+prodPrice.getText().toString();
        String expiration =""+prodExpiry.getText().toString();

        btnAddProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProduct(
                    ""+prodName.getText().toString(),
                    ""+prodPrice.getText().toString(),
                    ""+prodDesc.getText().toString(),
                    ""+prodExpiry.getText().toString()
                        );}
        });
    }

    private void addProduct(String name, String price, String description, String date_of_expiration) {

        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", name);
            jsonObject.put("price",price);
            jsonObject.put("description", description);
            jsonObject.put("date_of_expiration", date_of_expiration);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        //build response
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    System.out.println(response);
                    Toast.makeText(AddProduct.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(jsonObject);
                Toast.makeText(AddProduct.this, "Added failed", Toast.LENGTH_SHORT).show();
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