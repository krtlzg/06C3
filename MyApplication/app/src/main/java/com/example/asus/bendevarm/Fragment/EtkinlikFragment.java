package com.example.asus.bendevarm.Fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.bendevarm.R;
import com.example.asus.bendevarm.model.EtkinlikModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class EtkinlikFragment extends BaseFragment {

    EtkinlikModel etkinlik;
    FirebaseAuth mAuth;
    ImageView imageViewKurulusFoto;
    TextView textViewKurulusAdi,textViewEtkinlikAdi,textViewDestekSayısı,textViewTarih,textViewYer;
    Button buttonDestekle;


    public EtkinlikFragment() {
        // Required empty public constructor
    }


    public static synchronized EtkinlikFragment newInstance(Bundle b)
    {
        EtkinlikFragment userFragment = new EtkinlikFragment();
        userFragment.setArguments(b);
        return userFragment;
    }

    @Override
    protected int getFID() {
        return R.layout.fragment_etkinlik;
    }

    @Override
    protected void init() {
        if (getArguments() == null) { return; }

        mAuth = FirebaseAuth.getInstance();
        etkinlik = (EtkinlikModel) getArguments().get("etkinlik");
        getActivity().setTitle(etkinlik.getKurulusAdi());
        Log.d(getActivity().getLocalClassName(), "init: " + etkinlik.getId());

        imageViewKurulusFoto = (ImageView) getActivity().findViewById(R.id.imageViewKurulusFoto);
        textViewKurulusAdi = (TextView) getActivity().findViewById(R.id.textViewKurulusAdi);
        textViewEtkinlikAdi = (TextView) getActivity().findViewById(R.id.textViewEtkinlikAdi);
        textViewDestekSayısı = (TextView) getActivity().findViewById(R.id.textViewDestekSayısı);
        textViewTarih = (TextView) getActivity().findViewById(R.id.textViewTarih);
        textViewYer = (TextView) getActivity().findViewById(R.id.textViewYer);
        buttonDestekle = (Button) getActivity().findViewById(R.id.buttonDestekle);

        Glide.with(getActivity()).load(etkinlik.getLogoUrl()).into(imageViewKurulusFoto);
        textViewKurulusAdi.setText(etkinlik.getKurulusAdi());
        textViewEtkinlikAdi.setText(etkinlik.getEtkinlikAdi());
        textViewDestekSayısı.setText(etkinlik.getDestekListe().size() + " kez desteklendi.");
        textViewTarih.setText(etkinlik.getTarih());
        textViewYer.setText(etkinlik.getYer());

        destekKontrol();
    }

    @Override
    protected void handlers() {
        buttonDestekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonDestekle.getText().equals("Desteği Kaldır") && etkinlik.getDestekListe().contains(mAuth.getCurrentUser().getUid()))
                {
                    ArrayList<String> temp = etkinlik.getDestekListe();
                    temp.remove(mAuth.getCurrentUser().getUid());
                    etkinlik.setDestekListe(temp);
                    FirebaseDatabase mData = FirebaseDatabase.getInstance();
                    DatabaseReference dRef = mData.getReference("Etkinlikler/" + etkinlik.getId() + "/destekListe");
                    dRef.setValue(temp);
                }
                else if (buttonDestekle.getText().equals("Destekle") && !etkinlik.getDestekListe().contains(mAuth.getCurrentUser().getUid()))
                {
                    ArrayList<String> temp = etkinlik.getDestekListe();
                    temp.add(mAuth.getCurrentUser().getUid());
                    etkinlik.setDestekListe(temp);
                    FirebaseDatabase mData = FirebaseDatabase.getInstance();
                    DatabaseReference dRef = mData.getReference("Etkinlikler/" + etkinlik.getId() + "/destekListe");
                    dRef.setValue(temp);
                }
                destekKontrol();
            }
        });
    }

    private void destekKontrol()
    {
        int kontrol = 0;
        for (String s : etkinlik.getDestekListe())
        {
            if (s.equals(mAuth.getCurrentUser().getUid())) { kontrol++; }
        }

        if (kontrol == 0)
        {
            buttonDestekle.setText("Destekle");
            buttonDestekle.setTextColor(getActivity().getResources().getColor(R.color.colorPrimary));
            buttonDestekle.setBackgroundColor(Color.TRANSPARENT);

        }
        else
        {
            buttonDestekle.setText("Desteği Kaldır");
            buttonDestekle.setTextColor(Color.WHITE);
            buttonDestekle.setBackgroundColor(getActivity().getResources().getColor(R.color.colorPrimary));
        }

        textViewDestekSayısı.setText(etkinlik.getDestekListe().size() + " kez desteklendi.");
    }
}
