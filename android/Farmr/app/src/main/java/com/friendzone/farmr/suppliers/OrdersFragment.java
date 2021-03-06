package com.friendzone.farmr.suppliers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.friendzone.farmr.R;
import com.friendzone.farmr.adapters.OrderListViewAdapter;

/**
 * Created by marylourdessabio on 08/08/15.
 */
public class OrdersFragment extends Fragment {

    String[] orders = {"Chicken", "Beef", "Pork", "Rice"};
    OrderListViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_orders, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView title = (TextView) view.findViewById(R.id.textView);

        //ArrayList<HashMap<String, String>> itemList = new ArrayList<HashMap<String, String>>();

        ListView lv = (ListView) view.findViewById(R.id.listView);

        adapter = new OrderListViewAdapter(getActivity(), orders);//itemList);
        //new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, orders);
        lv.setAdapter(adapter);

        Log.e("", "adapter: " + adapter);

    }

}