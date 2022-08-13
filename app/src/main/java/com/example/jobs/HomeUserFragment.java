package com.example.jobs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jobs.adapters.categoryAdapter;
import com.example.jobs.model.Category;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeUserFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeUserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeUserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeUserFragment newInstance(String param1, String param2) {
        HomeUserFragment fragment = new HomeUserFragment();
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
        View v =  inflater.inflate(R.layout.fragment_home_user, container, false);

        RecyclerView rvc = v.findViewById(R.id.rvc);


        ArrayList<Category> categories = new ArrayList<>();

        categories.add(new Category(R.drawable.man, "تم الموافقة على الطلب بنجاح" ));
        categories.add(new Category(R.drawable.man, "تم الموافقة على الطلب بنجاح" ));
        categories.add(new Category(R.drawable.man, "تم الموافقة على الطلب بنجاح" ));
        categories.add(new Category(R.drawable.man, "تم الموافقة على الطلب بنجاح" ));
        categories.add(new Category(R.drawable.man, "تم الموافقة على الطلب بنجاح" ));
        categories.add(new Category(R.drawable.man, "تم الموافقة على الطلب بنجاح" ));
        categories.add(new Category(R.drawable.man, "تم الموافقة على الطلب بنجاح" ));
        categories.add(new Category(R.drawable.man, "تم الموافقة على الطلب بنجاح" ));
        categories.add(new Category(R.drawable.man, "تم الموافقة على الطلب بنجاح" ));
        categories.add(new Category(R.drawable.man, "تم الموافقة على الطلب بنجاح" ));
        categories.add(new Category(R.drawable.man, "تم الموافقة على الطلب بنجاح" ));


        rvc.setLayoutManager(new GridLayoutManager(getContext(), 2));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext()  , 2);

        //LinearLayoutManager linearLayoutManagerr = new LinearLayoutManager(CategoriesActivity.this , LinearLayoutManager.VERTICAL , false);
        rvc.setLayoutManager(gridLayoutManager);
        rvc.setHasFixedSize(true);
        categoryAdapter mAdapterr = new categoryAdapter(getContext() ,categories );
        rvc.setAdapter(mAdapterr);


        return v ;
    }
}