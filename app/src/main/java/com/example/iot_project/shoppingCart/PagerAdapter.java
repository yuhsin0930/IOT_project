package com.example.iot_project.shoppingCart;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.iot_project.ProductLaunchedAlreadyFragment;
import com.example.iot_project.ProductPendingReviewFragment;
import com.example.iot_project.ProductSoldOutFragment;
import com.example.iot_project.ProductViolationFragment;

import java.util.ArrayList;

public class PagerAdapter extends FragmentStateAdapter {
    private ArrayList<Fragment> fList = new ArrayList<Fragment>(){{
        add(new AllProductFragment());
        add(new BuyAgainFragment());

    }};

    public PagerAdapter(@NonNull FragmentActivity fragmentActivity) {
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
