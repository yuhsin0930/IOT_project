package com.example.iot_project.Main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainViewPagerAdapter extends FragmentStateAdapter {
    private final List<Fragment> Listfrag;
    private Fragment fragment;

    public MainViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);//每次開啟都會建立一次，從背景回來也會進入
        Listfrag = new ArrayList<>();
        Listfrag.add(new MainTab1Fragment());
        Listfrag.add(new MainTab2Fragment());
        Listfrag.add(new MainTab3Fragment());
        Listfrag.add(new MainTab4Fragment());

    }

    @Override
    public int getItemCount() {//傳回 Fragment 數量
        return 4;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) { //建立Fragment
        fragment = Listfrag.get(position);
        return fragment; //每次開啟都會建立一次，從背景回來也會進入
    }
}
