package com.jerry.yiyachat.main.self;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jerry.yiyachat.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelfFragment extends Fragment {


    public SelfFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.self_fragment, container, false);
    }

}
