package com.friendzone.farmr.buyers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.friendzone.farmr.R;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;

/**
 * Created by marylourdessabio on 08/08/15.
 */
public class BuyersFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_buyers, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int position = FragmentPagerItem.getPosition(getArguments());
//        TextView title = (TextView) view.findViewById(R.id.item_title);
//        title.setText(String.valueOf(position));
    }

}
