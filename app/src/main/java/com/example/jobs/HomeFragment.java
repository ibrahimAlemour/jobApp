package com.example.jobs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jobs.adapters.ArchiveAdapter;
import com.example.jobs.adapters.OpenTalabAdapter;
import com.example.jobs.model.Archive;
import com.example.jobs.model.OpenTalab;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View  v =  inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView rvot = v.findViewById(R.id.rvot);

//       TextView b= v.findViewById(R.id.tvTitleToolbar);
//       b.setText("الرئيسية");

        ArrayList<OpenTalab> openTalabs = new ArrayList<>();

        openTalabs.add(new OpenTalab("تصليح باب خشبي مزودج" , "تصليح باب خشبي مزودج تصليح باب خشبي مزودج تصليح باب خشبي مزودج تصليح باب خشبي مزودج تصليح باب خشبي مزودج تصليح باب خشبي مزودج "));
        openTalabs.add(new OpenTalab("تصليح باب خشبي مزودج" , "تصليح باب خشبي مزودج تصليح باب خشبي مزودج تصليح باب خشبي مزودج تصليح باب خشبي مزودج تصليح باب خشبي مزودج تصليح باب خشبي مزودج "));
        openTalabs.add(new OpenTalab("تصليح باب خشبي مزودج" , "تصليح باب خشبي مزودج تصليح باب خشبي مزودج تصليح باب خشبي مزودج تصليح باب خشبي مزودج تصليح باب خشبي مزودج تصليح باب خشبي مزودج "));
        openTalabs.add(new OpenTalab("تصليح باب خشبي مزودج" , "تصليح باب خشبي مزودج تصليح باب خشبي مزودج تصليح باب خشبي مزودج تصليح باب خشبي مزودج تصليح باب خشبي مزودج تصليح باب خشبي مزودج "));
        openTalabs.add(new OpenTalab("تصليح باب خشبي مزودج" , "تصليح باب خشبي مزودج تصليح باب خشبي مزودج تصليح باب خشبي مزودج تصليح باب خشبي مزودج تصليح باب خشبي مزودج تصليح باب خشبي مزودج "));
        openTalabs.add(new OpenTalab("تصليح باب خشبي مزودج" , "تصليح باب خشبي مزودج تصليح باب خشبي مزودج تصليح باب خشبي مزودج تصليح باب خشبي مزودج تصليح باب خشبي مزودج تصليح باب خشبي مزودج "));
        openTalabs.add(new OpenTalab("تصليح باب خشبي مزودج" , "تصليح باب خشبي مزودج تصليح باب خشبي مزودج تصليح باب خشبي مزودج تصليح باب خشبي مزودج تصليح باب خشبي مزودج تصليح باب خشبي مزودج "));
        openTalabs.add(new OpenTalab("تصليح باب خشبي مزودج" , "تصليح باب خشبي مزودج تصليح باب خشبي مزودج تصليح باب خشبي مزودج تصليح باب خشبي مزودج تصليح باب خشبي مزودج تصليح باب خشبي مزودج "));
        openTalabs.add(new OpenTalab("تصليح باب خشبي مزودج" , "تصليح باب خشبي مزودج تصليح باب خشبي مزودج تصليح باب خشبي مزودج تصليح باب خشبي مزودج تصليح باب خشبي مزودج تصليح باب خشبي مزودج "));
        openTalabs.add(new OpenTalab("تصليح باب خشبي مزودج" , "تصليح باب خشبي مزودج تصليح باب خشبي مزودج تصليح باب خشبي مزودج تصليح باب خشبي مزودج تصليح باب خشبي مزودج تصليح باب خشبي مزودج "));
        openTalabs.add(new OpenTalab("تصليح باب خشبي مزودج" , "تصليح باب خشبي مزودج تصليح باب خشبي مزودج تصليح باب خشبي مزودج تصليح باب خشبي مزودج تصليح باب خشبي مزودج تصليح باب خشبي مزودج "));







        LinearLayoutManager linearLayoutManagerr = new LinearLayoutManager(getContext() , LinearLayoutManager.VERTICAL , false);
        rvot.setLayoutManager(linearLayoutManagerr);
        rvot.setHasFixedSize(true);
        OpenTalabAdapter mAdapterr = new OpenTalabAdapter(getContext() ,openTalabs );
        rvot.setAdapter(mAdapterr);

        return v ;
    }
}