package com.example.asus.bendevarm.Kurumsal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.asus.bendevarm.R;
import com.example.asus.bendevarm.activity.AcilisActivity;
import com.example.asus.bendevarm.model.EtkinlikModel;
import com.example.asus.bendevarm.model.KurumsalModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EtkinlikActivity extends AppCompatActivity {

    FirebaseDatabase fData;
    DatabaseReference dRef, dRef2;
    KurumsalModel kurum;
    FirebaseAuth fAuth;
    FirebaseUser fUser;

    EditText editTextEtkinlikAdi, editTextYer, editTextTarih, editTextUrl;
    Button buttonKaydet,buttonCikisYap;
    RadioGroup radioGroupDurum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etkinlik);

        fData = FirebaseDatabase.getInstance();
        fAuth = FirebaseAuth.getInstance();
        fUser = fAuth.getCurrentUser();

        editTextEtkinlikAdi = (EditText) findViewById(R.id.editTextEtkinlikAdi);
        editTextYer = (EditText) findViewById(R.id.editTextYer);
        editTextUrl = (EditText) findViewById(R.id.editTextUrl);
        editTextTarih = (EditText) findViewById(R.id.editTextTarih);
        buttonKaydet = (Button) findViewById(R.id.buttonKaydet);
        buttonCikisYap = (Button) findViewById(R.id.buttonCikisYap);
        radioGroupDurum = (RadioGroup) findViewById(R.id.radioGroupDurum);
        radioGroupDurum.check(R.id.radioButton3);

        dRef = fData.getReference("Kurumsal/" + fUser.getUid() + "/");
        dRef2 = fData.getReference("Etkinlikler");
        dRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                kurum = dataSnapshot.getValue(KurumsalModel.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        buttonKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int durum = 0;
                String id = dRef2.push().getKey();
                switch (radioGroupDurum.getCheckedRadioButtonId())
                {
                    case R.id.radioButton:
                        durum = 1;
                        break;
                    case R.id.radioButton2:
                        durum = 2;
                        break;
                    case R.id.radioButton3:
                        durum = 3;
                        break;
                }
                EtkinlikModel etkinlik = new EtkinlikModel(durum, editTextEtkinlikAdi.getText().toString(),kurum.getAd(),
                        kurum.getUrl(),editTextTarih.getText().toString(),editTextYer.getText().toString(),id,
                        editTextUrl.getText().toString(),new ArrayList<String>());
                dRef2.child(id).setValue(etkinlik);
            }
        });

        buttonCikisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fAuth.signOut();
                Intent i = new Intent(getApplicationContext(),AcilisActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}
