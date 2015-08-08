package com.friendzone.farmr.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.friendzone.farmr.R;
import com.friendzone.farmr.suppliers.OrderViewActivity;

import java.util.ArrayList;
import java.util.HashMap;

@SuppressLint("ViewHolder")
public class OrderListViewAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    String[] orders;

    public OrderListViewAdapter(Context context, String[] orders) {
                                //ArrayList<HashMap<String, String>> arraylist) {
        this.context = context;
        this.orders = orders;
        //data = arraylist;
    }

    static class ViewHolder {
        TextView name, price, unit;
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
            convertView = inflater.inflate(R.layout.list_order_item, parent, false);
            holder.name = (TextView) convertView.findViewById (R.id.tvName);
            holder.price = (TextView) convertView.findViewById(R.id.tvPrice);
            holder.unit = (TextView) convertView.findViewById(R.id.tvUnit);
            holder.img = (ImageView) convertView.findViewById (R.id.imageView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

//        HashMap<String, String> resultp = new HashMap<String, String>();
//        resultp = data.get(position);

        holder.name.setText("Chicken");
                //resultp.get(Constants.KEY_CATEGORY));
        holder.price.setText("P 120");
                //resultp.get(Constants.KEY_PRICE));
        holder.unit.setText("Test");
                //resultp.get(Constants.KEY_UNIT));

        convertView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
//                HashMap<String, String> resultp = new HashMap<String, String>();
//                resultp = data.get(position);
                Intent intent = new Intent(context, OrderViewActivity.class);
//                intent.putExtra("category", resultp.get(Constants.KEY_CATEGORY));
//                intent.putExtra("price", resultp.get(Constants.KEY_PRODUCT_PRICE));
//                intent.putExtra("unit", resultp.get(Constants.KEY_UNIT));
                context.startActivity(intent);
            }
        });
 
        return convertView;
        
    }


}