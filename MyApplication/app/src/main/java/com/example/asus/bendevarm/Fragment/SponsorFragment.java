package com.example.asus.bendevarm.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.bendevarm.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SponsorFragment extends BaseFragment {

    private ImageView logo1,logo2;
    private TextView sponsoryazı;


    public SponsorFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getFID() {
        return R.layout.fragment_sponsor;
    }

    @Override
    protected void init() {

        logo1 = (ImageView) getActivity().findViewById(R.id.imageViewlogo1);
        logo2 = (ImageView) getActivity().findViewById(R.id.imageViewlogo2);
        sponsoryazı = (TextView) getActivity().findViewById(R.id.textViewsponsor);

    }

    @Override
    protected void handlers() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sponsor, container, false);
    }

}
