package com.example.iot_project;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class SalesRecordPageAdapter extends FragmentStateAdapter {

    private ArrayList<Fragment> fList = new ArrayList<Fragment>(){{
        add(new OrderToBeShipFragment());
        add(new OrderInvaildFragment());
        add(new OrderReturn());
        add(new OrderShippedFragment());
        add(new OrderCompletedFragment());
    }};

    public SalesRecordPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fList.get(position);
    }

    @Override
    public int getItemCount() {
        return fList.size();
    }
}
