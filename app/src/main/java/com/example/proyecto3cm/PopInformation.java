package com.example.proyecto3cm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PopInformation extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONObject> {

    TextView popName;
    TextView popDesc;
    ImageView popImage;
    String url;
    RequestQueue queue;
    JsonObjectRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_information);

        String id = getIntent().getStringExtra("ID");

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width * 0.8), (int)(height * 0.8));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);

        popName = (TextView) findViewById(R.id.tvPopName);
        popImage = (ImageView) findViewById(R.id.tvPopImage);
        popDesc = (TextView) findViewById(R.id.tvPopDesc);

        queue = Volley.newRequestQueue(this);
        url = getResources().getString(R.string.url_details) + id;
        Log.d("Url del elemento: ", url);
        request = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        queue.add(request);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Animatoo.animateFade(PopInformation.this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Animatoo.animateFade(PopInformation.this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        Animatoo.animateFade(PopInformation.this);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.d("Error: ", error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        Log.d("Respuesta", "Correcta");
        try {
            for(int i = 0; i < response.length(); i++){
                //jsonObject = response.getJSONObject();
                popName.setText(response.getString("name"));
                Picasso.with(this).load(response.getString("imag_url")).into(popImage);
                popDesc.setText(response.getString("desc"));
            }
        } catch (JSONException e) {

        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
