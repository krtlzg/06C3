package com.example.asus.bendevarm.Fragment;


import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.asus.bendevarm.R;
import com.example.asus.bendevarm.model.KullaniciModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class HesabimFragment extends BaseFragment {

    private FirebaseDatabase fDatabase;
    private DatabaseReference dReference;
    private FirebaseAuth fAuth;
    private KullaniciModel kullanici;
    private ImageView imageViewProfilFoto;
    private EditText editTextAd,editTextSoyad,editTextEposta, editTextIl, editTextDogumtarihi;
    private Button buttonKaydet;

    public HesabimFragment() { }

    @Override
    protected int getFID() {
        return R.layout.fragment_hesabim;
    }

    @Override
    protected void init() {
        fAuth = FirebaseAuth.getInstance();
        fDatabase = FirebaseDatabase.getInstance();
        dReference = fDatabase.getReference("Kullanici/" + fAuth.getCurrentUser().getUid());

        imageViewProfilFoto = (ImageView) getActivity().findViewById(R.id.imageViewProfilFoto);
        editTextAd = (EditText) getActivity().findViewById(R.id.editTextAd);
        editTextSoyad = (EditText) getActivity().findViewById(R.id.editTextSoyad);
        editTextEposta = (EditText) getActivity().findViewById(R.id.editTextEposta);
        editTextIl = (EditText) getActivity().findViewById(R.id.editTextIl);
        editTextDogumtarihi = (EditText) getActivity().findViewById(R.id.editTextDogumtarihi);
        buttonKaydet = (Button) getActivity().findViewById(R.id.buttongönüllüol);
    }

    @Override
    protected void handlers() {
        dReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                kullanici = dataSnapshot.getValue(KullaniciModel.class);
                görünümGüncelle();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        buttonKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ad, soyad, eposta, il, dt;
                eposta= editTextEposta.getText().toString();
                ad = editTextAd.getText().toString().trim();
                soyad = editTextSoyad.getText().toString().trim();
                il = editTextIl.getText().toString().trim();
                dt = editTextDogumtarihi.getText().toString().trim();

                

                dReference = fDatabase.getReference("Kullanici");
                KullaniciModel temp = new KullaniciModel(ad,soyad,eposta,kullanici.getSifre(),kullanici.getCep(),il,kullanici.getFotourl(),dt);
                dReference.child(fAuth.getCurrentUser().getUid()).setValue(temp);
            }
        });
    }
    private void görünümGüncelle()
    {
        Log.d(getActivity().getLocalClassName(), "görünümGüncelle: " + kullanici.getFotourl());
        Glide.with(getActivity())
                .load(kullanici.getFotourl())
                .into(imageViewProfilFoto);
        editTextAd.setText(kullanici.getAd());
        editTextSoyad.setText(kullanici.getSoyad());
        editTextEposta.setText(kullanici.getEposta());
        editTextIl.setText(kullanici.getIl());
        editTextDogumtarihi.setText(kullanici.getDogumtarihi());
    }
}
