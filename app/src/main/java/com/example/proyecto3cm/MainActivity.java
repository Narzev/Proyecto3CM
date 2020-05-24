package com.example.proyecto3cm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.proyecto3cm.Model.Producto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONArray>{

    ListView lvItems;
    ProgressBar pbConnection;
    String url;
    RequestQueue queue;
    JsonArrayRequest request;
    ArrayList<Producto> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvItems = findViewById(R.id.lvList);
        pbConnection = findViewById(R.id.pbConection);

        products = new ArrayList<Producto>();
        queue = Volley.newRequestQueue(this);
        url = getResources().getString(R.string.url_base);
        request = new JsonArrayRequest(Request.Method.GET, url, null, this, this);
        queue.add(request);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Animatoo.animateSlideRight(MainActivity.this);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        pbConnection.setVisibility(View.GONE);
        finish();
    }

    @Override
    public void onResponse(JSONArray response) {
        pbConnection.setVisibility(View.GONE);
        Log.d("Respuesta", response.toString());

        JSONObject jsonObject;
        try{
            for(int i = 0; i < response.length(); i++){
                jsonObject = response.getJSONObject(i);
                int id = jsonObject.getInt("id");
                String name = jsonObject.getString("name");
                String thumbnail_url = jsonObject.getString("thumbnail_url");
                String price = jsonObject.getString("price");
                String provider = jsonObject.getString("provider");
                String delivery = jsonObject.getString("delivery");
                if(delivery.equals("0.00")){
                    delivery = getString(R.string.price_delivery);
                }

                Producto product = new Producto(id, name, thumbnail_url, price, provider, delivery);
                products.add(product);
            }

            Adapter adapter = new Adapter(this, products);
            lvItems.setAdapter(adapter);

            lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(MainActivity.this, PopInformation.class);
                    Log.d("Identificador", Integer.toString(products.get(position).getId()));
                    intent.putExtra("ID", Integer.toString(products.get(position).getId()));
                    startActivity(intent);

                    Animatoo.animateCard(MainActivity.this);
                }
            });

        }catch(JSONException e){

        }
    }
}
