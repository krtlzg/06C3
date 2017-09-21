package com.example.asus.bendevarm.custom;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
import java.util.List;

/**
 * Created by asus on 14.8.2017.
 */

public class CustomAdapter extends BaseAdapter {

    private List<EtkinlikModel> etkinlikListe;
    private LayoutInflater layoutInflater;
    private Context context;
    private FirebaseAuth fAuth;
    private ArrayList<String> destekListe = new ArrayList<>();
    private boolean desteklendiMi = false;

    private Button btnDestekle;
    private TextView txtViewKurumAdi, txtViewEtkinlikAdi, txtViewYer, txtViewTarih, txtDestekSayisi;
    private ImageView imgViewLogo, imgDurum;

    public CustomAdapter(Activity activity, List<EtkinlikModel> etkinlikListe, Context context, FirebaseAuth fAuth) {
        this.layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.etkinlikListe = etkinlikListe;
        this.context = context;
        this.fAuth = fAuth;
    }

    @Override
    public int getCount() {
        return etkinlikListe.size();
    }

    @Override
    public Object getItem(int position) {
        return etkinlikListe.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.etkinliksatir,null);

        destekListe = etkinlikListe.get(position).getDestekListe();

        btnDestekle = (Button) view.findViewById(R.id.btnDestekle);
        txtViewKurumAdi = (TextView) view.findViewById(R.id.txtViewKurumAdi);
        txtViewEtkinlikAdi = (TextView) view.findViewById(R.id.txtViewEtkinlikAdi);
        txtViewYer = (TextView) view.findViewById(R.id.txtViewYer);
        txtViewTarih = (TextView) view.findViewById(R.id.txtViewTarih);
        txtDestekSayisi = (TextView) view.findViewById(R.id.txtDestekSayisi);
        imgViewLogo = (ImageView) view.findViewById(R.id.imgViewLogo);
        imgDurum = (ImageView) view.findViewById(R.id.imgDurum);

        if (etkinlikListe.get(position).getDurum() == 1)
        {
            imgDurum.setImageResource(R.drawable.iconred);
        }
        else if (etkinlikListe.get(position).getDurum() == 2)
        {
            imgDurum.setImageResource(R.drawable.iconlightgreen);
        }
        else
        {
            imgDurum.setImageResource(R.drawable.iconyellow);
        }

        txtViewEtkinlikAdi.setText(etkinlikListe.get(position).getEtkinlikAdi());
        txtViewKurumAdi.setText(etkinlikListe.get(position).getKurulusAdi());
        txtViewYer.setText(etkinlikListe.get(position).getYer());
        txtViewTarih.setText(etkinlikListe.get(position).getTarih());
        if (etkinlikListe.get(position).getDestekListe() != null)
        {
            if (etkinlikListe.get(position).getDestekListe().size() == 0)
            {
                txtDestekSayisi.setText("Hiç desteklenmedi :(");
            }
            else
            {
                txtDestekSayisi.setText(etkinlikListe.get(position).getDestekListe().size() + " kez desteklendi.");
            }
        }
        btnDestekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnDestekle.getText().equals("Desteklediniz") && etkinlikListe.get(position).getDestekListe().contains(fAuth.getCurrentUser().getUid()))
                {
                    ArrayList<String> temp = etkinlikListe.get(position).getDestekListe();
                    temp.remove(fAuth.getCurrentUser().getUid());
                    etkinlikListe.get(position).setDestekListe(temp);
                    FirebaseDatabase mData = FirebaseDatabase.getInstance();
                    DatabaseReference dRef = mData.getReference("Etkinlikler/" + etkinlikListe.get(position).getId() + "/destekListe");
                    dRef.setValue(temp);
                }
                else if (btnDestekle.getText().equals("Destekle") && !etkinlikListe.get(position).getDestekListe().contains(fAuth.getCurrentUser().getUid()))
                {
                    ArrayList<String> temp = etkinlikListe.get(position).getDestekListe();
                    temp.add(fAuth.getCurrentUser().getUid());
                    etkinlikListe.get(position).setDestekListe(temp);
                    FirebaseDatabase mData = FirebaseDatabase.getInstance();
                    DatabaseReference dRef = mData.getReference("Etkinlikler/" + etkinlikListe.get(position).getId() + "/destekListe");
                    dRef.setValue(temp);
                }
            }
        });

        destekKontrol(view);
        Glide.with(view).load(etkinlikListe.get(position).getLogoUrl()).into(imgViewLogo);

        return view;
    }

    private void destekKontrol(View view)
    {
        int kontrol = 0;
        for (String s : destekListe)
        {
            if (s.equals(fAuth.getCurrentUser().getUid())) { kontrol++; }
        }

        if (kontrol == 0)
        {
            desteklendiMi = false;
            btnDestekle.setText("Destekle");
            btnDestekle.setTextColor(view.getResources().getColor(R.color.colorPrimary));
            btnDestekle.setBackgroundColor(Color.TRANSPARENT);
        }
        else
        {
            desteklendiMi = true;
            btnDestekle.setText("Desteklediniz");
            btnDestekle.setTextColor(Color.WHITE);
            btnDestekle.setBackgroundColor(view.getResources().getColor(R.color.colorPrimary));
        }
    }
}
