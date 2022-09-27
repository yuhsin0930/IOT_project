package com.example.iot_project.Admin;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.util.Log;
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
 * Use the {@link AdminMemberFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminMemberFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
//    private ArrayList<? extends Parcelable> ListData;

    public AdminMemberFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdminMemberFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdminMemberFragment newInstance(String param1, String param2) {
        AdminMemberFragment fragment = new AdminMemberFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
//        ,List<Map<String,Object>> ListData
//        args.putParcelableArrayList("ListData",(ArrayList<? extends Parcelable>)ListData);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
//            ListData= getArguments().getParcelableArrayList("ListData");
//            Log.d("main"," ListData="+ ListData);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_member, container, false);
    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View viewFrag1 = inflater.inflate(R.layout.fragment_first, container, false);
//        ListViewItem = (ListView)viewFrag1.findViewById(R.id.listView_id);
//        return viewFrag1;
//    }
//
//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        itemData = new ArrayList<Map<String,Object>>();
//        String[] itemNameList = getResources().getStringArray(R.array.item_name);
//        for(int i =0; i<itemNameList.length;i++){
//            Map<String, Object> data = new HashMap<String, Object>();
//            data.put("name",itemNameList[i]);
//            data.put("pic",R.drawable.kalanchoe);
//            itemData.add(data);
//        }
//        SimpleAdapter adpter = new SimpleAdapter(getContext(), itemData, R.layout.listview_layout,
//                new String[]{"name","pic"}, new int[]{R.id.textView_title,R.id.imageView});
//        ListViewItem.setAdapter(adpter);
//
//    }
}