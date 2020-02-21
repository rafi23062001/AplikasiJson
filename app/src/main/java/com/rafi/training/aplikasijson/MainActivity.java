package com.rafi.training.aplikasijson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ProductAdapter adapter;
    RecyclerView recyclerView;
    ArrayList<Data> products;
    RequestQueue requestQueue;
    Merchant merchant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        products = new ArrayList<>();
        recyclerView = findViewById(R.id.rv_person);
        adapter = new ProductAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        String url = "http://192.168.6.221:81/api/products";
        JsonObjectRequest listProductku = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    ArrayList<Data> product = new ArrayList<>();
                    JSONArray data = response.getJSONArray("data");
                    for (int i=0;i<data.length();i++){
                        Gson gson = new Gson();
                        Data  products = gson.fromJson(data.getJSONObject(i).toString(),Data.class);
                        product.add(products);
                    }
                    adapter.addData(product);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(),String.valueOf(adapter.getItemCount()),Toast.LENGTH_LONG).show();
                }
                catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Voller Error",error.getMessage());
            }
        });
        requestQueue.add(listProductku);
    }
}
