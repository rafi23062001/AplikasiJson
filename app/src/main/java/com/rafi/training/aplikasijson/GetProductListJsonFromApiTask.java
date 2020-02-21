package com.rafi.training.aplikasijson;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class GetProductListJsonFromApiTask extends AsyncTask<String,Void, ArrayList<Data>> {
    Context context;
    WeakReference<ProductAdapter> productAdapterRef;

    public GetProductListJsonFromApiTask(Context context, ProductAdapter productAdapterRef) {
        this.context = context;
        this.productAdapterRef = new WeakReference<>(productAdapterRef);
    }

    @Override
    protected ArrayList<Data> doInBackground(String... url) {
        ArrayList<Data> products;
        String json = loadJsonFromApi(url[0]);
        products = deserializeJson(json);
        return products;
    }

    @Override
    protected void onPostExecute(ArrayList<Data> datalist) {
        ProductAdapter adapter = productAdapterRef.get();
        adapter.addData(datalist);
        adapter.notifyDataSetChanged();
    }

    private String loadJsonFromApi(String urlParam){
        String json = null;
        try {
            HttpURLConnection connection = null;
            URL url = new URL(urlParam);
            try {
                connection = (HttpURLConnection)url.openConnection();
                connection.connect();

                InputStream is = connection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);

                BufferedReader reader = new BufferedReader(isr);

                StringBuffer sb = new StringBuffer();
                String eachline;
                while ((eachline = reader.readLine())!=null){
                    sb.append(eachline);
                }
                json = sb.toString();
            }catch (Exception e){
                e.printStackTrace();
            }
            finally {
                if (connection !=null){
                    connection.disconnect();
                }
            }
        }
        catch (MalformedURLException e){
            e.printStackTrace();
            return e.getMessage();
        }
        return json;
    }
    private ArrayList<Data> deserializeJson(String jsonParam) {
        ArrayList<Data> productku = new ArrayList<>();
        String json = jsonParam;

        try {
            JSONObject jsonData = new JSONObject(json);
            JSONArray data = jsonData.getJSONArray("data");
            for (int i=0;i < data.length();i++){
                Data productObj;
                JSONObject productmu = data.getJSONObject(i);
                long productId = productmu.getLong("productId");
                String productName = productmu.getString("productName");
                String productSlug = productmu.getString("productSlug");
                String productImage = productmu.getString("productImage");
                int productQty = productmu.getInt("productQty");
                JSONObject merchant = productmu.getJSONObject("merchant");
                JSONObject category = productmu.getJSONObject("category");

                int categoryId = category.getInt("categoryId");
                String categoryName = category.getString("categoryName");

                Category categoryObj = new Category((int) categoryId,categoryName);

                int merchantId = merchant.getInt("merchantId");
                String merchantName = merchant.getString("merchantName");
                String merchantSlug = merchant.getString("merchantSlug");

                Merchant merchantobj = new Merchant( merchantId,merchantName,merchantSlug);

                productObj = new Data(productId,productName,productSlug,productQty,productImage,merchantobj,categoryObj);
                productku.add(productObj);
            }
        }catch (JSONException err){
            err.printStackTrace();
        }return productku;
    }
}
