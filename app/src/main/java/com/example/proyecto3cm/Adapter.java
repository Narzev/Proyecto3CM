package com.example.proyecto3cm;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyecto3cm.Model.Producto;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    Context contexto;
    ArrayList<Producto> products;

    public Adapter(Context contexto, ArrayList<Producto> products) {
        this.contexto = contexto;
        this.products = products;
        inflater = (LayoutInflater)contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final View vista = inflater.inflate(R.layout.adapter_list, null);
        ImageView th_product = (ImageView) vista.findViewById(R.id.ivProducto);
        ImageView th_envio = (ImageView) vista.findViewById(R.id.ivEnvio);
        TextView tvProducto = (TextView) vista.findViewById(R.id.tvNameArt);
        TextView tvProveedor = (TextView) vista.findViewById(R.id.tvProveedor);
        TextView tvPrecio = (TextView) vista.findViewById(R.id.tvPrecio);
        TextView tvEnvio = (TextView) vista.findViewById(R.id.tvEnvio);

        Picasso.with(contexto)
                .load(products.get(position).getThumbnail_url()).into(th_product);

        th_envio.setImageResource(R.drawable.delivery);
        tvProducto.setText(products.get(position).getName());
        tvProveedor.setText(products.get(position).getProvider());
        String pr = products.get(position).getPrice();
        String[] pr_arr = pr.split("\\.", 2);
        Log.d("Arreglo de Precio", pr_arr[0] + "/" + pr_arr[1]);
        StringBuffer pric = new StringBuffer(pr_arr[0]);
        if(pric.length() > 3){
            pric.insert(pric.length() - 3, ",");
        }
        tvPrecio.setText("$" + pric + "." +  pr_arr[1]);
        if(products.get(position).getDelivery().matches(".*\\d.*")){
            tvEnvio.setText("$" + products.get(position).getDelivery());
        }
        else {
            tvEnvio.setText(products.get(position).getDelivery());
        }

        return vista;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return products.get(position).getId();
    }


}
