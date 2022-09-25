package com.example.iot_project.Main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.iot_project.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainTab1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainTab1Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ListView listViewItem;
    private List<Map<String, Object>> itemData;


    public MainTab1Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainTab1Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainTab1Fragment newInstance(String param1, String param2) {
        MainTab1Fragment fragment = new MainTab1Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewFrag1 = inflater.inflate(R.layout.fragment_main_tab1, container, false);
        listViewItem = (ListView)viewFrag1.findViewById(R.id.listView_main_frag1);
        return viewFrag1;
    }

    @Override
    public void onResume() {
        super.onResume();
        itemData = new ArrayList<Map<String,Object>>();
        String[] itemNameList = getResources().getStringArray(R.array.item_name);
        for(int i =0; i<itemNameList.length;i++){
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("name",itemNameList[i]);
            data.put("store","花花世界");
            String date = String.valueOf(10 + i);
            data.put("date","2022/09/"+date);
            switch (i%2){
                case 0:
                    data.put("pic",R.drawable.photo);
                    data.put("price","100元");
                    break;
                case 1:
                    data.put("pic",R.drawable.pearls);
                    data.put("price","80元");
                    break;
            }
            itemData.add(data);
        }
        SimpleAdapter adpter = new SimpleAdapter(getContext(), itemData, R.layout.listview_main_layout,
                new String[]{"name","store","date","pic","price"},
                new int[]{R.id.textView_title,R.id.textView_store,
                        R.id.textView_date,R.id.imageView,R.id.textView_price});
        listViewItem.setAdapter(adpter);
    }
}