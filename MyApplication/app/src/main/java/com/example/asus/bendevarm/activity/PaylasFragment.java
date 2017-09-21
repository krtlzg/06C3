package com.example.asus.bendevarm.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.bendevarm.Fragment.BaseFragment;
import com.example.asus.bendevarm.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PaylasFragment extends BaseFragment {

    private ImageView logopaylas;
    private TextView yazıpaylas;
    private Button paylas;


    public PaylasFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getFID() {
        return R.layout.fragment_paylas;
    }

    @Override
    protected void init() {

        logopaylas = (ImageView) getActivity().findViewById(R.id.imageViewlogo1);
        yazıpaylas = (TextView) getActivity().findViewById(R.id.tvyazı);
        paylas= (Button) getActivity().findViewById(R.id.shareit);

        paylas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareIt();
            }
        });


    }
    private void shareIt() {
//sharing implementation here
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "AndroidSolved");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Now Learn Android with AndroidSolved clicke here to visit https://androidsolved.wordpress.com/ ");
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        /*noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
         */
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void handlers() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_paylas, container, false);
    }



}
