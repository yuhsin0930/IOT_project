package com.example.iot_project;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class SalesRecordPageAdapter extends FragmentStateAdapter {

    private ArrayList<Fragment> fList = new ArrayList<Fragment>();


    private Fragment fragment;

    public SalesRecordPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        fList.add(new OrderToBeShipFragment());
        fList.add(new OrderInvaildFragment());
        fList.add(new OrderReturn());
        fList.add(new OrderShippedFragment());
        fList.add(new OrderCompletedFragment());
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                fragment = fList.get(0);
                break;
            case 1:
                fragment = fList.get(1);
        }
        return fList.get(position);
    }

    @Override
    public int getItemCount() {
        return fList.size();
    }
}
