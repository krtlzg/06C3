package com.example.asus.bendevarm.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus.bendevarm.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MesajFragment extends BaseFragment {

   TextView calısma;


    public MesajFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getFID() {
        return R.layout.fragment_mesaj;
    }

    @Override
    protected void init() {
        calısma= (TextView) getActivity().findViewById(R.id.tvçalışma);

    }

    @Override
    protected void handlers() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mesaj, container, false);
    }

}
