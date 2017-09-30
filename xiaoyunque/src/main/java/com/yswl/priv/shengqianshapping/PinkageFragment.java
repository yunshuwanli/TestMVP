package com.yswl.priv.shengqianshapping;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yswl.priv.R;

import yswl.com.klibrary.base.MFragment;


public class PinkageFragment extends MFragment {

    public PinkageFragment() {
        // Required empty public constructor
    }

    public static PinkageFragment newInstance(String param1, String param2) {
        PinkageFragment fragment = new PinkageFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pinkage, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
