package com.friendzone.farmr.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.friendzone.farmr.R;

import java.util.ArrayList;
import java.util.HashMap;

@SuppressLint("ViewHolder")
public class StockListViewAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    String[] orders;

    public StockListViewAdapter(Context context, String[] orders) {
                                //ArrayList<HashMap<String, String>> arraylist) {
        this.context = context;
        this.orders = orders;
        //data = arraylist;
    }

    static class ViewHolder {
        TextView name, price, description;
        ImageView img;
    }
 
    @Override
    public int getCount() {
        return orders.length;
       // return data.size();
    }
 
    @Override
    public Object getItem(int position) {
        return null;
    }
 
    @Override
    public long getItemId(int position) {
        return 0;
    }
 
    @Override
	public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
 
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.list_order_item, null); //parent, false);

            holder.name = (TextView) convertView.findViewById (R.id.tvName);
            holder.price = (TextView) convertView.findViewById(R.id.tvPrice);
            holder.description = (TextView) convertView.findViewById(R.id.tvDescription);
            holder.img = (ImageView) convertView.findViewById (R.id.imageView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

//        HashMap<String, String> resultp = new HashMap<String, String>();
//        resultp = data.get(position);

        Log.e("", "LOG");

        holder.name.setText("Chicken");
                //resultp.get(Constants.KEY_PRODUCT_NAME));
        holder.price.setText("P 120");
                //Formatter.formatWithPesoSign(resultp.get(Constants.KEY_PRODUCT_PRICE)));
        holder.description.setText("Test");
                //resultp.get(Constants.KEY_PRODUCT_DESCRIPTION));

//        Picasso.with(activity)
//                .load(Constants.displayProductThumbnail(Constants.MERCHANT,
//                        resultp.get(Constants.KEY_PRODUCT_ID), "200"))
//                .placeholder(R.drawable.placeholder)
//                .fit()
//                .into(holder.img);

        convertView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
//                HashMap<String, String> resultp = new HashMap<String, String>();
//                resultp = data.get(position);
//                Intent intent = new Intent(context, ProductDetailsActivity.class);
//                intent.putExtra("productName", resultp.get(Constants.KEY_PRODUCT_NAME));
//                intent.putExtra("productPrice", resultp.get(Constants.KEY_PRODUCT_PRICE));
//                intent.putExtra("productDescription",
//                        resultp.get(Constants.KEY_PRODUCT_DESCRIPTION));
//                intent.putExtra("productId", resultp.get(Constants.KEY_PRODUCT_ID));
//                intent.putExtra(Constants.KEY_PARTITION_KEY,
//                        resultp.get(Constants.KEY_PARTITION_KEY));
//
//                Log.e("", "PK: " + resultp.get(Constants.KEY_PARTITION_KEY));
//                Log.e("", "RK: " + resultp.get(Constants.KEY_ROW_KEY));
//
//                intent.putExtra(Constants.KEY_ROW_KEY, resultp.get(Constants.KEY_ROW_KEY));
//
//
//                context.startActivity(intent);
            }
        });
 
        return convertView;
        
    }


}