package com.rafi.training.aplikasijson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GsonExample extends AppCompatActivity {

    RequestQueue  requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson_example);
        final ProductAdapter adapter = new ProductAdapter((this));
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
        /*jsonconventer = new Gson();

        Mahasiswa mahasiswa = new Mahasiswa(
                "Rafi'ul",
                "Huda",
                18,
                "FTI");
        //jsonconventer = new Gson();
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        //String json = jsonconventer.toJson(mahasiswa);
        //Toast.makeText(this,json,Toast.LENGTH_SHORT).show();
        String json = "{\"jurusan\":\"FTI\",\"namaBelakang\":\"Huda\",\"namaDepan\":\"Rafi\\u0027ul\",\"umur\":18}";
        Mahasiswa mhsConvert = gson.fromJson(json,Mahasiswa.class);*/
    }
}
